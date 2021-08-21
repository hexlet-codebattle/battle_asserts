(ns battle-asserts.issues.docker-builder
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["strings" "devops" "codebattle"])

(def description
  {:en "Create a function, that create docker build command from `language` and `version` for codebattle."
   :ru "Создайте функцию, которая генерирует команду сборки docker образа для codebattle с помощью `language` и `version`."})

(def signature
  {:input [{:argument-name "language" :type {:name "string"}}
           {:argument-name "version" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn- gen-version []
  (let [numbers (gen/sample (gen/choose 0 10) 3)
        version (s/join #"." numbers)
        release (rand-nth ["slim" "buster" "alpine" "cli" "latest"])]
    (str version ":" release)))

(defn arguments-generator []
  (let [languages ["elixir" "ruby" "haskell" "c++" "c#" "clojure" "dart" "go" "java" "js" "kotlin" "php" "python"]
        versions (repeatedly 40 gen-version)]
    (gen/tuple (gen/elements languages) (gen/elements versions))))

(def test-data
  [{:arguments ["clojure" "1.1.2:slim"]
    :expected  "docker build -t codebattle/clojure:1.1.2:slim ."}
   {:arguments ["ruby" "3.0.2:latest"]
    :expected  "docker build -t codebattle/ruby:3.0.2:latest ."}
   {:arguments ["elixir" "1.12.2:alpine"]
    :expected  "docker build -t codebattle/elixir:1.12.2:alpine ."}])

(defn solution [language version]
  (str "docker build -t codebattle/" language ":" version " ."))
