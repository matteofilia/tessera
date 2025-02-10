#!/bin/bash

# set -o xtrace

echo "Valid arguments: --lex --parse --codegen"

for i in $@;
do
	if [[ $i == "--lex" ]]; then
		echo "Only running lexer"
	elif [[ $i == "--parse" ]]; then
		echo "Only running lexer and parser"
	elif [[ $i == "--codegen" ]]; then
		echo "Only running lexer, parser, and assembler"
	fi
done

echo "Compiling Java using Ant..."
# ant TODO

echo "Starting program..."

java -jar main.jar "$@"
