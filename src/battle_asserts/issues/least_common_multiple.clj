(ns battle-asserts.issues.least-common-multiple
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["math"])

(def description
  {:en "Create a function that calculates LCM (Least Common Multiple)."
   :ru "Создайте функцию которая возвращает НОК (Наименьшее общее кратное)."})

(def signature
  {:input [{:argument-name "x" :type {:name "integer"}}
           {:argument-name "y" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 50) (gen/choose 1 50)))

(def test-data
  [{:arguments [4 5]
    :expected 20}
   {:arguments [4 6]
    :expected 12}
   {:arguments [10 30]
    :expected 30}
   {:arguments [10 32]
    :expected 160}])

(defn lcm
  [first-num second-num acc]
  (if (or
       (not= (rem acc first-num) 0)
       (not= (rem acc second-num) 0))
    (lcm first-num second-num (inc acc))
    acc))

(defn solution [x y]
  (lcm x y (max x y)))
