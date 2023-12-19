(ns battle-asserts.issues.subnet-checker
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [clojure.network.ip :as ip]))

(def level :medium)

(def tags ["network" "strings"])

(def description
  {:en "Create a function, that checks if `subnet` contains `address`. Subnet contains IPv4 address of network and subnet mask."
   :ru "Создайте функцию, которая проверяет, что подсеть (`subnet`) содержит адрес (`address`). Подсеть содержит IPv4 адрес сети и маску подсети."})

(def signature
  {:input  [{:argument-name "address" :type {:name "string"}}
            {:argument-name "subnet" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn- ip []
  (s/join #"."
          (apply conj
                 (gen/generate (gen/vector (gen/choose 1 255) 1))
                 (gen/generate (gen/vector (gen/choose 0 255) 3)))))
(defn- gen-addresses []
  (repeatedly 40 ip))

(defn arguments-generator []
  (let [addresses (gen-addresses)
        addresses-with-mask
        (mapv #(s/join #"/" [% (gen/generate (gen/choose 12 30))]) addresses)]
    (letfn [(gen-ip [] (gen/elements addresses))
            (gen-subnet [] (gen/elements addresses-with-mask))]
      (gen/tuple (gen-ip) (gen-subnet)))))

(def test-data
  [{:expected true
    :arguments ["192.168.100.5" "192.160.0.0/12"]}
   {:expected false
    :arguments ["192.11.100.255" "198.200.0.0/15"]}
   {:expected true
    :arguments ["201.224.121.15" "201.224.121.12/30"]}])

(defn solution [address subnet]
  (contains? (ip/make-network subnet) address))
