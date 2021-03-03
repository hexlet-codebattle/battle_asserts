(ns battle-asserts.issues.subnet-checker
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [clojure.network.ip :as ip]))

(def level :medium)

(def description
  {:en "Create a function, thats check if `subnet` contains in `address`, which contains IPv4 address and subnet mask."
   :ru "Создайте функцию, которая проверяет, входит ли `subnet` в `address`, который содержит IPv4 адрес и маску подсети."})

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
    (letfn [(gen-ip [] (gen/elements addresses-with-mask))
            (gen-subnet [] (gen/elements addresses))]
      (gen/tuple (gen-ip) (gen-subnet)))))

(def test-data
  [{:expected true
    :arguments ["192.168.100.1/12" "192.168.100.5"]}
   {:expected false
    :arguments ["198.201.121.1/15" "192.11.100.255"]}
   {:expected true
    :arguments ["201.224.121.12/30" "201.224.121.15"]}])

(defn solution [address subnet]
  (contains? (ip/make-network address) subnet))
