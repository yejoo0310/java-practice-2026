package src.ch4.ex13;
import java.util.*;

class Seat{
    private String name;

    public Seat(){
        name = "";
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public boolean isEmpty(){
        if (name.equals("")){
            return true;
        }
        return false;
    }
}

class Concert{
    Scanner scanner = new Scanner(System.in);

    private Seat[] sSeat;
    private Seat[] aSeat;
    private Seat[] bSeat;

    public Concert(){
        sSeat = new Seat[10];
        aSeat = new Seat[10];
        bSeat = new Seat[10];
        for (int i = 0; i < 10; i++){
            sSeat[i] = new Seat();
            aSeat[i] = new Seat();
            bSeat[i] = new Seat();
        }
        System.out.println("명품콘서트홀 예약 시스템입니다.");
    }

    public boolean run(){
        System.out.print("예약:1, 조회:2, 취소:3, 끝내기:4>>");
        int choice = scanner.nextInt();

        switch(choice){
            case 1:
                reservation();
                break;
            case 2:
                show();
                break;
            case 3:
                cancel();
                break;
            case 4:
                return false;
        }
        return true;
    }

    public void reservation(){
        System.out.print("좌석구분 S(1) A(2) B(3)>>");
        int choice = scanner.nextInt();
        Seat[] seat = null;

        switch(choice){
            case 1:
                seat = sSeat;
                show("S", seat);
                break;
            case 2:
                seat = aSeat;
                show("A", seat);
                break;
            case 3:
                seat = bSeat;
                show("B", seat);
                break;
        }

        System.out.print("이름>>");
        String name = scanner.next();
        System.out.print("번호>>");
        int number = scanner.nextInt();

        seat[number-1].setName(name);
    }

    public void show(String type, Seat[] seat){
        System.out.print(type + ">> ");
        for (int i = 0; i < seat.length; i++){
            if (seat[i].isEmpty()){
                System.out.print("--- ");
            } else {
                System.out.print(seat[i].getName() + " ");
            }
        }
        System.out.println();
    }

    public void show(){
        show("S", sSeat);
        show("A", aSeat);
        show("B", bSeat);
        System.out.println("<<<조회를 완료하였습니다.>>>");
    }

    public void cancel(){
        System.out.print("좌석구분 S(1) A(2) B(3)>>");
        int choice = scanner.nextInt();
        Seat[] seat = null;

        switch(choice){
            case 1:
                seat = sSeat;
                show("S", seat);
                break;
            case 2:
                seat = aSeat;
                show("A", seat);
                break;
            case 3:
                seat = bSeat;
                show("B", seat);
                break;
        }

        System.out.print("이름>>");
        String name = scanner.next();
        
        for (int i = 0; i < seat.length; i++){
            if (seat[i].getName().equals(name)){
                seat[i].setName("");
                break;
            }
        }
    }
}

public class ConcertApp {
    public static void main(String[] args){
        Concert concert = new Concert();
        while (concert.run()){
            concert.run();
        }
    }
}
