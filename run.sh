#!/bin/bash
nohup java -Xms512M -Xmx1024M -XX:NewRatio=3 -XX:PermSize=256M -XX:MaxPermSize=512M -XX:-UseGCOverheadLimit -jar gabdp-wf.jar &