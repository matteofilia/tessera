#!/bin/bash

set -o xtrace
echo "Starting program..."
echo "Valid arguments: --lex --parse --codegen"

java -jar main.jar "$@"
