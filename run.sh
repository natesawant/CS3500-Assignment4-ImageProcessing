#!/bin/bash
// Runs the image processing program.

echo Changing directory...
cd src

echo Compiling...
javac controller/ImageProcessing.java

echo Running...
java controller/ImageProcessing ../images/