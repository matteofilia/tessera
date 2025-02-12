#!/bin/bash

echo "Script Location: $0"
DIR="$(dirname $0)"

cd "$DIR"
cd "Tessera Server"
./mvnw spring-boot:run
