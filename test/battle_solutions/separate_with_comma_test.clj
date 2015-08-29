(ns battle-solutions.separate-with-comma-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn separate-with-comma
  [number]
  (apply str (reverse (clojure.string/replace (apply str (reverse (str number)))
                                              #"(\d\d\d)(?=\d)"
                                              "$1,"))))

(deftest test-asserts
  (assert-equal         "1" (separate-with-comma 1))
  (assert-equal        "10" (separate-with-comma 10))
  (assert-equal       "100" (separate-with-comma 100))
  (assert-equal     "1,000" (separate-with-comma 1000))
  (assert-equal    "10,000" (separate-with-comma 10000))
  (assert-equal   "100,000" (separate-with-comma 100000))
  (assert-equal "1,000,000" (separate-with-comma 1000000)))
