(ns battle-solutions.isomorphic-strings-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn process [string]
  (->
   string
   (clojure.string/split #"")
   (->>
    (reduce-kv #(assoc %1 %3 (conj (get %1 %3) %2)) {})
    (vals))))

(defn is-isomorphic [s, t]
  (= (process s) (process t)))

(deftest test-asserts
  (assert-true (is-isomorphic "egg" "add"))
  (assert-true (not (is-isomorphic "foo" "bar")))
  (assert-true (is-isomorphic "paper" "title"))
  (assert-true (is-isomorphic "ca" "ab"))
  (assert-true (is-isomorphic "aa" "bb"))
  (assert-true (is-isomorphic "aedor" "eiruw")))
