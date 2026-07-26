package src.ch5.ex09;

import java.util.Scanner;

public class BinaryArray extends BaseArray {
    private int threshold;

    public BinaryArray(int size, int threshold) {
        super(size);
        this.threshold = threshold;
    }

    @Override
    public void add(int n) {
        if (n > threshold) {
            super.add(1);
            return;
        }
        super.add(0);
    }

    public static void main(String[] args) {
        int threshold = 50;
        BinaryArray bArray = new BinaryArray(10, threshold);
    
        Scanner scanner = new Scanner(System.in);
        System.out.print(">>");
        for (int i = 0; i < bArray.length(); i++) {
            int n = scanner.nextInt();
            bArray.add(n);
        }
        bArray.print();
        scanner.close();
    }
}