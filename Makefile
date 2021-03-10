.DEFAULT_GOAL := build

setup:
	mvn -N io.takari:maven:wrapper -Dmaven=3.6.3

lint-default:
	./mvnw checkstyle:checkstyle

lint-google:
	./mvnw checkstyle:check -Dcheckstyle.config.location=./checkstyle/google_checks.xml

clean:
	./mvnw clean
	rm -R ${CATALINA_BASE}/webapps/myapp/*

compile:
	./mvnw compiler:compile

deploy:
	./mvnw package war:war

redeploy:
	rm -R ${CATALINA_BASE}/webapps/myapp/*
	./mvnw package war:war

build:
	./mvnw package war:exploded 

lint: lint-default lint-google

open-chrome:
	google-chrome --new-window http://localhost:8080

open-firefox:
	firefox --new-window http://localhost:8080

browse:
	browse http://localhost:8080

code:
	code-oss $@



