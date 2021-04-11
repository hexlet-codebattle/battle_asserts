(ns battle-asserts.issues.same-bread
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["training" "collections"])

(def description "Two arrays, which represent two sandwiches are given.
                  Return whether both sandwiches use the same type of bread.")

(def signature
  {:input [{:argument-name "first" :type {:name "array" :nested {:name "string"}}}
           {:argument-name "second" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (let [breads ["white bread" "brown bread" "toast"]
        fillings ["tomato" "chicken" "ham" "cheese"]]
    (gen/tuple (gen/tuple (gen/elements breads) (gen/elements fillings) (gen/elements breads))
               (gen/tuple (gen/elements breads) (gen/elements fillings) (gen/elements breads)))))

(def test-data
  [{:expected true
    :arguments [["white bread" "tomato" "white bread"] ["white bread" "chicken" "white bread"]]}
   {:expected false
    :arguments [["toast" "chicken" "brown bread"] ["brown bread" "chicken" "white bread"]]}
   {:expected true
    :arguments [["toast" "cheese" "toast"] ["toast" "ham" "toast"]]}])

(defn solution [first-sandwich second-sandwich]
  (let [first-bread-pair (vector (first first-sandwich) (last first-sandwich))
        second-bread-pair (vector (first second-sandwich) (last second-sandwich))]
    (= first-bread-pair second-bread-pair)))
