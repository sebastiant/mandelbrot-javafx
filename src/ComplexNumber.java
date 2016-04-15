public class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber() {
    }

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double realUnit) {
        this.real = realUnit;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginaryUnit) {
        this.imaginary = imaginaryUnit;
    }

    public void copy(ComplexNumber copy) {
        real = copy.getReal();
        imaginary = copy.getImaginary();
    }

    public ComplexNumber square() {
        return new ComplexNumber(real * real - imaginary * imaginary, 2 * real * imaginary);
    }

    public ComplexNumber add(ComplexNumber addend) {
        return new ComplexNumber(real + addend.real, imaginary + addend.imaginary);
    }

    public boolean absoluteValueGreaterThan(double limit) {
        return (real * real + imaginary * imaginary) > (limit * limit);
    }

    @Override
    public String toString() {
        return real + " + " + imaginary + "i";
    }
}
