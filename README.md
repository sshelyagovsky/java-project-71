### Hexlet tests and linter status:
[![Actions Status](https://github.com/sshelyagovsky/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/sshelyagovsky/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/0adf60d17c5c6fe1bffb/maintainability)](https://codeclimate.com/github/sshelyagovsky/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/0adf60d17c5c6fe1bffb/test_coverage)](https://codeclimate.com/github/sshelyagovsky/java-project-71/test_coverage)
# "Вычислитель отличий"
- Утилита для вычисления отличий файлов разных форматов

# "Формат файлов"
- JSON
- YAML

# "Правила чтения результата сравнения"
Отсутствие плюса или минуса говорит, что ключ есть в обоих файлах, и его значения совпадают. Во всех остальных ситуациях ключ был либо удалён, либо добавлен, либо изменён.

## Записи demo

#### DEMO 1
[![asciicast](https://asciinema.org/a/VbHV5kEV4T8JIYxVwhJFnyvq8.svg)](https://asciinema.org/a/VbHV5kEV4T8JIYxVwhJFnyvq8)
#### DEMO 2
[![asciicast](https://asciinema.org/a/hQiF2D5OpyDDmt1KHnVAJyMZQ.svg)](https://asciinema.org/a/hQiF2D5OpyDDmt1KHnVAJyMZQ)
#### DEMO 3
[![asciicast](https://asciinema.org/a/aFnK92E2t44qSuMgWlF0gfbMT.svg)](https://asciinema.org/a/aFnK92E2t44qSuMgWlF0gfbMT)

## Технологии
- [java](https://dev.java/learn/)
- [markdown](https://www.markdownguide.org/)
- [json](https://www.json.org/json-ru.html)
- [yaml](https://yaml.org/)

## Использование
- Запустить run-build (Сборка проекта)

- Запустите run-dist (Запуск консольного приложения)

- Запустите ./build/install/app/bin/app -h

- Запустите ./build/install/app/bin/app src/main/resources/file1.json src/main/resources/file2.json

- Запустите ./build/install/app/bin/app src/main/resources/file1.yaml src/main/resources/file2.yaml

## Run install app

```bash
install:
	./gradlew clean installDist
```

## Run Dist

```bash
run-dist:
	./build/install/app/bin/app
```

## Run build

```bash
build:
	./gradlew clean build
```

## Run

```bash
run:
	./gradlew run
```

## Run test

```bash
test:
	./gradlew test
```

## Run generate report

```bash
report:
	./gradlew jacocoTestReport
```

## Run linter
```bash
lint:
	./gradlew checkstyleMain
```