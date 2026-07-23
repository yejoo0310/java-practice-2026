package src.ch4.ex04;

public class Average {
    private final int SIZE = 10;
    private int[] inputs = new int[SIZE];
    private int nextIndex = 0;

    public void put(int input) {
        if (nextIndex < SIZE) {
            inputs[nextIndex] = input;
            nextIndex += 1;
            return;
        }
        System.out.println("배열의 크기가 꽉 차서 '" + input + "' 값을 넣지 못했습니다.");
    }

    public boolean isEmpty() {
        return nextIndex == 0;
    }

    public void showAll() {
        if (isEmpty()) {
            System.out.println("배열이 비어있습니다.");
            return;
        }

        for (int i = 0; i < nextIndex; i++) {
            System.out.print(inputs[i] + " ");
        }
        System.out.println();
    }

    public double getAvg() {
        if (isEmpty()) {
            System.out.println("배열이 비어있습니다.");
        }

        double sum = 0;
        for (int i = 0; i < nextIndex; i++) {
            sum += inputs[i];
        }
        return sum / nextIndex;
    }

    public static void main(String[] args) {
        Average avg = new Average();
        avg.put(10);
        avg.put(15);
        avg.put(100);
        avg.showAll();
        System.out.println("평균은 " + avg.getAvg());
    }
}
