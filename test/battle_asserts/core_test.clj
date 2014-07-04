(ns battle-asserts.core-test
  (:require [clojure.test :refer :all]
            [battle-asserts.core :refer :all]
            [battle-asserts.parse-solution :refer :all]
            [multicode.core :as mc]
            [battle-asserts.checker :refer :all]))

(deftest test-get-let-array-fetch
  (is (=
        ['(let [arr [\a \b \c]] (assert (= \b (fetch arr 1 \d))) (assert (= \d (fetch arr 5 \d))) (assert (= \c (fetch arr -1 \d))) (assert (= \d (fetch arr -5 \d))))]
        (get-asserts-from-file "test/fixtures/array_fetch.clj"))))

(deftest test-multicode-with-let-arrary-fetch
  (is (=
        "arr = ['a', 'b', 'c']\nassert(=('b', fetch(arr, 1, 'd')))\nassert(=('d', fetch(arr, 5, 'd')))\nassert(=('c', fetch(arr, -1, 'd')))\nassert(=('d', fetch(arr, -5, 'd')))"
        (mc/prettify-code
          :ruby
          (get-asserts-from-file "test/fixtures/array_fetch.clj")))))

(deftest test-multicode-without-let-arrary-transpose
  (is (=
        "assert(=([[1, :a], [2, :b], [3, :c]], transpose([[1, 2, 3], [:a, :b, :c]])))\nassert(=([[1, 3, 5], [2, 4, 6]], transpose([[1, 2], [3, 4], [5, 6]])))\nassert(=([], transpose([])))"
        (mc/prettify-code
          :ruby
          (get-asserts-from-file "test/fixtures/array_transpose.clj")))))

(deftest test-checker
  (is (check-file "test/fixtures/array_transpose.yml")))

(deftest test-array-fetch-checker
  (is (check-file "test/fixtures/array_fetch.yml")))
