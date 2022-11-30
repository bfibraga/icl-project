package src;

import src.astnodes.ASTNode;
import src.exceptions.LanguageException;
import src.misc.Environment;
import src.parser.ParseException;
import src.parser.Parser;
import src.misc.TypeFunctions;
import src.type.Type;
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
        Environment<Type> environmentType = new Environment<>();
        Environment<Value> environmentValue = new Environment<>();

        do {
            try {
                out.print("> ");
                exp = parser.Start();
                exp.typecheck(environmentType);
                exp.eval(environmentValue);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (LanguageException e){
                //out.println(e.getMessage());
                e.printStackTrace();
                parser.ReInit(in);
            }
        } while (loop);
    }
}
