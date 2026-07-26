package src.ch5.ex10;

import java.util.Scanner;
import src.ch5.ex09.BaseArray;

public class SortedArray extends BaseArray {
    public SortedArray(int size) {
        super(size);
    }

    @Override
    public void add(int n) {
        if (nextIndex == array.length) {
            return;
        }

        super.add(n);

        // 삽입정렬
        int i = nextIndex - 2;
        while (i >= 0 && array[i] > n) {
            array[i + 1] = array[i];
            i--;
        }

        array[i + 1] = n;
    }

    public static void main(String[] args) {
        SortedArray sArray = new SortedArray(10);

        Scanner scanner = new Scanner(System.in);
        System.out.print(">>");
        for (int i = 0; i < sArray.length(); i++) {
            int n = scanner.nextInt();
            sArray.add(n);
        }
        sArray.print();
        scanner.close();
    }
}