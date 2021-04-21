(ns battle-asserts.issues.secret-function
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["games" "math"])

(def description "Create a function based on the input and output.
                  Look at the examples, there is a pattern.
                  First number in range `[1, 7]`, second in `[0 8]`.
                  Operations `pow`, `*` and `-` can be helpful.")

(def signature
  {:input [{:argument-name "first" :type {:name "integer"}}
           {:argument-name "second" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 7)
             (gen/choose 0 8)))

(def test-data
  [{:expected 8 :arguments [2 4]}
   {:expected 8 :arguments [4 2]}
   {:expected -4 :arguments [1 5]}
   {:expected 15 :arguments [5 2]}
   {:expected 322 :arguments [7 3]}])

(defn solution [first-num second-num]
  (int (-
        (Math/pow first-num second-num)
        (* first-num second-num))))
