FROM openjdk:17-oraclelinux7

COPY target/chat.jar /root

COPY web/ /home/web

ENTRYPOINT java -jar /root/chat.jar --spring.profiles.active=pro
