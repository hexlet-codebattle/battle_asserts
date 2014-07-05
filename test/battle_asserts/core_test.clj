(ns battle-asserts.core-test
  (:require [clojure.test :refer :all]
            [battle-asserts.core :refer :all]
            [multicode.core :as mc]
            [battle-asserts.checker :refer [valid?]]))

; (deftest test-get-let-array-fetch
;   (is (=
;         ['(let [arr [\a \b \c]] (assert-equal \b (fetch arr 1 \d)) (assert-equal \d (fetch arr 5 \d)) (assert-equal \c (fetch arr -1 \d)) (assert-equal \d (fetch arr -5 \d)))]
;         (get-asserts-from-file "test/fixtures/array_fetch"))))

; (deftest test-multicode-with-let-arrary-fetch
;   (is (=
;         "arr = ['a', 'b', 'c']\nassert_equal('b', fetch(arr, 1, 'd'))\nassert_equal('d', fetch(arr, 5, 'd'))\nassert_equal('c', fetch(arr, -1, 'd'))\nassert_equal('d', fetch(arr, -5, 'd'))"
;         (mc/prettify-code
;           :ruby
;           (get-asserts-from-file "test/fixtures/array_fetch")))))

; (deftest test-checker
;   (is (valid? "test/fixtures/array_transpose.yml")))

; (deftest test-array-fetch-checker
;   (is (valid? "test/fixtures/array_fetch.yml")))
