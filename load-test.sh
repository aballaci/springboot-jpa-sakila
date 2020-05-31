#!/bin/bash

for ((i=1;i<=100;i++)); do   curl --header "Connection: keep-alive" "http://localhost:30035/actors/"; done

max="${1:-10}"
url="${2:'http://localhost:30035/actors/'}"
date
echo "url: $url
rate: $max calls / second"
START=$(date +%s);

get () {
  curl -s -v $url 2>&1 | tr '\r\n' '\\n' | awk -v date="$(date +'%r')" '{print $0"\n-----", date}' > perf-test.log
}

while true
do
  echo $(($(date +%s) - START)) | awk '{print int($max/60)":"int($max%60)}'
  sleep 1

  for i in `seq 1 $max`
  do
    get $url &
  done
done