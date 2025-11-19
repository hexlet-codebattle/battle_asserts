;; Moved to modern repository
(ns battle-asserts.issues.case-identify
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :easy)

(def tags ["strings"])

(def description
  {:en "You are given a string that represents variable name. You need to identify which naming convention used for this variable."
   :ru "Определите, какое соглашение об именовании используется для переданного названия переменной."})

(def signature
  {:input [{:argument-name "variable" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn var-name-generator []
  (let [len (gen/generate (gen/choose 2 4))
        gen-size 5]
    (letfn [(flat-case [] (s/join "" (faker/words {:n len})))
            (snake-case [] (s/join "_" (faker/words {:n len})))
            (camel-case [] (s/join "" (conj (map s/capitalize (faker/words {:n len}))
                                            (faker/word))))
            (kebab-case [] (s/join "-" (faker/words {:n len})))
            (train-case [] (s/join "-" (map s/capitalize (faker/words {:n len}))))]
      (vec (concat (repeatedly gen-size flat-case)
                   (repeatedly gen-size snake-case)
                   (repeatedly gen-size camel-case)
                   (repeatedly gen-size kebab-case)
                   (repeatedly gen-size train-case))))))

(defn arguments-generator []
  (gen/tuple (gen/elements (var-name-generator))))

(def test-data
  [{:expected "flat case" :arguments ["somevar"]}
   {:expected "snake case" :arguments ["some_var"]}
   {:expected "camel case" :arguments ["someVar"]}
   {:expected "kebab case" :arguments ["some-var"]}
   {:expected "train case" :arguments ["Some-Var"]}])

(defn solution [variable]
  (str
   (cond
     (s/includes? variable "_") "snake"
     (and (s/includes? variable "-")
          (= (s/upper-case (first variable))
             (str (first variable)))) "train"
     (s/includes? variable "-") "kebab"
     (= (s/lower-case variable) variable) "flat"
     :else "camel")
   " case"))
