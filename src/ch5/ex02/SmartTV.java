package src.ch5.ex02;

import src.ch5.ex01.ColorTV;

public class SmartTV extends ColorTV {
    private String address;

    public SmartTV(String address, int size, int color) {
        super(size, color);
        this.address = address;
    }

    @Override
    public void printProperty() {
        System.out.println("나의 SmartTV는 " + address + " 주소의 " + getSize() + "인치 " + getColor() + "컬러");
    }

    public static void main(String[] args) {
        SmartTV smartTV = new SmartTV("192.168.0.5", 77, 20000000);
        smartTV.printProperty();
    }
}
