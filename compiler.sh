#!/usr/bin/env bash
filename=$1
java -cp ./bin src.Compiler $filename
java -jar ../jasmin/jasmin.jar -d bin/src/jvm/result src/jvm/result/*.j
java -cp ./bin/src/jvm/result Header