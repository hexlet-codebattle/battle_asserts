(ns battle-solutions.build-key-value-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn b-key-val [hash]
  (letfn
    [(func [acc [k v]]
       (condp = (type v)
         clojure.lang.PersistentArrayMap (merge acc (b-key-val (map #(assoc % 0 (format "%s[%s]" k (% 0))) v)))
         clojure.lang.PersistentVector (merge acc (b-key-val (map-indexed #(vector (format "%s[%d]" k %1) %2) v)))
         (assoc acc k v)))]
    (reduce func {} (into [] hash))))

(deftest test-asserts
  (let [easy {"a[b]" 3}]
    (assert-equal easy (b-key-val {"a" {"b" 3}})))
  (let [medium {"x[0]" "1" "x[1]" "2" "x[2]" "3"}]
    (assert-equal medium (b-key-val {"x" ["1" "2" "3"]})))
  (let [hard {"a[d][0]" 1 "a[d][1]" 2}]
    (assert-equal hard (b-key-val {"a" { "d" [1 2]}})))
  (let [nightmare {"a[b]" 3 "a[c]" 2 "a[d][0]" 1 "a[d][1]" 2 "x[0]" "1" "x[1]" "2" "x[2]" "3"}]
    (assert-equal nightmare (b-key-val {"a" {"b" 3 "c" 2 "d" [1 2]} "x" ["1" "2" "3"]}))))
