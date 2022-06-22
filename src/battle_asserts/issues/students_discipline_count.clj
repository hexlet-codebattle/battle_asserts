(ns battle-asserts.issues.students-discipline-count
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]
            [battle-asserts.utility :as util]))

(def level :elementary)

(def disabled true)

(def tags ["training" "collections" "hash-maps" "harry-potter" "star-wars"])

(def description
  {:en "Given an array that contains the following information about a student: `student-name` - the student's name/surname and `discipline` - the subject that student is studying.
        Return the hash in which the statistics of students studying in each course are counted. The order corresponds to the order of the courses in the original array."
   :ru "Дан массив, в котором содержится следующая информация о студенте: `student-name` - имя/фамилия студента и `discipline` - предмет, который студент изучает.
        Верните хеш в котором подсчитана статистика студентов обучающихся на каждом предмете. Порядок соответствует порядку следования дисциплин в исходном массиве."})

(def signature
  {:input  [{:argument-name "students" :type {:name "array" :nested {:name "hash" :nested {:name "string"}}}}]
   :output {:type {:name "hash" :nested {:name "integer"}}}})

(defn arguments-generator []
  (letfn [(student-name [] (util/gen-name))
          (student-names [] (repeatedly 30 student-name))
          (student-disciplines [] (conj (map s/capitalize (repeatedly 3 faker/word)) "Chemistry" "Jedi" "Magic" "Programming" "Dance" "Biology"))
          (student-info-gen [] (gen/generate (gen/hash-map :student-name (gen/elements (student-names)) :discipline (gen/elements (student-disciplines)))))]
    (let [students (repeatedly 50 student-info-gen)]
      (gen/tuple (gen/vector (gen/elements students) 3 10)))))

(def test-data
  [{:expected {"Magic" 1}
    :arguments [[{:student-name "Harry Potter" :discipline "Magic"}]]}
   {:expected {"Magic" 3}
    :arguments [[{:student-name "Harry Potter" :discipline "Magic"} {:student-name "Hermione Granger" :discipline "Magic"} {:student-name "Ronald Weasley" :discipline "Magic"}]]}
   {:expected {"Jedi" 1, "Magic" 1, "Chemistry" 1}
    :arguments [[{:student-name "Luke Skywalker" :discipline "Jedi"} {:student-name "Hermione Granger" :discipline "Magic"} {:student-name "Walter White" :discipline "Chemistry"}]]}])

(defn solution [students]
  (->> students
       (map :discipline)
       (frequencies)))
