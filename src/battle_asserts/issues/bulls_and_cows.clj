(ns battle-asserts.issues.bulls-and-cows
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "On a sheet of paper, the players each write a 4-digit secret number.
                  The digits must be all different. Then, in turn, the players try to guess
                  their opponent's number who gives the number of matches. If the matching
                  digits are in their right positions, they are `bulls`, if in different
                  positions, they are `cows`. Write the function that calculates `bulls` and `cows`.")

(def signature
  {:input [{:argument-name "secret" :type {:name "integer"}}
           {:argument-name "guess" :type {:name "integer"}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1000 9999) (gen/choose 1000 9999)))

(def test-data
  [{:expected [1 2] :arguments [4271 1234]}
   {:expected [0 1] :arguments [4271 5682]}
   {:expected [4 0] :arguments [4271 4271]}
   {:expected [1 1] :arguments [4182 4273]}])

(defn solution [secret guess]
  (let [str-secret (str secret)
        str-guess (str guess)
        bulls (->> (map = str-secret str-guess) (filter true?) count)
        cows  (->> str-guess (filter (set str-secret)) count  (+ (- bulls)))]
    [bulls cows]))
