package src.ch6.openchallenge;

import java.util.Scanner;
import java.lang.StringBuffer;

public class AlphabetHistogramApp {
    private final Scanner scanner;
    private String str;
    private int[] count;

    public AlphabetHistogramApp() {
        scanner = new Scanner(System.in);
        count = new int[26];
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

    private void calculate() {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                count[ch - 'A']++;
            }
        }
    }

    private void printHistogram() {
        for (int i = 0; i < count.length; i++) {
            char alphabet = (char) ('A' + i);
            System.out.print(alphabet);
            for (int j = 0; j < count[i]; j++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }

    public void run() {
        str = readString().toUpperCase();
        calculate();
        printHistogram();
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
