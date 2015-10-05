(ns battle-solutions.separate-with-comma-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn separate-with-comma
  [number]
  (apply str (reverse (clojure.string/replace (apply str (reverse (str number)))
                                              #"(\d\d\d)(?=\d)"
                                              "$1,"))))

(deftest test-asserts
  (assert-equal         "1" (separate-with-comma 1))
  (assert-equal        "10" (separate-with-comma 10))
  (assert-equal       "150" (separate-with-comma 150))
  (assert-equal     "1,234" (separate-with-comma 1234))
  (assert-equal    "15,075" (separate-with-comma 15075))
  (assert-equal   "123,456" (separate-with-comma 123456))
  (assert-equal "1,238,592" (separate-with-comma 1238592)))
