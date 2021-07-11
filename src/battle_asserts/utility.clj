(ns battle-asserts.utility
  (:require [faker.generate :as faker]
            [clojure.test.check.generators :as gen]
            [faker.name :as f]))

(defn- prepare-signature [signature]
  (map #(dissoc % :argument-name) (signature :input)))

(defn- contains-val? [coll val]
  (reduce
   #(if (= val %2) (reduced true) %1)
   false coll))

(defn- nested? [element]
  (contains-val?
   [clojure.lang.PersistentList
    clojure.lang.PersistentArrayMap
    clojure.lang.PersistentVector]
   (type element)))

(defn- type-element [elem]
  (let [type-map
        {java.lang.String "string"
         java.lang.Long "integer"
         java.lang.Integer "integer"
         java.lang.Double "float"
         java.lang.Boolean "boolean"
         clojure.lang.Ratio "integer"
         clojure.lang.PersistentList "array"
         clojure.lang.PersistentVector "array"
         clojure.lang.PersistentArrayMap "hash"
         clojure.lang.Keyword "string"}]
    (cond
      (and (nested? elem) (= (type-map (type elem)) "hash")) {:type {:name (type-map (type elem)), :nested {:name (type-map (type (last (first elem))))}}}
      (and (nested? elem) (nested? (first elem))) {:type {:name (type-map (type elem)), :nested {:name (type-map (type (first elem))) :nested {:name (type-map (if (= (type (ffirst elem)) clojure.lang.MapEntry) (type (last (ffirst elem))) (type (ffirst elem))))}}}}
      (nested? elem) {:type {:name (type-map (type elem)), :nested {:name (type-map (type (first elem)))}}}
      :else {:type {:name (type-map (type elem))}})))

(defn- prepare-expected-results [expected]
  (list (type-element expected)))

(defn- prepare-arguments [arguments]
  (map type-element arguments))

(defn check-asserts-and-sign
  "Validates asserts by their generated data and signature."
  [data signature]
  (let [input-signature (prepare-signature signature)
        output-signature (list (signature :output))]
    (reduce (fn [acc task]
              (let [expected (task :expected)
                    arguments (task :arguments)
                    prepared-expected (prepare-expected-results expected)
                    prepared-args (prepare-arguments arguments)]
                (if (and (= prepared-args input-signature) (= prepared-expected output-signature))
                  acc
                  (conj acc task)))) [] data)))

(defn drop-nth [coll index]
  (keep-indexed #(when (not= index %1) %2) coll))

(defn unique-words [amount]
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
  (str (f/first-name) " " (f/last-name)))

