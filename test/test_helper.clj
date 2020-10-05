(ns test-helper
  (:require [clojure.test :refer :all]
            [clojure.test.check.generators :as gen]))

(defn generate-tests
  [data solution]
  (doseq [{expected :expected arguments :arguments} data]
    (is (= expected (apply solution arguments)))))

(def type-map
  {java.lang.String "string"
   java.lang.Long "integer"
   java.lang.Integer "integer"
   java.lang.Double "float"
   java.lang.Boolean "boolean"
   clojure.lang.Ratio "integer"
   clojure.lang.PersistentList "array"
   clojure.lang.PersistentVector "array"
   clojure.lang.PersistentArrayMap "hash"})

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
             (if (nested? arg)
               (if (nested? (first arg))
                 (conj acc {:type {:name (type-map (type arg)), :nested {:name (type-map (type (first arg))) :nested {:name (type-map (type (ffirst arg)))}}}})
                 (conj acc {:type {:name (type-map (type arg)), :nested {:name (type-map (type (first arg)))}}}))
               (conj acc {:type {:name (type-map (type arg))}})))
           ()
           arguments)))

(defn prepare-expected-results [expected]
  (if (nested? expected)
    (if (nested? (first expected))
      (list {:type {:name (type-map (type expected)), :nested {:name (type-map (type (first expected))) :nested {:name (type-map (type (ffirst expected)))}}}})
      (list {:type {:name (type-map (type expected)), :nested {:name (type-map (type (first expected)))}}}))
    (list {:type {:name (type-map (type expected))}})))

(defn generate-signatures [signature arguments]
  (let [prepared-sign (prepare-signature signature)
        prepared-arg (prepare-arguments arguments)]
    (= prepared-sign prepared-arg)))

(defn generate-data-tests [data signature]
  (let [input-signature (prepare-signature signature)
        output-signature (list (signature :output))]
  (doseq [{expected :expected arguments :arguments} data]
    (let [prepared-expected (prepare-expected-results expected)
          prepared-args (prepare-arguments arguments)]
    (is (= prepared-args input-signature))
    (is (= prepared-expected output-signature))))))