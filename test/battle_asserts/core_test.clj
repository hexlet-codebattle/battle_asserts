(ns battle-asserts.core-test
  (:require [clojure.test :refer :all]
            [battle-asserts.core :refer :all]
            [battle-asserts.check-asserts :refer :all]
            [battle-asserts.parse-solution :refer :all]
            [multicode.core :as mc]))

(def sources "source")

; FIXME not show assertions 
(deftest test-check-all-asserts
  (apply map 
         #(partial (is (= % nil)))
         (check-asserts sources))) 

(deftest test-get-let-array-fetch
  (is (=
        ['(let [arr [\a \b \c]] (assert (= \b (fetch arr 1 \d))) (assert (= \d (fetch arr 5 \d))) (assert (= \c (fetch arr -1 \d))) (assert (= \d (fetch arr -5 \d))))]
        (get-asserts-from-file "test/support/fixtures/array_fetch/multicode.clj")))) 

(deftest test-multicode
  (is (= 
        "assert(=(1, fibo(a)))\nassert(=(2, fibo(b)))"
        (mc/prettify-code 
          :ruby 
          ['(assert (= 1 (fibo a)))
           '(assert (= 2 (fibo b)))])))) 

(deftest test-multicode-with-let-arrary-fetch
  (is (= 
        "arr = ['a', 'b', 'c']\nassert(=('b', fetch(arr, 1, 'd')))\nassert(=('d', fetch(arr, 5, 'd')))\nassert(=('c', fetch(arr, -1, 'd')))\nassert(=('d', fetch(arr, -5, 'd')))"
        (mc/prettify-code 
          :ruby 
          (get-asserts-from-file "test/support/fixtures/array_fetch/multicode.clj"))))) 

(deftest test-multicode-without-let-arrary-transpose
  (is (= 
        "assert(=([[1, :a], [2, :b], [3, :c]], transpose([[1, 2, 3], [:a, :b, :c]])))\nassert(=([[1, 3, 5], [2, 4, 6]], transpose([[1, 2], [3, 4], [5, 6]])))\nassert(=([], transpose([])))"
        (mc/prettify-code 
          :ruby 
          (get-asserts-from-file "test/support/fixtures/array_transpose/multicode.clj"))))) 
