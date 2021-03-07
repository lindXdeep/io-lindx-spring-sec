setup:
	mvn -N io.takari:maven:wrapper -Dmaven=3.6.3

clean:
	./mvnw clean

compile:
	./mvnw compiler:compile

execute:
	./mvnw exec:exec

build:
	./mvnw package assembly:single

run:
	java -jar ./target/io-lindx-sec-jar-with-dependencies.jar 


