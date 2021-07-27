(defproject battle_asserts "0.1.0-SNAPSHOT"
  :description "Hexlet Codebattle Asserts"
  :url "http://battle.hexlet.io"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.5.1"
  :plugins [[lein-kibit "0.1.8"]
            [lein-cljfmt "0.7.0"]
            [jonase/eastwood "0.9.4"]]
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [jonase/eastwood "0.9.4" :exclusions  [org.clojure/clojure]]
                 [org.clojure/tools.namespace "1.1.0"]
                 [org.clojure/data.json "2.4.0"]
                 [org.clojure/test.check "1.1.0"]
                 [helpshift/faker "0.2.0"]
                 [clj-commons/clj-yaml "0.7.107"]
                 [com.velisco/strgen "0.1.8"]
                 [faker "0.2.2"]
                 [kovacnica/clojure.network.ip "0.1.3"]]
  :profiles {:kaocha {:dependencies [[lambdaisland/kaocha "1.0.861"]]}}
  :aliases {"kaocha" ["with-profile" "+kaocha" "run" "-m" "kaocha.runner"]}
  :source-paths ["src"]
  ; :eval-in :nrepl
  :main battle-asserts.core)
