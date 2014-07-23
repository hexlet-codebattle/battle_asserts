(ns battle-solutions.parse-post-form-params-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]
            [clojure.string :as str]))

(defn- transform [acc [chain v]]
  (if (= (last chain) "[]")
    (update-in acc (drop-last chain) concat [v])
    (assoc-in acc chain v)))

(defn parse-http-params [s]
  (letfn [(nesting-chain [expr] (re-seq #"[a-z]|\[\]" expr))]
    (->>
      (str/split s #"&")
      (map #(str/split % #"="))
      (map #(vector (nesting-chain (first %)) (second %)))
      (reduce transform {}))))

(deftest test-asserts
  (let [baby { "a" { "b" "1" } }]
    (assert-equal baby (parse-http-params "a[b]=1")))
  (let [easy { "a" { "b" "1" "c" "2" } }]
    (assert-equal easy (parse-http-params "a[b]=1&a[c]=2")))
  (let [medium { "a" { "b" "2" "c" "2" "d" { "e" "3" } } "x" '("1" "2") }]
    (assert-equal medium (parse-http-params "a[b]=2&a[c]=2&a[d][e]=3&x[]=1&x[]=2")))
  (let [hard { "a" { "b" "1" "c" "2" "d" { "e" "3" } "f" '("24" "42") } }]
    (assert-equal hard (parse-http-params "a[b]=1&a[c]=2&a[d][e]=3&a[f][]=24&a[f][]=42")))
  (let [nightmare { "a" { "b" { "w" { "y" "3" } } "c" "2" "d" '("1" "2") } "x" '("1" "2" "3") }]
    (assert-equal nightmare (parse-http-params "a[b][w][y]=3&a[c]=2&a[d][]=1&a[d][]=2&x[]=1&x[]=2&x[]=3"))))
