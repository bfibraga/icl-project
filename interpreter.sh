#!/usr/bin/env bash
filename=$1

echo "Executing Interpreter..."

java -cp ./bin src.Interpreter $filename