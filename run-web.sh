#!/bin/bash

echo "Script Location: $0"
DIR="$(dirname $0)"

cd "$DIR"


cd "Tessera Website"
python3 -m http.server
