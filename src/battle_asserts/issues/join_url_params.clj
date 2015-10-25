(ns battle-asserts.issues.join-url-params
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write a function which returns a query string (URL) generated from the given address and a hash map of parameters.")

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

(def test-data
  [{:expected "http://www.foobar.com?first_param=123&second_param=456&third_param=678"
    :arguments ["http://www.foobar.com"
                {:first_param 123
                 :second_param 456
                 :third_param 678}]}
   {:expected "http://www.example.com/search?q=findme&useragent=chrome"
    :arguments ["http://www.example.com/search"
                {:q "findme"
                 :useragent "chrome"}]}])

(defn solution [url params]
  (let [ordered-params (into (sorted-map) params)]
    (str url "?"
         (clojure.string/join "&"
                              (map #(str % "=" (ordered-params (keyword %)))
                                   (map name (keys ordered-params)))))))
