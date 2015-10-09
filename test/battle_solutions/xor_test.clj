(ns battle-solutions.xor-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn new-xor
  [str1 str2]
  (clojure.string/join (map char
                            (map bit-xor
                                 (doall
                                  (map int
                                       (seq str1)))
                                 (doall
                                  (map int
                                       (seq str2)))))))

(deftest test-asserts
  (let [str1 "string1"
        str2 "string2"]
    (assert-equal str2 (new-xor (new-xor str1 str2) str1))
    (assert-equal "5*" (new-xor "xor" "ME"))))
