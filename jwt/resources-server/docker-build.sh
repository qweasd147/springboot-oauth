#!/bin/bash


if [ -z $1 ]
then
echo "example : sh docker-build.sh 1.0.0"
exit -1
fi

./gradlew build -x test

TAG=$1

echo "exec : docker build -t jwt_res:${TAG}"
docker build -t jwt_res:${TAG} -f Dockerfile-cache .
