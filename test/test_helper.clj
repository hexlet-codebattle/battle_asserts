(ns battle-asserts.test_helper
  (:require [clojure.test :refer t]))

(defn generate-tests
  [data solution]
  (deftest test-solution
    (print data)
    (map %(is (= (:output %) (apply solution (:agruments %))))
         data)))
