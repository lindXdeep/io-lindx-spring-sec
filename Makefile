setup:
	mvn -N io.takari:maven:wrapper -Dmaven=3.6.3

clean:
	./mvnw clean

compile:
	./mvnw compiler:compile

execute:
	./mvnw exec:exec
