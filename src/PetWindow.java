import javax.swing.*;
import java.awt.*;

public class PetWindow extends JFrame {
    private JLabel petLabel;
    private boolean isHappy = true;

    public PetWindow() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); // Fully transparent
        setAlwaysOnTop(true); // Always visible
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close properly
        setSize(128, 128);
        setLocationRelativeTo(null); // Center on screen

        petLabel = new JLabel();
        
        add(petLabel);

        setVisible(true);
    }

    public void updateSprite(ImageIcon icon) {
        Image scaledIcon = icon.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH);
        petLabel.setIcon(new ImageIcon(scaledIcon));
    }

    public void setHappy(boolean happy) {
        isHappy = happy;
    }

    public boolean isHappy() {
        return isHappy;
    }
}
