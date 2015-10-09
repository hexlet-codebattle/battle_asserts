(ns battle-solutions.squish-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn squish
  [str1]
  (clojure.string/join
   (reverse (reduce
             #(if (and (= (first %1) \space)
                       (= %2 \space))
                %1
                (conj %1 %2))
             '()
             (seq str1)))))

(deftest test-asserts
  (let [input "This    string  has   too    much spaces"
        output "This string has too much spaces"]
    (assert-equal output (squish input))))
