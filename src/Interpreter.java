package src;

import src.astnodes.ASTNode;
import src.misc.Environment;
import src.parser.Parser;

public class Interpreter {
    /** Main entry point. */
    public static void main(String[] args) {
        Parser parser = new Parser(System.in);
        ASTNode exp;
        Environment<Integer> environment = new Environment<>();

        while (true) {
            try {
                exp = parser.Start();
                System.out.println( exp.eval(environment) );
            } catch (Exception e) {
                e.printStackTrace();
                parser.ReInit(System.in);
            }
        }
    }
}
