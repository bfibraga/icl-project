package src;

import src.value.Array;
import src.value.Bool;
import src.value.Int;

public class Test {
    public static void main(String[] args) {
        Array array = new Array();
        array.push(new Int(1));
        System.out.println(array);

        array.push(new Bool(true));
        System.out.println(array);

        array.push(new Bool(false));
        System.out.println(array);

        array.dequeue();
        array.pop();

        System.out.println(array);
    }
}
