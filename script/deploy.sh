#!/usr/bin/env bash

echo "
           _     _       _       _     _              _       _
          (_)   | |     (_)     | |   | |            | |     | |
 _ __ ___  _  __| |_ __  _  __ _| |__ | |_ ______ ___| |_   _| |__ ______ ___  ___ _ ____   _____ _ __
| '_ ' _ \| |/ _' | '_ \| |/ _' | '_ \| __|______/ __| | | | | '_ \______/ __|/ _ \ '__\ \ / / _ \ '__|
| | | | | | | (_| | | | | | (_| | | | | |_      | (__| | |_| | |_) |     \__ \  __/ |   \ V /  __/ |
|_| |_| |_|_|\__,_|_| |_|_|\__, |_| |_|\__|      \___|_|\__,_|_.__/      |___/\___|_|    \_/ \___|_|
                            __/ |
                           |___/

"

BASE=$(cd $(dirname $(readlink $0 || echo $0))/..;/bin/pwd)

SERVICE=midnight-club-server
HOST=ec2-52-78-164-222.ap-northeast-2.compute.amazonaws.com

$BASE/gradlew clean build -x test -p $BASE

## copy
echo "scp $BASE/build/libs/$SERVICE.jar uadmin@$HOST:/uadmin/service/$SERVICE/"
scp $BASE/build/libs/$SERVICE.jar uadmin@$HOST:/uadmin/service/$SERVICE/


## restart
ssh uadmin@$HOST "pkill -9 -f /uadmin/service/$SERVICE"
sleep 1;

ssh uadmin@$HOST "nohup java \
  -Dcom.sun.management.jmxremote \
  -Dcom.sun.management.jmxremote.port=8999 \
  -Dcom.sun.management.jmxremote.local.only=false \
  -Dcom.sun.management.jmxremote.authenticate=false \
  -Dcom.sun.management.jmxremote.ssl=false \
  -Dspring.profiles.active=develop \
  -server -Xms256m -Xmx256m -XX:+UseG1GC \
  -jar /uadmin/service/$SERVICE/$SERVICE.jar > /dev/null 2>&1 &"

## check application log
echo -n "Restart $SERVICE"; for i in {1..10}; do echo -n "."; sleep 1; done; echo
ssh uadmin@$HOST '(tail -10 /uadmin/logs/'$SERVICE'/`ls -t /uadmin/logs/'$SERVICE' | head -1`)'
