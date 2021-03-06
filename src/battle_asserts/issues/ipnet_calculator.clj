(ns battle-asserts.issues.ipnet-calculator
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [clojure.network.ip :as ip]))

(def level :medium)

(def tags ["network" "strings"])

(def description
  {:en "Create a function, thats calculates how much network contains in `address`, which contains IPv4 and subnet mask."
   :ru "Создайте функцию, которая рассчитывает количество сетевых адресов, входящих в `address`, который содержит IPv4 и маску подсети."})

(def signature
  {:input  [{:argument-name "address" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (letfn [(ip []
            (s/join #"/"
                    [(s/join #"."
                             (apply conj
                                    (gen/generate (gen/vector (gen/choose 1 255) 1))
                                    (gen/generate (gen/vector (gen/choose 0 255) 3))))
                     (gen/generate (gen/choose 12 30))]))
          (gen-ip [] (gen/elements (repeatedly 30 ip)))]
    (gen/tuple (gen-ip))))

(def test-data
  [{:expected 1048576
    :arguments ["192.168.100.1/12"]}
   {:expected 131072
    :arguments ["198.201.121.1/15"]}
   {:expected 4
    :arguments ["201.224.121.12/30"]}])

(defn solution [address]
  (count (ip/make-network address)))
