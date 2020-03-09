(ns battle-asserts.issues.rna-transcription
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Given a DNA strand, return its RNA complement.
                 Both DNA and RNA strands are a sequence of nucleotides.
                 DNA's nucleotides are adenine (A), cytosine (C), guanine (G) and thymidine (T).
                 RNA's nucleotides are adenine (A), cytosine (C), guanine (G) and uracil (U).
                 The transcribed RNA strand of a DNA strand is formed by replacing each nucleotide with its complement:
                 G -> C, C -> G, T -> A, A -> U.")

(def signature
  {:input  [{:argument-name "dna" :type {:name "string"}}]
   :output {:type {:name "string"}}})

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
