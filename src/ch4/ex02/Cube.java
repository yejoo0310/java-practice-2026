package src.ch4.ex02;

public class Cube {
    private int width, depth, height;

    public Cube(int width, int depth, int height) {
        this.width = width;
        this.depth = depth;
        this.height = height;
    }

    public int getVolume() {
        return width * depth * height;
    }

    public void increase(int w, int d, int h) {
        this.width += w;
        this.depth += d;
        this.height += h;
    }

    public boolean isZero() {
        return !(getVolume() > 0);
    }

    public static void main(String[] args) {
        Cube cube = new Cube(1, 2, 3);
        System.out.println("큐브의 부피는 " + cube.getVolume());

        cube.increase(1, 2, 3);
        System.out.println("큐브의 부피는 " + cube.getVolume());

        if (cube.isZero()) {
            System.out.println("큐브의 부피는 0");
        } else {
            System.out.println("큐브의 부피는 0이 아님");
        }
    }
}
