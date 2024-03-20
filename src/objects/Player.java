package objects;

import javafx.scene.image.Image;

public class Player extends GameObject {

    public Player(Image img, double x, double y) {
        super(img, x, y);
        setVelocity(0, 0);
    }
}
