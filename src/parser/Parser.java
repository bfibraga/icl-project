/* Parser.java */
/* Generated By:JavaCC: Do not edit this line. Parser.java */
/** ID lister. */
package src.parser;

import src.astnodes.*;
import src.astnodes.binding.*;
import src.astnodes.control.*;
import src.astnodes.functions.*;
import src.astnodes.value.array.ASTArrayLen;
import src.astnodes.value.function.ASTApplyFunc;
import src.astnodes.value.function.ASTFunction;
import src.astnodes.functions.io.out.*;
import src.astnodes.operations.arithmetic.*;
import src.astnodes.operations.relational.*;
import src.astnodes.value.array.*;
import src.astnodes.value.primitives.*;
import src.astnodes.value.struct.*;
import src.misc.*;
import java.util.*;

public class Parser implements ParserConstants {

  static final public ASTNode Start() throws ParseException {ASTNode t;
    t = Seq();
    jj_consume_token(COMMA);
    jj_consume_token(COMMA);
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Main() throws ParseException {Token n;
    ASTNode t;
    jj_consume_token(FUN);
    jj_consume_token(MAIN);
    jj_consume_token(LPAR);
    jj_consume_token(RPAR);
    t = Seq();
t = new ASTMain(t);
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Seq() throws ParseException {Token n;
    ASTNode t1, t2;
    t1 = SeqE();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case SEMCOL:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(SEMCOL);
      t2 = SeqE();
t1 = new ASTSeq(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode SeqE() throws ParseException {Token n;
    ASTNode t1, t2;
    t1 = BoolAdd();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ARROWRIGHT:{
        ;
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(ARROWRIGHT);
      t2 = BoolAdd();
t1 = new ASTAssign(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode BoolAdd() throws ParseException {Token n;
    ASTNode t1, t2;
    t1 = BoolMult();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case OR:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_3;
      }
      jj_consume_token(OR);
      t2 = BoolMult();
t1 = new ASTOr(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode BoolMult() throws ParseException {Token n;
    ASTNode t1, t2;
    t1 = RelOps();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case AND:{
      jj_consume_token(AND);
      t2 = RelOps();
t1 = new ASTAnd(t1,t2);
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      ;
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode RelOps() throws ParseException {Token op;
    ASTNode t1, t2;
    t1 = Exp();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EQREL:
    case GT:
    case GEQT:
    case LT:
    case LEQT:
    case DIFF:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case GT:{
        op = jj_consume_token(GT);
        break;
        }
      case GEQT:{
        op = jj_consume_token(GEQT);
        break;
        }
      case LT:{
        op = jj_consume_token(LT);
        break;
        }
      case LEQT:{
        op = jj_consume_token(LEQT);
        break;
        }
      case EQREL:{
        op = jj_consume_token(EQREL);
        break;
        }
      case DIFF:{
        op = jj_consume_token(DIFF);
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Exp();
if (op.kind == GT)
            t1 = new ASTGrt(t1,t2);
        else if (op.kind == GEQT)
            t1 = new ASTGrtEq(t1,t2);
        else if (op.kind == LT)
            t1 = new ASTLwt(t1,t2);
        else if (op.kind == LEQT)
            t1 = new ASTLwtEq(t1,t2);
        else if (op.kind == EQREL)
            t1 = new ASTEq(t1,t2);
        else if (op.kind == DIFF)
            t1 = new ASTDiff(t1,t2);
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      ;
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Exp() throws ParseException {Token op;
    ASTNode t1, t2;
    t1 = Term();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:
      case MINUS:{
        ;
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:{
        op = jj_consume_token(PLUS);
        break;
        }
      case MINUS:{
        op = jj_consume_token(MINUS);
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Term();
if (op.kind == PLUS)
             t1 = new ASTPlus(t1,t2);
         else  t1 = new ASTSub(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Term() throws ParseException {Token op;
    ASTNode t1, t2;
    t1 = Fact();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case TIMES:
    case MOD:
    case POW:
    case DIV:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TIMES:{
        op = jj_consume_token(TIMES);
        break;
        }
      case DIV:{
        op = jj_consume_token(DIV);
        break;
        }
      case MOD:{
        op = jj_consume_token(MOD);
        break;
        }
      case POW:{
        op = jj_consume_token(POW);
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Term();
if (op.kind == TIMES)
           t1 = new ASTTimes(t1,t2);
       else if (op.kind == DIV)
           t1 = new ASTDiv(t1,t2);
       else if (op.kind == MOD)
           t1 = new ASTMod(t1,t2);
       else if (op.kind == POW)
           t1 = new ASTPow(t1,t2);
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      ;
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode IfElse() throws ParseException {ASTNode c, b, e;
    jj_consume_token(IF);
    c = BoolAdd();
    jj_consume_token(LBRACKET);
    b = Seq();
    jj_consume_token(RBRACKET);
    jj_consume_token(ELSE);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case IF:{
      e = IfElse();
      break;
      }
    case LBRACKET:{
      jj_consume_token(LBRACKET);
      e = Seq();
      break;
      }
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return new ASTIfElse(c, b, e);}
    throw new Error("Missing return statement in function");
}

  static final public Bind<String, ASTNode> Let() throws ParseException {Token n, mut, type;
    ASTNode t;
    Bind<String, ASTNode> b;
mut = new Token(); type = new Token();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case MUT:{
      mut = jj_consume_token(MUT);
      break;
      }
    default:
      jj_la1[11] = jj_gen;
      ;
    }
    n = jj_consume_token(Id);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case DDOT:{
      jj_consume_token(DDOT);
      type = TypeBuild();
      break;
      }
    default:
      jj_la1[12] = jj_gen;
      ;
    }
    jj_consume_token(EQ);
    t = BoolAdd();
if (mut.image != null && !mut.image.trim().equals("")){
            t = new ASTNew(t);
        }
        if (type.image != null && !type.image.trim().equals("")){
            b = new Bind<>(n.image, type.image, t);
        } else {
            b = new Bind<>(n.image, t);
        }

        {if ("" != null) return b;}
    throw new Error("Missing return statement in function");
}

  static final public Bind<String, ASTNode> DefFunc() throws ParseException {Token n, mut, type;
    ASTNode t;
    Bind<String, ASTNode> b;
    jj_consume_token(FUN);
    n = jj_consume_token(Id);
    t = Function(n);
b = new Bind<>(n.image, t);
{if ("" != null) return b;}
    throw new Error("Missing return statement in function");
}

  static final public Bind<String, ASTNode> DefLet() throws ParseException {Bind<String, ASTNode> b;
    jj_consume_token(LET);
    b = Let();
{if ("" != null) return b;}
    throw new Error("Missing return statement in function");
}

  static final public List<Bind<String, ASTNode>> DefVar() throws ParseException {Token n;
    List<Bind<String, ASTNode>> l = new ArrayList<>();
    Bind<String, ASTNode> b;
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LET:
      case FUN:{
        ;
        break;
        }
      default:
        jj_la1[13] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LET:{
        b = DefLet();
        break;
        }
      case FUN:{
        b = DefFunc();
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
l.add(b);
      jj_consume_token(SEMCOL);
    }
{if ("" != null) return l;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Array() throws ParseException {Token n;
    ASTNode t;
List<ASTNode> l = new ArrayList<>();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT:
    case BOOL:
    case STRUCT:
    case MINUS:
    case QMARK:
    case LPAR:
    case LBRACKET:
    case LSBRACKET:
    case IF:
    case MATCH:
    case WHILE:
    case FOR:
    case NOT:
    case NEW:
    case LAMBDA:
    case LEN:
    case PRINT:
    case PRINTLN:
    case PRINTF:
    case EXIT:
    case STR:
    case Id:{
      t = BoolAdd();
l.add(t);
      label_6:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[15] = jj_gen;
          break label_6;
        }
        jj_consume_token(COMMA);
        t = BoolAdd();
l.add(t);
      }
      break;
      }
    default:
      jj_la1[16] = jj_gen;
      ;
    }
t = new ASTArray(l);
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Struct() throws ParseException {Bind<String, ASTNode> b;
    Token n;
    ASTNode t;
Map<String, ASTNode> m = new HashMap<>();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case MUT:
    case Id:{
      b = Let();
      break;
      }
    case FUN:{
      b = DefFunc();
      break;
      }
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
m.put(b.getId(), b.getValue());
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case SEMCOL:{
        ;
        break;
        }
      default:
        jj_la1[18] = jj_gen;
        break label_7;
      }
      jj_consume_token(SEMCOL);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MUT:
      case Id:{
        b = Let();
        break;
        }
      case FUN:{
        b = DefFunc();
        break;
        }
      default:
        jj_la1[19] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
m.put(b.getId(), b.getValue());
    }
t = new ASTStruct(m);
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
}

  static final public Token TypeBuild() throws ParseException {Token type, in = new Token(), rt = new Token();
in.image=""; rt.image="";
    type = jj_consume_token(Id);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LPAR:{
      in = jj_consume_token(LPAR);
Token n = new Token();
      n = TypeBuild();
in.image += n.image;
      label_8:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[20] = jj_gen;
          break label_8;
        }
Token n2 = new Token();
        jj_consume_token(COMMA);
        n2 = TypeBuild();
in.image += "," + n2.image;
      }
in.image += ")";
      jj_consume_token(RPAR);
      jj_consume_token(ARROWRIGHT);
      rt = TypeBuild();
in.image += ":" + rt.image;
      break;
      }
    default:
      jj_la1[21] = jj_gen;
      ;
    }
type.image += in.image;
        {if ("" != null) return type;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Function(Token id) throws ParseException {ASTNode t;
List<Pair<String,String>> p = new ArrayList<>();
        Token returnType = new Token();
        returnType.image = "";
    jj_consume_token(LPAR);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Id:{
Token type = new Token(), node; type.image = "";
      node = jj_consume_token(Id);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case DDOT:{
        jj_consume_token(DDOT);
        type = TypeBuild();
        break;
        }
      default:
        jj_la1[22] = jj_gen;
        ;
      }
p.add(new Pair<>(node.image, type.image));
      label_9:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[23] = jj_gen;
          break label_9;
        }
        jj_consume_token(COMMA);
        node = jj_consume_token(Id);
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case DDOT:{
          jj_consume_token(DDOT);
          type = TypeBuild();
          break;
          }
        default:
          jj_la1[24] = jj_gen;
          ;
        }
p.add(new Pair<>(node.image, type.image));
      }
      break;
      }
    default:
      jj_la1[25] = jj_gen;
      ;
    }
    jj_consume_token(RPAR);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ARROWRIGHT:{
      jj_consume_token(ARROWRIGHT);
      returnType = TypeBuild();
      break;
      }
    default:
      jj_la1[26] = jj_gen;
      ;
    }
    jj_consume_token(LBRACKET);
    t = Seq();
    jj_consume_token(RBRACKET);
//System.out.println(id.image);
        {if ("" != null) return new ASTFunction(id.image, p, t, returnType.image);}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Fact() throws ParseException {Token n = new Token();
    ASTNode t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case QMARK:{
      jj_consume_token(QMARK);
      t = BoolAdd();
t = new ASTRef(t);
      break;
      }
    case INT:{
      n = jj_consume_token(INT);
t = new ASTInt(Integer.parseInt(n.image));
      break;
      }
    case BOOL:{
      n = jj_consume_token(BOOL);
t = new ASTBool(Boolean.parseBoolean(n.image));
      break;
      }
    case STR:{
      n = jj_consume_token(STR);
t = new ASTStr(n.image);
      break;
      }
    case LAMBDA:{
      jj_consume_token(LAMBDA);
      t = Function(n);
      break;
      }
    case LSBRACKET:{
      jj_consume_token(LSBRACKET);
      t = Array();
      jj_consume_token(RSBRACKET);
      break;
      }
    case LEN:{
      jj_consume_token(LEN);
      n = jj_consume_token(Id);
t = new ASTArrayLen(n.image);
      break;
      }
    case Id:{
      n = jj_consume_token(Id);
t = new ASTId(n.image); Token n2;
      label_10:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case LPAR:
        case LSBRACKET:
        case DOT:{
          ;
          break;
          }
        default:
          jj_la1[27] = jj_gen;
          break label_10;
        }
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case DOT:{
          jj_consume_token(DOT);
          n2 = jj_consume_token(Id);
t = new ASTStructField(t, n2.image);
          break;
          }
        case LPAR:{
List<ASTNode> a = new ArrayList<>();
          jj_consume_token(LPAR);
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case INT:
          case BOOL:
          case STRUCT:
          case MINUS:
          case QMARK:
          case LPAR:
          case LBRACKET:
          case LSBRACKET:
          case IF:
          case MATCH:
          case WHILE:
          case FOR:
          case NOT:
          case NEW:
          case LAMBDA:
          case LEN:
          case PRINT:
          case PRINTLN:
          case PRINTF:
          case EXIT:
          case STR:
          case Id:{
ASTNode aux;
            aux = BoolAdd();
a.add(aux);
            label_11:
            while (true) {
              switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
              case COMMA:{
                ;
                break;
                }
              default:
                jj_la1[28] = jj_gen;
                break label_11;
              }
              jj_consume_token(COMMA);
              aux = BoolAdd();
a.add(aux);
            }
            break;
            }
          default:
            jj_la1[29] = jj_gen;
            ;
          }
          jj_consume_token(RPAR);
t = new ASTApplyFunc(t, a);
          break;
          }
        case LSBRACKET:{
ASTNode t2;
          jj_consume_token(LSBRACKET);
          t2 = BoolAdd();
          jj_consume_token(RSBRACKET);
t = new ASTArrayIndex(t, t2);
          break;
          }
        default:
          jj_la1[30] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      break;
      }
    case MINUS:{
      jj_consume_token(MINUS);
      t = Fact();
t = new ASTNeg(t);
      break;
      }
    case LPAR:{
      jj_consume_token(LPAR);
      t = BoolAdd();
      jj_consume_token(RPAR);
      break;
      }
    case NOT:{
      jj_consume_token(NOT);
      t = Fact();
t = new ASTNot(t);
      break;
      }
    case LBRACKET:{
      jj_consume_token(LBRACKET);
List<Bind<String, ASTNode>> l;
      l = DefVar();
      t = Seq();
t = new ASTDef(l, t);
      jj_consume_token(RBRACKET);
      break;
      }
    case STRUCT:{
      jj_consume_token(STRUCT);
      jj_consume_token(LBRACKET);
      t = Struct();
      jj_consume_token(RBRACKET);
      break;
      }
    case NEW:{
      jj_consume_token(NEW);
      jj_consume_token(LPAR);
      t = BoolAdd();
      jj_consume_token(RPAR);
t = new ASTNew(t);
      break;
      }
    case IF:{
      t = IfElse();
      jj_consume_token(RBRACKET);
      break;
      }
    case MATCH:{
//Token v;
        ASTNode v, cond, c, d;
        Map<List<ASTNode>, ASTNode> cases = new HashMap<>();
      jj_consume_token(MATCH);
      cond = BoolAdd();
      jj_consume_token(LBRACKET);
      label_12:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case INT:
        case BOOL:
        case STRUCT:
        case MINUS:
        case QMARK:
        case LPAR:
        case LBRACKET:
        case LSBRACKET:
        case IF:
        case MATCH:
        case WHILE:
        case FOR:
        case NOT:
        case NEW:
        case LAMBDA:
        case LEN:
        case PRINT:
        case PRINTLN:
        case PRINTF:
        case EXIT:
        case STR:
        case Id:{
          ;
          break;
          }
        default:
          jj_la1[31] = jj_gen;
          break label_12;
        }
List<ASTNode> p = new ArrayList<>();
        v = BoolAdd();
p.add(v);
        label_13:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case VB:{
            ;
            break;
            }
          default:
            jj_la1[32] = jj_gen;
            break label_13;
          }
          jj_consume_token(VB);
          v = BoolAdd();
p.add(v);
        }
        jj_consume_token(EQ);
        c = Seq();
cases.put(p, c);
        jj_consume_token(COMMA);
      }
      jj_consume_token(DEFAULTCASE);
      jj_consume_token(DDOT);
      d = Seq();
      jj_consume_token(RBRACKET);
t = new ASTMatch(cond, cases, d);
      break;
      }
    case WHILE:{
ASTNode b;
      jj_consume_token(WHILE);
      t = BoolAdd();
      jj_consume_token(LBRACKET);
      b = Seq();
      jj_consume_token(RBRACKET);
t = new ASTWhile(t, b);
      break;
      }
    case FOR:{
Bind<String, ASTNode> init; ASTNode b;
      jj_consume_token(FOR);
      init = DefLet();
      jj_consume_token(TO);
      t = BoolAdd();
      jj_consume_token(LBRACKET);
      b = Seq();
      jj_consume_token(RBRACKET);
t = new ASTFor(init, t, b);
      break;
      }
    case PRINT:{
List<ASTNode> l = new ArrayList<>();
      jj_consume_token(PRINT);
      jj_consume_token(LPAR);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INT:
      case BOOL:
      case STRUCT:
      case MINUS:
      case QMARK:
      case LPAR:
      case LBRACKET:
      case LSBRACKET:
      case IF:
      case MATCH:
      case WHILE:
      case FOR:
      case NOT:
      case NEW:
      case LAMBDA:
      case LEN:
      case PRINT:
      case PRINTLN:
      case PRINTF:
      case EXIT:
      case STR:
      case Id:{
        t = BoolAdd();
l.add(t);
        label_14:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case COMMA:{
            ;
            break;
            }
          default:
            jj_la1[33] = jj_gen;
            break label_14;
          }
          jj_consume_token(COMMA);
          t = BoolAdd();
l.add(t);
        }
        break;
        }
      default:
        jj_la1[34] = jj_gen;
        ;
      }
      jj_consume_token(RPAR);
