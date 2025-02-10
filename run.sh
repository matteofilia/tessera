#!/bin/bash

echo "Valid arguments: --lex --parse --codegen"

cd "Tessera Java Compiler"
java -jar ./build/jar/main.jar "$@"
