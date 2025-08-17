import javax.swing.*;
import java.awt.*;

public class PetController {
    private final PetWindow window;
    private final PetStats stats;
    private final GitFeeder feeder;

    private double vx = 1.5, vy = 0.8;
    private final int tickMs = 16; 
    private long lastSecondMark = System.currentTimeMillis();

    public PetController(PetWindow window, PetStats stats, GitFeeder feeder) {
        this.window = window;
        this.stats = stats;
        this.feeder = feeder;
    }

    public void start() {
        new Timer(tickMs, e -> tickFrame()).start();

        new Timer(8000, e -> {
            stats.tickDecay();
            window.setScale(stats.getScale());
        }).start();

        new Timer(3 * 60 * 1000, e -> {
            int commits = feeder.pollNewCommits();
            if (commits > 0) {
                stats.feedCommits(commits);
                window.setScale(stats.getScale());
                vy -= Math.min(8, commits * 0.6);
            }
        }).start();
    }

    private void tickFrame() {
        Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment()
        .getDefaultScreenDevice().getDefaultConfiguration().getBounds();

        double speed = stats.getSpeedFactor();
        Point p = window.getLocation();
        int x = (int) Math.round(p.x + vx * speed);
        int y = (int) Math.round(p.y + vy * speed);

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

        vy += 0.02;

        long now = System.currentTimeMillis();
        if (now - lastSecondMark >= 1000) {
            lastSecondMark = now;
            double jumpChance = stats.getJumpChance();
            if (Math.random() < jumpChance) {
                vy -= 5.0; 
            }
        }
    }
}
