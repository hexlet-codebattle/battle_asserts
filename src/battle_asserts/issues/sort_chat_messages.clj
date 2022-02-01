(ns battle-asserts.issues.sort-chat-messages
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as fk]))

(def level :easy)

(def disabled true)

(def tags ["collections" "strings" "sorting" "codebattle"])

(def description
  {:en "Create function that sorts messages in chat by hours."
   :ru "Создайте функцию, которая сортирует сообщения в чате по часам."})

(defn- add-zeros [time]
  (if (< time 10)
    (str "0" time)
    time))

(defn- gen-message []
  (let [hours (gen/generate (gen/choose 0 23))
        minutes (gen/generate (gen/choose 0 59))
        seconds (gen/generate (gen/choose 0 59))
        timestamp (s/join #":" (map add-zeros [hours minutes seconds]))]
    (str timestamp " " (fk/sentence))))

(def signature
  {:input [{:argument-name "messages" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (let [messages (repeatedly 60 gen-message)]
    (gen/tuple (gen/vector (gen/elements messages) 4 10))))

(def test-data
  [{:expected ["00:02:06 Gogogogoog!11!"
               "02:02:03 Some message."
               "03:03:04 Oh, hi Mark!"]
    :arguments [["02:02:03 Some message."
                 "03:03:04 Oh, hi Mark!"
                 "00:02:06 Gogogogoog!11!"]]}
   {:expected ["13:05:59 Ruby awesome!"
               "13:06:01 Clojure too!"]
    :arguments [["13:05:59 Ruby awesome!"
                 "13:06:01 Clojure too!"]]}])

(defn solution [messages]
  (vec (sort-by #(Integer/parseInt (first (s/split % #":"))) < messages)))

