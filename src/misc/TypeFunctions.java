package src.misc;

import src.jvm.JVMType;
import src.type.*;

import java.util.ArrayList;
import java.util.List;

public class TypeFunctions {

    public static boolean sameType(Type givenType, Type targetType) {
        return givenType != null &&
                targetType != null &&
                (givenType.show().equals(new TVoid().show()) ||
                        targetType.show().equals(new TVoid().show()) ||
                        givenType.show().equals(targetType.show()));
    }

    public static boolean isVoid(Type type){
        return sameType(type, new TVoid());
    }

    public static Type getType(String name){
        if (name == null || name.trim().equals("")){
            return new TVoid();
        }

        if (name.contains("array")){
            return new TArray();
        }
        if (name.equals("bool")){
            return new TBool();
        }
        if (name.contains("ref")){
            return new TCell(new TVoid());
        }
        if (name.equals("int")){
            return new TInt();
        }
        if (name.equals("structure")){
            return new TStruct();
        }
        if (name.equals("str")){
            return new TStr();
        }

        if (name.contains("func")){
            //TODO Implement type detector in params

            System.out.println(name);

            String[] funcParts = name.split(":");
            String mainBody = funcParts[0];
            String returnTypename = funcParts[1];

            String[] typeParams = mainBody.substring(mainBody.indexOf("(")+1, mainBody.indexOf(")")).split(",");
            List<Pair<String, Type>> listType = new ArrayList<>();

            for (int i = 0; i < typeParams.length; i++) {
                String id = String.valueOf(i);
                Type paramType = TypeFunctions.getType(typeParams[i]);

                listType.add(new Pair<>(id, paramType));
            }
            Type returnType = TypeFunctions.getType(returnTypename);

            System.out.println(listType);
            System.out.println(returnType);

            return new TClosure(listType, returnType, returnType);
        }

        return new TVoid();
    }

    public static String convertTypename(String typename){
        String result = "";

        if (typename.contains(JVMType.CLOSURE.name)){
            String[] parts = typename.replace(",","").split("_");
            result = "C_";
            for (int i = 1; i < parts.length; i++) {
                result += parts[i];
            }
        } else if (typename.contains(JVMType.STRING.name)) {
            result = "S";
        } else if (typename.contains(JVMType.INT.name) || typename.contains(JVMType.BOOL.name)){
            result = String.valueOf(typename.charAt(0));
        }


        return result;
    }
}
