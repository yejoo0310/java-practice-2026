package src.ch7.ex11;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DailyStepTracker {
    private final Scanner scanner;
    private final HashMap<String, ArrayList<Integer>> dailyStepsByPerson;

    public DailyStepTracker(Scanner scanner) {
        this.scanner = scanner;
        this.dailyStepsByPerson = new HashMap<String, ArrayList<Integer>>();
    }

    private void readPersonAndSteps() {
        while (true) {
            System.out.print("이름과 걸음수>>");
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                System.out.println("이름과 걸음수를 입력해주세요.");
                continue;
            }

            if (line.equals("그만")) {
                return;
            }

            String[] inputs = line.split("\\s+");

            String name = inputs[0].trim();
            if (name.isEmpty()) {
                System.out.println("이름은 빈 값일 수 없습니다.");
                continue;
            }

            if (inputs.length < 2) {
                System.out.println("이름과 걸음수를 함께 입력해야 합니다.");
                continue;
            }

            ArrayList<Integer> newSteps = new ArrayList<Integer>();

            for (int i = 1; i < inputs.length; i++) {
                try {
                    int step = Integer.parseInt(inputs[i].trim());
                    if (step < 0) {
                        System.out.println(step + "는 음수이므로 포함되지 않습니다.");
                        continue;
                    }
                    newSteps.add(step);
                } catch (NumberFormatException e) {
                    System.out.println("걸음수는 숫자로 입력해야 합니다.");
                }
            }

            if (newSteps.isEmpty()) {
                System.out.println("유효한 걸음수가 하나 이상 필요합니다.");
                continue;
            }

            ArrayList<Integer> savedSteps = dailyStepsByPerson.getOrDefault(name, new ArrayList<Integer>());
            savedSteps.addAll(newSteps);

            dailyStepsByPerson.put(name, savedSteps);
        }
    }

    private int calculateTotalSteps(ArrayList<Integer> steps) {
        int total = 0;
        for (Integer step : steps) {
            total += step;
        }
        return total;
    }

    private String findTopWalker() {
        Map.Entry<String, ArrayList<Integer>> firstEntry = dailyStepsByPerson.entrySet().iterator().next();
        String topWalker = firstEntry.getKey();
        int maxTotalSteps = calculateTotalSteps(firstEntry.getValue());

        for (Map.Entry<String, ArrayList<Integer>> entry : dailyStepsByPerson.entrySet()) {
            String name = entry.getKey();
            int totalSteps = calculateTotalSteps(entry.getValue());

            if (totalSteps > maxTotalSteps) {
                maxTotalSteps = totalSteps;
                topWalker = name;
            }
        }

        return topWalker;
    }

    private void printTopWalker() {
        String topWalker = findTopWalker();
        int topTotalSteps = calculateTotalSteps(dailyStepsByPerson.get(topWalker));
        System.out.println("걸음수가 가장 많은 사람은 " + topWalker + " " + topTotalSteps + "보");
    }

    private void searchWalkers() {
        while (true) {
            System.out.print("검색할 이름>>");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("검색하고 싶은 사람의 이름을 입력해야 합니다.");
                continue;
            }

            if (name.equals("그만")) {
                return;
            }

            ArrayList<Integer> steps = dailyStepsByPerson.get(name);
            if (steps == null) {
                System.out.println(name + "은(는) 없는 사람입니다.");
                continue;
            }

            for (Integer step : steps) {
                System.out.print(step + " ");
            }

            int total = calculateTotalSteps(steps);
            int average = total / steps.size();

            System.out.println("평균 " + average);
        }
    }

    public void run() {
        readPersonAndSteps();

        if (dailyStepsByPerson.isEmpty()) {
            System.out.println("입력된 사람이 없습니다.");
            return;
        }

        printTopWalker();
        searchWalkers();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            DailyStepTracker tracker = new DailyStepTracker(scanner);
            tracker.run();
        } finally {
            scanner.close();
        }
    }
}
