package src;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.parser.Parser;

import java.io.*;

public class Compiler {
    public static void main(String[] args) {
        String filename = args[0];
        if (!filename.endsWith(".icl")){
            System.out.println("Not a icl file");
            return;
        }

        try {
            File file = new File(filename);
            InputStream in = new FileInputStream(file);
            Parser parser = new Parser(in);

            ASTNode exp;
            Environment<Coordinates> environment = new Environment<>();
            CodeBlock code = new CodeBlock();
            PrintWriter out = new PrintWriter(System.out);

            exp = parser.Start();
            exp.compile(code, environment);

            code.dump(out);
        } catch (Exception e) {
            e.printStackTrace();
            //parser.ReInit(System.in);
        }
    }
}
