(ns battle-solutions.years-since-millennium-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn years-since-millennium [y]
  (mod y 1000))

(deftest test-asserts
  (assert-equal 15 (years-since-millennium 2015))
  (assert-equal 205 (years-since-millennium 10205)))
