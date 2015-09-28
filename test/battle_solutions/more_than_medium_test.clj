(ns battle-solutions.more-than-medium-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn more-than-medium
  [string]
  (let [words (re-seq #"\w+" string)
        average (/ (reduce + (map #(count %)
                                  words))
                   (count words))]

    (vec
      (filter #(> (count %) average)
              words))))

(deftest test-asserts
  (assert-equal ["This" "sample" "string"] (more-than-medium "This is a sample string"))
  (assert-equal ["another" "sample"] (more-than-medium "Some another sample"))
  (assert-equal [] (more-than-medium "Do, do, do, do... do it!")))
