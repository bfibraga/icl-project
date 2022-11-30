#!/usr/bin/env bash

cd src/parser
javacc Parser.jj
cd ../..
javac -d ./bin src/*.java
