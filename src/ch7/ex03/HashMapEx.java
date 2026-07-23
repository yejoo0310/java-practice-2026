package src.ch7.ex03;
import java.util.*;

public class HashMapEx {
    HashMap<String, Integer> h = new HashMap<String, Integer>();

    public void read(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("주식 종목과 주가를 입력하세요(예:삼송전자 75000)");
        while (true){
            System.out.print("종목, 주가>>");
            String line = scanner.nextLine();
            if (line.equals("그만")){
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            String name = st.nextToken();
            String price = st.nextToken();
            
            h.put(name, Integer.parseInt(price));
        }
        scanner.close();
    }
    
    public void search(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("주가를 검색합니다.");
        while (true){
            System.out.print("종목>>");
            String name = scanner.next();
            if (name.equals("그만")){
                break;
            }

        }
        scanner.close();
    }

    public static void main(String[] args){
        HashMapEx hm = new HashMapEx();

    }
}
