package src.ch5.ex13;

import java.util.Scanner;
import java.util.StringTokenizer;

abstract class Calc {
    private int x, y;
    private String errorMsg;

    public void setValue(int x, int y) {
        this.x = x;
        this.y = y;
        errorMsg = null;
    }

    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

    protected void setErrorMsg(String str) {
        errorMsg = str;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public abstract int calculate();
}

class Add extends Calc {
    @Override
    public int calculate() {
        return getX() + getY();
    }
}

class Sub extends Calc {
    @Override
    public int calculate() {
        return getX() - getY();
    }
}

class Mul extends Calc {
    @Override
    public int calculate() {
        return getX() * getY();
    }
}

// todo: 에러시 0 반환 대신, 예외 던지기로 수정
class Div extends Calc {
    @Override
    public int calculate() {
        if (getY() == 0) {
            setErrorMsg("0으로 나눌 수 없음. 프로그램 종료");
            return 0;
        }
        return getX() / getY();
    }
}

// todo: 입력 처리와 객체 생성 책임 분리
public class CalculatorApp {
    private final Scanner scanner;

    public CalculatorApp() {
        scanner = new Scanner(System.in);
    }

    private Calc getCalc() {
        while (true) {
            System.out.print("두 정수와 연산자를 입력하시오>>");

            String line = scanner.nextLine().trim();
            StringTokenizer st = new StringTokenizer(line);

            if (st.countTokens() != 3) {
                System.out.println("두 정수와 연산자를 순서대로 입력해야 합니다.");
                continue;
            }

            int a, b;
            try {
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
            } catch (NumberFormatException e) {
                System.out.println("정수를 입력해야 합니다.");
                continue;
            }
            String operator = st.nextToken();

            Calc calc;
            switch (operator) {
                case "+":
                    calc = new Add();
                    break;
                case "-":
                    calc = new Sub();
                    break;
                case "*":
                    calc = new Mul();
                    break;
                case "/":
                    calc = new Div();
                    break;
                default:
                    System.out.println("잘못된 연산자입니다. 다시 입력해주세요.");
                    continue;
            }

            calc.setValue(a, b);
            return calc;
        }
    }

    public void run() {
        while (true) {
            Calc calc = getCalc();
            int result = calc.calculate();

            if (calc.getErrorMsg() != null) {
                System.out.println(calc.getErrorMsg());
                break;
            }
            System.out.println("계산 결과 " + result);
        }
    }

    public void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        CalculatorApp app = new CalculatorApp();
        try {
            app.run();
        } finally {
            app.close();
        }
    }
}
