package src;

import src.astnodes.ASTNode;
import src.exceptions.LanguageException;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.parser.ParseException;
import src.parser.Parser;

import java.io.*;
import java.util.Scanner;

public class Compiler {

    private static final int CODE_LINE = 31;

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
            
            PrintWriter out = new PrintWriter(new File("./src/jvm/result/Header.j"));
            //PrintWriter out = new PrintWriter(System.out);

            exp = parser.Start();
            exp.compile(code, environment);
            
            publishCode(code, out, new Scanner(new File("./src/jvm/Dummy.j")));

        } catch (LanguageException e) {
            System.out.println(e.getMessage());
            //parser.ReInit(System.in);
        } catch (ParseException | IOException e)  {
            e.printStackTrace();
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
}
