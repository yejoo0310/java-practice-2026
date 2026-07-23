package src.ch5.ex12;

import java.util.*;

abstract class PairMap{
    protected String keyArray[];
    protected String valueArray[];
    abstract public String get(String key);
    abstract public void put(String key, String value);
    abstract public String delete(String key);
    abstract public int length();
}

class Dictionary extends PairMap{
    private int size;
    private int amount;
    public Dictionary(int size){
        this.size = size;
        this.amount = 0;
        keyArray = new String[this.size];
        valueArray = new String[this.size];
    }

    public String get(String key){
        for (int i = 0; i < keyArray.length; i++){
            if (keyArray[i].equals(key)){
                return valueArray[i];
            }
        }   
        return null;
    }

    public void put(String key, String value){
        if (amount == size){
            return;
        }
        if (get(key).equals("null")){
            keyArray[amount] = key;
            valueArray[amount] = value;
            amount++;
            return;
        }
        for (int i = 0; i < keyArray.length; i++){
            if (keyArray[i].equals(key)){
                valueArray[i] = value;
                return;
            }
        }
    }

    public String delete(String key){
        for (int i = 0; i < keyArray.length; i++){
            if (keyArray[i].equals(key)){
                keyArray[i] = null;
                valueArray[i] = null;
                for (int j = i; j < keyArray.length; j++){
                    String tmpKey = keyArray[j]
                }
                amount--;
            }
        }
    }
}

public class DictionaryApp {
    public static void main(String[] args){
        Dictionary dic = new Dictionary(10);
        dic.put("황기태", "자바");
        dic.put("이재문", "파이썬");
        dic.put("이재문", "C++");
        System.out.println("이재문의 값은 " + dic.get("이재문"));
        System.out.println("황기태의 값은 " + dic.get("황기태"));
        dic.delete("황기태");
        System.out.println("황기태의 값은 " + dic.get("황기태"));
    }
}
