(ns battle-asserts.issues.build-key-value
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def disabled true)

(def level :medium)

(def description "Flatten the given hash.
  This is how HTTP libraries pack data when POST requests are made using x-www-form-urlencoded.")

(def signature
  {:input [{:argument-name "words" :type {:name "array" :nested {:name "string"}}}
           {:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "hash" :nested {:name "integer"}}}})

(defn arguments-generator []
  (let [word-generator (gen/elements (faker/words {:lang :en :n 30}))]
    (gen/tuple (gen/map word-generator
                        (gen/one-of [gen/small-integer
                                     gen/boolean
                                     word-generator
                                     (gen/recursive-gen
                                      #(gen/one-of [(gen/vector % 1 4) (gen/map word-generator %)])
                                      (gen/one-of [gen/boolean gen/small-integer]))])))))

(def test-data
  [{:expected {"x[0]" "1" "x[1]" "2" "x[2]" "3"}
    :arguments [{"x" ["1" "2" "3"]}]}
   {:expected {"a[b]" 3}
    :arguments [{"a" {"b" 3}}]}
   {:expected {"a[d][0]" 1 "a[d][1]" 2}
    :arguments [{"a" {"d" [1 2]}}]}
   {:expected {"a[b]" 3 "a[c]" 2 "a[d][0]" 1 "a[d][1]" 2 "x[0]" "1" "x[1]" "2" "x[2]" "3"}
    :arguments [{"a" {"b" 3 "c" 2 "d" [1 2]} "x" ["1" "2" "3"]}]}])

(defn solution [hash]
  (letfn
   [(func [acc [k v]]
      (cond
        (map? v) (merge acc
                        (solution (map #(assoc % 0 (format "%s[%s]" k (% 0)))
                                       v)))
        (vector? v) (merge acc
                           (solution (map-indexed #(vector (format "%s[%d]" k %1) %2)
                                                  v)))
        :else (assoc acc k v)))]
    (reduce func {} (vec hash))))
