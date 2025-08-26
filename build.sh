#!/bin/bash

# set -o xtrace

echo "Compiling Java using Ant..."

cd "Tessera Java Interpreter" 
ant clean
ant compile
ant jar
