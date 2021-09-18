(ns battle-asserts.issues.filter-messages
  (:require [clojure.test.check.generators :as gen]
            [battle-asserts.utility :as u]
            [faker.generate :as f]))

(def level :elementary)

(def tags ["training" "strings" "collections" "hash-maps"])

(def description
  {:en "Implement a function that extracts users messages from Codebattle chat. If there is no such messages, return `['no messages!']`"
   :ru "Создайте функцию, которая фильтрует сообщения в чате Codebattle по имени пользователя. Если таких сообщений нет, верните `['no messages!']`"})

(def signature
  {:input [{:argument-name "messages" :type {:name "array" :nested {:name "hash" :nested {:name "string"}}}}
           {:argument-name "user" :type {:name "string"}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (let [users (repeatedly 10 u/gen-name)
        message-texts (repeatedly 40 f/sentence)
        messages (repeatedly 50 (fn [] {:user (rand-nth users) :text (rand-nth message-texts)}))]
    (gen/tuple (gen/vector (gen/elements messages) 5 20) (gen/elements users))))

(def test-data
  [{:expected ["Some message"]
    :arguments [[{:user "Some user" :text "Some message"} {:user "User" :text "Message"}] "Some user"]}
   {:expected ["no messages!"]
    :arguments [[{:user "vtm" :text "Ruby is cool!"} {:user "solar05" :text "Clojure too!"} {:user "ReDBrother" :text "JS are the same!"}] "NewUser"]}
   {:expected ["Ruby is cool!" "Elixir is breath taking!"]
    :arguments [[{:user "vtm" :text "Ruby is cool!"} {:user "solar05" :text "Clojure too!"} {:user "ReDBrother" :text "JS are the same!"} {:user "vtm" :text "Elixir is breath taking!"}] "vtm"]}])

(defn solution [messages user]
  (let [result (mapv :text (filterv #(= (:user %) user) messages))]
    (if (zero? (count result))
      ["no messages!"]
      result)))
