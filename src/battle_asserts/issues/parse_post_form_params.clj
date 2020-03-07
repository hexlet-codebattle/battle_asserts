(ns battle-asserts.issues.parse-post-form-params
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :medium)

(def description "Parse a given request string.")

(def disabled true)

(defn to-query [value]
  (letfn [(to-string [name value]
            (cond
              (map? value) (map #(to-string (str name "[" (first %) "]") (second %)) value)
              (or (vector? value) (seq? value)) (map #(to-string (str name "[]") %) value)
              :else (str name "=" value)))]
    (s/join "&" (flatten (map #(apply to-string %) value)))))

(defn arguments-generator []
  (let [word-generator (gen/elements (faker/words {:lang :en :n 30}))]
    (gen/tuple (gen/bind (gen/map word-generator
                                  (gen/one-of [gen/small-integer
                                               word-generator
                                               (gen/recursive-gen
                                                #(gen/map word-generator %)
                                                (gen/one-of [gen/small-integer
                                                             (gen/vector gen/small-integer)]))]))
                         #(gen/return (to-query %))))))

(def test-data
  [{:expected {"a" {"b" "2" "c" "2" "d" {"e" "3"}} "x" '("1" "2")}
    :arguments ["a[b]=2&a[c]=2&a[d][e]=3&x[]=1&x[]=2"]}
   {:expected {"a" {"b" "1"}}
    :arguments ["a[b]=1"]}
   {:expected {"a" {"b" "1" "c" "2"}}
    :arguments ["a[b]=1&a[c]=2"]}
   {:expected {"a" {"b" "1" "c" "2" "d" {"e" "3"} "f" '("24" "42")}}
    :arguments ["a[b]=1&a[c]=2&a[d][e]=3&a[f][]=24&a[f][]=42"]}
   {:expected {"a" {"b" {"w" {"y" "3"}} "c" "2" "d" '("1" "2")} "x" '("1" "2" "3")}
    :arguments ["a[b][w][y]=3&a[c]=2&a[d][]=1&a[d][]=2&x[]=1&x[]=2&x[]=3"]}])

(defn- transform [acc [chain v]]
  (cond
    (nil? chain) acc
    (= (last chain) "[]") (update-in acc (drop-last chain) concat [v])
    :else (assoc-in acc chain v)))

(defn solution [s]
  (letfn [(nesting-chain [expr] (re-seq #"[a-z]+|\[\]" expr))]
    (->>
     (s/split s #"&")
     (map #(s/split % #"="))
     (map #(vector (nesting-chain (first %)) (second %)))
     (reduce transform {}))))
