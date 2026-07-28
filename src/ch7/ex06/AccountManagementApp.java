package src.ch7.ex06;

import java.util.*;

public class AccountManagementApp {
    private final Scanner scanner;
    private final Map<String, Integer> account;

    public AccountManagementApp(Scanner scanner) {
        this.scanner = scanner;
        this.account = new HashMap<String, Integer>();
    }

    public void run() {
        System.out.println("*** 명품 은행에 오신 것을 환영합니다. ***");
        readUserMenu();
    }

    private void deposit() {
        while (true) {
            System.out.print("계좌명과 액수>>");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("계좌명과 액수를 입력해야 합니다.");
                continue;
            }
            String[] inputs = line.split("\\s+");
            if (inputs.length != 2) {
                System.out.println("계좌명과 액수를 빈 칸을 기준으로 입력해야 합니다.");
                continue;
            }

            String name = inputs[0];
            try {
                int amount = Integer.parseInt(inputs[1]);
                if (amount < 0) {
                    System.out.println("액수는 0보다 커야 합니다.");
                    continue;
                }
                int currentAmount = account.getOrDefault(name, 0);
                account.put(name, currentAmount + amount);
                return;
            } catch (NumberFormatException e) {
                System.out.println("액수는 정수로 입력해야 합니다.");
            }
        }
    }

    private void withdraw() {
        while (true) {
            System.out.print("계좌명과 액수>>");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("계좌명과 액수를 입력해야 합니다.");
                continue;
            }
            String[] inputs = line.split("\\s+");
            if (inputs.length != 2) {
                System.out.println("계좌명과 액수를 빈 칸을 기준으로 입력해야 합니다.");
                continue;
            }

            String name = inputs[0];
            try {
                int amount = Integer.parseInt(inputs[1]);
                if (amount < 0) {
                    System.out.println("액수는 0보다 커야 합니다.");
                    continue;
                }

                if (!account.containsKey(name)) {
                    System.out.println("계좌가 존재하지 않는 고객이 아닙니다.");
                    return;
                }
                int currentAmount = account.getOrDefault(name, 0);
                if (currentAmount < amount) {
                    System.out.println("잔액이 부족하여 출금할 수 없습니다!");
                    return;
                }
                account.put(name, currentAmount - amount);
                return;
            } catch (NumberFormatException e) {
                System.out.println("액수는 정수로 입력해야 합니다.");
            }
        }
    }

    private void showAccount() {
        while (true) {
            System.out.print("계좌명>>");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("계좌명을 입력해주세요.");
                continue;
            }
            if (account.containsKey(input)) {
                int amount = account.get(input);
                System.out.println("(" + input + ":" + amount + ")");
                return;
            }
            System.out.println("존재하지 않는 계좌명입니다.");
            return;
        }

    }

    private void showAllAccount() {
        for (Map.Entry<String, Integer> entry : account.entrySet()) {
            String name = entry.getKey();
            Integer amount = entry.getValue();

            System.out.print("(" + name + ":" + amount + ")");
        }
        System.out.println();
    }

    private void readUserMenu() {
        while (true) {
            System.out.print("입금:1, 출금:2, 조회:3, 전체 조회:4, 종료:5>>");
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                if (choice < 1 || choice > 5) {
                    System.out.println("1-5 사이의 정수를 입력해야 합니다.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        deposit();
                        break;
                    case 2:
                        withdraw();
                        break;
                    case 3:
                        showAccount();
                        break;
                    case 4:
                        showAllAccount();
                        break;
                    case 5:
                        return;
                }
            } catch (NumberFormatException e) {
                System.out.println("1-5 사이의 정수를 입력해야 합니다.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            AccountManagementApp app = new AccountManagementApp(scanner);
            app.run();
        } finally {
            scanner.close();
        }
    }
}
