(ns battle-solutions.string-format-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn string-format
  [num]
  (format "Value is %05d" num))

(deftest test-asserts
  (assert-equal "Value is 00005" (string-format 5))
  (assert-equal "Value is 00123" (string-format 123))
  (assert-equal "Value is 12345" (string-format 12345)))
