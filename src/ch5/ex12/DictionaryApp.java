package src.ch5.ex12;

abstract class PairMap {
    protected String keyArray[];
    protected String valueArray[];

    abstract public String get(String key);

    abstract public void put(String key, String value);

    abstract public String delete(String key);

    abstract public int length();
}

class Dictionary extends PairMap {
    private int size;
    private int length;

    public Dictionary(int size) {
        this.size = size;
        this.length = 0;
        keyArray = new String[this.size];
        valueArray = new String[this.size];
    }

    @Override
    public String get(String key) {
        for (int i = 0; i < length; i++) {
            if (key.equals(keyArray[i])) {
                return valueArray[i];
            }
        }
        return null;
    }

    @Override
    public void put(String key, String value) {
        for (int i = 0; i < length; i++) {
            if (key.equals(keyArray[i])) {
                valueArray[i] = value;
                return;
            }
        }

        if (length == size) {
            return;
        }

        keyArray[length] = key;
        valueArray[length] = value;
        length++;
    }

    @Override
    public String delete(String key) {
        for (int i = 0; i < length; i++) {
            if (key.equals(keyArray[i])) {
                String deletedValue = valueArray[i];

                for (int j = i; j < length - 1; j++) {
                    keyArray[j] = keyArray[j + 1];
                    valueArray[j] = valueArray[j + 1];
                }

                length--;
                keyArray[length] = null;
                valueArray[length] = null;
                return deletedValue;
            }
        }
        return null;
    }

    @Override
    public int length() {
        return length;
    }
}

public class DictionaryApp {
    public static void main(String[] args) {
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
