package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {
    private double velocityX;
    private double velocityY;
    private double positionX;
    private double positionY;

    private Image icon;
    private double width;
    private double height;

    public GameObject(Image img, double x, double y) {
        positionX = x;
        positionY = y;
        icon = img;
        width = img.getWidth();
        height = img.getHeight();
    }

    public void rotate(GraphicsContext gContext) {
        gContext.save();
        gContext.rotate(90);
        draw(gContext);
        gContext.restore();
    }

    public void draw(GraphicsContext gContext) {
        gContext.drawImage(icon, positionX - width / 2, positionY - height / 2);
    }

    public void update(double canvasWidth, double canvasHeight) {
        positionX += velocityX;
        positionY += velocityY;

        // logic to make the objects wrap around the screen
        if (positionX > canvasWidth + width) {
            positionX = -height;
        }
        else if (positionX < -height) {
            positionX = canvasWidth + width;
        }
        if (positionY > canvasHeight + height) {
            positionY = -height;
        }
        else if (positionY < -height) {
            positionY = canvasHeight + height;
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
