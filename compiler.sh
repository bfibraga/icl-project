#!/usr/bin/env bash
filename=$1

echo "Executing Compiler..."

rm ./src/jvm/result/*.j
java -cp ./bin src.Compiler $filename
java -jar ../jasmin/jasmin.jar -d bin/src/jvm/result -g src/jvm/result/*.j
java -cp ./bin/src/jvm/result Header -g
