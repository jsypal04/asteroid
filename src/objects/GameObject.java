package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameObject {
    private double velocityX;
    private double velocityY;
    private double positionX;
    private double positionY;

    private ImageView iconView;
    private Image icon;
    private double width;
    private double height;

    public GameObject(Image img, double x, double y) {
        positionX = x;
        positionY = y;
        icon = img;
        width = img.getWidth();
        height = img.getHeight();
        iconView = new ImageView(icon);
    }

    public void rotate(double degrees) {
        iconView.setRotate(90);
    }

    public void draw(GraphicsContext gContext) {
        gContext.drawImage(icon, positionX - width / 2, positionY - height / 2);
    }

    public void update(double canvasWidth, double canvasHeight) {
        positionX += velocityX;
        positionY += velocityY;

        // logic to make the objects wrap around the screen
        if (positionX > canvasWidth + 10) {
            positionX = -height;
        }
        else if (positionX < -height) {
            positionX = canvasWidth + 10;
        }
        if (positionY > canvasHeight + 10) {
            positionY = -height;
        }
        else if (positionY < -height) {
            positionY = canvasHeight + 10;
        }
    }

    public void setVelocity(double velX, double velY) {
        velocityX = velX;
        velocityY = velY;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
