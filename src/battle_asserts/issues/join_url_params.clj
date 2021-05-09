(ns battle-asserts.issues.join-url-params
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :easy)

(def tags ["network" "strings"])

(def description
  {:en "Return a query string (URL) generated from the given address and a hash map of parameters.
        The parameters in your result string should be arranged in alphabetical order."
   :ru "Верните строку запроса (URL), созданную на основе адреса и словаря параметров.
        Параметры в результирующей строке должны быть упорядочены по алфавиту."})

(def signature
  {:input  [{:argument-name "url" :type {:name "string"}}
            {:argument-name "params" :type {:name "hash" :nested {:name "string"}}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (letfn [(address []
            (str "http://" (faker/word {:lang :en}) ".com"))
          (gen-word []
            (gen/elements (faker/words {:lang :en :n 50})))
          (gen-keyword []
            (gen/elements (map keyword (faker/words {:lang :en :n 50}))))]
    (gen/tuple (gen/return (address))
               (gen/map (gen-keyword) (gen-word) {:min-elements 2 :max-elements 8}))))

(def test-data
  [{:expected "http://www.foobar.com?first_param=test&second_param=some&third_param=clj"
    :arguments ["http://www.foobar.com"
                {:first_param "test"
                 :second_param "some"
                 :third_param "clj"}]}
   {:expected "http://www.example.com/search?q=findme&useragent=chrome"
    :arguments ["http://www.example.com/search"
                {:q "findme"
                 :useragent "chrome"}]}
   {:expected "http://authority.com?smile=weight&steam=metal&surprise=connection"
    :arguments ["http://authority.com"
                {:smile "weight"
                 :surprise "connection"
                 :steam "metal"}]}])

(defn solution [url params]
  (str url "?"
       (s/join "&"
               (map #(str (name %) "=" (params %))
                    (sort (keys params))))))
