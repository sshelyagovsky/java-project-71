### Hexlet tests and linter status:
[![Actions Status](https://github.com/sshelyagovsky/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/sshelyagovsky/java-project-71/actions)
# "Вычислитель отличий"
- Утилита для вычисления отличий файлов разных форматов

# "Формат файлов"
- JSON

# "Правила чтения результата сравнения"
Отсутствие плюса или минуса говорит, что ключ есть в обоих файлах, и его значения совпадают. Во всех остальных ситуациях ключ был либо удалён, либо добавлен, либо изменён.

## Записи demo

#### DEMO 1
[![asciicast](https://asciinema.org/a/VbHV5kEV4T8JIYxVwhJFnyvq8.svg)](https://asciinema.org/a/VbHV5kEV4T8JIYxVwhJFnyvq8)
#### DEMO 2
[![asciicast](https://asciinema.org/a/hQiF2D5OpyDDmt1KHnVAJyMZQ.svg)](https://asciinema.org/a/hQiF2D5OpyDDmt1KHnVAJyMZQ)

## Технологии
- [java](https://dev.java/learn/)
- [markdown](https://www.markdownguide.org/)
- [json](https://www.json.org/json-ru.html)

## Использование
- Запустить run-build (Сборка проекта)

- Запустите run-dist (Запуск консольного приложения)

- Запустите ./build/install/app/bin/app -h

- Запустите ./build/install/app/bin/app src/main/resources/file1.json src/main/resources/file2.json

## Make file

```bash
.DEFAULT_GOAL := build-run
```

```bash
setup:
	./gradlew wrapper --gradle-version 8.5
```

```bash
clean:
	./gradlew clean
```

```bash
build:
	./gradlew clean build
```

```bash
install:
	./gradlew clean install
```

```bash
run-dist:
	./build/install/app/bin/app
```

```bash
run:
	./gradlew run
```

```bash
test:
	./gradlew test
```

```bash
report:
	./gradlew jacocoTestReport
```

```bash
lint:
	./gradlew checkstyleMain
```

```bash
check-deps:
	./gradlew dependencyUpdates -Drevision=release
```

```bash
build-run: build run
```

```bash
.PHONY: build
```