import java.util.LinkedList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import objects.Bullet;
import objects.GameObject;
import objects.Player;

public class App extends Application {

    static final LinkedList<GameObject> objects = new LinkedList<>();

    @Override
    public void start(Stage stage) throws Exception {
        // load game objects
        Image exploImg = new Image("assets/explosion.png");
        Image playerImg = new Image("assets/player.png");
        Image bulletImg = new Image("assets/bullet.png");

        Player player = new Player(playerImg, 400, 300);
        GameObject explo = new GameObject(exploImg, 30, 200);
        explo.setVelocity(0, 0);

        // add objects to objects list
        objects.add(explo);
        objects.add(player);

        // setup scene nodes
        Canvas canvas = new Canvas(800, 600);
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.setResizable(false);

        // get graphics context and load images
        GraphicsContext gContext = canvas.getGraphicsContext2D();

        // set event listeners
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent key) {
                if (key.getCode().name() == "UP") {
                    player.accelerate();
                }
                else if (key.getCode().name() == "SPACE") {
                    Bullet bullet = player.spawnBullet(bulletImg);
                    objects.add(bullet);
                }
                else if (key.getCode().name() == "LEFT") {
                    player.updateAngle(-10);
                }
                else if (key.getCode().name() == "RIGHT") {
                    player.updateAngle(10);
                }
            }
        });
        
        // game loop
        new AnimationTimer() {
            public void handle(long currentTime) {
                // update the explosion
                for (GameObject obj : objects) {
                    obj.update(canvas.getWidth(), canvas.getHeight());
                }

                // redraw
                gContext.setFill(Color.BLACK);
                gContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                for (GameObject obj : objects) {
                    obj.draw(gContext);
                }
            }
        }.start();


        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
