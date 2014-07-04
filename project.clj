(defproject battle_asserts "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.3.0"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.namespace "0.2.4"]
                 [me.raynes/fs "1.4.4"]
                 [circleci/clj-yaml "0.5.2"]
                 [clj-stacktrace "0.2.7"]]
  :injections [(let [orig (ns-resolve (doto 'clojure.stacktrace require)
                                      'print-cause-trace)
                     new (ns-resolve (doto 'clj-stacktrace.repl require)
                                     'pst)]
                 (alter-var-root orig (constantly (deref new))))]
  :source-paths ["src"]
  :eval-in :nrepl
  :main battle-asserts.core)
