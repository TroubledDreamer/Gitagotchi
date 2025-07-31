import javax.swing.ImageIcon;


public class main {
    public static void main(String[] args) {
        // Always run Swing code on Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            PetWindow petWindow = new PetWindow();
            ImageIcon originalIcon = new ImageIcon("/Users/carlyon/Documents/Projects/Fun/Gitagotchi/resources/milo/5878D816-C4DD-4CEA-84DF-BDC5BDD9EBD7.png");

            petWindow.updateSprite(originalIcon);
            Movement movement = new Movement(petWindow);
            new Thread(movement).start();
        });
    }
}
