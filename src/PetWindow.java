// PetWindow.java
import javax.swing.*;
import java.awt.*;

public class PetWindow extends JFrame {
    private final JLabel petLabel = new JLabel();
    private Image baseImage;
    private int baseSize = 128;
    private double scale = 1.0;

    public PetWindow() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(baseSize, baseSize);
        setLocationRelativeTo(null);
        add(petLabel);
        setVisible(true);
    }

    /** Provide a base (unscaled) image once. */
    public void setBaseImage(Image image, int baseSizePx) {
        this.baseImage = image;
        if (baseSizePx > 0) this.baseSize = baseSizePx;
        applyScale();
    }

    /** Call when stats-based scale changes. */
    public void setScale(double scale) {
        this.scale = Math.max(0.5, Math.min(2.0, scale)); // clamp for sanity
        applyScale();
    }

    private void applyScale() {
        int size = (int) Math.round(baseSize * scale);
        if (baseImage != null) {
            Image scaled = baseImage.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            petLabel.setIcon(new ImageIcon(scaled));
        }
        // Keep window centered on its own center while resizing
        Point p = getLocation();
        int oldW = getWidth();
        int oldH = getHeight();
        setSize(size, size);
        setLocation(p.x + (oldW - size) / 2, p.y + (oldH - size) / 2);
        revalidate();
        repaint();
    }

    /** Move the pet window to a new top-left screen position. */
    public void moveTo(int x, int y) {
        setLocation(x, y);
    }
}
