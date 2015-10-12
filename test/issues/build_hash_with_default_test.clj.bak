(ns battle-solutions.build_hash_with_default_test
  (:require [clojure.test :refer [deftest]]
            [battle-asserts.test-helper :refer :all]))

(defn generate
  [v default]
  (reduce #(assoc %1 %2 default) {} v))

(deftest test-asserts
  (let []
    (assert-equal {:draft 0 :completed 0} (generate [:draft :completed] 0))
    (assert-equal {:one 4 :two 4} (generate [:one :two] 4))))

