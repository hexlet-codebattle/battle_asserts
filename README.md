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

1. **Create yaml file with the name of your issue. Look at the example**
    ```yml
    # source/array_fetch.yml
    level: easy # (elementary|easy|medium|hard)
    tags: [string, numbers]
    author:
      github_nickname: your_name
      web_page: "http://your.site"
    description: |
      This is description.
      The description can be multiline.
    multicode_checks:
      langs: [javascript, php] #[ruby, javascript, python, php]
    ```
    Example: [array_fetch.yml](source/array_fetch.yml)

2. **Create a .clj file in the test/battle_solutions folder, e.g. array_fetch_test.clj**

  - **Write a test by defining a function (deftest test-asserts ()) Look at the example**
    ```clojure
    ; test/battle_solutions/array_fetch_test.clj
    (deftest test-asserts
      (let [arr [\a \b \c]]
      (assert-equal \b (fetch arr 1 \d))
      (assert-equal \d (fetch arr 5 \d))
      (assert-equal \c (fetch arr -1 \d))
      (assert-equal \d (fetch arr -5 \d))))
    ```

  - **Write a namespace with your issue name**
    ```clojure
    ; test/battle_solutions/array_fetch_test.clj
    (ns battle-solutions.array-fetch-test
      (:require [clojure.test :refer :all]
                [battle-asserts.test-helper :refer [assert-equal assert]]))
    ```

  - **Write a function to resolve your tests**
    ```clojure
    ; test/battle_solutions/array_fetch_test.clj
    (defn fetch
      [s index default]
      (let [positive-index (if (> index 0) index (+ (count s) index))]
        (nth s positive-index default)))
    ```

  Example: [array_fetch_test.clj](test/battle_solutions/array_fetch_test.clj)

3. **Run tests**
  ```
  lein test
  ```
  If you want to run test for only one issue - use lein test + namespace
  ```
  lein test battle-solutions.array-fetch-test
  ```

4. **Run code format**
  ```
  make format
  ```

## Problems and exercises

### PHP
* https://github.com/Kami/practical-php-testing-exercise-solutions

### Python
* https://github.com/zhiwehu/Python-programming-exercises
* https://github.com/Aristide1o/python_kata

### Go

### Erlang
### Ruby

* https://github.com/Abica/ruby-99-problems
* https://github.com/hcf/99-problems-in-Ruby
* https://github.com/kevinrutherford/rrwb-code

### javascript

* https://github.com/lisafrench/JavaScriptExercises
* https://github.com/beastaugh/js-programming-exercises
* https://github.com/timestep/javascript-exercises
* https://github.com/wvmitchell/javascript

## Related links

### Leiningen

* http://leiningen.org/#install
* https://github.com/technomancy/leiningen/blob/stable/doc/TUTORIAL.md

[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/kaize/battle_asserts/trend.png)](https://bitdeli.com/free "Bitdeli Badge")
