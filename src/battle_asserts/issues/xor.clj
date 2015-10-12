(ns battle-asserts.issues.xor
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Write bitwise XOR function for 2 strings.")

(defn arguments-generator
  []
  (gen/tuple gen/string-ascii
             gen/string-ascii))

; (gen/sample (arguments-generator) 1)

(defn solution
  [str1 str2]
  (clojure.string/join (map char
                            (map bit-and
                                 (doall
                                  (map int
                                       (seq str1)))
                                 (doall
                                  (map int
                                       (seq str2)))))))
