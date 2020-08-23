(ns battle-asserts.issues.luke-relations
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Luke Skywalker has family and friends. Help him remind them who is who. Given a string with a name, return the relation of that person to Luke.
                  Person      | Relation
                  Darth Vader | father
                  Leia	      | sister
                  Han         | brother in law
                  R2D2        | droid")

(def signature
  {:input [{:argument-name "person" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/elements ["Darth Vader" "Leia" "Han" "R2D2"])))

(def test-data
  [{:expected "Luke, I am your father." :arguments ["Darth Vader"]}
   {:expected "Luke, I am your sister." :arguments ["Leia"]}
   {:expected "Luke, I am your droid." :arguments ["R2D2"]}
   {:expected "Luke, I am your brother in law." :arguments ["Han"]}])

(def relations-map
  {"Darth Vader" "father"
   "Leia" "sister"
   "R2D2" "droid"
   "Han" "brother in law"})

(defn solution [person]
  (str "Luke, I am your " (relations-map person) "."))
