(ns battle-asserts.translation-tool
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.tools.namespace.find :as nsf]))

(defn check-translations [& _args]
  (let [namespaces (-> "src/battle_asserts/issues"
                       clojure.java.io/as-file
                       nsf/find-namespaces-in-dir)
        translated-count (atom 0)
        untranslated-list (atom (list))
        namespaces-count (count namespaces)]
    (doseq [namespace namespaces]
      (require namespace)
      (let [issue-name (s/replace (last (s/split (str namespace) #"\."))
                                  #"-"
                                  "_")
            description @(ns-resolve namespace 'description)]
        (if (map? description)
          (swap! translated-count inc)
          (swap! untranslated-list conj issue-name))))
    (println "Tasks without translations: ")
    (println (s/join "\n" @untranslated-list))
    (println (str "Total translation progress " @translated-count " / " namespaces-count " or " (format "%.1f" (float (* (/ @translated-count namespaces-count) 100))) "%"))))
