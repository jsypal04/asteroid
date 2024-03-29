package objects;

import javafx.scene.image.Image;

public class Bullet extends GameObject {

    private static final double initVelocity = 10;

    private long start;
    private static final long lifetime = 800000000;
    
    public Bullet(Image img, double x, double y) {
        super(img, x, y);

        start = System.nanoTime();
    }

    @Override
    public void update(double canvasWidth, double canvasHeight, long currentTime) {
        super.update(canvasWidth, canvasHeight, currentTime);

        dead = currentTime - start > lifetime;
    }

    public double getInitVelocity() {
        return initVelocity;
    }
}
