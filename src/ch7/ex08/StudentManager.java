package src.ch7.ex08;

import java.util.Scanner;
import java.util.Vector;

class Student {
    private final String name;
    private final String major;
    private final int id;
    private final double gpa;

    // todo: 생성자 예외 처리로 Student가 스스로 유효성 보장
    public Student(String name, String major, int id, double gpa) {
        this.name = name;
        this.major = major;
        this.id = id;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public int getId() {
        return id;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return "이름:" + name + "   전공:" + major + "   학번:" + id + "   학점평균:" + gpa;
    }
}

public class StudentManager {
    private static final int STUDENT_COUNT = 4;
    private static final double SCHOLARSHIP_GPA = 4.0;

    private final Scanner scanner;
    private final Vector<Student> students;

    public StudentManager(Scanner scanner) {
        this.scanner = scanner;
        this.students = new Vector<Student>();
    }

    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private void readStudents() {
        System.out.println("4명 이름, 전공, 학번, 학점 입력");

        while (students.size() < STUDENT_COUNT) {
            System.out.print(">>");
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                System.out.println("이름, 전공, 학번, 학점을 입력해주세요.");
                continue;
            }

            String[] inputs = line.split(",");
            if (inputs.length != 4) {
                System.out.println("이름, 전공, 학번, 학점을 ',' 기준으로 입력해주세요.");
                continue;
            }

            String name = inputs[0].trim();
            String major = inputs[1].trim();

            if (name.isEmpty() || major.isEmpty()) {
                System.out.println("이름과 전공은 비워둘 수 없습니다.");
                continue;
            }

            try {
                int id = Integer.parseInt(inputs[2].trim());
                if (findStudentById(id) != null) {
                    System.out.println("이미 등록된 학번입니다.");
                    continue;
                }
                double gpa = Double.parseDouble(inputs[3].trim());
                if (gpa < 0 || gpa > 4.5) {
                    System.out.println("학점 평균은 0 이상 4.5 이하여야 합니다.");
                    continue;
                }

                Student student = new Student(name, major, id, gpa);
                students.add(student);
            } catch (NumberFormatException e) {
                System.out.println("학번과 학점은 숫자를 입력해야 합니다.");
            }
        }
    }

    private void printAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void printScholarshipStudents() {
        System.out.print("장학생: ");

        boolean found = false;

        for (Student student : students) {
            if (student.getGpa() >= SCHOLARSHIP_GPA) {
                System.out.print(student.getName() + " ");
                found = true;
            }
        }

        if (!found) {
            System.out.print("없음");
        }

        System.out.println();
    }

    private Student findStudent(String name) {
        for (Student student : students) {
            if (name.equals(student.getName())) {
                return student;
            }
        }
        return null;
    }

    private void searchStudents() {
        while (true) {
            System.out.print("학생 이름 >> ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("검색을 원하는 학생 이름을 입력해주세요.");
                continue;
            }

            if (name.equals("그만")) {
                return;
            }

            Student student = findStudent(name);
            if (student == null) {
                System.out.println(name + "학생이 없습니다.");
                continue;
            }

            System.out.println(
                    student.getName() + ", " + student.getMajor() + ", " + student.getId() + ", " + student.getGpa());
        }
    }

    public void run() {
        readStudents();
        System.out.println("-------------------");
        printAllStudents();
        System.out.println("-------------------");
        printScholarshipStudents();
        System.out.println("-------------------");
        searchStudents();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            StudentManager manager = new StudentManager(scanner);
            manager.run();
        } finally {
            scanner.close();
        }
    }
}
