(ns test-helper
  (:require [clojure.test :refer :all]))

(defn generate-tests
  [data solution]
  (doseq [{expected :expected arguments :arguments} data]
    (is (= expected (apply solution arguments)))))
