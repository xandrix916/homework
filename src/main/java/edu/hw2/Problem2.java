package edu.hw2;

@SuppressWarnings("InnerTypeLast")
public class Problem2 {
    public sealed interface Rect {
        double area();

        double getHeight();

        double getWidth();

        record Rectangle(double width, double height) implements Rect {
            @Override public double getHeight() {
                return height;
            }

            @Override public double getWidth() {
                return width;
            }

            @Override public double area() {
                return height * width;
            }

        }

        record Square(double side) implements Rect {
            @Override public double getHeight() {
                return side;
            }

            @Override public double getWidth() {
                return side;
            }

            @Override public double area() {
                return side * side;
            }
        }
    }

    public String mediumAreaHeightWidth(Rect[] rects) {
        double area = 0d;
        double width = 0d;
        double height = 0d;

        for (var rect: rects) {
            area += rect.area();
            width += rect.getWidth();
            height += rect.getHeight();
        }

        return "Medium area is approximately %d, medium height is approximately %d, medium width is approximately %d\n".formatted((int) Math.ceil(area), (int) Math.ceil(width), (int) Math.ceil(height));
    }

    public String checkLSP(Rect[] rects) {
        Rect rectTest;
        StringBuilder s = new StringBuilder();

        for (var r: rects) {
            rectTest = r;
            s.append("Width - %d, Height - %d\n".formatted((int) rectTest.getHeight(), (int) rectTest.getWidth()));
        }

        return s.toString();
    }
}
