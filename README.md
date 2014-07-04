[![Build Status](https://travis-ci.org/kaize/battle_asserts.png?branch=master)](https://travis-ci.org/kaize/battle_asserts)

## Issue sources

* https://github.com/blakeembrey/code-problems

### PHP
* https://github.com/Kami/practical-php-testing-exercise-solutions
* 

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

### Leiningen

* http://leiningen.org/#install
* https://github.com/technomancy/leiningen/blob/stable/doc/TUTORIAL.md

## Setup for development

* install leiningen

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Make changes
4. Run tests (`lein test`).
5. Commit your changes (`git commit -am 'Added some feature'`)
6. Push to the branch (`git push origin my-new-feature`) 
7. Create new Pull Request
8. Check if Request passed Travis-Ci

## Add new source

* Create yaml file with the name of your issue. Look at example.
~~~Yaml
	---
level: easy # (elementary|easy|medium|hard)
tags: [string, numbers]
description: this is description
author:
  github_nickname: your_name
  web_page: "http://your.site"
checks: # (ruby|javascript|you lang)
  ruby:
    description: this is ruby description
    setup: |
      arr = []
    asserts: # (assert|assert_equal|assert_raises)
      - assert_equal 0, fibo_finder(0)
      - assert_equal 1, fibo_finder(1)
      - assert_equal 3, fibo_finder(4)
      - assert_equal 13, fibo_finder(7)
      - assert_equal 55, fibo_finder(10)

multicode_checks:
  langs: [javascript]#[ruby, javascript, python, php]
  asserts:
    - (assert (= 0 (fibo-finder 0))
    - (assert (= 1 (fibo-finder 1)))
    - (assert (= 3 (fibonacci 4)))
    - (assert (= 13 (fibonacci 7)))
    - (assert (= 55 (fib 10)))
~~~

If you need special things in your task, such as some Ruby objects, write asserts in "cheks:" section. If your task multiplatform write assers in "multicode_checks:" section.

* Create .clj file in test/battle_solutions folder such as - array_fetch_test.cl.

* Write test by define function (deftest test-asserts ())
Look at example.
~~~Clojure
(deftest test-asserts
  (let [arr [\a \b \c]]
    (assert-equal \b (fetch arr 1 \d))
    (assert-equal \d (fetch arr 5 \d))
    (assert-equal \c (fetch arr -1 \d))
    (assert-equal \d (fetch arr -5 \d))))
~~~

* Write namespace with your issue name
Example
~~~Clojure
(ns battle-solutions.array-fetch-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))
~~~

* Write function to resolve your tests
Example
~~~Clojure
(defn fetch
  [s index default]
  (let [positive-index (if (> index 0) index (+ (count s) index))]
    (nth s positive-index default)))
~~~

* Run tests (`lein test`).


[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/kaize/battle_asserts/trend.png)](https://bitdeli.com/free "Bitdeli Badge")


