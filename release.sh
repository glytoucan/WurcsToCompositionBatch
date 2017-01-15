#!/bin/sh
. build.env
echo docker-compose -f docker-compose.release.yml up
docker-compose -f docker-compose.release.yml up
docker commit glytoucanWurcsToCompositionv1.2.7-TOCO glycoinfo.org:5000/glytoucan_wurcstocomposition:v1.2.7-TOCO
docker push glycoinfo.org:5000/glytoucan_wurcstocomposition:v1.2.7-TOCO
