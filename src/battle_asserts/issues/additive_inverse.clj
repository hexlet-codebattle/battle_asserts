(ns battle-asserts.issues.additive-inverse
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["training" "collections"])

(def description
  {:en "Implement a function that returns an array of additive inverses. A number added with its `additive inverse` equals zero."
   :ru "Создайте функцию которая возвращает массив аддитивных инверсий. Число сложенное со своей аддитивной инверсией даёт ноль."})

(def signature
  {:input [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (letfn [(neg-int [] (gen/generate (gen/choose -1000 -1)))
          (pos-int [] (gen/generate (gen/choose 1 1000)))
          (numbers [] (gen/elements (concat
                                     (repeatedly 20 neg-int) (repeatedly 20 pos-int))))]
    (gen/tuple (gen/vector (numbers) 3 10))))

(def test-data
  [{:expected [-1 2 -3 4 5] :arguments [[1 -2 3 -4 -5]]}
   {:expected [-1 -1 -1 -10] :arguments [[1 1 1 10]]}
   {:expected [5 -25 -125] :arguments [[-5 25 125]]}])

(defn solution [numbers]
  (mapv - numbers))
