package src;

import src.astnodes.ASTNode;
import src.parser.Parser;

public class Interpreter {
    /** Main entry point. */
    public static void main(String args[]) {
        Parser parser = new Parser(System.in);
        ASTNode exp;

        while (true) {
            try {
                exp = parser.Start();
                System.out.println( exp.eval() );
            } catch (Exception e) {
                e.printStackTrace();
                parser.ReInit(System.in);
            }
        }
    }
}
