package src.ch5.ex14;

import java.util.Scanner;

abstract class Shape {
    private Shape next;

    public Shape() {
        next = null;
    }

    public void setNext(Shape obj) {
        next = obj;
    }

    public Shape getNext() {
        return next;
    }

    public abstract void draw();
}

class Line extends Shape {
    @Override
    public void draw() {
        System.out.println("Line");
    }
}

class Rect extends Shape {
    @Override
    public void draw() {
        System.out.println("Rect");
    }
}

class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Circle");
    }
}

public class GraphicEditor {
    private final Scanner scanner;
    private Shape start;
    private Shape last;
    private int count;

    public GraphicEditor() {
        scanner = new Scanner(System.in);
        start = null;
        last = null;
        count = 0;
    }

    private int validateInput(String input, int min, int max) {
        try {
            int choice = Integer.parseInt(input);
            if (choice < min || choice > max) {
                System.out.println(min + "-" + max + " 사이의 정수를 입력해야 합니다.");
                return 0;
            }
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("1-4 사이의 정수를 입력해야 합니다.");
            return 0;
        }
    }

    private int getUserMenu() {
        while (true) {
            System.out.print("삽입(1), 삭제(2), 모두 보기(3), 종료(4)>>");
            String input = scanner.nextLine().trim();
            int choice = validateInput(input, 1, 4);
            if (choice != 0) {
                return choice;
            }
        }
    }

    private int getUserShape() {
        while (true) {
            System.out.print("Line(1), Rect(2), Circle(3)>>");
            String input = scanner.nextLine().trim();
            int choice = validateInput(input, 1, 3);
            if (choice != 0) {
                return choice;
            }
        }
    }

    private int getUserIndex() {
        while (true) {
            System.out.print("삭제할 도형의 위치>>");
            String input = scanner.nextLine().trim();
            int index = validateInput(input, 1, count);
            if (index != 0) {
                return index;
            }
        }
    }

    public void run() {
        System.out.println("그래픽 에디터 Beauty Graphic Editor를 실행합니다.");

        while (true) {
            int command = getUserMenu();
            switch (command) {
                case 1:
                    int selectedShape = getUserShape();
                    addShape(selectedShape);
                    break;
                case 2:
                    if (count == 0) {
                        System.out.println("삭제할 수 없습니다.");
                        break;
                    }
                    int index = getUserIndex();
                    if (index == 0) {
                        System.out.println("삭제할 수 없습니다.");
                        break;
                    }
                    deleteShape(index);
                    break;
                case 3:
                    printShape();
                    break;
                case 4:
                    System.out.println("Beuty Graphic Editor를 종료합니다.");
                    return;
            }
        }

    }

    private void addShape(int type) {
        Shape newShape = createShape(type);

        if (start == null) {
            start = newShape;
            last = newShape;
        } else {
            last.setNext(newShape);
            last = newShape;
        }
        count++;
    }

    private Shape createShape(int type) {
        switch (type) {
            case 1:
                return new Line();
            case 2:
                return new Rect();
            case 3:
                return new Circle();
            default:
                throw new IllegalArgumentException("존재하지 않는 도형입니다.");
        }
    }

    private void deleteShape(int index) {
        if (index == 1) {
            start = start.getNext();
            count--;

            if (start == null) {
                last = null;
            }
            return;
        }

        Shape previous = start;
        for (int i = 1; i < index - 1; i++) {
            previous = previous.getNext();
        }

        Shape target = previous.getNext();
        previous.setNext(target.getNext());
        if (target == last) {
            last = previous;
        }
        count--;
    }

    private void printShape() {
        Shape p = start;
        while (p != null) {
            p.draw();
            p = p.getNext();
        }
    }

    public void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        GraphicEditor editor = new GraphicEditor();

        try {
            editor.run();
        } finally {
            editor.close();
        }
    }
}
