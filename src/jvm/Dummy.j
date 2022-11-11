.class public Header
.super java/lang/Object

;
; standard initializer
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V

       ; set limits used by this method
       .limit locals 10
       .limit stack 256

       ; setup local variables:

       ;    1 - the PrintStream object held in java.lang.System.out

       getstatic java/lang/System/out Ljava/io/PrintStream;

       ; place bytecodes here

       ; initialize SL variable to null value
       aconst_null
       astore_3

       ; START


       ; END 


       ; convert to String;
       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;

       ; call println 
       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

       return

.end method






