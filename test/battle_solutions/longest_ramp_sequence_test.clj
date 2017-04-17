(ns battle-solutions.longest-ramp-sequence-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn get-state
  [value1 value2]
  (if (= value1 value2)
    0
    (if (> value1 value2) -1 1)))


(defn find-longest-ramp-sequence
  [numbers]
  (if (empty? numbers)
    0
    (loop [state 0 current-length 1 max-length 0 v (rest numbers) previous-number (first numbers)]
     (if (empty? v)
       (max max-length current-length)
       (let [current-number (first v) new-state (get-state previous-number current-number)]
         (if(or (= state new-state) (zero? new-state))
           (if(= current-number previous-number)
             (recur new-state 1 (max max-length current-length) (rest v) current-number)
             (recur new-state 2 (max max-length current-length) (rest v) current-number))
           (recur new-state (inc current-length) max-length (rest v) current-number))))))
  )


(deftest test-asserts
  (assert-equal 0 (find-longest-ramp-sequence []))
  (assert-equal 1 (find-longest-ramp-sequence [1]))
  (assert-equal 1 (find-longest-ramp-sequence [5 5 5]))
  (assert-equal 2 (find-longest-ramp-sequence [1 2 3]))
  (assert-equal 6 (find-longest-ramp-sequence [1 2 1 3 -2 5]))
  (assert-equal 3 (find-longest-ramp-sequence [1 2 3 3 1 -4 -6 0 1 -1]))
  (assert-equal 4 (find-longest-ramp-sequence [1 3 5 5 4 2 0 1 -1])))
