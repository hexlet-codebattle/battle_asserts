(ns battle-asserts.util-progress-tools
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
            description @(ns-resolve namespace 'description)
            level @(ns-resolve namespace 'level)]
        (if (map? description)
          (swap! translated-count inc)
          (swap! untranslated-list conj (str issue-name " " level)))))
    (println "Tasks without translations: ")
    (println (s/join "\n" @untranslated-list))
    (println (str "Total translation progress " @translated-count " / " namespaces-count " or " (format "%.1f" (float (* (/ @translated-count namespaces-count) 100))) "%"))))


(defn check-tags [& _args]
  (let [namespaces (-> "src/battle_asserts/issues"
                       clojure.java.io/as-file
                       nsf/find-namespaces-in-dir)
        tagged-count (atom 0)
        untagged-list (atom (list))
        namespaces-count (count namespaces)]
    (doseq [namespace namespaces]
      (require namespace)
      (let [issue-name (s/replace (last (s/split (str namespace) #"\."))
                                  #"-"
                                  "_")
            tags (ns-resolve namespace 'tags)
            level @(ns-resolve namespace 'level)]
        (if (not (nil? tags))
          (swap! tagged-count inc)
          (swap! untagged-list conj (str issue-name " " level)))))
    (println "Tasks without tags: ")
    (println (s/join "\n" @untagged-list))
    (println (str "Total tagged tasks progress " @tagged-count " / " namespaces-count " or " (format "%.1f" (float (* (/ @tagged-count namespaces-count) 100))) "%"))))
