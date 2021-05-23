(ns battle-asserts.issues.sastry-numbers
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["math"])

(def description
  {:en "Check if a given integer `n` is a Sastry number.
        A number `n` is a Sastry number if concatenated with `n + 1` it gives a square."
   :ru "Проверьте, являетли ли переданное число `n` числом Састри (Sastry number).
        Число `n` является числом Састри, если при конкатенации с `n + 1` получается квадрат какого - либо числа."})

(def signature
  {:input [{:argument-name "number" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (gen/tuple (gen/elements [183
                            328
                            528
                            (gen/generate (gen/choose 184 600))
                            (gen/generate (gen/choose 184 600))
                            (gen/generate (gen/choose 184 600))
                            (gen/generate (gen/choose 184 600))])))

(def test-data
  [{:expected true :arguments [183]}
   {:expected true :arguments [328]}
   {:expected true :arguments [528]}
   {:expected false :arguments [322]}])

(defn solution [number]
  (let [concated (Integer/parseInt (str number (inc number)))
        perfect-square (rem (Math/sqrt concated) 1)]
    (zero? perfect-square)))
