(defproject battle_asserts "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.5.1"
  :plugins [[lein-kibit "0.1.2"]]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [me.raynes/fs "1.4.6"]
                 [circleci/clj-yaml "0.5.2"]
                 [multicode "0.1.0-SNAPSHOT"]]
  :source-paths ["src"]
  :eval-in :nrepl
  :main battle-asserts.core)
