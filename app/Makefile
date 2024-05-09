.DEFAULT_GOAL := build-run

install:
	./gradlew clean installDist

run-dist:
	./build/install/app/bin/app

build:
	./gradlew clean build

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

.PHONY: build

