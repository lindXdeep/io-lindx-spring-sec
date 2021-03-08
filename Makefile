.DEFAULT_GOAL := build-run

setup:
	mvn -N io.takari:maven:wrapper -Dmaven=3.6.3

clean:
	./mvnw clean

compile:
	./mvnw compiler:compile

lint-default:
	./mvnw checkstyle:checkstyle

lint-google:
	./mvnw checkstyle:check -Dcheckstyle.config.location=./checkstyle/google_checks.xml

execute:
	./mvnw exec:exec

build:
	./mvnw package assembly:single

run:
	java -jar ./target/io-lindx-sec-jar-with-dependencies.jar 

lint: lint-default lint-google

build-run: build run


