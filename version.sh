#!/bin/bash

export VERSION="1.12.2-git"
source ./version.info

sed -i "s/1.12.2-git/$VERSION/g" src/main/java/com/teamisotope/crystalline/api/CStatic.java
sed -i "s/1.12.2-git/$VERSION/g" build.gradle