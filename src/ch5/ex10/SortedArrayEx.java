package src.ch5.ex10;

import java.util.*;

class BaseArray {
    protected int array[];
    protected int nextIndex = 0;

    public BaseArray(int size) {
        array = new int[size];
    }

    public int length() {
        return array.length;
    }

    public void add(int n) {
        if (nextIndex == array.length) {
            return;
        }
        array[nextIndex] = n;
        nextIndex++;
    }

    public void print() {
        for (int n : array) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
}

class SortedArray extends BaseArray{
    public SortedArray(int size){
        super(size);
    }

    public void add(int n){
        super.add(n);
        
    }
}

public class SortedArrayEx {
    public static void main(String[] args){
        SortedArray sArray = new SortedArray(10);

        Scanner scanner = new Scanner(System.in);
        System.out.print(">>");
        for (int i = 0; i < sArray.length(); i++){
            int n = scanner.nextInt();
            sArray.add(n);
        }
        sArray.print();
        scanner.close();
    }
}
