package src.ch4.ex14;

class VArray {
    private int size;
    private int[] array;

    public VArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "초기 용량은 1 이상이어야 합니다.");
        }
        this.size = 0;
        array = new int[capacity];
    }

    public int capacity() {
        return array.length;
    }

    public int size() {
        return size;
    }

    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public void add(int num) {
        resizeArray();
        array[size] = num;
        size += 1;
    }

    public void insert(int index, int num) {
        if (index < 0 || index > size) {
            return;
        }
        resizeArray();

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = num;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        array[size] = 0;
    }

    private void resizeArray() {
        if (size < array.length) {
            return;
        }

        int[] newArray = new int[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
}

public class VArrayEx {
    public static void main(String[] args) {
        VArray v = new VArray(5);
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());

        for (int i = 0; i < 7; i++) {
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

        for (int i = 50; i < 55; i++) {
            v.add(i);
        }
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());
        v.printAll();
    }
}
