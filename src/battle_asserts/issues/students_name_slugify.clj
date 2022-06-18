(ns battle-asserts.issues.students-name-slugify
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]
            [faker.name :as f]))

(def level :elementary)

(def tags ["training" "collections" "hash-maps"])

(def description
  {:en "Given an array that contains the following information about a student: `student-name` - the student's name/surname and `discipline` - the subject that student is studying.
        Return the array of names formatted by the following rule `Harry Potter` -> `harry-potter`."
   :ru "Дан массив, в котором содержится следующая информация о студенте: `student-name` - имя/фамилия студента и `discipline` - предмет, который студент изучает.
        Верните массив имен отформатированных по следующему правилу `Harry Potter` -> `harry-potter`."})

(def signature
  {:input  [{:argument-name "students" :type {:name "array" :nested {:name "hash" :nested {:name "string"}}}}]
   :output {:type {:name "array", :nested {:name "string"}}}})

(defn arguments-generator []
  (letfn [(student-name [] (s/join " " [(f/first-name) (f/last-name)]))
          (student-names [] (repeatedly 40 student-name))
          (student-disciplines [] (conj (map s/capitalize (repeatedly 10 faker/word)) "Chemistry" "Jedi" "Magic" "Programming" "Dance" "Biology"))
          (student-info-gen [] (gen/generate (gen/hash-map :student-name (gen/elements (student-names)) :discipline (gen/elements (student-disciplines)))))]
    (let [students (repeatedly 50 student-info-gen)]
      (gen/tuple (gen/vector (gen/elements students) 1 10)))))

(def test-data
  [{:expected ["harry-potter"]
    :arguments [[{:student-name "Harry Potter" :discipline "Magic"}]]}
   {:expected ["luke-skywalker" "hermione-granger" "walter-white"]
    :arguments [[{:student-name "Luke Skywalker" :discipline "Jedi"} {:student-name "Hermione Granger" :discipline "Magic"} {:student-name "Walter White" :discipline "Chemistry"}]]}])

(defn- student-names
  ([] (map :student-name))
  ([students] (sequence (student-names) students)))

(defn- lowercase-names
  ([] (map  clojure.string/lower-case))
  ([students] (sequence (lowercase-names) students)))

(defn- slugify-names
  ([] (map #(clojure.string/replace % #" " "-")))
  ([students] (sequence (slugify-names) students)))

(defn solution [students]
  (into [] (comp (student-names) (lowercase-names) (slugify-names)) students))
