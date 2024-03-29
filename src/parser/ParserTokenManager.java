/* ParserTokenManager.java */
/* Generated By:JavaCC: Do not edit this line. ParserTokenManager.java */
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

/** Token Manager. */
@SuppressWarnings ("unused")
public class ParserTokenManager implements ParserConstants {

  /** Debug output. */
  public static  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public static  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private static final int jjStopStringLiteralDfa_0(int pos, long active0, long active1){
   switch (pos)
   {
      case 0:
         if ((active0 & 0x80000L) != 0L)
            return 27;
         if ((active0 & 0xbe00af0600018000L) != 0L || (active1 & 0x3dL) != 0L)
         {
            jjmatchedKind = 71;
            return 14;
         }
         if ((active0 & 0x4000400000000000L) != 0L)
         {
            jjmatchedKind = 71;
            return 6;
         }
         if ((active0 & 0x8000000000L) != 0L)
            return 28;
         if ((active0 & 0x1000000000000L) != 0L)
         {
            jjmatchedKind = 71;
            return 2;
         }
         return -1;
      case 1:
         if ((active0 & 0x4401810000000000L) != 0L)
            return 14;
         if ((active0 & 0xba006e0600018000L) != 0L || (active1 & 0x3dL) != 0L)
         {
            jjmatchedKind = 71;
            jjmatchedPos = 1;
            return 14;
         }
         return -1;
      case 2:
         if ((active0 & 0x3a00400600008000L) != 0L)
            return 14;
         if ((active0 & 0x80002e0000010000L) != 0L || (active1 & 0x3dL) != 0L)
         {
            jjmatchedKind = 71;
            jjmatchedPos = 2;
            return 14;
         }
         return -1;
      case 3:
         if ((active0 & 0x8000060000000000L) != 0L || (active1 & 0x20L) != 0L)
            return 14;
         if ((active0 & 0x280000010000L) != 0L || (active1 & 0x1dL) != 0L)
         {
            jjmatchedKind = 71;
            jjmatchedPos = 3;
            return 14;
         }
         return -1;
      case 4:
         if ((active0 & 0x280000000000L) != 0L || (active1 & 0x1cL) != 0L)
            return 14;
         if ((active0 & 0x10000L) != 0L || (active1 & 0x1L) != 0L)
         {
            if (jjmatchedPos != 4)
            {
               jjmatchedKind = 71;
               jjmatchedPos = 4;
            }
            return 14;
         }
         return -1;
      case 5:
         if ((active0 & 0x10000L) != 0L || (active1 & 0x11L) != 0L)
            return 14;
         if ((active1 & 0x8L) != 0L)
         {
            jjmatchedKind = 71;
            jjmatchedPos = 5;
            return 14;
         }
         return -1;
      default :
         return -1;
   }
}
private static final int jjStartNfa_0(int pos, long active0, long active1){
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0, active1), pos + 1);
}
static private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
static private int jjMoveStringLiteralDfa0_0(){
   switch(curChar)
   {
      case 33:
         jjmatchedKind = 56;
         return jjMoveStringLiteralDfa1_0(0x80000000000000L, 0x0L);
      case 35:
         return jjStopAtPos(0, 65);
      case 37:
         return jjStopAtPos(0, 23);
      case 39:
         return jjStartNfaWithStates_0(0, 19, 27);
      case 40:
         return jjStopAtPos(0, 27);
      case 41:
         return jjStopAtPos(0, 28);
      case 42:
         return jjStopAtPos(0, 22);
      case 43:
         return jjStopAtPos(0, 20);
      case 44:
         return jjStopAtPos(0, 38);
      case 45:
         jjmatchedKind = 21;
         return jjMoveStringLiteralDfa1_0(0x20000L, 0x0L);
      case 46:
         return jjStartNfaWithStates_0(0, 39, 28);
      case 47:
         jjmatchedKind = 25;
         return jjMoveStringLiteralDfa1_0(0x60L, 0x0L);
      case 58:
         return jjStopAtPos(0, 49);
      case 59:
         return jjStopAtPos(0, 37);
      case 60:
         jjmatchedKind = 53;
         return jjMoveStringLiteralDfa1_0(0x40000000040000L, 0x0L);
      case 61:
         jjmatchedKind = 35;
         return jjMoveStringLiteralDfa1_0(0x1000000000L, 0x0L);
      case 62:
         jjmatchedKind = 51;
         return jjMoveStringLiteralDfa1_0(0x10000000000000L, 0x0L);
      case 63:
         return jjStopAtPos(0, 26);
      case 91:
         return jjStopAtPos(0, 31);
      case 93:
         return jjStopAtPos(0, 32);
      case 94:
         return jjStopAtPos(0, 24);
      case 95:
         return jjStopAtPos(0, 50);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x200000000008000L, 0x0L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x60000000000L, 0x20L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x4000400000000000L, 0x0L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x810000000000L, 0x0L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x200000000L, 0x1L);
      case 109:
         return jjMoveStringLiteralDfa1_0(0x8000080400000000L, 0x0L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x1000000000000000L, 0x0L);
      case 111:
         return jjMoveStringLiteralDfa1_0(0x400000000000000L, 0x0L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x1cL);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x10000L, 0x0L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x1000000000000L, 0x0L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0x2000000000000000L, 0x0L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x200000000000L, 0x0L);
      case 120:
         return jjMoveStringLiteralDfa1_0(0x800000000000000L, 0x0L);
      case 123:
         return jjStopAtPos(0, 29);
      case 124:
         return jjStopAtPos(0, 44);
      case 125:
         return jjStopAtPos(0, 30);
      default :
         return jjMoveNfa_0(3, 0);
   }
}
static private int jjMoveStringLiteralDfa1_0(long active0, long active1){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0, active1);
      return 1;
   }
   switch(curChar)
   {
      case 42:
         if ((active0 & 0x20L) != 0L)
            return jjStopAtPos(1, 5);
         break;
      case 45:
         if ((active0 & 0x40000L) != 0L)
            return jjStopAtPos(1, 18);
         break;
      case 47:
         if ((active0 & 0x40L) != 0L)
            return jjStopAtPos(1, 6);
         break;
      case 61:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStopAtPos(1, 36);
         else if ((active0 & 0x10000000000000L) != 0L)
            return jjStopAtPos(1, 52);
         else if ((active0 & 0x40000000000000L) != 0L)
            return jjStopAtPos(1, 54);
         else if ((active0 & 0x80000000000000L) != 0L)
            return jjStopAtPos(1, 55);
         break;
      case 62:
         if ((active0 & 0x20000L) != 0L)
            return jjStopAtPos(1, 17);
         break;
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0xa000080000000000L, active1, 0x1L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000000200000000L, active1, 0L);
      case 102:
         if ((active0 & 0x10000000000L) != 0L)
            return jjStartNfaWithStates_0(1, 40, 14);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x200000000000L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x60000000000L, active1, 0L);
      case 110:
         if ((active0 & 0x800000000000L) != 0L)
            return jjStartNfaWithStates_0(1, 47, 14);
         else if ((active0 & 0x4000000000000000L) != 0L)
            return jjStartNfaWithStates_0(1, 62, 14);
         return jjMoveStringLiteralDfa2_0(active0, 0x200000000000000L, active1, 0L);
      case 111:
         if ((active0 & 0x1000000000000L) != 0L)
            return jjStartNfaWithStates_0(1, 48, 14);
         return jjMoveStringLiteralDfa2_0(active0, 0x800400000000000L, active1, 0L);
      case 114:
         if ((active0 & 0x400000000000000L) != 0L)
            return jjStartNfaWithStates_0(1, 58, 14);
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L, active1, 0x1cL);
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x400000000L, active1, 0L);
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x20L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0, active1);
}
static private int jjMoveStringLiteralDfa2_0(long old0, long active0, long old1, long active1){
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(0, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0, active1);
      return 2;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x200000000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 57, 14);
         break;
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000220000000000L, active1, 0x3cL);
      case 108:
         if ((active0 & 0x2000000000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 61, 14);
         break;
      case 109:
         return jjMoveStringLiteralDfa3_0(active0, 0L, active1, 0x1L);
      case 114:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(2, 15, 14);
         else if ((active0 & 0x400000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 46, 14);
         else if ((active0 & 0x800000000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 59, 14);
         return jjMoveStringLiteralDfa3_0(active0, 0x10000L, active1, 0L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000000000L, active1, 0L);
      case 116:
         if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(2, 33, 14);
         else if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_0(2, 34, 14);
         return jjMoveStringLiteralDfa3_0(active0, 0x80000000000L, active1, 0L);
      case 119:
         if ((active0 & 0x1000000000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 60, 14);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0, active1);
}
static private int jjMoveStringLiteralDfa3_0(long old0, long active0, long old1, long active1){
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(1, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0, active1);
      return 3;
   }
   switch(curChar)
   {
      case 98:
         return jjMoveStringLiteralDfa4_0(active0, 0L, active1, 0x1L);
      case 99:
         return jjMoveStringLiteralDfa4_0(active0, 0x80000000000L, active1, 0L);
      case 101:
         if ((active0 & 0x40000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 42, 14);
         break;
      case 102:
         if ((active0 & 0x20000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 41, 14);
         break;
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000000000L, active1, 0L);
      case 110:
         if ((active0 & 0x8000000000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 63, 14);
         return jjMoveStringLiteralDfa4_0(active0, 0L, active1, 0x1cL);
      case 116:
         if ((active1 & 0x20L) != 0L)
            return jjStartNfaWithStates_0(3, 69, 14);
         break;
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x10000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0, active1);
}
static private int jjMoveStringLiteralDfa4_0(long old0, long active0, long old1, long active1){
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(2, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0, active1);
      return 4;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa5_0(active0, 0x10000L, active1, 0L);
      case 100:
         return jjMoveStringLiteralDfa5_0(active0, 0L, active1, 0x1L);
      case 101:
         if ((active0 & 0x200000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 45, 14);
         break;
      case 104:
         if ((active0 & 0x80000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 43, 14);
         break;
      case 116:
         if ((active1 & 0x4L) != 0L)
         {
            jjmatchedKind = 66;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0L, active1, 0x18L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0, active1);
}
static private int jjMoveStringLiteralDfa5_0(long old0, long active0, long old1, long active1){
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(3, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0, active1);
      return 5;
   }
   switch(curChar)
   {
      case 97:
         if ((active1 & 0x1L) != 0L)
            return jjStartNfaWithStates_0(5, 64, 14);
         break;
      case 102:
         if ((active1 & 0x10L) != 0L)
            return jjStartNfaWithStates_0(5, 68, 14);
         break;
      case 108:
         return jjMoveStringLiteralDfa6_0(active0, 0L, active1, 0x8L);
      case 116:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(5, 16, 14);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0, active1);
}
static private int jjMoveStringLiteralDfa6_0(long old0, long active0, long old1, long active1){
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(4, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, 0L, active1);
      return 6;
   }
   switch(curChar)
   {
      case 110:
         if ((active1 & 0x8L) != 0L)
            return jjStartNfaWithStates_0(6, 67, 14);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, 0L, active1);
}
static private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 27;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 11)
                        kind = 11;
                     { jjCheckNAddStates(0, 5); }
                  }
                  else if (curChar == 46)
                     { jjCheckNAddTwoStates(25, 26); }
                  else if (curChar == 39)
                     { jjCheckNAddStates(6, 8); }
                  break;
               case 27:
                  if (curChar == 39)
                  {
                     if (kind > 70)
                        kind = 70;
                  }
                  else if (curChar == 32)
                     { jjCheckNAddTwoStates(9, 10); }
                  break;
               case 28:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 13)
                        kind = 13;
                     { jjCheckNAdd(26); }
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 12)
                        kind = 12;
                     { jjCheckNAdd(25); }
                  }
                  break;
               case 6:
               case 14:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 71)
                     kind = 71;
                  { jjCheckNAdd(14); }
                  break;
               case 2:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 71)
                     kind = 71;
                  { jjCheckNAdd(14); }
                  break;
               case 8:
                  if (curChar == 39)
                     { jjCheckNAddStates(6, 8); }
                  break;
               case 9:
                  if (curChar == 32)
                     { jjCheckNAddTwoStates(9, 10); }
                  break;
               case 11:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjCheckNAddStates(9, 12); }
                  break;
               case 12:
                  if (curChar == 39 && kind > 70)
                     kind = 70;
                  break;
               case 15:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 11)
                     kind = 11;
                  { jjCheckNAddStates(0, 5); }
                  break;
               case 16:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 11)
                     kind = 11;
                  { jjCheckNAdd(16); }
                  break;
               case 17:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjCheckNAddTwoStates(17, 18); }
                  break;
               case 18:
                  if (curChar != 46)
                     break;
                  if (kind > 12)
                     kind = 12;
                  { jjCheckNAdd(19); }
                  break;
               case 19:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 12)
                     kind = 12;
                  { jjCheckNAdd(19); }
                  break;
               case 20:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 13)
                     kind = 13;
                  { jjCheckNAdd(20); }
                  break;
               case 21:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjCheckNAddTwoStates(21, 22); }
                  break;
               case 22:
                  if (curChar != 46)
                     break;
                  if (kind > 13)
                     kind = 13;
                  { jjCheckNAdd(23); }
                  break;
               case 23:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 13)
                     kind = 13;
                  { jjCheckNAdd(23); }
                  break;
               case 24:
                  if (curChar == 46)
                     { jjCheckNAddTwoStates(25, 26); }
                  break;
               case 25:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 12)
                     kind = 12;
                  { jjCheckNAdd(25); }
                  break;
               case 26:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 13)
                     kind = 13;
                  { jjCheckNAdd(26); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 71)
                        kind = 71;
                     { jjCheckNAdd(14); }
                  }
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 6;
                  else if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 27:
               case 10:
               case 11:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                     { jjCheckNAddStates(9, 12); }
                  break;
               case 6:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 71)
                        kind = 71;
                     { jjCheckNAdd(14); }
                  }
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 2:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 71)
                        kind = 71;
                     { jjCheckNAdd(14); }
                  }
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 0:
                  if (curChar == 101 && kind > 14)
                     kind = 14;
                  break;
               case 1:
                  if (curChar == 117)
                     { jjCheckNAdd(0); }
                  break;
               case 4:
                  if (curChar == 115)
                     { jjCheckNAdd(0); }
                  break;
               case 5:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 7:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 13:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 71)
                     kind = 71;
                  { jjCheckNAdd(14); }
                  break;
               case 14:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 71)
                     kind = 71;
                  { jjCheckNAdd(14); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 27 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static private int jjMoveStringLiteralDfa0_2(){
   switch(curChar)
   {
      case 10:
         return jjStopAtPos(0, 9);
      default :
         return 1;
   }
}
static private int jjMoveStringLiteralDfa0_1(){
   switch(curChar)
   {
      case 42:
         return jjMoveStringLiteralDfa1_1(0x80L);
      default :
         return 1;
   }
}
static private int jjMoveStringLiteralDfa1_1(long active0){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 47:
         if ((active0 & 0x80L) != 0L)
            return jjStopAtPos(1, 7);
         break;
      default :
         return 2;
   }
   return 2;
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, "\141\162\162", "\163\164\162\165\143\164", "\55\76", "\74\55", "\47", 
"\53", "\55", "\52", "\45", "\136", "\57", "\77", "\50", "\51", "\173", "\175", 
"\133", "\135", "\154\145\164", "\155\165\164", "\75", "\75\75", "\73", "\54", "\56", 
"\151\146", "\145\154\151\146", "\145\154\163\145", "\155\141\164\143\150", "\174", 
"\167\150\151\154\145", "\146\157\162", "\151\156", "\164\157", "\72", "\137", "\76", "\76\75", "\74", 
"\74\75", "\41\75", "\41", "\141\156\144", "\157\162", "\170\157\162", "\156\145\167", 
"\166\141\154", "\146\156", "\155\141\151\156", "\154\141\155\142\144\141", "\43", 
"\160\162\151\156\164", "\160\162\151\156\164\154\156", "\160\162\151\156\164\146", 
"\145\170\151\164", null, null, };
static protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}
static final int[] jjnextStates = {
   16, 17, 18, 20, 21, 22, 9, 10, 12, 9, 10, 11, 12, 
};

