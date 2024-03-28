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
        double vMagnitude = Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2));
        double vx = (vMagnitude + 10) * Math.cos(Math.toRadians(angle));
        double vy = (vMagnitude + 10) * Math.sin(Math.toRadians(angle));
        bullet.setVelocity(vx, vy);
        return bullet;  
    }
}
