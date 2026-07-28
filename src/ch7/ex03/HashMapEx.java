package src.ch7.ex03;

import java.util.*;

public class HashMapEx {
    private final Scanner scanner;
    private final Map<String, Integer> stocks;

    public HashMapEx(Scanner scanner) {
        this.scanner = scanner;
        this.stocks = new HashMap<String, Integer>();
    }

    private void readStocks() {
        System.out.println("주식 종목과 주가를 입력하세요(예:삼송전자 75000)");

        while (true) {
            System.out.print("종목, 주가>>");
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                System.out.println("종목과 주가를 입력해주세요.");
                continue;
            }

            if (line.equals("그만")) {
                break;
            }

            String inputs[] = line.split("\\s+");
            if (inputs.length != 2) {
                System.out.println("종목과 주가를 빈 칸 기준으로 작성해야 합니다.");
                continue;
            }

            String name = inputs[0];
            try {
                int price = Integer.parseInt(inputs[1]);
                stocks.put(name, price);
            } catch (NumberFormatException e) {
                System.out.println("주가는 정수로 입력해야 합니다.");
            }
        }
    }

    private void searchStocks() {
        System.out.println("주가를 검색합니다.");

        while (true) {
            System.out.print("주가>>");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("종목을 검색해주세요.");
                continue;
            }

            if (name.equals("그만")) {
                return;
            }

            if (stocks.containsKey(name)) {
                int price = stocks.get(name);
                System.out.println(name + "의 주가는 " + price + "원");
            } else {
                System.out.println(name + "은(는) 없는 종목입니다.");
            }
        }
    }

    public void run() {
        readStocks();
        searchStocks();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            HashMapEx ex = new HashMapEx(scanner);
            ex.run();
        } finally {
            scanner.close();
        }
    }
}
