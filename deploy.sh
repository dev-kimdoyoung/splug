#!/bin/bash

# 스크립트 내 변수 설정
REPOSITORY=/home/ubuntu/app
PROJECT_NAME=splug-springboot-webservice

echo "> Build 파일 복사"

cp $REPOSITORY/zip/build/libs/*.jar $REPOSITORY/

echo "> 현재 구동 중인 애플리케이션 PID 확인"
# 현재 실행 중인 java 웹 애플리케이션의 PID 저장
CURRENT_PID=$(pgrep -fl java | awk '{print $1}')

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

# CURRENT_PID에 값이 없으면 (길이가 0이면)
if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  # 현재 실행 중인 프로세스를 정상적인 방법 (15)으로 종료
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID

  # 5초 동안 대기 (wait)
  sleep 5
fi

# 가장 최신에 배포된 jar 파일을 JAR_NAME에 저장
echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"
echo "> $JAR_NAME 에 실행권한 추가"
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"
# JAR_NAME을 백그라운드로 실행 & nohup.out 파일을 표준 입출력으로 사용
nohup java -jar \
-Dspring.config.location=classpath:/application.properties \
-Dspring.profiles.active=real \
$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
