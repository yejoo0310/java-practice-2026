package src.ch4.ex01;

public class TV {
    private String name;
    private int size, price;

    public TV(String name, int size, int price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public void show() {
        System.out.println(this.name + "에서 만든 " + this.price + "만원짜리의 " + this.size + "인치 TV");
    }

    public static void main(String[] var0) {
        TV tv = new TV("Samsung", 50, 300);
        tv.show();
    }
}
