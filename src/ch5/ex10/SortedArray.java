package src.ch5.ex10;
import java.util.Scanner;
import src.ch5.ex09.BaseArray;

public class SortedArray extends BaseArray{
    public SortedArray(int size){
        super(size);
    }

    public void add(int n){
        super.add(n);
        
    }
    
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