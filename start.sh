#!/bin/bash
nohup java -jar target/cmrs-service-0.1.0-SNAPSHOT.jar>/dev/null 2>&1 --server.port=9580 &