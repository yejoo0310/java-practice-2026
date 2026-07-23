package src.ch6.ex02;

class Book {
    private String title, author, name;

    public Book(String author, String title, String name) {
        this.author = author;
        this.title = title;
        this.name = name;
    }

    public String toString() {
        return name + "이(가) 구입한 도서: " + author + "의 " + title;
    }

    public boolean equals(Object obj) {
        Book b = (Book) obj;
        if (b.title == title && b.author == author) {
            return true;
        }
        return false;
    }
}

public class BookApp {
    public static void main(String[] args) {
        Book a = new Book("황기태", "명품자바", "김하진");
        Book b = new Book("황기태", "명품자바", "하여린");
        System.out.println(a);
        System.out.println(b);

        if (a.equals(b)) {
            System.out.println("같은 책");
        } else {
            System.out.println("다른 책");
        }
    }
}
