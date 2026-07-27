package src.ch5.ex15;

interface Shape {
    final double PI = 3.14;

    void draw();

    double getArea();

    default public void redraw() {
        System.out.print("--- 다시 그립니다. ");
        draw();
    }
}

class Circle implements Shape {
    private final int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("반지름이 " + radius + "인 원");
    }

    @Override
    public double getArea() {
        return PI * radius * radius;
    }
}

class Oval implements Shape {
    private final int x, y;

    public Oval(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        System.out.println(x + "x" + y + "에 내접하는 타원");
    }

    @Override
    public double getArea() {
        return PI * x * y;
    }
}

class Rect implements Shape {
    private final int x, y;

    public Rect(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        System.out.println(x + "x" + y + "크기의 사각형");
    }

    @Override
    public double getArea() {
        return x * y;
    }
}

public class ShapeEx {
    static public void main(String[] args) {
        Shape[] list = new Shape[3];
        list[0] = new Circle(5);
        list[1] = new Oval(20, 30);
        list[2] = new Rect(10, 40);
        for (int i = 0; i < list.length; i++) {
            list[i].redraw();
        }
        for (int i = 0; i < list.length; i++) {
            System.out.println("면적은 " + list[i].getArea());
        }
    }
}
