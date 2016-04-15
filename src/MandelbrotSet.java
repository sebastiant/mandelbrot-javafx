import javafx.scene.paint.Color;

import java.util.function.Consumer;

public class MandelbrotSet {
    final static int max_iterations = 500;
    final static double diameter = 4.0;

    public static void paintMandelbrotSet(int width, int height, Consumer<Pixel> colorPixel) {
        double minReal = -2.0;
        double maxReal = 1.0;
        double minImaginary = -1.2;
        double maxImaginary = minImaginary + (maxReal - minReal) * height / width;

        double x_factor = (maxReal - minReal) / (width - 1);
        double y_factor = (maxImaginary - minImaginary) / (height - 1);

        for (int x = 0; x < width; x++) {
            double c_real = minReal + x * x_factor;
            for (int y = 0; y < height; y++) {
                double c_imaginary = maxImaginary - y * y_factor;
                double z_real = c_real, z_imaginary = c_imaginary;
                int iterations = 0;
                for (;Math.pow(z_real,2) + Math.pow(z_imaginary,2) < diameter && iterations < max_iterations;iterations++) {
                    double z_real_new = Math.pow(z_real,2) - Math.pow(z_imaginary,2) + c_real;
                    z_imaginary = 2 * z_real * z_imaginary + c_imaginary;
                    z_real = z_real_new;
                }
                colorPixel(colorPixel, y, x, iterations);
            }
        }
    }

    private static void colorPixel(Consumer<Pixel> colorPixel, int row, int column, int iterations) {
        Pixel pixel = inMandelbrotSet(iterations) ? new Pixel(column, row, Color.BLACK) : new Pixel(column, row, Color.rgb(iterations % 255, 0, 0));
        colorPixel.accept(pixel);
    }

    private static boolean inMandelbrotSet(int iterations) {
        return iterations >= max_iterations;
    }
}
