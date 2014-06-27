(ns battle-asserts.check-asserts
  (:require [clojure.java.io :as io]
            [clojure.tools.namespace.find :as tools] ))

(defn get-namespaces
  [src]
  (tools/find-namespaces-in-dir (io/file src)))

(defn load-function-from-ns
  [name-space]
  (require name-space)
  (ns-resolve name-space 'check))

(defn check-asserts
  [src]
  (map 
    #((load-function-from-ns %))
    (get-namespaces src)) 
  ) 