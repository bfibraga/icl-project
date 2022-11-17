/* Parser.java */
/* Generated By:JavaCC: Do not edit this line. Parser.java */
/** ID lister. */
package src.parser;

import src.astnodes.*;
import src.astnodes.binding.*;
import src.astnodes.control.*;
import src.astnodes.functions.io.out.*;
import src.astnodes.operations.arithmetic.*;
import src.astnodes.operations.relational.*;
import src.astnodes.value.primitives.*;
import src.astnodes.value.record.*;
import src.misc.*;
import src.value.*;
import java.util.*;

public class Parser implements ParserConstants {

  static final public ASTNode Start() throws ParseException {ASTNode t;
    t = Seq();
    jj_consume_token(SEMCOL);
    jj_consume_token(SEMCOL);
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Seq() throws ParseException {Token n;
    ASTNode t1, t2;
    t1 = SeqE();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMMA:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(COMMA);
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
      case SETVAL:{
        ;
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(SETVAL);
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
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AND:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_4;
      }
      jj_consume_token(AND);
      t2 = RelOps();
t1 = new ASTAnd(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode RelOps() throws ParseException {Token op;
    ASTNode t1, t2;
    t1 = Exp();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EQ:
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
      case EQ:{
        op = jj_consume_token(EQ);
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
        else if (op.kind == EQ)
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
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:
      case MINUS:{
        ;
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        break label_5;
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

  static final public ASTNode Fact() throws ParseException {Token n;
    ASTNode t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT:{
      n = jj_consume_token(INT);
t = new ASTNum(Integer.parseInt(n.image));
      break;
      }
    case BOOL:{
      n = jj_consume_token(BOOL);
t = new ASTBool(Boolean.parseBoolean(n.image));
      break;
      }
    case QUOTE:{
StringBuilder result = new StringBuilder();
      jj_consume_token(QUOTE);
      label_6:
      while (true) {
        n = jj_consume_token(Id);
result.append(n.image).append(" ");
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case Id:{
          ;
          break;
          }
        default:
          jj_la1[10] = jj_gen;
          break label_6;
        }
      }
      jj_consume_token(QUOTE);
result.deleteCharAt(result.length()-1);
    t = new ASTStr(result.toString());
      break;
      }
    case LSBRACKET:{
      jj_consume_token(LSBRACKET);
Token mut = new Token();
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
      jj_consume_token(DDOT);
      t = BoolAdd();
Map<String, ASTNode> m = new HashMap<>();
                if (mut.image != null && !mut.image.trim().equals("")){
                    t = new ASTNew(t);
                }
                m.put(n.image, t);
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[12] = jj_gen;
          break label_7;
        }
mut = new Token();
        jj_consume_token(COMMA);
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case MUT:{
          mut = jj_consume_token(MUT);
          break;
          }
        default:
          jj_la1[13] = jj_gen;
          ;
        }
        n = jj_consume_token(Id);
        jj_consume_token(DDOT);
        t = BoolAdd();
if (mut.image != null && !mut.image.trim().equals("")){
                    t = new ASTNew(t);
                }
                 m.put(n.image, t);
      }
t = new ASTRecord(m);
      jj_consume_token(RSBRACKET);
{if ("" != null) return t;}
      break;
      }
    case Id:{
      n = jj_consume_token(Id);
t = new ASTId(n.image);
      label_8:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case DOT:{
          ;
          break;
          }
        default:
          jj_la1[14] = jj_gen;
          break label_8;
        }
Token n2;
        jj_consume_token(DOT);
        n2 = jj_consume_token(Id);
t = new ASTRecordField(t, n.image, n2.image);
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
List<Bind<String, ASTNode>> l = new ArrayList<>();
            ASTNode b;
      label_9:
      while (true) {
        jj_consume_token(LET);
Token mut = new Token();
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case MUT:{
          mut = jj_consume_token(MUT);
          break;
          }
        default:
          jj_la1[15] = jj_gen;
          ;
        }
        n = jj_consume_token(Id);
        jj_consume_token(EQ);
        t = BoolAdd();
        jj_consume_token(SEMCOL);
if (mut.image != null && !mut.image.trim().equals("")){
                    t = new ASTNew(t);
                }
                l.add(new Bind<>(n.image, t));
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case LET:{
          ;
          break;
          }
        default:
          jj_la1[16] = jj_gen;
          break label_9;
        }
      }
      b = Seq();
t = new ASTDef(l, b);
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
ASTNode ci, bt, be;
      jj_consume_token(IF);
      ci = BoolAdd();
      jj_consume_token(LBRACKET);
      bt = Seq();
      jj_consume_token(RBRACKET);
      jj_consume_token(ELSE);
      jj_consume_token(LBRACKET);
      be = Seq();
      jj_consume_token(RBRACKET);
t = new ASTIfElse(ci, bt, be);
      break;
      }
    case MATCH:{
Token v;
        ASTNode cond, c, d;
        Map<Value, ASTNode> cases = new HashMap<>();
      jj_consume_token(MATCH);
      cond = BoolAdd();
      jj_consume_token(LBRACKET);
      label_10:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case INT:{
          ;
          break;
          }
        default:
          jj_la1[17] = jj_gen;
          break label_10;
        }
        v = jj_consume_token(INT);
        jj_consume_token(DDOT);
        c = Seq();
cases.put(new Int(Integer.parseInt(v.image)), c);
        jj_consume_token(SEMCOL);
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
    case REF:{
      jj_consume_token(REF);
      jj_consume_token(LPAR);
      t = BoolAdd();
      jj_consume_token(RPAR);
t = new ASTRef(t);
      break;
      }
    case PRINT:{
List<ASTNode> l = new ArrayList<>();
      jj_consume_token(PRINT);
      jj_consume_token(LPAR);
      t = BoolAdd();
l.add(t);
      label_11:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[18] = jj_gen;
          break label_11;
        }
        jj_consume_token(COMMA);
        t = BoolAdd();
l.add(t);
      }
      jj_consume_token(RPAR);
t = new ASTPrint(l);
      break;
      }
    case PRINTLN:{
List<ASTNode> l = new ArrayList<>();
      jj_consume_token(PRINTLN);
      jj_consume_token(LPAR);
      t = BoolAdd();
l.add(t);
      label_12:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[19] = jj_gen;
          break label_12;
        }
        jj_consume_token(COMMA);
        t = BoolAdd();
l.add(t);
      }
      jj_consume_token(RPAR);
t = new ASTPrintln(l);
      break;
      }
    default:
      jj_la1[20] = jj_gen;
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
  static final private int[] jj_la1 = new int[21];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x10000000,0x200,0x0,0x0,0x4000000,0x4000000,0x3000,0x3000,0x3c000,0x3c000,0x0,0x2000000,0x10000000,0x2000000,0x20000000,0x2000000,0x1000000,0x20,0x10000000,0x10000000,0x40542920,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x0,0x0,0x1000,0x800,0x3e0,0x3e0,0x0,0x0,0x0,0x0,0x80000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0xb6406,};
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
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
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
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
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
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
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
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
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
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
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
	 boolean[] la1tokens = new boolean[52];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 21; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 52; i++) {
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