static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static int jjmatchedPos;
static int jjmatchedKind;

/** Get the next Token. */
public static Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(Exception e)
   {
      jjmatchedKind = 0;
      jjmatchedPos = -1;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   for (;;)
   {
     switch(curLexState)
     {
       case 0:
         try { input_stream.backup(0);
            while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
               curChar = input_stream.BeginToken();
         }
         catch (java.io.IOException e1) { continue EOFLoop; }
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_0();
         break;
       case 1:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_1();
         if (jjmatchedPos == 0 && jjmatchedKind > 8)
         {
            jjmatchedKind = 8;
         }
         break;
       case 2:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_2();
         if (jjmatchedPos == 0 && jjmatchedKind > 10)
         {
            jjmatchedKind = 10;
         }
         break;
     }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else if ((jjtoSkip[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
      if (jjnewLexState[jjmatchedKind] != -1)
        curLexState = jjnewLexState[jjmatchedKind];
        curPos = 0;
        jjmatchedKind = 0x7fffffff;
        try {
           curChar = input_stream.readChar();
           continue;
        }
        catch (java.io.IOException e1) { }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
   }
  }
}

static void SkipLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
static void MoreLexicalActions()
{
   jjimageLen += (lengthOfMatch = jjmatchedPos + 1);
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
static void TokenLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
static private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
static private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
static private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

static private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

    /** Constructor. */
    public ParserTokenManager(SimpleCharStream stream){

      if (input_stream != null)
        throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);

    input_stream = stream;
  }

  /** Constructor. */
  public ParserTokenManager (SimpleCharStream stream, int lexState){
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Reinitialise parser. */
  
  static public void ReInit(SimpleCharStream stream)
  {


    jjmatchedPos =
    jjnewStateCnt =
    0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }

  static private void ReInitRounds()
  {
    int i;
    jjround = 0x80000001;
    for (i = 27; i-- > 0;)
      jjrounds[i] = 0x80000000;
  }

  /** Reinitialise parser. */
  static public void ReInit(SimpleCharStream stream, int lexState)
  
  {
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Switch to specified lex state. */
  public static void SwitchTo(int lexState)
  {
    if (lexState >= 3 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
    else
      curLexState = lexState;
  }


/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "WithinCommentBlock",
   "WithinComment",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, 1, 2, 0, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0xfffffffffffff801L, 0xffL, 
};
static final long[] jjtoSkip = {
   0x2feL, 0x0L, 
};
static final long[] jjtoSpecial = {
   0x0L, 0x0L, 
};
static final long[] jjtoMore = {
   0x500L, 0x0L, 
};
    static protected SimpleCharStream  input_stream;

    static private final int[] jjrounds = new int[27];
    static private final int[] jjstateSet = new int[2 * 27];
    private static final StringBuilder jjimage = new StringBuilder();
    private static StringBuilder image = jjimage;
    private static int jjimageLen;
    private static int lengthOfMatch;
    static protected int curChar;
}
