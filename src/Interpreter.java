package src;

import src.astnodes.ASTNode;
import src.misc.Environment;
import src.parser.Parser;
import src.value.Value;

import java.io.InputStream;
import java.io.PrintStream;

public class Interpreter {
    /** Main entry point. */
    public static void main(String[] args) {
        InputStream in = System.in;
        PrintStream out = System.out;

        Parser parser = new Parser(in);
        ASTNode exp;
        Environment<Value> environment = new Environment<>();

        while (true) {
            try {
                out.print("> ");
                exp = parser.Start();
                exp.eval(environment);
                out.println();
            } catch (Exception e) {
                e.printStackTrace();
                parser.ReInit(System.in);
            }
        }
    }
}
