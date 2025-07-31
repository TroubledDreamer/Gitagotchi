// PetController.java
import javax.swing.*;
import java.awt.*;

public class PetController {
    private final PetWindow window;
    private final PetStats stats;
    private final GitFeeder feeder;

    // Simple wander physics
    private double vx = 1.5, vy = 0.8;
    private final int tickMs = 16; // ~60 FPS
    private long lastSecondMark = System.currentTimeMillis();

    public PetController(PetWindow window, PetStats stats, GitFeeder feeder) {
        this.window = window;
        this.stats = stats;
        this.feeder = feeder;
    }

    public void start() {
        // UI/motion timer
        new Timer(tickMs, e -> tickFrame()).start();

        // Stat decay every 8 seconds
        new Timer(8000, e -> {
            stats.tickDecay();
            window.setScale(stats.getScale());
        }).start();

        // Poll Git every 3 minutes
        new Timer(3 * 60 * 1000, e -> {
            int commits = feeder.pollNewCommits();
            if (commits > 0) {
                stats.feedCommits(commits);
                window.setScale(stats.getScale());
                // Little celebratory nudge
                vy -= Math.min(8, commits * 0.6);
            }
        }).start();
    }

    private void tickFrame() {
        // Screen bounds
        Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration().getBounds();

        // Move based on energy
        double speed = stats.getSpeedFactor();
        Point p = window.getLocation();
        int x = (int) Math.round(p.x + vx * speed);
        int y = (int) Math.round(p.y + vy * speed);

        // Bounce off edges
        int w = window.getWidth(), h = window.getHeight();
        if (x <= screen.x || x + w >= screen.x + screen.width) {
            vx = -vx;
            x = Math.max(screen.x, Math.min(x, screen.x + screen.width - w));
        }
        if (y <= screen.y || y + h >= screen.y + screen.height) {
            vy = -vy;
            y = Math.max(screen.y, Math.min(y, screen.y + screen.height - h));
        }
        window.moveTo(x, y);

        // Gravity-ish drift
        vy += 0.02;

        // Once per second, chance to jump based on energy
        long now = System.currentTimeMillis();
        if (now - lastSecondMark >= 1000) {
            lastSecondMark = now;
            double jumpChance = stats.getJumpChance();
            if (Math.random() < jumpChance) {
                vy -= 5.0; // hop!
            }
        }
    }
}
