(ns battle-solutions.between-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn between
  [string left right]
  (-> (str left "(.*)" right)
      re-pattern
      (re-find string)
      rest))

(deftest test-asserts
  (let [str1 "<a>foo</a>"
        str2 ["foo"]]
    (assert-equal str2 (between str1 "<a>" "</a>")))
  (let [str1 "this is 'text'"
        str2 ["text"]]
    (assert-equal str2 (between str1 "'" "'")))
  (let [str1 "www.google.com"
        str2 ["google"]]
    (assert-equal str2 (between str1 "www." ".com")))
  (let [str1 "xonix"
        str2 ["oni"]]
    (assert-equal str2 (between str1 "x" "x"))))
