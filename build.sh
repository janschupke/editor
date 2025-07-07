#! /usr/bin/bash

# create target folders
mkdir -p target
mkdir -p target/classes/resources
mkdir -p target/manifest
mkdir -p target/package/docs

# create classes
javac -d\
 target/classes\
 src/main/java/eu/janschupke/editor/*.java\
 src/main/java/eu/janschupke/editor/actions/*.java\
 src/main/java/eu/janschupke/editor/gui/*.java\
 src/main/java/eu/janschupke/editor/layout/*.java\
 src/main/java/eu/janschupke/editor/filehandling/*.java\
 src/main/java/eu/janschupke/editor/dialog/*.java\
 src/main/java/eu/janschupke/editor/config/*.java\
 src/main/java/eu/janschupke/editor/menu/*.java

# copy resource files
cp -p src/main/resources/*.txt target/package
cp -p src/main/resources/*.gif target/classes/resources

# convert language resources to ASCII + copy
native2ascii src/main/resources/Language_en_GB.properties target/classes/Language_en_GB.properties
native2ascii src/main/resources/Language_cs_CZ.properties target/classes/Language_cs_CZ.properties

# copy documentation files
cp -p src/main/docs/*.html target/package/docs

# copy exe scripts:

# dev scripts into src
cp -p build.sh src/main/scripts
cp -p clean.sh src/main/scripts
cp -p backup.sh src/main/scripts

# devRun.sh differs from run.sh
cp -p run.sh src/main/scripts/devRun.sh

# run scripts into distr.
cp -p src/main/scripts/run.sh target/package
cp -p src/main/scripts/run.bat target/package

echo Main-Class: eu.janschupke.editor.Editor > target/manifest/manifest.txt

# create archive
jar cfm target/package/Editor.jar target/manifest/manifest.txt -C target/classes .

wc -cl\
 src/main/java/eu/janschupke/editor/*.java\
 src/main/java/eu/janschupke/editor/actions/*.java\
 src/main/java/eu/janschupke/editor/gui/*.java\
 src/main/java/eu/janschupke/editor/layout/*.java\
 src/main/java/eu/janschupke/editor/filehandling/*.java\
 src/main/java/eu/janschupke/editor/dialog/*.java\
 src/main/java/eu/janschupke/editor/config/*.java\
 src/main/java/eu/janschupke/editor/menu/*.java
