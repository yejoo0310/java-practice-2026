package src.ch4.ex14;
class VArray{
    private int capacity;
    private int size;
    private int[] array;

    public VArray(int capacity){
        this.capacity = capacity;
        this.size = 0;
        array = new int[this.capacity];
    }

    public int capacity(){
        return capacity;
    }

    public void setCapacity(){
        this.capacity *= 2;
    }

    public int size(){
        return size;
    }

    public void printAll(){
        for (int i = 0; i < size; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public void add(int num){
        if (size >= capacity){
            initialize();
        }
        array[size] = num;
        size += 1;
    }

    public void insert(int index, int num){
        if (size >= capacity){
            initialize();
        }
        for (int i = index; i <= size; i++){
            int tmp = array[i];
            array[i] = num;
            num = tmp;
        }
        size += 1;
    }

    public void remove(int index){
        if (index > size) {
            return;
        }
        for (int i = index; i < size; i++){
            array[i] = array[i + 1];
        }
        size -= 1;
        array[size] = 0;
    }

    public void initialize(){
        int[] newArray = new int[capacity * 2];
        for (int i = 0; i < array.length; i++){
            newArray[i] = array[i];
        }
        setCapacity();
        array = newArray;
    }
}

public class VArrayEx {
    public static void main(String[] args){
        VArray v = new VArray(5);
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());

        for (int i = 0; i < 7; i++){
            v.add(i);
        }
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());
        v.printAll();

        v.insert(3, 100);
        v.insert(5, 200);
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());
        v.printAll();

        v.remove(10);
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());
        v.printAll();

        for(int i = 50; i < 55; i++){
            v.add(i);
        }
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());
        v.printAll();
    }
}
