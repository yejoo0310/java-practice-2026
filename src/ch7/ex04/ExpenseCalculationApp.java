package src.ch7.ex04;

import java.util.*;

public class ExpenseCalculationApp {
    private final Scanner scanner;
    private final Map<String, Integer> menu;

    public ExpenseCalculationApp(Scanner scanner) {
        this.scanner = scanner;
        this.menu = new HashMap<String, Integer>();
        initializeMenu();
    }

    private void initializeMenu() {
        menu.put("고추장", 3000);
        menu.put("만두", 500);
        menu.put("새우깡", 1500);
        menu.put("콜라", 600);
        menu.put("참치캔", 2000);
        menu.put("치약", 1000);
        menu.put("연어", 2500);
        menu.put("삼겹살", 2500);
    }

    private void showMenuList() {
        Set<String> keys = menu.keySet();
        Iterator<String> it = keys.iterator();

        while (it.hasNext()) {
            String name = it.next();
            int price = menu.get(name);
            System.out.print("[" + name + "," + price + "]");
        }
        System.out.println();
    }

    private void readPurchaseList() {
        while (true) {
            System.out.print("물건과 개수를 입력하세요>>");
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                System.out.println("물건과 개수를 입력해주세요!");
                continue;
            }

            if (line.equals("그만")) {
                return;
            }

            String[] list = line.split("\\s+");
            if (list.length % 2 != 0) {
                System.out.println("물건과 개수를 짝으로 입력해야 합니다!");
                continue;
            }

            int totalCost = calculateTotalCost(list);
            if (totalCost > 0) {
                System.out.println("전체 비용은 " + totalCost + "입니다.");
            }
        }
    }

    private int calculateTotalCost(String[] purchaseList) {
        int totalCost = 0;

        for (int i = 0; i < purchaseList.length; i += 2) {
            String productName = purchaseList[i];

            if (!menu.containsKey(productName)) {
                System.out.println(productName + "은(는) 없는 상품입니다!");
                continue;
            }

            try {
                int count = Integer.parseInt(purchaseList[i + 1]);
                if (count <= 0) {
                    System.out.println("입력에 문제가 있습니다!");
                    continue;
                }
                int price = menu.get(productName);
                totalCost += price * count;
            } catch (NumberFormatException e) {
                System.out.println("입력에 문제가 있습니다!");
            }
        }

        return totalCost;
    }

    public void run() {
        System.out.println("쇼핑 비용을 계산해드립니다. 구입 가능 물건과 가격은 다음과 같습니다.");
        showMenuList();
        readPurchaseList();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            ExpenseCalculationApp app = new ExpenseCalculationApp(scanner);
            app.run();
        } finally {
            scanner.close();
        }
    }
}
