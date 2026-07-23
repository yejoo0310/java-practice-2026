package src.ch6.ex03.main;

import src.ch6.ex03.main.util.Calc;

public class MainApp {
    public static void main(String[] args) {
        Calc c = new Calc(10, 20);
        System.out.println(c.sum());
    }
}
