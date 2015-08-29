(ns battle-solutions.nrzi-encoding-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn nrzi-encoding
  [coded-seq]
  (letfn [(interpreter
            [num]
            (apply str "1" (repeat (- num 1)
                                   "0")))]
    (-> coded-seq
        (clojure.string/split #"\|")
        (->> (map #(interpreter (count %)))
             (clojure.string/join))
        (clojure.string/replace-first #"1" "0"))))

(deftest test-asserts
  (let [input "¯|___|¯¯¯¯¯|___|¯|_|¯"
        output "010010000100111"]
    (assert-equal output (nrzi-encoding input)))
  (let [input "¯|__|¯|___|¯¯"
        output "010110010"]
    (assert-equal output (nrzi-encoding input)))
  (let [input "_|¯¯¯|_|¯¯¯¯|_|¯¯"
        output "010011000110"]
    (assert-equal output (nrzi-encoding input))))
