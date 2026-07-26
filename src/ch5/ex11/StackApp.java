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
    private int length;

    public StringStack(int size) {
        stack = new String[size];
        this.length = 0;
    }

    @Override
    public int capacity() {
        return stack.length;
    }

    @Override
    public int length() {
        return length;
    }

    public boolean isFull() {
        return length == stack.length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean push(String val) {
        if (isFull()) {
            return false;
        }
        stack[length] = val;
        length++;
        return true;
    }

    @Override
    public String pop() {
        if (isEmpty()) {
            return null;
        }

        String tmp = stack[--length];
        stack[length] = null;
        return tmp;
    }
}

public class StackApp {
    private final Scanner scanner;

    public StackApp() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        int capacity = getCapacity();
        StringStack stack = new StringStack(capacity);
        processStack(stack);
    }

    private int getCapacity() {
        while (true) {
            System.out.print("스택 용량>>");
            String input = scanner.nextLine().trim();

            try {
                int capacity = Integer.parseInt(input);
                if (capacity > 0) {
                    return capacity;
                }
            } catch (NumberFormatException ignored) {
            }

            System.out.println("양의 정수를 입력해야 합니다.");
        }
    }

    private void processStack(StringStack stack) {
        while (true) {
            System.out.print("문자열 입력>>");
            String str = scanner.nextLine().trim();

            if (str.equals("")) {
                System.out.println("문자열을 입력해야 합니다.");
                continue;
            }

            if (str.equals("그만")) {
                printAll(stack);
                return;
            }

            if (!stack.push(str)) {
                System.out.println("스택이 꽉 차서 " + str + " 저장 불가");
            }
        }
    }

    private void printAll(StringStack stack) {
        System.out.print("스택에 저장된 문자열 팝 :");
        while (!stack.isEmpty()) {
            System.out.print(" " + stack.pop());
        }
        System.out.println();
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
