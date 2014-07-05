(ns battle-asserts.test-helper
 (:require [clojure.test :refer [is]]))

(defn assert-equal
  [expected actual]
  (is (= expected actual)))

(defn assert
  [actual]
  (is actual))
