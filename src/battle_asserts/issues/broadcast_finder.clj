(ns battle-asserts.issues.broadcast-finder
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [clojure.network.ip :as ip]))

(def level :medium)

(def tags ["network" "strings"])

(def description
  {:en "Create a function, thats finds broadcast network address in `address`, which contains IPv4 address and subnet mask."
   :ru "Создайте функцию, которая находит адрес широкого вещания, входящий в `address`, который содержит IPv4 адрес и маску подсети."})

(def signature
  {:input  [{:argument-name "address" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (letfn [(ip []
            (s/join #"/"
                    [(s/join #"."
                             (apply conj
                                    (gen/generate (gen/vector (gen/choose 1 255) 1))
                                    (gen/generate (gen/vector (gen/choose 0 255) 3))))
                     (gen/generate (gen/choose 12 30))]))
          (gen-ip [] (gen/elements (repeatedly 20 ip)))]
    (gen/tuple (gen-ip))))

(def test-data
  [{:expected "192.175.255.255"
    :arguments ["192.168.100.1/12"]}
   {:expected "198.201.255.255"
    :arguments ["198.201.121.1/15"]}
   {:expected "201.224.121.15"
    :arguments ["201.224.121.12/30"]}])

(defn solution [address]
  (str (last (ip/make-network address))))
