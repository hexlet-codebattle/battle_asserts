(ns test-helper
  (:require [clojure.test :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct]))

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

(def type-check-map
  {"string" String
   "integer" Number
   "float" Number
   "boolean" Boolean
   "array" clojure.lang.PersistentVector
   "hash" clojure.lang.PersistentArrayMap})

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

(defn run-solution-test
  [data solution issue-name]
  (testing (str "Solution for " issue-name)
    (doseq [{expected :expected arguments :arguments} data]
      (is (= expected (apply solution arguments))))))

(defn run-test-data-spec-test
  [test-data signature issue-name]
  (testing (str "Test-data and signature for " issue-name)
    (generate-data-tests test-data signature)))

(defn run-generator-spec-test
  [arguments-generator signature issue-name]
  (testing (str "Generated spec and described spec for " issue-name)
    (tc/quick-check 20 (prop/for-all [v (arguments-generator)]
                                     (is (true? (generate-signatures signature v)))))))

(defn run-solution-spec-test
  [arguments-generator signature solution issue-name]
  (testing (str "Solution spec for " issue-name)
    (let [output-type (type-check-map (((signature :output) :type) :name))]
      (tc/quick-check 20 (prop/for-all [v (arguments-generator)]
                                       (is (instance? output-type (apply solution v))))))))