import java.util.HashMap;

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

    static final HashMap<Long, GameObject> objects = new HashMap<Long, GameObject>();
    static final HashMap<Long, GameObject> deadObjects = new HashMap<Long, GameObject>();

    public void pprintMap(HashMap<Long, GameObject> map) {
        for (Long key : map.keySet()) {
            System.out.print(key);
            System.out.print(": ");
            System.out.println(map.get(key));
        }
        System.out.println();
    }
    
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
        objects.put(explo.id, explo);
        objects.put(player.id, player);

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
                    objects.put(bullet.id, bullet);
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
                // update the game objects
                for (GameObject obj : objects.values()) {
                    obj.update(canvas.getWidth(), canvas.getHeight(), System.nanoTime());
                    if (obj.isDead()) {
                        deadObjects.put(obj.id, obj);
                    }
                }

                // remove dead objects
                for (GameObject obj : deadObjects.values()) {
                    if (objects.containsKey(obj.id)) {
                        objects.remove(obj.id, obj);
                    }
                }

                // clear the deadObjects map
                deadObjects.clear();

                // redraw
                gContext.setFill(Color.BLACK);
                gContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                for (GameObject obj : objects.values()) {
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
