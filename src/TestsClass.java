package src;

import src.value.Bool;
import src.value.Float;
import src.value.Int;
import src.value.Value;

public class TestsClass {
    public static void main(String[] args) {
        Int intValue = new Int(1);
        Float floatValue = new Float(2.1f);
        Bool boolValue = new Bool(true);

        printType(intValue);
        printType(floatValue);
        printType(boolValue);

        Int intValue2 = new Int(2);
        System.out.println((int)intValue.getValue() + (int)intValue2.getValue());
        System.out.println();

        Float floatValue2 = new Float(3.6f);
        System.out.println((float)floatValue2.getValue() / (float)floatValue.getValue());
        System.out.println();

        Bool boolValue2 = new Bool(false);
        System.out.println((boolean)boolValue.getValue() && (boolean)boolValue2.getValue());
        System.out.println();

    }

    private static void printType(Value value){
        System.out.println(value.getClass().getSimpleName());
        System.out.println("Value: " + value.getValue());
        System.out.println("isNumber: " + value.isNumber());
        System.out.println("isBoolean: " + value.isBoolean() + "\n");
        System.out.println(value.toInt());
        System.out.println(value.toBool());
        System.out.println(value.toFloat());
        System.out.println();
    }
}
