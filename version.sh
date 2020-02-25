#!/bin/bash

export VERSION="1.0.0+experimental.git.1.15.2"
source ./version.info

if [[ "$VERSION" == *.git ]]; then
	export GITTIME=$(git show -s --format=\"%ct\" HEAD)
	sed -i "s/.git/.git.${GITTIME}/g" version.info
fi

source ./version.info

sed -i "s/1.0.0+experimental.git.1.15.2/$VERSION/g" build.gradle
