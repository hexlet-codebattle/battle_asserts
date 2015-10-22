(ns battle-asserts.issues.xor
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "#FIXME Напишите функцию, принимающую на вход 2 строки и
                 возвращяющую строку полученную путем применения XOR к входным строка")

(defn arguments-generator
  []
  (gen/tuple gen/string-ascii
             gen/string-ascii))

(def test-data
  [{:expected "5*"
    :arguments ["xor" "ME"]}
   {:expected "AAAA"
    :arguments ["jjjj" "++++"]}])

(defn solution
  [str1 str2]
  (clojure.string/join (map char
                            (map bit-xor
                                 (doall
                                  (map int
                                       (seq str1)))
                                 (doall
                                  (map int
                                       (seq str2)))))))
