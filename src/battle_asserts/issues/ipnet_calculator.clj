(ns battle-asserts.issues.ipnet-calculator
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [clojure.network.ip :as ip]))

(def level :medium)

(def description
  {:en "Create a function, thats calculates how much network contains in `address`, which contains ip and subnet mask."
   :ru "Создайте функцию, которая рассчитывает количество сетевых адресов, входящих в `address`, который содержит ip и маску подсети."})

(def signature
  {:input  [{:argument-name "address" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (letfn [(ip []
            (s/join #"/"
                    [(s/join #"." (gen/generate (gen/vector (gen/choose 0 255) 4)))
                     (gen/generate (gen/choose 12 32))]))
          (gen-ip [] (gen/elements (repeatedly 50 ip)))]
    (gen/tuple (gen-ip))))
(gen/generate (arguments-generator))

(def test-data
  [{:expected 1048576
    :arguments ["192.168.100.1/12"]}
   {:expected 131072
    :arguments ["198.201.121.1/15"]}
   {:expected 4
    :arguments ["201.224.121.12/30"]}])

(defn solution [address]
  (count (ip/make-network address)))
