import javafx.scene.paint.Color;

import java.util.function.Consumer;

public class MandelbrotSet {
    final static int max_iterations = 1000;

    public static void paintMandelbrotSet(int width, int height, Consumer<Pixel> colorPixel) {
        double minReal = -2.0;
        double maxReal = 1.0;
        double minImaginary = -1.2;
        double maxImaginary = minImaginary + (maxReal - minReal) * height / width;

        double x_factor = (maxReal - minReal) / (width - 1);
        double y_factor = (maxImaginary - minImaginary) / (height - 1);

        ComplexNumber c = new ComplexNumber();
        ComplexNumber z = new ComplexNumber();
        for (int x = 0; x < width; x++)
        {
            c.setReal(minReal + x * x_factor);
            for (int y = 0; y < height; y++)
            {
                c.setImaginary(maxImaginary - y * y_factor);
                z.copy(c);
                int iterations = 0;
                do {
                    z = z.square().add(c);
                    iterations++;
                } while (iterations < max_iterations && !z.absoluteValueGreaterThan(2));
                colorPixel(colorPixel, x, y, iterations);
            }
        }
    }

    private static void colorPixel(Consumer<Pixel> colorPixel, int x, int y, int iterations) {
        Pixel pixel = inMandelbrotSet(iterations) ? new Pixel(x, y, Color.BLACK) : new Pixel(x, y, Color.rgb(iterations % 255, 0, 0));
        colorPixel.accept(pixel);
    }

    private static boolean inMandelbrotSet(int iterations) {
        return iterations == max_iterations;
    }
}