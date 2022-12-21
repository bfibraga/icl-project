package src;

import src.astnodes.ASTNode;
import src.exceptions.LanguageException;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.parser.ParseException;
import src.parser.Parser;
import src.type.Type;

import java.io.*;
import java.util.Scanner;

public class Compiler {

    private static final int CODE_LINE = 31;

    public static void main(String[] args) {

        String stackTraceFlag = args.length > 1 ? args[1] : "";
        boolean stackTrace = stackTraceFlag.equals("true");

        System.out.println(stackTraceFlag);
        System.out.println(stackTrace);

        try {
            String filename = args[0];
            if (!filename.endsWith(".icl")){
                System.out.println("Not a icl file");
                return;
            }

            File file = new File(filename);
            InputStream in = new FileInputStream(file);
            Parser parser = new Parser(in);

            ASTNode exp;
            Environment<Coordinates> environmentCoord = new Environment<>();
            Environment<Type> environmentType= new Environment<>();

            CodeBlock code = new CodeBlock();
            
            PrintWriter out = new PrintWriter("./src/jvm/result/Header.j");
            //PrintWriter out = new PrintWriter(System.out);

            exp = parser.Start();
            exp.typecheck(environmentType);
            exp.compile(code, environmentCoord);
            
            publishCode(code, out, new Scanner(new File("./src/jvm/Dummy.j")));

        } catch (LanguageException e) {
            handleException("Language error encountered!", e, stackTrace);
        } catch (ParseException | IOException e)  {
            handleException("Syntax error encountered!", e, stackTrace);
        }
    }

    private static void publishCode(CodeBlock code, PrintWriter out, Scanner in) throws IOException{

        int i = 0;
        String line = "";
        while ( in.hasNextLine() ){
            line = in.nextLine();

            if (i == CODE_LINE){
                code.dump(out);
            } else {
                out.println(line);
            }

            i++;
        }

        out.close();
        in.close();
    }

    private static void handleException(String message, Exception e, boolean stackTrace) {
        System.out.println(message + "\nExiting...");

        System.out.println("Exception message:");
        System.out.println(e.getMessage());

        if (stackTrace){
            System.out.println("Stack trace on exit:");
            e.printStackTrace();
        }

        System.exit(1);
    }
}
