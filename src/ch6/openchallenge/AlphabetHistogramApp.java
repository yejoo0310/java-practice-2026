package src.ch6.openchallenge;

import java.util.Scanner;

public class AlphabetHistogramApp {
    private final Scanner scanner;

    public AlphabetHistogramApp() {
        scanner = new Scanner(System.in);
    }

    private String readString() {
        StringBuffer sb = new StringBuffer();
        while (true) {
            String line = scanner.nextLine();
            if (line.equals(";")) {
                break;
            }
            sb.append(line);
        }
        return sb.toString();
    }

    private int[] countAlphabets(String text) {
        int[] counts = new int[26];
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                counts[ch - 'A']++;
            }
        }
        return counts;
    }

    private void printHistogram(int[] counts) {
        for (int i = 0; i < counts.length; i++) {
            char alphabet = (char) ('A' + i);
            System.out.print(alphabet);
            for (int j = 0; j < counts[i]; j++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }

    public void run() {
        String text = readString().toUpperCase();
        int[] counts = countAlphabets(text);
        printHistogram(counts);
    }

    public void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        AlphabetHistogramApp app = new AlphabetHistogramApp();

        try {
            app.run();
        } finally {
            app.close();
        }
    }
}
