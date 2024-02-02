(ns battle-asserts.issues.underscore
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :medium)

(def tags ["strings"])

(def description
  {:en "Given a text with words separated in different ways, return an underscored, lowercase form."
   :ru "Дан текст со словами, которые разделены разными способами, верните текст, в котором слова разделены нижними подчеркиваниями и обращены в нижний регистр."})

(def signature
  {:input  [{:argument-name "text" :type {:name "string"}}]
   :output {:type {:name "string"}}})

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
   (s/replace #"-" "_")
   (s/replace #"([a-z\d])([A-Z])" "$1_$2")
   (s/replace #"([A-Z+])([A-Z][a-z])" "$1_$2")
   (s/replace #"::" "/")
   s/lower-case))
