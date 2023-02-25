(ns battle-asserts.utility
  (:require [faker.generate :as faker]
            [clojure.test.check.generators :as gen]
            [faker.name :as fk]
            [test-helper :as h]))

(defn check-asserts-and-sign
  "Validates asserts by their generated data and signature."
  [data signature]
  (let [input-signature (h/prepare-signature signature)
        output-signature (list (signature :output))]
    (reduce (fn [acc task]
              (let [expected (task :expected)
                    arguments (task :arguments)
                    prepared-expected (h/prepare-expected-results expected)
                    prepared-args (h/prepare-arguments arguments)]
                (if (and (= prepared-args input-signature) (= prepared-expected output-signature))
                  acc
                  (conj acc task)))) [] data)))

(defn drop-nth
  "Drops nth element from collection."
  [coll index]
  (keep-indexed #(when (not= index %1) %2) coll))

(defn unique-words
  "Generates unique words."
  [amount]
  (letfn [(gen-words []
            (faker/words {:lang :en :n amount}))]
    (loop [words (gen-words)]
      (if (= (count words) (count (distinct words)))
        words
        (recur (gen-words))))))

(def gen-pos-num (gen/fmap inc gen/nat))

(defn gen-name
  "Generates random first name + last name."
  []
  (str (fk/first-name) " " (fk/last-name)))
