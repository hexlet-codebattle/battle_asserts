(ns battle-solutions.rna-transcription-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn to-rna
  [dna]
  (let [transcription {\C \G
                       \G \C
                       \A \U
                       \T \A}]
    (apply str
           (map #(transcription %) dna))))

(deftest test-asserts
  (assert-equal "G" (to-rna "C"))
  (assert-equal "C" (to-rna "G"))
  (assert-equal "U" (to-rna "A"))
  (assert-equal "UGCACCAGAAUU" (to-rna "ACGTGGTCTTAA")))
