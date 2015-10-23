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

1. **Создайте .clj файл с именем вашей задачи в папке src/battle_asserts/issues.**

   - **Опишите пространство имен соответсвующие вашей задаче. Смотри пример**
     ```clojure
     (ns battle-asserts.issues.array-transpose
       (:require [clojure.test.check.generators :as gen]
                 [faker.generate :as faker]))
       ```

   - **Укажите уровень сложности вашей задачи :elementary, :easy, :medium, :hard. Смотри пример**
     ```clojure
     (def level :easy)
     ```

    - **Добавьте подробное описание соответствующе вашей задаче. Смотри пример**
      ```clojure
      (def description "Implement the matrix transposition function.
                       Matrices are presented as arrays of arrays, where internal arrays are rows of the matrix.
                       Transposition consists of three steps:
                       1) reflect the array over its main diagonal (which runs from top-left to bottom-right);
                       2) write the rows of the original matrix as columns of the new one;
                       3) write the columns of the original matrix as rows of the new one.")
       ```

    - **Напишите функцию описывающую генератор входных данных для вашей задачи. Смотри пример**
      ```clojure
      (defn arguments-generator []
        (gen/tuple (gen/bind (gen/choose 2 5)
                             #(gen/vector (gen/vector gen/int %)))))
     ```

    - **Опишите входные данные и ожидаемы результат работы решения вашей задачи. Смотри пример**
      ```clojure
      (def test-data
        [{:expected [[1 :a] [2 :b] [3 :c]]
          :arguments [[[1 2 3] [:a :b :c]]]}
         {:expected [[1 3 5] [2 4 6]]
          :arguments [[[1 2] [3 4] [5 6]]]}
         {:expected []
          :arguments [[]]}])
       ```

    - **Напишите функцию решающую вашу задачу. Смотри пример**
      ```clojure
      (defn solution [vectors]
        (if (not-empty vectors)
            (apply mapv vector vectors)
            []))
      ```
    Example: [array_transpose.clj](src/battle_asserts/issues/array_transpose.clj)

2. **Создайте файл .clj в папке test/battle_asserts/issues с именем, например, array_fetch_test.clj**

  - **Напишите тест объявив функцию (deftest test-solution ()). Смотри пример**
    ```clojure
    ; test/battle_asserts/issues/array_transpose_test.clj
    (deftest test-solution
      (h/generate-tests issue/test-data issue/solution))
    ```

  - **Напишите код тестирующий свойства результата вашего решения. Смотри пример**
    ```clojure
    (ct/defspec test-solution
      20
      (prop/for-all [v (issue/arguments-generator)]
                    (= (count (apply issue/solution v))
                       (count (ffirst v)))))
    ```

  - **Опишите простратство имен соответсвующие вашему тесту. Смотри пример**
    ```clojure
    ; test/battle_asserts/issues/array_transpose_test.clj
    (ns battle-asserts.issues.array-transpose-test
      (:require [clojure.test :refer :all]
                [clojure.test.check.properties :as prop :include-macros true]
                [clojure.test.check.clojure-test :as ct :include-macros true]
                [test-helper :as h]
                [battle-asserts.issues.array-transpose :as issue]))
    ```

  Example: [array_transpose_test.clj](test/battle_asserts/issues/array_transpose_test.clj)

3. **Run tests**
  ```
  lein test
  ```
  If you want to run test for only one issue - use lein test + namespace
  ```
  lein test battle-solutions.issues.array-transpose-test
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
