import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Random;

public class Cadre extends JFrame implements MouseInputListener {
    public JPanel conteneur;
    public GameModel model;
    public JPanel etiquette;
    public boolean jeuFini = false;

    public Cadre() {
        this.model = new GameModel();
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.etiquette = new JPanel();
        this.etiquette.add(new JLabel("Jeu gagné"));
        this.etiquette.setBounds(0, 0, 100, 50);
        this.etiquette.setVisible(false);

        this.conteneur = new JPanel();
        this.conteneur.add(etiquette);

        int nbreCarres = (int) (Math.random() * 10) + 1;
        for (int i = 0; i < nbreCarres; i++) {
            Carre carre = new Carre();
            carre.addMouseListener(carre);
            this.conteneur.addMouseMotionListener(carre);
            this.conteneur.add(carre);
            this.model.add(carre);
        }

        conteneur.addMouseListener(this);
        this.setContentPane(conteneur);
        this.setLayout(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        new Cadre();
                    }
                }
        );
    }

    public void finJeu() {
        if (this.model.gagne()) {
            this.etiquette.setVisible(true);
            this.jeuFini = true;
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (this.jeuFini) {
            this.setVisible(false);
            this.dispose();
        }
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
        if (this.jeuFini) {
            this.setVisible(false);
            this.dispose();
        }
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

    class Carre extends JPanel implements MouseInputListener {
        public boolean isMoving = false;
        public int clickedX;
        public int clickedY;

        Carre() {
            this.setBackground(getRandomColor());

            int randX = (int) ((Math.random()) * (Cadre.this.getWidth() - 100));
            int randY = (int) ((Math.random()) * (Cadre.this.getHeight() - 100));
            this.setBounds(randX, randY, 50, 50);
        }

        public static Color getRandomColor() {
            Random rand = new Random();
            int choix = rand.nextInt(4);

            if (choix == 0) {
                return new Color(255, 0, 0);
            }
            if (choix == 1) {
                return new Color(0, 255, 0);
            }
            if (choix == 2) {
                return new Color(0, 0, 255);
            } else {
                return new Color(255, 0, 255);
            }
        }

        public void mouseClicked(MouseEvent e) {
            this.isMoving = !this.isMoving;
            this.clickedX = e.getX();
            this.clickedY = e.getY();

            this.setBackground(new Color(0, 255, 0));
            Cadre.this.finJeu();
        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {
            this.setBackground(new Color(0, 0, 255));
            Cadre.this.finJeu();
        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseDragged(MouseEvent e) {

        }

        public void mouseMoved(MouseEvent e) {
            if (this.isMoving) {
                Cadre cadre = Cadre.this;
                int mouseX = e.getXOnScreen() - cadre.getX() - cadre.getInsets().left;
                int mouseY = e.getYOnScreen() - cadre.getY() - cadre.getInsets().top;

                int newX = mouseX - this.clickedX;
                int newY = mouseY - this.clickedY;

                this.setLocation(newX, newY);
            }
        }
    }
}
