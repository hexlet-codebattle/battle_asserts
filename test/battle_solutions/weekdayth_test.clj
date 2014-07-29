(ns battle-solutions.weekdayth-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))


(defn weekdayth []
  (let [new-method "(defn %sth? [d#] (= \"%s\" (clojure.string/lower-case (.format (java.text.SimpleDateFormat. \"EEEE\"(. java.util.Locale US))(.parse (java.text.SimpleDateFormat. \"d.M.y.Z\")(str d# \".+0000\"))))))"
        weekdays ["monday" "tuesday" "wednesday" "thursday" "friday" "saturday" "sunday"]]
    (map #(load-string (format new-method % %)) weekdays)
    ))


(defn mondayth? [d] ((first (weekdayth)) d))
(defn tuesdayth? [d] ((nth (weekdayth) 1) d))
(defn sundayth? [d] ((nth (weekdayth) 6) d))

(deftest test-asserts
  (assert-equal true (mondayth? "22.02.1993"))
  (assert-equal false (sundayth? "22.02.1993"))
  (assert-equal true (tuesdayth? "29.07.2014")))
