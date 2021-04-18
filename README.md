[![Build Status](https://travis-ci.org/hexlet-codebattle/battle_asserts.svg?branch=master)](https://travis-ci.org/hexlet-codebattle/battle_asserts)
[![Hexlet chat](http://slack-ru.hexlet.io/badge.svg)](http://slack-ru.hexlet.io)

## Setup for development

- Install [leiningen](http://leiningen.org)

## Contributing

1. Fork it
2. Clone repo (`https://github.com/{your-nickname}/battle_asserts.git`)
3. Create your feature branch (`git checkout -b my-new-feature`)
4. Make changes
5. Run tests (`make test`).
6. Commit your changes (`git commit -am 'Added some feature'`)
7. Push to the branch (`git push origin my-new-feature`)
8. Create new Pull Request
9. Check if Request passed GithubActions

## How to add a new issue

### 1. Write the description of the issue with function signature and test-data

A description of the issue includes:

- `level` — difficulty of the problem; possible values are `elementary`, `easy`, `medium`, `hard`.
- `description` — detailed description of the issue. Now supported laguages is `ru` and `en`, `en` is required. You can run `make check-issue-trans` for list current translation progress.
- `tags` — tags that provides addittional info about task.
- `signature` — function signature; map with `input` and `output` types. Available types `integer`, `float`, `string`, `array`, `hash (map, dicts)`.
  See examples in the existing issues.

- `test-data` — data in a specified format which will be used to test solutions. The first element in this list will be displayed as an example to players, so it should clarify and illustrate the problem as much as possible. Do not choose a trivial case for this example.

Example:

```clojure
(ns battle-asserts.issues.array_sum)

(def level :elementary)

(def tags ["training" "collections"])

(def description 
  {:en "Calculate the sum of array."
   :ru "Рассчитатйте сумму всех чисел в массиве."})

(def signature
  {:input  [{:argument-name "numbers" :type {:name "arr" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(def test-data
  [{:expected 1
    :arguments [[1]]}
  {:expected 0
    :arguments [[]]}
   {:expected 10
    :arguments [[1 2 3 4]]}])
```

See examples in `src/battle_asserts/issues/*.clj`

### 2. Create pull-request

It will appear on [codebattle](http://codebattle.hexlet.io) after merge


### 3. Write the implementation of the issue and test

An implementation of the issue includes:

- `arguments-generator` — arguments generator for the `solution` function;
  generated arguments will be used to test players' solutions.
- `solution` — implemented correct solution.

Example:

```clojure
(ns battle-asserts.issues.array_sum)

(def level :elementary)

(def tags ["training" "collections"])

(def description 
  {:en "Calculate the sum of array."
   :ru "Рассчитатйте сумму всех чисел в массиве."})

(def signature
  {:input  [{:argument-name "numbers" :type {:name "arr" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(def test-data
  [{:expected 1
    :arguments [[1]]}
  {:expected 0
    :arguments [[]]}
   {:expected 10
    :arguments [[1 2 3 4]]}])

(defn arguments-generator
  []
  (gen/tuple (gen/list gen/small-integer))

(defn solution
  [numbers]
  (apply + numbers))
```

Corresponding tests are in `test/test_helper.clj` there is no need to add tests to this file, because tests runs dynamically, depending on the described `signature`, `test-data`, `arguments-generator` and `solution`. 
- `run-solution-spec-test` tests are checking that result type of solving issue is correct.
- `run-generator-spec-test` tests are checking that input signatures are correctly described for `arguments-generator`.
- `run-solution-test` tests are checking that test data solves correctly.
- `run-test-data-spec-test` tests are checking that test data corresponds to signature.

### 4. Create pull-request

It will appear with auto-generated asserts on [codebattle](http://codebattle.hexlet.io) after merge

## Related links

### Leiningen

- http://leiningen.org/#install
- https://github.com/technomancy/leiningen/blob/stable/doc/TUTORIAL.md
