#!/bin/sh

#cd /storage/watchdog-new-production

java -XX:+CMSClassUnloadingEnabled -XX:PermSize=128m -XX:MaxPermSize=128m -Xms2048m -Xmx2048m -jar server-testing-persistence-actors-assembly-0.1.jar >> backend.log &

java -XX:+CMSClassUnloadingEnabled -XX:PermSize=128m -XX:MaxPermSize=128m -Xms2048m -Xmx2048m -jar server-testing-persistence-restapi-assembly-0.1.jar >> restapi.log &
