(ns battle-asserts.issues.grade-calculator
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["strings" "collections"])

(def description
  {:en "Calculate grade of five student subjects. If grade average is bigger than 90, final grade is A. If grade average is bigger than 80, final grade is B. If grade average is bigger than 70, final grade is C. If grade average is bigger than 60, final grade is D. In other cases final grade is F."
   :ru "Рассчитайте финальную оценку студента по пяти предметам. Если средняя оценка больше 90, то итоговая `A`. Если средняя оценка больше 80, то итоговая `B`. Если средняя оценка больше 70, то итоговая оценка `C`. Если средняя оценка больше 60, то итоговая оценка `D`. В остальных случаях итоговая оценка `F`."})

(def signature
  {:input [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple (gen/not-empty (gen/vector (gen/choose 0 100) 5))))

(def test-data
  [{:arguments [[90 91 99 93 100]]
    :expected "Grade: A"}
   {:arguments [[92 77 85 84 84]]
    :expected "Grade: B"}
   {:arguments [[70 72 78 72 70]]
    :expected "Grade: C"}
   {:arguments [[60 61 62 63 70]]
    :expected "Grade: D"}
   {:arguments [[50 42 20 31 0]]
    :expected "Grade: F"}
   {:arguments [[10 9 2 3 5]]
    :expected "Grade: F"}])

(defn solution [grades]
  (let [avg-grade (int (/ (apply + grades) 5))]
    (cond
      (> avg-grade 90) "Grade: A"
      (> avg-grade 80) "Grade: B"
      (> avg-grade 70) "Grade: C"
      (> avg-grade 60) "Grade: D"
      :else "Grade: F")))
