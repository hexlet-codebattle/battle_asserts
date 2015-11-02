(ns battle-asserts.issues.underscore
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Given a string with words separated in different ways, return an underscored, lowercase form.")

(defn arguments-generator []
  (letfn [(camel-case []
            (let [words (faker/words {:lang :en :n (inc (rand-int 6))})
                  separators (repeatedly (count words) #(rand-nth ["::" ""]))
                  words-with-separators (drop-last (interleave words separators))]
              (s/join (map s/capitalize words-with-separators))))]
    (gen/tuple (gen/elements (repeatedly 50 camel-case)))))

(def test-data
  [{:expected "active_model/errors"
    :arguments ["ActiveModel::Errors"]}
   {:expected "hello_how_are_you"
    :arguments ["HelloHowAreYou"]}
   {:expected "my_name_is_bond_james_bond"
    :arguments ["MyNAMEIsBOND-JamesBond"]}
   {:expected "main_company/best_main_user"
    :arguments ["MAINCompany::BEST-MAINUser"]}])

(defn solution [string]
  (->
   string
   (clojure.string/replace #"-" "_")
   (clojure.string/replace #"([a-z\d])([A-Z])" "$1_$2")
   (clojure.string/replace #"([A-Z+])([A-Z][a-z])" "$1_$2")
   (clojure.string/replace #"::" "/")
   clojure.string/lower-case))
