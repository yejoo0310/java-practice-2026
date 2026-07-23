package src.ch7.ex02;

import java.util.*;

public class SumPositives {
    private Vector<Integer> v = new Vector<Integer>();
    Scanner scanner = new Scanner(System.in);

    public void read() {
        System.out.print("0이 입력될 때까지 정수 입력>>");
        while (true) {
            int input = scanner.nextInt();
            if (input == 0) {
                return;
            }
            v.add(input);
        }
    }

    public void changeToZero() {
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i) < 0) {
                v.set(i, 0);
            }
        }
    }

    public void showAll() {
        Iterator<Integer> it = v.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }

    public int add() {
        int sum = 0;
        Iterator<Integer> it = v.iterator();
        while (it.hasNext()) {
            sum += it.next();
        }
        return sum;
    }

    public static void main(String[] args) {
        SumPositives sp = new SumPositives();
        sp.read();
        sp.changeToZero();
        System.out.print("음수를 0으로 바꾸면 ");
        sp.showAll();
        System.out.println("양수들의 합은 " + sp.add());
    }
}
