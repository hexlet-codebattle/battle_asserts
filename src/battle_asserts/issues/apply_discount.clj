(ns battle-asserts.issues.apply-discount
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Create a function that applies a `discount` to every `price` of the product in the array. Round result to nearest.")

(def signature
  {:input [{:argument-name "prices" :type {:name "array" :nested {:name "integer"}}}
           {:argument-name "discount" :type {:name "integer"}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose 10 9999) 5 20) (gen/choose 1 90)))

(def test-data
  [{:expected [1 2 3 5] :arguments [[2 4 6 10] 50]}
   {:expected [9 18 36 90] :arguments [[10 20 40 100] 10]}
   {:expected [1 2 4 10] :arguments [[10 20 40 100] 90]}])

(defn apply-discount [price discount]
  (int (Math/round (- price (* price (float (/ discount 100)))))))

(defn solution [prices discount]
  (mapv #(apply-discount % discount) prices))
