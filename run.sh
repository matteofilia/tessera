#!/bin/bash

DIR="$(dirname $0)"

cd "$DIR/Tessera Java Interpreter"
java -jar "build/jar/main.jar" "$@"
