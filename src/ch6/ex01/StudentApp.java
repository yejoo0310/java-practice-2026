package src.ch6.ex01;

class Student {
    private String name;
    private int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String toString() {
        return "학번이 " + id + "인 " + name + "입니다.";
    }

    public boolean equals(Object obj) {
        Student s = (Student) obj;
        if (s.name == name && s.id == id) {
            return true;
        }
        return false;
    }
}

public class StudentApp {
    public static void main(String[] args) {
        Student a = new Student("황기태", 23);
        Student b = new Student("황기태", 77);

        System.out.println(a);

        if (a.equals(b)) {
            System.out.println("같은 학생입니다.");
        } else {
            System.out.println("다른 학생입니다.");
        }
    }
}
