(ns battle-asserts.issues.join-url-params
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Написать функцию, возвращающую строку запроса собранную из данного адреса и мапа с параметрами")

(defn arguments-generator []
  (letfn [(address []
            (str "http://" (faker/word {:lang :en}) ".com"))
          (gen-word []
                    (gen/elements (faker/words {:lang :en :n 50})))
          (gen-keyword []
                       (gen/elements (map keyword (faker/words {:lang :en :n 50}))))]
    (gen/tuple (gen/return (address))
               (gen/map (gen-keyword) (gen/one-of [(gen-word)
                                                   gen/int])))))

(defn solution [url params]
  (let [ordered-params (into (sorted-map) params)]
    (str url "?"
         (clojure.string/join "&"
                              (map #(str % "=" (ordered-params (keyword %)))
                                   (map name (keys ordered-params)))))))
