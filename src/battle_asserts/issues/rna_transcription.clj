(ns battle-asserts.issues.rna-transcription
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Write a program that, given a DNA strand, returns its RNA complement (per RNA transcription).
                 Both DNA and RNA strands are a sequence of nucleotides.
                 The four nucleotides found in DNA are adenine (A), cytosine (C), guanine (G) and thymidine (T).
                 The four nucleotides found in RNA are adenine (A), cytosine (C), guanine (G) and uracil (U).
                 Given a DNA strand, its transcribed RNA strand is formed by replacing each nucleotide with its complement:
                 G -> C, C -> G, T -> A, A -> U")

(defn arguments-generator []
  (letfn [(dna-strand []
            (s/join (repeatedly (inc (rand-int 10))
                                #(rand-nth [\G \C \T \A]))))
          (dna-strands []
                       (repeatedly (inc (rand-int 40))
                                   dna-strand))]
    (gen/tuple (gen/elements (dna-strands)))))

(def test-data
  [{:expected "UGCACCAGAAUU"
    :arguments ["ACGTGGTCTTAA"]}
   {:expected "G"
    :arguments ["C"]}
   {:expected "C"
    :arguments ["G"]}
   {:expected "U"
    :arguments ["A"]}])

(defn solution [dna]
  (let [transcription {\C \G
                       \G \C
                       \A \U
                       \T \A}]
    (s/join
     (map transcription dna))))
