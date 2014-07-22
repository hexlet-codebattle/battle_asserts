(ns battle-solutions.counting-array-elements-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn count [arr]
  (reduce #(update-in %1 [%2] (fnil inc 0)) {} arr))

(deftest test-asserts
  (let [arr [:cat, :dog, :fish, :fish]]
    (assert-equal {:cat 1, :dog 1, :fish 2} (count arr)))
  (let [arr [:Spam, :egg, :Spam, :Spam, :bacon, :Spam]]
    (assert-equal {:bacon 1, :egg 1, :Spam 4} (count arr))))
