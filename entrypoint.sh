#!/usr/bin/env sh

APP_HOME=/app
cd ${APP_HOME}



run_bash() {
  java -Xms1024m -Xmx1024m -Duser.timezone=UTC -Dvertx.logger-delegate-factory-class-name=io.vertx.core.logging.SLF4JLogDelegateFactory -Dlogback.configurationFile=${APP_HOME}/src/main/resources/logback.xml -jar ${APP_HOME}/build/ml-bff-1.0.0-SNAPSHOT-runner.jar
}

run_bash

exit 0
