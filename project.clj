(defproject battle_asserts "0.1.0-SNAPSHOT"
  :description "Hexlet Codebattle Asserts"
  :url "http://battle.hexlet.io"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.5.1"
  :plugins [[lein-kibit "0.1.2"]
            [lein-cljfmt "0.3.0"]
            [jonase/eastwood "0.2.1"]]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [jonase/eastwood "0.2.1" :exclusions  [org.clojure/clojure]]
                 [org.clojure/tools.namespace "0.2.11"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/test.check "0.9.0"]
                 [helpshift/faker "0.2.0"]
                 [circleci/clj-yaml "0.5.3"]
                 ]
  :source-paths ["src"]
  ; :eval-in :nrepl
  :main battle-asserts.core)
