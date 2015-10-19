(ns test-helper
  (:require [clojure.test :refer :all]))

(defn generate-tests
  [data solution]
  (deftest test-solution
    (print data)
    (map #(is (= (:output %) (apply solution (:agruments %))))
         data)))
