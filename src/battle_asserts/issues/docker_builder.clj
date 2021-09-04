(ns battle-asserts.issues.docker-builder
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as fk]))

(def level :easy)

(def tags ["strings" "devops" "codebattle"])

(def description
  {:en "Create a function, that create docker build command from `language`, `version` and `path` (which can be empty string) for codebattle."
   :ru "Создайте функцию, которая генерирует команду сборки docker образа для codebattle с помощью `language`, `version` и `path` (который может быть пустой стрококй)."})

(def signature
  {:input [{:argument-name "language" :type {:name "string"}}
           {:argument-name "version" :type {:name "string"}}
           {:argument-name "path" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn- gen-version []
  (let [numbers (gen/sample (gen/choose 0 10) 3)
        version (s/join #"." numbers)
        release (rand-nth ["slim" "buster" "alpine" "cli" "latest"])]
    (str version ":" release)))

(defn- gen-path []
  (let [elems (fk/words {:n (gen/generate (gen/choose 1 8))})
        combined (concat (conj elems ".") '("Dockerfile"))]
  (s/join #"/" combined)))

(defn arguments-generator []
  (let [languages ["elixir" "ruby" "haskell" "c++" "c#" "clojure" "dart" "go" "java" "js" "kotlin" "php" "python"]
        versions (repeatedly 40 gen-version)
        dirs (conj (repeatedly 30 gen-path) "")]
    (gen/tuple (gen/elements languages)
               (gen/elements versions)
               (gen/elements dirs))))

(def test-data
  [{:arguments ["clojure" "1.1.2:slim" ""]
    :expected  "docker build -t codebattle/clojure:1.1.2:slim ."}
   {:arguments ["ruby" "3.0.2:latest" ""]
    :expected  "docker build -t codebattle/ruby:3.0.2:latest ."}
   {:arguments ["elixir" "1.12.2:alpine" "./Some/Dockerfile"]
    :expected  "docker build -t codebattle/elixir:1.12.2:alpine -f ./Some/Dockerfile ."}])

(defn solution [language version path]
  (str "docker build -t codebattle/" language ":" version
       (when-not (empty? path) (str " -f " path))
       " ."))
