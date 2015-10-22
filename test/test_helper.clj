(ns test-helper
  (:require [clojure.test :refer :all]))

(defn generate-tests
  [data solution]
  (doseq [{output :output arguments :arguments} data]
    (is (= output (apply solution arguments)))))
