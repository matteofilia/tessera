# Tessera Programming Language

## About
Tessera is a minimalist interpreted toy programming language, created by me.

## Try It Now
Try it now -> http://www.tessera-lang.org (Coming Soon!)

![Tessera Screenshot](https://github.com/matteofilia/tessera/blob/main/screenshot.PNG)

## Run Tessera Interpreter

./run.sh (Run Locally)

Example:
```
./run.sh "../Tessera Demo Code/example3.tess"
```

Note that the code file is relative to the path of the Tessera Java Interpreter

## Run Tessera Website

./run-web.sh (Run Locally, Web)

## Run Tessera Server

Always make sure to set TESSERA_WEB_HOST to the address of the webserver (do not add the port or it will break CORS!) Mental reminder: The origin MUST match exactly. http://www.example.com and http://example.com are NOT the same.
Example:
```
export TESSERA_WEB_HOST="http://1.2.3.4"
export TESSERA_WEB_HOST_B="http://www.example.com"
./run-server.sh
```

## Requirements

- Ant
- Java
- Python3

## Structure

Tessera Code -> Lexer -> Tokens

Tokens -> Parser -> Abstract Syntax Tree (AST)

AST -> Intepreter -> Tessera Program (Interpreted)