t = new ASTPrint(l);
      break;
      }
    case PRINTF:{
List<ASTNode> l = new ArrayList<>(); ASTNode str;
      jj_consume_token(PRINTF);
      jj_consume_token(LPAR);
      str = BoolAdd();
      label_15:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[35] = jj_gen;
          break label_15;
        }
        jj_consume_token(COMMA);
        t = BoolAdd();
l.add(t);
      }
      jj_consume_token(RPAR);
t = new ASTPrintf(str, l);
      break;
      }
    case PRINTLN:{
List<ASTNode> l = new ArrayList<>();
      jj_consume_token(PRINTLN);
      jj_consume_token(LPAR);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INT:
      case BOOL:
      case STRUCT:
      case MINUS:
      case QMARK:
      case LPAR:
      case LBRACKET:
      case LSBRACKET:
      case IF:
      case MATCH:
      case WHILE:
      case FOR:
      case NOT:
      case NEW:
      case LAMBDA:
      case LEN:
      case PRINT:
      case PRINTLN:
      case PRINTF:
      case EXIT:
      case STR:
      case Id:{
        t = BoolAdd();
l.add(t);
        label_16:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case COMMA:{
            ;
            break;
            }
          default:
            jj_la1[36] = jj_gen;
            break label_16;
          }
          jj_consume_token(COMMA);
          t = BoolAdd();
l.add(t);
        }
        break;
        }
      default:
        jj_la1[37] = jj_gen;
        ;
      }
      jj_consume_token(RPAR);
