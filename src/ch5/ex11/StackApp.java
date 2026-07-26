package src.ch5.ex11;

import java.util.Scanner;

interface IStack {
    int capacity();

    int length();

    boolean push(String val);

    String pop();
}

class StringStack implements IStack {
    private String[] stack;
    private int size; // 스택 크기
    private int capacity; // 스택에 저장 가능한 개수
    private int length; // 스택에 현재 저장된 개수

    public StringStack(int size) {
        stack = new String[size];
        this.size = size;
        this.capacity = this.size;
        this.length = 0;
    }

    public int capacity() {
        return capacity;
    }

    public int length() {
        return length;
    }

    public boolean isFull() {
        return capacity == 0;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean push(String val) {
        if (isFull()) {
            return false;
        }
        stack[length] = val;
        length++;
        capacity--;
        return true;
    }

    public String pop() {
        if (isEmpty()) {
            return null;
        }
        String tmp = stack[length - 1];
        length--;
        capacity++;
        return tmp;
    }
}

public class StackApp {
    private final Scanner scanner;

    public StackApp() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        int size;
        while (true) {
            System.out.print("스택 용량>>");
            String input = scanner.nextLine().trim();
            try {
                size = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("양의 정수를 입력해야 합니다.");
                continue;
            }
            break;
        }
        StringStack stack = new StringStack(size);

        while (true) {
            System.out.print("문자열 입력>>");
            String str = scanner.nextLine().trim();
            if (str.equals("")) {
                System.out.println("문자열을 입력해야 합니다.");
                continue;
            }
            if (str.equals("그만")) {
                System.out.print("스택에 저장된 문자열 팝 :");
                for (int i = stack.length() - 1; i >= 0; i--) {
                    System.out.print(" " + stack.pop());
                }
                break;
            }
            if (!stack.push(str)) {
                System.out.println("스택이 꽉 차서 " + str + " 저장 불가");
            }
        }
    }

    public void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        StackApp app = new StackApp();
        try {
            app.run();
        } finally {
            app.close();
        }
    }
}
