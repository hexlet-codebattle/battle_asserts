[![Build Status](https://travis-ci.org/Hexlet/battle_asserts.png?branch=master)](https://travis-ci.org/hexlet/battle_asserts)
[![Hexlet chat](http://slack-ru.hexlet.io/badge.svg)](http://slack-ru.hexlet.io)

## Setup for development

* Install [leiningen](http://leiningen.org)

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Make changes
4. Run tests (`lein test`).
5. Commit your changes (`git commit -am 'Added some feature'`)
6. Push to the branch (`git push origin my-new-feature`)
7. Create new Pull Request
8. Check if Request passed Travis-Ci

## How to add a new source

Необходимо написать описание задания, реализацию и тест к ней по нижеследующей инструкции.

Примеры заданий лежат в `src/battle_asserts/issues`. Тесты для них в папке `test/battle_asserts/issues`.

Файл задания включает в себя:

* `level` уровень задания, возможные варианты `elementary`, `easy`, `medium`, `hard`.
* `description` подробное описание задания без примеров.
* `test-data` данные в определенном формате которые будут использоваться при тестировании решения.
    Важно то что первым элементом в списке должен быть пример максимально полно раскрывающий задание,
    так как эти данные будут использоваться в качестве примера на сайте.
* `arguments-generator` генератор аргументов функции `solution`,
    эти данные используются при проверке пользовательского решения.
* `solution` решение задачи

Файл теста содержит вызов генератора тестов (одинаковый для всех тестов).

## Related links

### Leiningen

* http://leiningen.org/#install
* https://github.com/technomancy/leiningen/blob/stable/doc/TUTORIAL.md

[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/kaize/battle_asserts/trend.png)](https://bitdeli.com/free "Bitdeli Badge")
