(defproject battle_asserts "0.1.0-SNAPSHOT"
  :description "Hexlet Codebattle Asserts"
  :url "http://battle.hexlet.io"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.5.1"
  :plugins [[lein-kibit "0.1.6"]
            [lein-cljfmt "0.6.4"]
            [jonase/eastwood "0.3.7"]]
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [jonase/eastwood "0.3.7" :exclusions  [org.clojure/clojure]]
                 [org.clojure/tools.namespace "0.3.1"]
                 [org.clojure/data.json "0.2.7"]
                 [org.clojure/test.check "0.10.0"]
                 [helpshift/faker "0.2.0"]
                 [circleci/clj-yaml "0.6.0"]
                 [com.velisco/strgen "0.1.8"]
                 [faker "0.2.2"]]
  :source-paths ["src"]
  ; :eval-in :nrepl
  :main battle-asserts.core)
