package src.ch5.ex03;

public class ColorPoint extends Point {
    private String color;

    public ColorPoint(int x, int y, String color) {
        super(x, y);
        this.color = color;
    }

    protected void setXY(int x, int y) {
        move(x, y);
    }

    protected void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String toString() {
        return getColor() + "색의 (" + getX() + "," + getY() + ")의 점";
    }

    public static void main(String[] args) {
        ColorPoint cp = new ColorPoint(5, 5, "RED");
        cp.setXY(10, 20);
        cp.setColor("BLUE");
        String str = cp.toString();
        System.out.println(str + "입니다.");
    }
}