(ns battle-asserts.utility
  (:require [faker.generate :as faker]
            [clojure.test.check.generators :as gen]))

(def type-map
  {java.lang.String "string"
   java.lang.Long "integer"
   java.lang.Integer "integer"
   java.lang.Double "float"
   java.lang.Boolean "boolean"
   clojure.lang.Ratio "integer"
   clojure.lang.PersistentList "array"
   clojure.lang.PersistentVector "array"
   clojure.lang.PersistentArrayMap "hash"
   clojure.lang.Keyword "string"})

(defn prepare-signature [signature]
  (map #(dissoc % :argument-name) (signature :input)))

(defn contains-val? [coll val]
  (reduce
   #(if (= val %2) (reduced true) %1)
   false coll))

(defn nested? [element]
  (contains-val?
   [clojure.lang.PersistentList
    clojure.lang.PersistentArrayMap
    clojure.lang.PersistentVector]
   (type element)))

(defn prepare-arguments [arguments]
  (reverse
   (reduce (fn [acc arg]
             (cond
               (and (nested? arg) (= (type-map (type arg)) "hash")) (conj acc {:type {:name (type-map (type arg)), :nested {:name (type-map (type (last (first arg))))}}})
               (and (nested? arg) (nested? (first arg))) (conj acc {:type {:name (type-map (type arg)), :nested {:name (type-map (type (first arg))) :nested {:name (type-map (type (ffirst arg)))}}}})
               (nested? arg) (conj acc {:type {:name (type-map (type arg)), :nested {:name (type-map (type (first arg)))}}})
               :else (conj acc {:type {:name (type-map (type arg))}})))
           ()
           arguments)))

(defn prepare-expected-results [expected]
  (cond
    (and (nested? expected) (= (type-map (type expected)) "hash")) (list {:type {:name (type-map (type expected)), :nested {:name (type-map (type (last (first expected))))}}})
    (and (nested? expected) (nested? (first expected))) (list {:type {:name (type-map (type expected)), :nested {:name (type-map (type (first expected))) :nested {:name (type-map (type (ffirst expected)))}}}})
    (nested? expected) (list {:type {:name (type-map (type expected)), :nested {:name (type-map (type (first expected)))}}})
    :else (list {:type {:name (type-map (type expected))}})))

(defn check-asserts-and-sign [data signature]
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
  (keep-indexed #(if (not= index %1) %2) coll))

(defn unique-words [amount]
  (letfn [(gen-words []
            (faker/words {:lang :en :n amount}))]
    (loop [words (gen-words)]
      (if (= (count words) (count (distinct words)))
        words
        (recur (gen-words))))))

(def gen-pos-num (gen/fmap inc gen/nat))
