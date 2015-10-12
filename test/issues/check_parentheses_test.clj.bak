(ns battle-solutions.check-parentheses-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]
            [clojure.string :refer [blank?]]
            [clojure.set :refer [union]]))

(defn is-correct-parentheses [s]
  (let [opening (set "(")
        closing (set ")")]
    (->> s
         (reduce #(cond
                    (< %1 0) (reduced %1)
                    (opening %2) (inc %1)
                    (closing %2) (dec %1)
                    :else %1)
                 0)
         (= 0))))

(deftest test-asserts
  (assert-true (is-correct-parentheses ""))
  (assert-true (is-correct-parentheses " "))
  (assert-true (is-correct-parentheses "()"))
  (assert-true (is-correct-parentheses " ( )(  )"))
  (assert-true (is-correct-parentheses "(() )"))
  (assert-true (not (is-correct-parentheses ") ")))
  (assert-true (not (is-correct-parentheses "(")))
  (assert-true (not (is-correct-parentheses ") (")))
  (assert-true (not (is-correct-parentheses "( )  )")))
  (assert-true (not (is-correct-parentheses "(( )"))))
