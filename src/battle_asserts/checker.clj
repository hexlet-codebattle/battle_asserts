(ns battle-asserts.checker
  (:require [clojure.string :as string])
  (:require [clojure.set :as sets]))

(def allowed_keys #{:level, :tags, :description, :checks, :author, :multicode_checks})
(def required_keys #{:level})

(def allowed_lang_keys #{:description, :setup, :asserts, :langs})
(def required_lang_keys #{})
(def required_multi_keys #{:langs})

(def allowed_levels #{"elementary", "easy", "medium", "hard"})

(def allowed_author_keys #{:github_nickname, :web_page})
(def required_author_keys #{})


(defn- check-allow-keys
  [p-hash]
  (sets/superset? allowed_keys (set (keys p-hash))))

(defn- check-require-keys
  [p-hash]
  (sets/superset? (set (keys p-hash)) required_keys))


(defn- check-allow-levels
  [p-hash]
  (sets/superset? allowed_levels #{(get p-hash :level)}))


(defn- check-allow-authors
  [p-hash]
  (sets/superset? allowed_author_keys (set (keys (get p-hash :author)))))

(defn- check-require-authors
  [p-hash]
  (not (nil? (get-in p-hash [:author :github_nickname]))))


(defn- check-lang
  [p-hash lang]
  (sets/superset? allowed_lang_keys (set (keys (get p-hash lang)))))

(defn- check-allow-langs
  [p-hash]
  (or (check-lang p-hash :checks) (check-lang p-hash :multicode_checks)))


(defn- check-require-lang
  [p-hash]
  (sets/superset? (set (keys (get p-hash :checks))) required_lang_keys))

(defn- check-require-multi
  [p-hash]
  (sets/superset? (set (keys (get p-hash :multicode_checks))) required_multi_keys))

(defn- check-require-lang-and-multi
  [p-hash]
  (or (check-require-lang p-hash) (check-require-multi p-hash)))

(defn check
  [data test-code]
  (let [checkers [check-allow-keys
                  check-require-keys
                  check-allow-levels
                  check-allow-authors
                  check-allow-langs
                  check-require-lang-and-multi]]
    (map #(% test-code) checkers)))
