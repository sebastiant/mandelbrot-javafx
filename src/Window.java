import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class Window extends Application {
    public static final int IMAGE_WIDTH = 500;
    public static final int IMAGE_HEIGHT = 500;

    private PixelWriter pixelWriter;
    private Scene scene;

    public Window() {
        WritableImage image = new WritableImage(IMAGE_WIDTH,IMAGE_HEIGHT);
        pixelWriter = image.getPixelWriter();
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        scene = new Scene(root,IMAGE_WIDTH,IMAGE_HEIGHT);
    }

    @Override
    public void start(Stage primaryStage) {
        paintMandelbrotSet();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mandelbrot set");
        primaryStage.show();
    }

    private void paintMandelbrotSet() {
        Consumer<Pixel> pixelPainter = (pixel) -> pixelWriter.setColor(pixel.getX(),pixel.getY(),pixel.getColor());
        MandelbrotSet.paintMandelbrotSet(IMAGE_WIDTH, IMAGE_HEIGHT, pixelPainter);
    }
    public static void main(String[] args) {
        new Window().launch();
    }
}