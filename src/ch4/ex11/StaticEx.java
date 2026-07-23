package src.ch4.ex11;

class ArrayUtil {
    public static int [] concat(int[] a, int[] b){
        int[] newArray = new int[a.length + b.length];
        for (int i = 0; i < a.length; i++){
            newArray[i] = a[i];
        }
        for (int i = 0; i < b.length; i++){
            newArray[a.length + i] = b[i];
        }

        return newArray;
    }

    public static void print(int[] a){
        System.out.print("[ ");
        for (int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println("]");
    }
}

public class StaticEx {
    public static void main(String[] args){
        int[] array1 = {1, 5, 7, 9};
        int[] array2 = {3, 6, -1, 100, 77};
        int[] array3 = ArrayUtil.concat(array1, array2);
        ArrayUtil.print(array3);
    }
}
