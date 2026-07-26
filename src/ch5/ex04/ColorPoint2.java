package src.ch5.ex04;

import src.ch5.ex03.ColorPoint;

public class ColorPoint2 extends ColorPoint {
    public ColorPoint2() {
        super(0, 0, "WHITE");
    }

    public ColorPoint2(int x, int y) {
        super(x, y, "BLACK");
    }

    public ColorPoint2(int x, int y, String color) {
        super(x, y, color);
    }

    protected void set(String color) {
        setColor(color);
    }

    protected void set(int x, int y) {
        setXY(x, y);
    }

    protected double getDistance(ColorPoint2 cp) {
        return Math.sqrt(Math.pow(((double) cp.getX() - (double) getX()), 2)
                + Math.pow(((double) cp.getY() - (double) getY()), 2));
    }

    public static void main(String[] args) {
        ColorPoint2 zeroPoint = new ColorPoint2();
        System.out.println(zeroPoint.toString() + "입니다.");

        ColorPoint2 cp = new ColorPoint2(10, 10, "RED");
        cp.set("BLUE");
        cp.set(10, 20);
        System.out.println(cp.toString() + "입니다.");

        ColorPoint2 thresholdPoint = new ColorPoint2(100, 100);
        System.out.println("cp에서 임계점까지의 거리는 " + cp.getDistance(thresholdPoint));
    }
}