(ns test-helper
  (:require [clojure.test :refer [is testing]]
            [clojure.test.check :as tc]
            [clojure.test.check.properties :as prop]))

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
   "integer" java.lang.Long
   "float" java.lang.Double
   "boolean" Boolean
   "array" clojure.lang.PersistentVector
   "hash" clojure.lang.PersistentArrayMap})

(defn- check-output-type [data]
  (if (or (= (type data) java.lang.Integer) (= (type data) java.lang.Long))
    java.lang.Long
    (type data)))

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

(defn type-element [elem]
  (cond
    (and (nested? elem) (= (type-map (type elem)) "hash")) {:type {:name (type-map (type elem)), :nested {:name (type-map (type (last (first elem))))}}}
    (and (nested? elem) (nested? (first elem))) {:type {:name (type-map (type elem)), :nested {:name (type-map (type (first elem))) :nested {:name (type-map (type (ffirst elem)))}}}}
    (nested? elem) {:type {:name (type-map (type elem)), :nested {:name (type-map (type (first elem)))}}}
    :else {:type {:name (type-map (type elem))}}))

(defn prepare-expected-results [expected]
  (list (type-element expected)))

(defn prepare-arguments [arguments]
  (map type-element arguments))

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
  (testing (str "Solution for " issue-name " task.")
    (doseq [{expected :expected arguments :arguments} data]
      (is (= expected (apply solution arguments))))))

(defn run-test-data-spec-test
  [test-data signature issue-name]
  (testing (str "Test-data and signature for " issue-name " task.")
    (generate-data-tests test-data signature)))

(defn run-generator-spec-test
  [arguments-generator signature issue-name]
  (testing (str "Generated spec and described spec for " issue-name " task.")
    (tc/quick-check 20 (prop/for-all [v (arguments-generator)]
                                     (is (= (prepare-signature signature) (prepare-arguments v)))))))

(defn run-solution-spec-test
  [arguments-generator signature solution issue-name]
  (testing (str "Solution spec for " issue-name " task.")
    (let [output-type (type-check-map (((signature :output) :type) :name))]
      (tc/quick-check 20 (prop/for-all [v (arguments-generator)]
                                       (let [actual-output-type
                                             (check-output-type (apply solution v))]
                                         (is (= output-type actual-output-type))))))))

(defn run-description-test [description issue-name]
  (testing (str "Test minimal description lang for " issue-name " task.")
    (is (= true (or (string? description) (contains? description :en))))))
