package src;

import src.astnodes.ASTNode;
import src.misc.Environment;
import src.parser.Parser;
import src.value.Value;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

public class Interpreter {
    /** Main entry point. */
    public static void main(String[] args) throws FileNotFoundException {
        InputStream in;
        boolean loop;
        if (args.length == 0 || args[0].trim().equals("")){
            in = System.in;
            loop = true;
        } else {
            String filename = args[0];
            in = new FileInputStream(filename);
            loop = false;
        }

        PrintStream out = System.out;

        Parser parser = new Parser(in);
        ASTNode exp;
        Environment<Value> environment = new Environment<>();

        do {
            try {
                out.print("> ");
                exp = parser.Start();
                out.println(exp.eval(environment));
            } catch (Exception e) {
                e.printStackTrace();
                parser.ReInit(System.in);
            }
        } while (loop);
    }
}
