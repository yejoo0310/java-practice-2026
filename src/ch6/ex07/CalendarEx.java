package src.ch6.ex07;
import java.util.*;

class MakeCalendar{
    private int year;
    private Calendar calendar;
    public MakeCalendar(int year){
        this.year = year;
        calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
    }

    
}

public class CalendarEx {
    public static void Main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("년도 입력(-1이면 종료)>>");
            int inputYear = scanner.nextInt();
            if (inputYear == -1){
                break;
            }
            MakeCalendar calendar = new MakeCalendar(inputYear);

        }
        scanner.close();
    }
}
