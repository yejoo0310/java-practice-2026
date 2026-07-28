package src.ch7.ex05;

import java.util.*;

public class MileageManagementApp {
    private final Scanner scanner;
    private final Map<String, Integer> customerList;

    public MileageManagementApp(Scanner scanner) {
        this.scanner = scanner;
        this.customerList = new HashMap<String, Integer>();
    }

    private void readCustomerList() {
        while (true) {
            System.out.print("이름과 마일리지>>");
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                System.out.println("이름과 마일리지를 빈 칸 기준으로 입력해주세요.");
                continue;
            }

            if (line.equals("그만")) {
                return;
            }

            String[] list = line.split("\\s+");
            if (list.length != 2) {
                System.out.println("이름과 마일리지를 빈 칸 기준으로 입력해주세요.");
                continue;
            }

            String name = list[0];
            int mileage;
            try {
                mileage = Integer.parseInt(list[1]);
                if (mileage < 0) {
                    System.out.println("마일리지는 0 이상이여야 합니다.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("마일리지를 정수로 입력해주세요.");
                continue;
            }

            addCustomer(name, mileage);
        }
    }

    private void addCustomer(String name, int mileage) {
        int currentMileage = customerList.getOrDefault(name, 0);
        customerList.put(name, currentMileage + mileage);
    }

    private void showAllCustomer() {
        if (customerList.isEmpty()) {
            System.out.println("저장된 고객이 없습니다.");
            return;
        }
        for (Map.Entry<String, Integer> entry : customerList.entrySet()) {
            String name = entry.getKey();
            int mileage = entry.getValue();

            System.out.print("(" + name + ":" + mileage + ")");
        }
        System.out.println();
    }

    private void showHighestCustomer() {
        Map.Entry<String, Integer> firstEntry = customerList.entrySet().iterator().next();

        String highestCustomer = firstEntry.getKey();
        int maxMileage = firstEntry.getValue();

        for (Map.Entry<String, Integer> entry : customerList.entrySet()) {
            if (entry.getValue() > maxMileage) {
                maxMileage = entry.getValue();
                highestCustomer = entry.getKey();
            }
        }

        System.out.println("가장 마일리지가 높은 고객은 " + highestCustomer + "입니다.");
    }

    public void run() {
        System.out.println("*** 마일리지 관리 프로그램입니다.***");
        readCustomerList();
        showAllCustomer();
        showHighestCustomer();
        System.out.println("프로그램을 종료합니다.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            MileageManagementApp app = new MileageManagementApp(scanner);
            app.run();
        } finally {
            scanner.close();
        }
    }
}
