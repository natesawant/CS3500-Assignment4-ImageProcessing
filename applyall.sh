#!/bin/bash
// Test script

echo Changing directory...
cd src

echo Compiling...
javac controller/ApplyAllProcesses.java

echo Running...
java controller/ApplyAllProcesses ../images ship.jpg .png

$SHELL