package src.ch7.ex10;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class StringDeduplicator {
    private final Scanner scanner;

    public StringDeduplicator(Scanner scanner) {
        this.scanner = scanner;
    }

    private void readStrings() {
        while (true) {
            List<String> strings = new ArrayList<>();

            System.out.print("문자열들을 입력하세요>>");
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                System.out.println("문자열들을 입력해주세요.");
                continue;
            }

            if (line.equals("그만")) {
                return;
            }

            String[] inputs = line.split("\\s+");
            for (String input : inputs) {
                strings.add(input);
            }

            removeDuplicates(strings);
            printStrings(strings);
        }
    }

    private void removeDuplicates(List<String> strings) {
        int i = 0;
        while (i < strings.size()) {
            String target = strings.get(i);
            int j = i + 1;

            while (j < strings.size()) {
                if (strings.get(j).equals(target)) {
                    strings.remove(j);
                } else {
                    j++;
                }
            }
            i++;
        }
    }

    private void printStrings(List<String> strings) {
        for (String string : strings) {
            System.out.print(string + " ");
        }
        System.out.println();
    }

    public void run() {
        readStrings();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            StringDeduplicator deduplicator = new StringDeduplicator(scanner);
            deduplicator.run();
        } finally {
            scanner.close();
        }
    }
}
