(ns battle-solutions.time-difference-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn time-difference
  [str1 str2]
  (defn to-int [s]
    (Integer/parseInt s))
  (->> (map #(->> (clojure.string/split % #"\:")
                  (map to-int)
                  (map * [3600 60 1])
                  (reduce +))
            [str1 str2])
       (reduce -)
       Math/abs))

(deftest test-asserts
  (let [str1 "09:40:22"
        str2 "12:33:55"
        output 10413]
    (assert-equal output (time-difference str1 str2)))
  (let [str1 "15:02:43"
        str2 "11:11:24"
        output 13879]
    (assert-equal output (time-difference str1 str2)))
  (let [str1 "00:00:00"
        str2 "22:23:15"
        output 80595]
    (assert-equal output (time-difference str1 str2)))
  (let [str1 "20:24:58"
        str2 "20:24:57"
        output 1]
    (assert-equal output (time-difference str1 str2))))
