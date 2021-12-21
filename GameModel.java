import java.awt.*;
import java.util.LinkedList;

public class GameModel {
    public LinkedList<Cadre.Carre> carres;

    public GameModel() {
        this.carres = new LinkedList<>();
    }

    public void add(Cadre.Carre carre) {
        this.carres.add(carre);
    }

    public boolean gagne() {
        for (int i = 1; i < this.carres.size(); i++) {
            if (!this.carres.get(i).getBackground().equals(this.carres.get(i-1).getBackground())) {
                return false;
            }
        }
        return true;
    }
}
