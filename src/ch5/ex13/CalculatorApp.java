package src.ch5.ex13;

import java.util.*;

abstract class Calculaotr {
    private int x, y;
    private String errorMsg;

    public void setValue(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setErrorMsg(String str) {
        errorMsg = str;
    }

    public void printErrorMsg() {
        System.out.println(errorMsg);
    }

    abstract public int calculator();
}

class Add extends Calculaotr {
    public Add(int x, int y) {
        setValue(x, y);
    }

    public int calculator() {
        return getX() + getY();
    }
}

class Sub extends Calculaotr {
    public Sub(int x, int y) {
        setValue(x, y);
    }

    public int calculator() {
        return getX() - getY();
    }
}

class Mul extends Calculaotr {
    public Mul(int x, int y) {
        setValue(x, y);
    }

    public int calculator() {
        return getX() * getY();
    }
}

class Div extends Calculaotr {
    public Div(int x, int y) {
        setValue(x, y);
    }

    public int calculator() {
        if (getY() == 0) {
            setErrorMsg("0으로 나눌 수 없음. 프로그램 종료");
            printErrorMsg();
            return 0;
        }
        return getX() / getY();
    }
}

public class CalculatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int flag = 1;

        while (flag == 1) {
            System.out.print("두 정수와 연산자를 입력하시오>>");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            String operator = scanner.next();

            switch (operator) {
                case "+":
                    Add add = new Add(x, y);
                    System.out.println("계산 결과 " + add.calculator());
                    add = null;
                    break;
                case "-":
                    Sub sub = new Sub(x, y);
                    System.out.println("계산 결과 " + sub.calculator());
                    sub = null;
                    break;
                case "*":
                    Mul mul = new Mul(x, y);
                    System.out.println("계산 결과 " + mul.calculator());
                    mul = null;
                    break;
                case "/":
                    Div div = new Div(x, y);
                    int result = div.calculator();
                    if (result == 0) {
                        flag = 0;
                        break;
                    }
                    System.out.println("계산 결과 " + result);
                    break;
            }
        }
        scanner.close();
    }
}
