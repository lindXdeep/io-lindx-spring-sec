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
	rm -R ${CATALINA_BASE}/webapps/myapp/*
	./mvnw package war:war

build:
	./mvnw package war:exploded

lint: lint-default lint-google



