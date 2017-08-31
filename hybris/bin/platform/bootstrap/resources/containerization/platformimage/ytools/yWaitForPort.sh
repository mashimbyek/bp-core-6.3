#!/bin/bash

function doLog {
        echo "[ywaitForPort] $1"
}

function doWait {
        doLog "Waiting for $1"
        hostPort=(${1//:/ })
        for a in {1..1000}
        do
                nc -z -w2 ${hostPort[0]} ${hostPort[1]}
                if [ $? -eq 0 ]
                        then
                                return 0
                        else
                                doLog "Attempt $a failed" && sleep 2
                fi
        done
        return 1
}

for i in ${@:1}
do
        if doWait $i;
                then
                        doLog "$i is open"
                else
                        doLog "Giving up waiting on $i"
                        exit 1
        fi
done

exit 0
