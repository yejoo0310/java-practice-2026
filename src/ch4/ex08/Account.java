package src.ch4.ex08;

public class Account {
    private int money;

    public Account(){
        this(0);
    }

    public Account(int money){
        this.money = money;
    }

    public void deposit(int money){
        this.money += money;
    }

    public void deposit(int[] money){
        for (int i = 0; i < money.length; i++){
            this.money += money[i];
        }
    }

    public int getBalance(){
        return money;
    }

    public int withdraw(int money){
        if (this.money < money) {
            int tmp = this.money;
            this.money = 0;
            return tmp;
        }

        this.money -= money;
        return money;
    }

    public static void main(String[] args){
        Account a = new Account(100);
        a.deposit(5000);
        System.out.println("잔금은 " + a.getBalance() + "원입니다.");

        int bulk[] = {100, 500, 200, 700};
        a.deposit(bulk);
        System.out.println("잔금은 " + a.getBalance() + "원입니다.");

        int money = 1000;
        int wMoney = a.withdraw(money);
        if (wMoney < money){
            System.out.println(wMoney + "원만 인출");
        } else {
            System.out.println(wMoney + "원 인출");
        }

        System.out.println("잔금은 " + a.getBalance() + "원입니다.");
    }
}
