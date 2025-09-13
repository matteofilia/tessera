#!/bin/bash

echo "Script Location: $0"
DIR="$(dirname $0)"

cd "$DIR"

echo "Copying demo code files to website..."
echo "Demo code files should be named example1.tess or example7.tess"
# cp 'Tessera Demo Code'/*.tess 'Tessera Website'/public/code_examples

cd "Tessera Website"
python3 -m http.server
