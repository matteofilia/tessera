#!/bin/bash

echo "Script Location: $0"
DIR="$(dirname $0)"

# Set this to the location of the web host
export TESSERA_WEB_HOST="http://localhost:64184"
export TESSERA_WEB_HOST_B="http://localhost:8000"

echo "Using $TESSERA_WEB_HOST..."
echo "Using $TESSERA_WEB_HOST_B..."

cd "$DIR"
cp "Tessera Java Interpreter/build/jar/main.jar" "Tessera Server/src/main/resources/main.jar"
cd "Tessera Server"
./mvnw spring-boot:run
