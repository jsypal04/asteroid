package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class GameObject {
    protected double velocityX;
    protected double velocityY;
    protected double positionX;
    protected double positionY;

    protected double angle;

    private Image icon;
    private double width;
    private double height;

    public GameObject(Image img, double x, double y) {
        positionX = x;
        positionY = y;
        icon = img;
        width = img.getWidth();
        height = img.getHeight();
        angle = 0;
    }

    public void rotate(GraphicsContext gContext) {
        Rotate r = new Rotate(angle, positionX, positionY);
        gContext.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    public void draw(GraphicsContext gContext) {
        gContext.save();
        rotate(gContext);
        gContext.drawImage(icon, positionX - width / 2, positionY - height / 2);
        gContext.restore();
    }

    public void update(double canvasWidth, double canvasHeight) {
        positionX += velocityX;
        positionY += velocityY;

        // logic to make the objects wrap around the screen
        if (positionX > canvasWidth + width) {
            positionX = -width - 10;
        }
        else if (positionX < -width - 10) {
            positionX = canvasWidth + width;
        }
        if (positionY > canvasHeight + height) {
            positionY = -height - 10;
        }
        else if (positionY < -height - 10) {
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

    public void updateAngle(double delta) {
        angle += delta;
    }
}
