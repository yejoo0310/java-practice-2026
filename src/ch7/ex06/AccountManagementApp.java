package src.ch7.ex06;

import java.util.*;

public class AccountManagementApp {
    private final Scanner scanner;
    private final Map<String, Integer> balances;

    public AccountManagementApp(Scanner scanner) {
        this.scanner = scanner;
        this.balances = new HashMap<String, Integer>();
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
                if (amount <= 0) {
                    System.out.println("액수는 0보다 커야 합니다.");
                    continue;
                }
                int currentAmount = balances.getOrDefault(name, 0);
                balances.put(name, currentAmount + amount);
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
                if (amount <= 0) {
                    System.out.println("액수는 0보다 커야 합니다.");
                    continue;
                }

                if (!balances.containsKey(name)) {
                    System.out.println("존재하지 않는 계좌입니다.");
                    return;
                }
                int currentAmount = balances.get(name);
                if (currentAmount < amount) {
                    System.out.println("잔액이 부족하여 출금할 수 없습니다!");
                    return;
                }
                balances.put(name, currentAmount - amount);
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
            if (balances.containsKey(input)) {
                int amount = balances.get(input);
                System.out.println("(" + input + ":" + amount + ")");
                return;
            }
            System.out.println("존재하지 않는 계좌명입니다.");
            return;
        }

    }

    private void showAllAccounts() {
        if (balances.isEmpty()) {
            System.out.println("등록된 계좌가 없습니다.");
            return;
        }
        for (Map.Entry<String, Integer> entry : balances.entrySet()) {
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
                        showAllAccounts();
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
