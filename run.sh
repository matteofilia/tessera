#!/bin/bash

echo "Valid arguments: --lex --parse --codegen"

echo "Script Location: $0"
DIR="$(dirname $0)"

cd "$DIR/Tessera Java Interpreter"
java -jar "build/jar/main.jar" "$@"
