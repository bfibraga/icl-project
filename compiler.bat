@echo off
echo Executing Compiler...

del .\src\jvm\result\*.j
java -cp ./bin src.Compiler %1
java -jar ../jasmin/jasmin.jar -d bin/src/jvm/result -g src/jvm/result/*.j
java -cp ./bin/src/jvm/result Header -g