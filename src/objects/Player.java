package objects;

import javafx.scene.image.Image;

public class Player extends GameObject {

    private double thrust;

    public Player(Image img, double x, double y) {
        super(img, x, y);
        setVelocity(0, 0);
        thrust = 2.0;
    }

    public void accelerate() {
        velocityX += thrust * Math.cos(Math.toRadians(angle));
        velocityY += thrust * Math.sin(Math.toRadians(angle));
    }

    public Bullet spawnBullet(Image img) {
        Bullet bullet = new Bullet(img, positionX, positionY);
        double vx = (velocityX + 10) * Math.cos(Math.toRadians(angle));
        double vy = (velocityY + 10) * Math.sin(Math.toRadians(angle));
        bullet.setVelocity(vx, vy);
        return bullet;  
    }
}
