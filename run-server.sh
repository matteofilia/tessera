#!/bin/bash

echo "Script Location: $0"
DIR="$(dirname $0)"

export TESSERA_WEB_HOST="http://16.52.132.40"
export TESSERA_WEB_HOST_B="http://www.tessera-lang.org"

cd "$DIR"
cp "Tessera Java Interpreter/build/jar/main.jar" "Tessera Server/src/main/resources/main.jar"
cd "Tessera Server"
./mvnw spring-boot:run
