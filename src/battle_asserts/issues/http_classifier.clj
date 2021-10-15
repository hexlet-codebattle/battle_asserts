(ns battle-asserts.issues.http-classifier
  (:require [clojure.test.check.generators :as gen]
            [clojure.spec.alpha :as s]))

(def level :elementary)

(def tags ["training" "strings" "network"])

(def description
  {:en "Return type of provided http status `code`. Checkout examples for types. If code has no classification, return \"unrecognized\""
   :ru "Верните тип полученного http `code`. В примерах приведены возможные типы кодов. Если код не поддается классификации, то верните \"unrecognized\""})

(def signature
  {:input [{:argument-name "code" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple (gen/one-of [(gen/choose 0 1000)
                          (gen/choose 100 199)
                          (gen/choose 200 299)
                          (gen/choose 300 399)
                          (gen/choose 400 499)
                          (gen/choose 500 599)])))

(def test-data
  [{:expected "informational" :arguments [121]}
   {:expected "success" :arguments [201]}
   {:expected "redirection" :arguments [333]}
   {:expected "client error" :arguments [418]}
   {:expected "server error" :arguments [500]}])

(defn solution [code]
  (cond
    (s/int-in-range? 100 199 code) "informational"
    (s/int-in-range? 200 299 code) "success"
    (s/int-in-range? 300 399 code) "redirection"
    (s/int-in-range? 400 499 code) "client error"
    (s/int-in-range? 500 599 code) "server error"
    :else "unrecognized"))
