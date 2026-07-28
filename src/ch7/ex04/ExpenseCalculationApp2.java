package src.ch7.ex04;

import java.util.*;

public class ExpenseCalculationApp2 {
    private final Scanner scanner;
    private final ArrayList<String> menuName;
    private final ArrayList<Integer> menuPrice;

    public ExpenseCalculationApp2(Scanner scanner) {
        this.scanner = scanner;
        this.menuName = new ArrayList<String>();
        this.menuPrice = new ArrayList<Integer>();
        initializeMenu();
    }

    private void initializeMenu() {
        menuName.add("고추장");
        menuName.add("만두");
        menuName.add("새우깡");
        menuName.add("콜라");
        menuName.add("참치캔");
        menuName.add("치약");
        menuName.add("연어");
        menuName.add("삼겹살");

        menuPrice.add(3000);
        menuPrice.add(500);
        menuPrice.add(1500);
        menuPrice.add(600);
        menuPrice.add(2000);
        menuPrice.add(1000);
        menuPrice.add(2500);
        menuPrice.add(2500);
    }

    private void showMenuList() {
        for (int i = 0; i < menuName.size(); i++) {
            System.out.print("[" + menuName.get(i) + "," + menuPrice.get(i) + "] ");
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

            Integer totalCost = calculateTotalCost(list);

            if (totalCost != null) {
                System.out.println("전체 비용은 " + totalCost + "입니다.");
            }
        }
    }

    private Integer calculateTotalCost(String[] purchaseList) {
        int totalCost = 0;

        for (int i = 0; i < purchaseList.length; i += 2) {
            String productName = menuName.get(i);
            int price = menuPrice.get(i);

            if (!menuName.contains(productName)) {
                System.out.println(productName + "은(는) 없는 상품입니다!");
                return null;
            }

            try {
                int count = Integer.parseInt(purchaseList[i + 1]);
                if (count <= 0) {
                    System.out.println("입력에 문제가 있습니다!");
                    return null;
                }
                totalCost += price * count;
            } catch (NumberFormatException e) {
                System.out.println("입력에 문제가 있습니다!");
                return null;
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
