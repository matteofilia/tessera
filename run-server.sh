#!/bin/bash

echo "Script Location: $0"
DIR="$(dirname $0)"

export TESSERA_WEB_HOST="http://localhost:8000"

cd "$DIR"
cp "Tessera Java Compiler/build/jar/main.jar" "Tessera Server/src/main/resources/main.jar"
cd "Tessera Server"
./mvnw spring-boot:run
