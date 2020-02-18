#!/bin/bash

export VERSION="1.0.0+experimental.git.1.14.4"
source ./version.info

if [[ "$VERSION" == *-git ]]; then
	export GITTIME=$(git show -s --format=\"%ct\" HEAD)
	sed -i "s/-git/-git.${GITTIME}/g" version.info
fi

source ./version.info
	
#sed -i "s/1.0.0+experimental.git.1.14.4/$VERSION/g" src/main/java/com/teamisotope/crystalline/api/CStatic.java # don't think a static references class is necessary in 1.14.4
sed -i "s/1.0.0+experimental.git.1.14.4/$VERSION/g" build.gradle
