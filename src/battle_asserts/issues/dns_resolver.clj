(ns battle-asserts.issues.dns-resolver
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :easy)

(def tags ["strings" "collections" "network"])

(def description
  {:en "Create a function, thats pick up an IP from `domains` by `domain` name. If there is no record, then return \"DNS_PROBE_FINISHED_NXDOMAIN\"."
   :ru "Создайте функцию, которая достает из `domains` IP, связанный с именем `domain`. Если такая запись отсутствует, то верните \"DNS_PROBE_FINISHED_NXDOMAIN\"."})

(def signature
  {:input  [{:argument-name "domains" :type {:name "hash" :nested {:name "string"}}}
            {:argument-name "domain" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (let [domain-top ["ru" "com" "me" "net" "su" "ge" "tv" "io" "org"]]
    (letfn [(domains [] (mapv #(str % "." (rand-nth domain-top))
                              (faker/words {:lang :en :n 50})))
            (gen-domains [] (gen/elements (domains)))
            (ip [] (s/join #"." (gen/generate (gen/vector (gen/choose 0 255) 4))))
            (gen-ip [] (gen/elements (repeatedly 30 ip)))]
      (gen/tuple
       (gen/map (gen-domains) (gen-ip) {:min-elements 1 :max-elements 8})
       (gen-domains)))))

(def test-data
  [{:expected "103.95.84.1"
    :arguments [{"rubyonrails.org" "211.116.107.5" "clojure.org" "103.95.84.1" "phoenixframework.org" "234.214.199.63" "reactjs.org" "20.199.101.214"} "clojure.org"]}
   {:expected "234.214.199.63"
    :arguments [{"rhythm.ru" "201.116.147.4" "building.ru" "103.176.11.27" "hexlet.io" "234.214.199.63" "brass.ru" "201.116.147.4"} "hexlet.io"]}
   {:expected "DNS_PROBE_FINISHED_NXDOMAIN"
    :arguments [{"some.com" "127.0.0.1"} "test.net"]}])

(defn solution [domains domain]
  (get domains domain "DNS_PROBE_FINISHED_NXDOMAIN"))
