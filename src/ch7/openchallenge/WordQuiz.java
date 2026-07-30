package src.ch7.openchallenge;

import java.util.*;

class Word {
    private final String eng;
    private final String kor;

    public Word(String eng, String kor) {
        this.eng = eng;
        this.kor = kor;
    }

    public String getEng() {
        return eng;
    }

    public String getKor() {
        return kor;
    }
}

public class WordQuiz {
    private final Scanner scanner;
    private final Random random;
    private final Vector<Word> v;

    public WordQuiz(Scanner scanner) {
        this.scanner = scanner;
        random = new Random();
        v = new Vector<Word>();
        createDictionary();
    }

    private void createDictionary() {
        v.add(new Word("love", "사랑"));
        v.add(new Word("animal", "동물"));
        v.add(new Word("cat", "고양이"));
        v.add(new Word("rabbit", "토끼"));
        v.add(new Word("dog", "개"));
        v.add(new Word("human", "사람"));
        v.add(new Word("book", "책"));
        v.add(new Word("steak", "스테이크"));
        v.add(new Word("jelly", "젤리"));
        v.add(new Word("study", "공부"));
        v.add(new Word("house", "집"));
    }

    private Vector<String> createChoices(Word question) {
        Vector<String> choices = new Vector<String>();

        choices.add(question.getKor());

        Vector<Word> candidates = new Vector<Word>(v);
        Collections.shuffle(candidates, random);

        for (Word candidate : candidates) {
            String korean = candidate.getKor();

            if (korean.equals(question.getKor())) {
                continue;
            }

            if (choices.contains(korean)) {
                continue;
            }

            choices.add(korean);

            if (choices.size() == 4) {
                break;
            }
        }
        if (choices.size() < 4) {
            throw new IllegalStateException("서로 다른 한글 뜻이 최소 4개 필요합니다.");
        }

        Collections.shuffle(choices, random);
        return choices;
    }

    private int createQuestion(Word question) {
        Vector<String> choices = createChoices(question);

        System.out.println(question.getEng() + "?");

        for (int i = 0; i < choices.size(); i++) {
            System.out.print("(" + (i + 1) + ")" + choices.get(i) + " ");
        }
        System.out.print(":>");

        return choices.indexOf(question.getKor()) + 1;
    }

    private int getUserAnswer() {
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.print("답을 입력해주세요.:>");
                continue;
            }

            try {
                int answer = Integer.parseInt(input);
                if (answer == -1 || (answer >= 1 && answer <= 4)) {
                    return answer;
                }
                System.out.print("1-4, 또는 종료를 원하면 -1을 입력해주세요.:>");
            } catch (NumberFormatException e) {
                System.out.print("숫자를 입력해주세요.:>");
            }
        }
    }

    private void play() {
        while (true) {
            int randomIndex = random.nextInt(v.size());
            Word question = v.get(randomIndex);
            int answer = createQuestion(question);
            int userAnswer = getUserAnswer();

            if (userAnswer == -1) {
                return;
            }

            if (answer == userAnswer) {
                System.out.println("Excellent !!");
            } else {
                System.out.println("No. !!");
            }
        }
    }

    public void run() {
        System.out.println("\"명품영어\"의 단어 테스트를 시작합니다. -1을 입력하면 종료합니다.");
        System.out.println("현재 " + v.size() + "개의 단어가 들어 있습니다.");
        play();
        System.out.println("\"명품영어\"를 종료합니다...");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            WordQuiz quiz = new WordQuiz(scanner);
            quiz.run();
        } finally {
            scanner.close();
        }
    }
}
