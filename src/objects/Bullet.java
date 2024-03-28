package objects;

import javafx.scene.image.Image;

public class Bullet extends GameObject {

    private long start;
    private static long lifetime = 800000000;
    
    public Bullet(Image img, double x, double y) {
        super(img, x, y);

        start = System.nanoTime();
    }

    @Override
    public void update(double canvasWidth, double canvasHeight, long currentTime) {
        super.update(canvasWidth, canvasHeight, currentTime);

        dead = currentTime - start > lifetime;
    }
}
