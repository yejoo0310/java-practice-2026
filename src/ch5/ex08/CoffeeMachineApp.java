package src.ch5.ex08;

import java.util.*;

abstract class Box{
    protected int size;
    public Box(int size){
        this.size = size;
    }

    protected int getSize(){
        return size;
    }
    
    protected void setSize(int amount){
        size -= amount;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public abstract boolean consume();

    public abstract void print();
}

class IngredientBox extends Box{
    private String name;
    public IngredientBox(String name, int size){
        super(size);
        this.name = name;
    }

    public boolean consume(){
        if (isEmpty()){
            return false;
        }
        setSize(1);
        return true;
    }

    public void print(){
        System.out.print(name + " ");
        for (int i = 0; i < getSize(); i++){
            System.out.print("*");
        }
        System.out.println(getSize());
    }
}

class CoffeeMachine{
    private IngredientBox coffee;
    private IngredientBox cream;
    private IngredientBox sugar;

    public CoffeeMachine(int coffeeSize, int creamSize, int sugarSize){
        coffee = new IngredientBox("coffee", coffeeSize);
        cream = new IngredientBox("cream", creamSize);
        sugar = new IngredientBox("sugar", sugarSize);
    }

    public boolean makeCoffee(int menu){
        switch (menu){
            case 1:
                return makeDabangCoffee();
            case 2:
                return makeSugarCoffee();
            case 3:
                return makeBlackCoffee();
            default:
                return false;
        }
    }

    public boolean makeDabangCoffee(){
        if (coffee.isEmpty() || cream.isEmpty() || sugar.isEmpty()){
            return false;
        }
        coffee.consume();
        cream.consume();
        sugar.consume();
        return true;
    }

    public boolean makeSugarCoffee(){
        if (coffee.isEmpty() || sugar.isEmpty()){
            return false;
        }
        coffee.consume();
        sugar.consume();
        return true;
    }

    public boolean makeBlackCoffee(){
        if (coffee.isEmpty()){
            return false;
        }
        coffee.consume();
        return true;
    }
}

public class CoffeeMachineApp {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine(5, 5, 5);

        System.out.println("*****청춘 커피 자판기 입니다.*****");

        while (true){

        }
    }
}
