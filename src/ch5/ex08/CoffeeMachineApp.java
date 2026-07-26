package src.ch5.ex08;

import java.util.Scanner;

// todo: 유효하지 않은 경우 방어 코드 추가
// todo: 콘솔 출력 부분을 CoffeeMachine -> CoffeeMachineApp으로 분리
abstract class Box {
    private int size;

    public Box(int size) {
        this.size = size;
    }

    protected int getSize() {
        return size;
    }

    protected void decreaseSize(int amount) {
        size -= amount;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public abstract boolean consume();

    public abstract void print();
}

class IngredientBox extends Box {
    private final String name;

    public IngredientBox(String name, int size) {
        super(size);
        this.name = name;
    }

    public boolean consume() {
        if (isEmpty()) {
            return false;
        }
        decreaseSize(1);
        return true;
    }

    public void print() {
        System.out.print(name + " ");
        for (int i = 0; i < getSize(); i++) {
            System.out.print("*");
        }
        System.out.println(getSize());
    }
}

class CoffeeMachine {
    private IngredientBox coffee;
    private IngredientBox cream;
    private IngredientBox sugar;

    public CoffeeMachine(int coffeeSize, int creamSize, int sugarSize) {
        coffee = new IngredientBox("커피", coffeeSize);
        cream = new IngredientBox("프림", creamSize);
        sugar = new IngredientBox("설탕", sugarSize);
    }

    // todo: 메뉴 enum으로 바꾸기
    public boolean makeCoffee(int menu) {
        switch (menu) {
            case 1:
                return makeDabangCoffee();
            case 2:
                return makeSugarCoffee();
            case 3:
                return makeBlackCoffee();
            default:
                return false;
        }
    }

    private boolean makeDabangCoffee() {
        if (coffee.isEmpty() || cream.isEmpty() || sugar.isEmpty()) {
            return false;
        }
        coffee.consume();
        cream.consume();
        sugar.consume();
        return true;
    }

    private boolean makeSugarCoffee() {
        if (coffee.isEmpty() || sugar.isEmpty()) {
            return false;
        }
        coffee.consume();
        sugar.consume();
        return true;
    }

    private boolean makeBlackCoffee() {
        if (coffee.isEmpty()) {
            return false;
        }
        coffee.consume();
        return true;
    }

    public void printAllIngredient() {
        coffee.print();
        cream.print();
        sugar.print();
    }
}

public class CoffeeMachineApp {
    private final Scanner scanner;
    private final CoffeeMachine machine;

    public CoffeeMachineApp() {
        scanner = new Scanner(System.in);
        machine = new CoffeeMachine(5, 5, 5);
    }

    public void run() {
        System.out.println("*****청춘 커피 자판기 입니다.*****");
        machine.printAllIngredient();
        order();
    }

    private void order() {
        while (true) {
            System.out.print("다방커피:1, 설탕 커피:2, 블랙 커피:3, 종료:4>>");
            String input = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("1-4 사이의 정수를 입력해야 합니다.");
                continue;
            }

            if (choice == 4) {
                System.out.println("청춘 커피 자판기 프로그램을 종료합니다.");
                break;
            }

            boolean result = machine.makeCoffee(choice);
            if (!result) {
                System.out.println("원료가 부족합니다.");
            }
            machine.printAllIngredient();
        }
    }

    public void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        CoffeeMachineApp app = new CoffeeMachineApp();

        try {
            app.run();
        } finally {
            app.close();
        }
    }
}
