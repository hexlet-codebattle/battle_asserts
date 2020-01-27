(ns battle-asserts.issues.bob
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Bob answers 'Sure.' if you ask him a question.
                 He answers 'Whoa, chill out!' if you yell at him.
                 He says 'Fine. Be that way!' if you address him without actually saying anything.
                 He answers 'Whatever.' to anything else.  ")

(def signature
  {:input [{:argument-name "sentence" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (letfn [(question []
            (gen/elements (map #(str % "?") (faker/words {:lang :en :n 50}))))
          (yell []
            (gen/elements (map s/upper-case (faker/words {:lang :en :n 50}))))
          (silence []
            (gen/elements (repeatedly 50 #(s/join (repeat (rand-int 5) " ")))))
          (whatever []
            (gen/elements (faker/words {:lang :en :n 50})))
          (random-sentence []
            (let [expressions [#(format "1, 2, 3 %s!" (s/upper-case %))
                               #(str (s/upper-case %) "!!1!!1!")
                               #(str (s/upper-case %) "?")
                               #(str % " ? " %)
                               #(str "It is " (s/upper-case %))]]
              (gen/elements (map #((rand-nth expressions) %) (faker/words {:lang :en :n 50})))))]
    (gen/tuple (gen/one-of [(question)
                            (yell)
                            (silence)
                            (whatever)
                            (random-sentence)]))))

(def test-data
  [{:expected "Whoa, chill out!"
    :arguments ["1, 2, 3 GO!"]}
   {:expected "Whoa, chill out!"
    :arguments ["ZOMBIES ARE COMING!!11!!1!"]}
   {:expected "Whoa, chill out!"
    :arguments ["WHAT THE HELL WERE YOU THINKING?"]}
   {:expected "Whatever."
    :arguments ["It is OK."]}
   {:expected "Whatever."
    :arguments ["Ending with ? means a question."]}
   {:expected "Fine. Be that way!"
    :arguments [""]}
   {:expected "Fine. Be that way!"
    :arguments ["    "]}
   {:expected "Sure."
    :arguments ["4?"]}])

(defn solution [sentence]
  (cond
    (and (= (s/upper-case sentence) sentence)
         (re-find #"[A-Z]" sentence)) "Whoa, chill out!"
    (= (last sentence) \?) "Sure."
    (s/blank? sentence) "Fine. Be that way!"
    :else "Whatever."))
