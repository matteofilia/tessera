#!/bin/bash

echo "Script Location: $0"
DIR="$(dirname $0)"

cd "$DIR"
cp "Tessera Java Compiler/build/jar/main.jar" "$DIR/Tessera Website/app/main.jar"

cd "Tessera Website"
python3 -m http.server
