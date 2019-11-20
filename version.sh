#!/bin/bash

export VERSION="1.12.2-git"
source ./version.info

if [[ "$VERSION" == *-git ]]; then
	export GITTIME=$(git show -s --format=\"%ct\" HEAD)
	sed -i "s/-git/-git.${GITTIME}/g" version.info
fi

source ./version.info
	
sed -i "s/1.12.2-git/$VERSION/g" src/main/java/com/teamisotope/crystalline/api/CStatic.java
sed -i "s/1.12.2-git/$VERSION/g" build.gradle