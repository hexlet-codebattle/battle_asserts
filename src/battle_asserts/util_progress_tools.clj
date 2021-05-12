(ns battle-asserts.util-progress-tools
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.tools.namespace.find :as nsf]))

(defn- collect-namespaces []
  (-> "src/battle_asserts/issues"
      clojure.java.io/as-file
      nsf/find-namespaces-in-dir))

(defn- prepare-namespace-name [namespace]
  (s/replace (last (s/split (str namespace) #"\."))
             #"-"
             "_"))

(defn- present-untraslated-tasks [task-list level]
  (if (empty? task-list)
    (println (str "Tasks with level " level " translated!"))
    (println (str (s/capitalize level) " tasks without translations: \n" (s/join "\n" task-list) "\n"))))

(defn check-translations [& _args]
  (let [namespaces (collect-namespaces)
        translated-count (atom 0)
        untranslated-list (atom {:elementary '()
                                 :easy '()
                                 :medium '()
                                 :hard '()})
        namespaces-count (count namespaces)]
    (doseq [namespace namespaces]
      (require namespace)
      (let [issue-name (prepare-namespace-name namespace)
            description @(ns-resolve namespace 'description)
            level @(ns-resolve namespace 'level)]
        (if (map? description)
          (swap! translated-count inc)
          (swap! untranslated-list (fn [acc el]
                                     (update-in acc [level] conj el)) issue-name))))
    (if (empty? @untranslated-list)
      (println "All tasks are translated!")
      (do
        (present-untraslated-tasks (@untranslated-list :elementary) "elementary")
        (present-untraslated-tasks (@untranslated-list :easy) "easy")
        (present-untraslated-tasks (@untranslated-list :medium) "medium")
        (present-untraslated-tasks (@untranslated-list :hard) "hard")))
    (println (str "Total translation progress " @translated-count " / " namespaces-count " or " (format "%.1f" (float (* (/ @translated-count namespaces-count) 100))) "%"))))

(defn check-tags [& _args]
  (let [namespaces (collect-namespaces)
        tagged-count (atom 0)
        untagged-list (atom (list))
        namespaces-count (count namespaces)]
    (doseq [namespace namespaces]
      (require namespace)
      (let [issue-name (prepare-namespace-name namespace)
            tags (ns-resolve namespace 'tags)
            level @(ns-resolve namespace 'level)]
        (if-not (nil? tags)
          (swap! tagged-count inc)
          (swap! untagged-list conj (str issue-name " " level)))))
    (if (empty? @untagged-list)
      (println "All tasks are tagged!")
      (println (str "Tasks without tags: \n" (s/join "\n" @untagged-list))))
    (println (str "Total tagged tasks progress " @tagged-count " / " namespaces-count " or " (format "%.1f" (float (* (/ @tagged-count namespaces-count) 100))) "%"))))

(defn check-generators-and-solutions [& _args]
  (let [namespaces (collect-namespaces)
        completed-task-count (atom 0)
        without-solution (atom (list))
        without-generator (atom (list))
        namespaces-count (count namespaces)]
    (doseq [namespace namespaces]
      (require namespace)
      (let [issue-name (prepare-namespace-name namespace)
            solution (ns-resolve namespace 'solution)
            generator (ns-resolve namespace 'arguments-generator)
            level @(ns-resolve namespace 'level)]
        (cond
          (and (not (nil? solution)) (not (nil? generator))) (swap! completed-task-count inc)
          (and (nil? solution) (nil? generator))
          (do
            (swap! without-solution conj (str issue-name " " level))
            (swap! without-generator conj (str issue-name " " level)))
          (nil? solution) (swap! without-solution conj (str issue-name " " level))
          (nil? generator) (swap! without-generator conj (str issue-name " " level)))))
    (if (empty? @without-solution)
      (println "All tasks have solution!")
      (println (str "Tasks without solution: \n" (s/join "\n" @without-solution))))
    (if (empty? @without-generator)
      (println "All tasks have arguments generator!")
      (println (str "Tasks without arguments generator: \n" (s/join "\n" @without-generator))))
    (println (str "Total tasks solution and generator progress " @completed-task-count " / " namespaces-count " or " (format "%.1f" (float (* (/ @completed-task-count namespaces-count) 100))) "%"))))

(defn collect-tags [& _args]
  (let [namespaces (collect-namespaces)
        tags-list (atom [])]
    (doseq [namespace namespaces]
      (require namespace)
      (let [issue-name (prepare-namespace-name namespace)
            tags (ns-resolve namespace 'tags)]
        (if-not (nil? tags)
          (swap! tags-list (fn [acc elem] (apply conj acc elem)) @tags)
          (println (str "Task " issue-name " without tags!")))))
    (println "Total tags stats:\n" (frequencies @tags-list))))
