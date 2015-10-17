(ns battle-asserts.issues.build-key-value
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :medium)

(def description "Write function, which flatten given hash.
                 Example:
                 Input: { \"x\" => [1, 2, 3]}
                 Output: {\"x[0]\" => l, \"x[1]\" => 2,  \"x[2]\" => 3}")

(defn arguments-generator []
  (let [word-generator (gen/elements (faker/words {:lang :en :n 30}))]
    (gen/tuple (gen/map word-generator
                        (gen/one-of [gen/int
                                     gen/boolean
                                     word-generator
                                     (gen/recursive-gen
                                      #(gen/one-of [(gen/vector %) (gen/map word-generator %)])
                                      (gen/one-of [gen/boolean gen/int]))])))))

(def test-data
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn solution [hash]
  (letfn
   [(func [acc [k v]]
      (condp = (type v)
        clojure.lang.PersistentArrayMap (merge acc
                                               (solution (map #(assoc % 0 (format "%s[%s]" k (% 0)))
                                                              v)))
        clojure.lang.PersistentVector (merge acc
                                             (solution (map-indexed #(vector (format "%s[%d]" k %1) %2)
                                                                    v)))
        (assoc acc k v)))]
    (reduce func {} (into [] hash))))
