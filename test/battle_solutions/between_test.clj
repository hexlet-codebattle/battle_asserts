(ns battle-solutions.between-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn between
  [string left right]
  (-> (str left "(.*)" right)
      re-pattern
      (re-find string)
      rest))

(deftest test-asserts
  (let [str1 "<a>foo</a>"
        str2 ["foo"]]
    (assert-equal str2 (between str1 "<a>" "</a>"))))