t = new ASTPrintln(l);
      break;
      }
    case EXIT:{
      jj_consume_token(EXIT);
t = new ASTExit();
      break;
      }
    default:
      jj_la1[38] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
}

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[39];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static private int[] jj_la1_2;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	   jj_la1_init_2();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x0,0x20000,0x0,0x0,0x0,0x0,0x300000,0x300000,0x3c00000,0x3c00000,0x20000000,0x0,0x0,0x0,0x0,0x0,0xac214800,0x0,0x0,0x0,0x0,0x8000000,0x0,0x0,0x0,0x0,0x20000,0x88000000,0x0,0xac214800,0x88000000,0xac214800,0x0,0x0,0xac214800,0x0,0x0,0xac214800,0xac214800,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x20,0x0,0x4000000,0x2000000,0xf80010,0xf80010,0x0,0x0,0x0,0x0,0x100,0x4,0x20000,0x40000002,0x40000002,0x40,0x11006900,0x40000004,0x20,0x40000004,0x40,0x0,0x20000,0x40,0x20000,0x0,0x0,0x80,0x40,0x11006900,0x80,0x11006900,0x1000,0x40,0x11006900,0x40,0x40,0x11006900,0x11006900,};
	}
	private static void jj_la1_init_2() {
	   jj_la1_2 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0xff,0x80,0x0,0x80,0x0,0x0,0x0,0x0,0x0,0x80,0x0,0x0,0x0,0xff,0x0,0xff,0x0,0x0,0xff,0x0,0x0,0xff,0xff,};
	}

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser.  ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 39; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 39; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 39; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new ParserTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 39; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 39; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 39; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  static private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[72];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 39; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		   if ((jj_la1_2[i] & (1<<j)) != 0) {
			 la1tokens[64+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 72; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  static private boolean trace_enabled;

/** Trace enabled. */
  static final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

                     }
