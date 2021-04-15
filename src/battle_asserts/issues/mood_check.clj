(ns battle-asserts.issues.mood-check
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["strings"])

(def description "Create a function that takes in a current mood and return a sentence in the following format from tests.")

(def signature
  {:input [{:argument-name "mood" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (let [moods ["" "happy" "sad" "good" "excited" "confused"]]
    (gen/tuple (gen/elements moods))))

(def test-data
  [{:expected "Today, I am feeling neutral." :arguments [""]}
   {:expected "Today, I am feeling happy." :arguments ["happy"]}
   {:expected "Today, I am feeling sad." :arguments ["sad"]}
   {:expected "Today, I am feeling good." :arguments ["good"]}
   {:expected "Today, I am feeling excited." :arguments ["excited"]}
   {:expected "Today, I am feeling confused." :arguments ["confused"]}])

(defn solution [mood]
  (if (empty? mood)
    "Today, I am feeling neutral."
    (str "Today, I am feeling " mood ".")))
