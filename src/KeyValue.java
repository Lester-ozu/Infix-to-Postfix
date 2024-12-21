public class KeyValue {

    private char [] keys;
    private int [] values;
    private int size;

    public KeyValue(String notations) {

        keys = notations.toCharArray();
        values = new int[notations.length()];
        size = 0;
    }

    public char [] getKeys() {return keys;}
    public int [] getValues() {return values;}

    public void setKeys(char [] keys){this.keys=keys;}
    public void setValues(int [] values){this.values=values;}

    public void addValue(int value) {

        values[size] = value;
        size++;
    }

    public int getValue(char key) {

        for (int i = 0 ; i < keys.length ; i++) {

            if(keys[i] == key) {

                return values[i];
            }
        }

        return -1;
    }
}