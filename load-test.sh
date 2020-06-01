#!/bin/bash

PORT=$(kubectl get --namespace default -o jsonpath="{.spec.ports[0].port}" services springboot-jpa)

APP_URL="http://localhost:${PORT}/film/"

sb -v -u $APP_URL -c 4 -n 1000