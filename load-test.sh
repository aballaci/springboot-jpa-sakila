#!/bin/bash

<<<<<<< HEAD
PORT=$(kubectl get --namespace default -o jsonpath="{.spec.ports[0].port}" services springboot-jpa)

APP_URL="http://localhost:${PORT}/film/"

sb -v -u $APP_URL -c 4 -n 1000
=======
NODE_PORT=$(kubectl get --namespace default -o jsonpath="{.spec.ports[0].nodePort}" services springboot-jpa)
echo $NODE_PORT
APP_URL="http://localhost:${NODE_PORT}/actors/"

echo "app_url: ${APP_URL}"
#for ((i=1;i<=100;i++)); do   curl --header "Connection: keep-alive" ${APP_URL}; done

max="${1:-10}"
url="${APP_URL}"
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
>>>>>>> 25813f9116b698316023d86024e1530aa95664d6
