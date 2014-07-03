(ns battle-asserts.checker
  (:require [clojure.java.io :as io])
  (:require [clj-yaml.core :as yaml])
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


(defn- read-file
	[file-name] 
	(slurp file-name))

(defn- parse-file
  [file-input]
  (yaml/parse-string file-input))

(defn- get-cheks
   [p-hash]
   (get p-hash :checks))

(defn- get-multi-checks
  [p-hash]
  (get (get p-hash :multicode_checks) :langs))

(defn- check-lang-dupl-count
  [p-hash]
  (letfn [(check-lang [acc c] 
            (+ acc (if (nil? (get (get-cheks p-hash) (keyword c))) 0 1)))] 
    (reduce check-lang 0 (get-multi-checks p-hash))))

(defn- check-lang-dupl
  [p-hash]
  (if (== (check-lang-dupl-count p-hash) 0) true false))

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
  (not (nil? (get (get p-hash :author) :github_nickname))))


(defn- check-lang
  [p-hash lang]
  (sets/superset? allowed_lang_keys (set (keys (get p-hash lang)))))

(defn- check-allow-langs
  [p-hash]
  (if (or (check-lang p-hash :checks) (check-lang p-hash :multicode_checks)) true false))


(defn- check-require-lang
  [p-hash]
  (sets/superset? (set (keys (get p-hash :checks))) required_lang_keys))

(defn- check-require-multi
  [p-hash]
  (sets/superset? (set (keys (get p-hash :multicode_checks))) required_multi_keys))

(defn- check-require-lang-and-multi
  [p-hash]
  (if (or (check-require-lang p-hash) (check-require-multi p-hash)) true false))

(defn all-checkers
  [parsed-yaml]
    (and (check-allow-keys parsed-yaml)
      (check-require-keys parsed-yaml)
      (check-allow-levels parsed-yaml)
      (check-allow-authors parsed-yaml)
      (check-allow-langs parsed-yaml)
      (check-require-lang-and-multi parsed-yaml)
      (check-lang-dupl parsed-yaml)))

(defn check-file 
  "path to file"
  [path-file]
  (all-checkers (parse-file (read-file path-file))))

