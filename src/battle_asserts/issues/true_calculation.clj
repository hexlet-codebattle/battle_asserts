(ns battle-asserts.issues.true-calculation
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Bob answers 'Sure.' if you ask him a question.
                 He answers 'Whoa, chill out!' if you yell at him.
                 He says 'Fine. Be that way!' if you address him without actually saying anything.
                 He answers 'Whatever.' to anything else.  ")

(def signature
  {:input [{:argument-name "facts" :type {:name "array" :nested {:name "boolean"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector gen/boolean 5 10)))

(def test-data
  [{:expected 4 :arguments [[true true false true false true]]}
   {:expected 5 :arguments [[false true false true true true true]]}
   {:expected 1 :arguments [[false false false true]]}])

(defn solution [facts]
  (count (filter true? facts)))
