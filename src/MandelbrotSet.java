import javafx.scene.paint.Color;

import java.util.function.Consumer;

public class MandelbrotSet {
    final static int max_iterations = 500;
    final static double diameter = 4.0;

    public static void paintMandelbrotSet(int width, int height, Consumer<Pixel> colorPixel) {

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                double c_real = (column - width / 2) * diameter / width;
                double c_imaginary = (row - height / 2) * diameter / width;
                double z = 0, y = 0;
                int iterations = 0;
                for (;Math.pow(z,2) + Math.pow(y,2) < 4 && iterations < max_iterations;iterations++) {
                    double z_new = Math.pow(z,2) - Math.pow(y,2) + c_real;
                    y = 2 * z * y + c_imaginary;
                    z = z_new;
                }
                Pixel pixel = inMandelbrotSet(iterations) ? new Pixel(column, row, Color.BLACK) : new Pixel(column, row, Color.rgb(iterations % 255, 0, 0));
                colorPixel.accept(pixel);
            }
        }
    }

    private static boolean inMandelbrotSet(int iterations) {
        return iterations >= max_iterations;
    }
}
