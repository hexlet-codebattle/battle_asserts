(ns battle-asserts.issues.split-names
  (:require [clojure.test.check.generators :as gen]
            [battle-asserts.utility :as util]
            [clojure.string :as s]))

(def level :elementary)

(def tags ["strings" "hash-maps"])

(def description
  {:ru "Дан массив со строками, представляющими собой комбинацию имени и фамилии. Создайте функцию, которая извлекает имя и фамилию в хэш-мапу и добавляет ее в итоговый массив."
   :en "Given array with strings, that represents combination of first name and last name. Create a function, that extracts first and last name in hash-map and adds it to result array. Keep order the same."})

(def signature {:input [{:argument-name "names" :type {:name "array" :nested {:name "string"}}}]
                :output {:type {:name "array" :nested {:name "hash" :nested {:name "string"}}}}})

(defn arguments-generator []
  (let [names (repeatedly 30 util/gen-name)]
    (gen/tuple (gen/vector (gen/elements names) 3 8))))

(def test-data
  [{:expected [{:first "John" :last "Doe"}] :arguments [["John Doe"]]}
   {:expected [{:first "Harold" :last "Abelson"} {:first "Julie" :last "Sussman"}] :arguments [["Harold Abelson" "Julie Sussman"]]}])

(defn solution [names]
  (reduce
   (fn [acc name] (let [splitted (s/split name #" " 2)] (conj acc {:first (first splitted) :last (last splitted)})))
   [] names))
