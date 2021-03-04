(ns battle-asserts.issues.is-ip-address
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :elementary)

(def description
  {:en "Create a function, thats check is `address` is IPv4 address."
   :ru "Создайте функцию, которая проверяет, является ли `address` адресом IPv4 ."})

(def signature
  {:input  [{:argument-name "address" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn- ip []
  (s/join #"."
          (apply conj
                 (gen/generate (gen/vector (gen/choose 1 255) 1))
                 (gen/generate (gen/vector (gen/choose 0 255) 3)))))

(defn- gen-addresses []
  (repeatedly 15 ip))

(defn arguments-generator []
  (let [addresses (gen-addresses)
        words (faker/words {:lang :en :n 10})
        random-ascii (repeat 10 (gen/generate gen/string-ascii))
        result (concat addresses words random-ascii)]
    (letfn [(gen-variants [] (gen/elements result))]
      (gen/tuple (gen-variants)))))

(def test-data
  [{:expected true :arguments ["192.168.100.5"]}
   {:expected true :arguments ["127.0.0.1"]}
   {:expected false :arguments ["127.260.12.1"]}
   {:expected false :arguments ["127.-20.12.1"]}
   {:expected false :arguments ["clojure"]}
   {:expected false :arguments [">;-~htQOvnV"]}])

(defn solution [address]
  (let [elements (s/split address #"\.")]
    (if (= (count elements) 4)
      (empty? (filter (fn [elem] (not (and (>= elem 0) (<= elem 255))))
                      (map #(Integer/parseInt %) elements)))
      false)))
