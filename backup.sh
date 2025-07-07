#! /usr/bin/bash

# delete previous
sh clean.sh

# full fresh build
sh build.sh

# make directory if does not exist
mkdir -p versions

# archive
ARCHIVE_NAME=`date +%s`
tar -cf versions/$ARCHIVE_NAME.tar src target

# cleanup
sh clean.sh
