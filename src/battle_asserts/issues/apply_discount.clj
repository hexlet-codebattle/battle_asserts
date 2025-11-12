;; Moved to modern repository
(ns battle-asserts.issues.apply-discount
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["collections"])

(def description
  {:en "Create a function that applies `discounts` to every `price` of the products in the array and calculate sum of whole payment. If after applying the `discount` to `price` intermediate result becames negative, consider intermediate result as zero."
   :ru "Создайте функцию, которая применяет `скидку` к соответствующей `цене` товара в массиве, и посчитайте общую стоимость покупки. Если после применения `скидки` к `цене` получается отрицательный результат - цена данного товара считается нулём."})

(def signature
  {:input [{:argument-name "prices" :type {:name "array" :nested {:name "integer"}}}
           {:argument-name "discounts" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (let [size (gen/generate (gen/choose 4 15))]
    (gen/tuple (gen/vector (gen/choose 10 9999) size) (gen/vector (gen/choose 10 9999) size))))

(def test-data
  [{:expected 5 :arguments [[10 15] [10 10]]}
   {:expected 2 :arguments [[2 4 6 10] [1 5 7 9]]}
   {:expected 3 :arguments [[10 20 40 100] [9 18 40 200]]}
   {:expected 87 :arguments [[10 20 40 100] [1 2 30 50]]}])

(defn solution [prices discounts]
  (let [zipped (mapv vector prices discounts)]
    (reduce (fn [acc [price discount]]
              (if (neg? (- price discount))
                acc
                (+ acc (- price discount)))) 0 zipped)))
