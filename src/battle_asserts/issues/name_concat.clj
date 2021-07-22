(ns battle-asserts.issues.name-concat
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.name :as fk]))

(def level :elementary)

(def tags ["training" "strings" "collections" "hash-maps"])

(def description
  {:en "Implement a function that combine first and last name from hash into full name."
   :ru "Создайте функцию, которая извлекает из хеша имя и фамилию и собирает их в одну строку."})

(def signature
  {:input [{:argument-name "names" :type {:name "array" :nested {:name "hash" :nested {:name "string"}}}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (let [first-names (repeatedly 40 fk/first-name)
        last-names  (repeatedly 40 fk/last-name)
        names (repeatedly 50 (fn [] {:first (rand-nth first-names) :last (rand-nth last-names)}))]
    (gen/tuple (gen/vector (gen/elements names) 3 10))))

(def test-data
  [{:expected ["John Doe" "Rich Hickey"]
    :arguments [[{:first "John" :last "Doe"} {:first "Rich" :last "Hickey"}]]}
   {:expected ["Harold Abelson" "Gerald Jay Sussman" "Pat Shaughnessy" "Matthias Felleisen"]
    :arguments [[{:first "Harold" :last "Abelson"} {:first "Gerald" :last "Jay Sussman"} {:first "Pat" :last "Shaughnessy"} {:first "Matthias" :last "Felleisen"}]]}])

(defn solution [names]
  (mapv (fn [name] (s/join #" " [(name :first) (name :last)])) names))

