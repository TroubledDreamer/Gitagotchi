// PetApp.java
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PetApp {
    public static void main(String[] args) {
        // Args: [0] = path to git repo, [1] = optional author filter
        String repo = args.length > 0 ? args[0] : ".";
        String author = args.length > 1 ? args[1] : null;

        SwingUtilities.invokeLater(() -> {
            PetWindow win = new PetWindow();

            Image img = new ImageIcon("../resources/milo/5878D816-C4DD-4CEA-84DF-BDC5BDD9EBD7.png").getImage();
            // Image img = makeCircleSprite(128, new Color(255, 200, 120));
            win.setBaseImage(img, 128);

            PetStats stats = new PetStats();
            GitFeeder feeder = new GitFeeder(repo, author);
            PetController ctl = new PetController(win, stats, feeder);
            ctl.start();
        });
    }

    private static Image makeCircleSprite(int size, Color fill) {
        Image img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(fill);
        g.fillOval(0, 0, size - 1, size - 1);
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, size - 1, size - 1);
        g.dispose();
        return img;
    }
}
