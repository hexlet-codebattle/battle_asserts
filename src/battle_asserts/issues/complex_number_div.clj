(ns battle-asserts.issues.complex-number-div
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["math"])

(def description
  {:en "Implement a function that divide two complex numbers. Return result as array where first number is real part and second number is imaginary part. Use floor rounding."
   :ru "Создайте функцию, которая делит два комплексных числа. Верните результат как массив, где первое число - реальная часть, а вторая - мнимая. Результаты округлите в меньшую сторону."})

(def signature
  {:input  [{:argument-name "first" :type {:name "array" :nested {:name "integer"}}}
            {:argument-name "second" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator
  []
  (gen/tuple (gen/tuple gen/small-integer gen/small-integer)
             (gen/tuple gen/small-integer (gen/choose 0 50))))

(def test-data
  [{:expected [1 2] :arguments [[3 10] [3 1]]}
   {:expected [12 -6] :arguments [[30 2] [2 1]]}
   {:expected [-1 -8] :arguments [[77 -11] [1 11]]}
   {:expected [-3 -2] :arguments [[30 11] [-12 1]]}])

(defn solution [[real1 img1] [real2 img2]]
  (try
    (letfn [(real-part [first-pair second-pair]
              (int (Math/floor (/ (+ (* (first first-pair) (first second-pair)) (* (last first-pair) (last second-pair)))
                                  (+ (* (first second-pair) (first second-pair)) (* (last second-pair) (last second-pair)))))))
            (imaginary-part [first-pair second-pair]
              (int (Math/floor (/ (- (* (last first-pair) (first second-pair)) (* (first first-pair) (last second-pair)))
                                  (+ (* (first second-pair) (first second-pair)) (* (last second-pair) (last second-pair)))))))]
      (let
       [real (real-part [real1 img1] [real2 img2])
        img  (imaginary-part [real1 img1] [real2 img2])]
        [real img]))
    (catch ArithmeticException _e [0 0])))
