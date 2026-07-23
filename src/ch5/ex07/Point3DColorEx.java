package src.ch5.ex07;

import src.ch5.ex05.Point;

class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    protected void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Point3DColor extends Point {
    private int z;
    private String color;

    public Point3DColor(int x, int y, int z, String color) {
        super(x, y);
        this.z = z;
        this.color = color;
    }

    protected void move(Point3DColor p) {
        super.move(p.getX(), p.getY());
    }

    public String toString() {
        return "(" + getX() + "," + getY() + "," + z + ")" + color + "점";
    }

    public boolean equals(Point3DColor p) {
        if (p.getX() == getX() && p.getY() == getY() && p.z == z && p.color == color) {
            return true;
        }
        return false;
    }
}

public class Point3DColorEx {
    public static void main(String[] args) {
        Point3DColor p = new Point3DColor(10, 20, 30, "RED");
        System.out.println(p.toString() + "입니다.");

        Point3DColor q = new Point3DColor(1, 2, 3, "BLUE");
        p.move(q);
        System.out.println(p.toString() + "입니다.");

        Point3DColor r = new Point3DColor(1, 2, 30, "RED");
        if (p.equals(r)) {
            System.out.println("예. 같은 위치 같은 색깔의 점입니다.");
        } else {
            System.out.println("아니오");
        }
    }
}
