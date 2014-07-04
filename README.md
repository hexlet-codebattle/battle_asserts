[![Build Status](https://travis-ci.org/kaize/battle_asserts.png?branch=master)](https://travis-ci.org/kaize/battle_asserts)

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

#### Create yaml file with the name of your issue. Look at example.

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
	multicode_checks:
	  langs: [javascript]#[ruby, javascript, python, php]

If you need special things in your task, such as some Ruby objects, write asserts in "cheks:" section. If your task multiplatform write assers in "multicode_checks:" section.

#### Create .clj file in test/battle_solutions folder such as - array_fetch_test.cl.

#### Write test by define function (deftest test-asserts ()) Look at example.

    (deftest test-asserts
  		(let [arr [\a \b \c]]
    	(assert-equal \b (fetch arr 1 \d))
    	(assert-equal \d (fetch arr 5 \d))
    	(assert-equal \c (fetch arr -1 \d))
    	(assert-equal \d (fetch arr -5 \d))))

#### Write namespace with your issue name.


	(ns battle-solutions.array-fetch-test
	  (:require [clojure.test :refer :all]
	            [battle-asserts.test-helper :refer [assert-equal assert]]))

#### Write function to resolve your tests

	(defn fetch
	  [s index default]
	  (let [positive-index (if (> index 0) index (+ (count s) index))]
	    (nth s positive-index default)))

#### Run tests (`lein test`).

#### If you want to run test for only one issue - use lein test + namespace

	lein test battle-solutions.array-fetch-test


[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/kaize/battle_asserts/trend.png)](https://bitdeli.com/free "Bitdeli Badge")


