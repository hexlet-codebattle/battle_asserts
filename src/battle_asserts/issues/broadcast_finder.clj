(ns battle-asserts.issues.broadcast-finder
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [clojure.network.ip :as ip]))

(def level :medium)

(def tags ["network" "strings"])

(def description
  {:en "Create a function, thats finds broadcast network address in `address`, which contains IPv4 address and subnet mask. If adrerss invalid, return \"invalid adress!!111\""
   :ru "Создайте функцию, которая находит адрес широкого вещания, входящий в `address`, который содержит IPv4 адрес и маску подсети. Если адрес невалиден, то верните \"invalid adress!!111\""})

(def signature
  {:input  [{:argument-name "address" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn- gen-ip-address []
  (str (s/join #"."
               (gen/generate
                (gen/vector
                 (gen/choose 0 255) 4)))
       "/"
       (gen/generate (gen/choose 22 32))))

(defn arguments-generator []
  (let [addresses (repeatedly 30 gen-ip-address)]
    (gen/tuple (gen/elements addresses))))

(def test-data
  [{:expected "192.175.255.255"
    :arguments ["192.168.100.1/12"]}
   {:expected "198.201.255.255"
    :arguments ["198.201.121.1/15"]}
   {:expected "201.224.121.15"
    :arguments ["201.224.121.12/30"]}
   {:expected "invalid adress!!111"
    :arguments ["0.168.100.1/33"]}])

(defn solution [address]
  (try
    (str (last (ip/make-network address)))
    (catch java.lang.AssertionError _ "invalid adress!!111")))
