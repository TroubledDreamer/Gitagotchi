import java.awt.*;

public class Movement implements Runnable {
    private final PetWindow petWindow;
    private double velY = 0;
    private final double gravity = 0.5;
    private final double jumpForce = -10; // Negative = upward

    private int direction = 0; // -1 = left, 1 = right, 0 = idle
    private int stepsRemaining = 0;

    public Movement(PetWindow petWindow) {
        this.petWindow = petWindow;
    }

    /** Pick a new walking direction and how long to keep it */
    private void pickNewDirection() {
        int[] choices = {-1, 0, 1}; // left, idle, right
        direction = choices[(int) (Math.random() * choices.length)];

        // ✅ Less frequent direction changes (3–10 seconds)
        stepsRemaining = 100 + (int) (Math.random() * 200);
    }

    /** Manual jump trigger */
    public void jump() {
        if (velY == 0) { // Only jump if on ground
            velY = jumpForce;
        }
    }

    @Override
    public void run() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;

        while (true) {
            // ✅ Pick a new direction if needed
            if (stepsRemaining <= 0) {
                pickNewDirection();
            } else {
                stepsRemaining--;
            }

            // ✅ Random jump (very rare)
            if (velY == 0 && Math.random() < 0.001) { // 0.1% chance per frame
                velY = jumpForce - Math.random() * 4; // Random jump height
            }

            // ✅ Smooth horizontal movement
            int x = petWindow.getX() + direction * 2;

            // ✅ Apply gravity
            velY += gravity;
            int y = petWindow.getY() + (int) velY;

            // ✅ Stop at the ground
            int petHeight = petWindow.getHeight();
            if (y + petHeight > screenHeight - 50) {
                y = screenHeight - petHeight - 50;
                velY = 0;
            }

            // ✅ Prevent going off-screen horizontally
            if (x < 0) x = 0;
            if (x + petWindow.getWidth() > screenSize.width) {
                x = screenSize.width - petWindow.getWidth();
            }

            // ✅ Update pet position
            petWindow.setLocation(x, y);

            try {
                Thread.sleep(30); // ~33 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
