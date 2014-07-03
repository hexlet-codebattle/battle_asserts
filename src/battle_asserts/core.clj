(ns battle-asserts.core
  (:import java.io.File)
  (:require [battle-asserts.checker :as checker]
            [battle-asserts.reader :as reader]))

(defn create-source-dir [file-name]
  (str "source/" file-name "/"))

(defn create-output-dir [file-name]
  (str "issues/" )) 

(defn get-sub-dir [folder]
  (map 
    #(.getName %) 
    (filter 
      #(.isDirectory %) 
      (drop 1 (file-seq (clojure.java.io/file folder))))))


(defn transform-file [file-name]
  (let [src-dir (create-source-dir file-name)
        out-dir (create-output-dir file-name)
        src-url (str src-dir "/" file-name ".yml")]
    (if (checker/check-file src-url)
      (reader/transform file-name src-dir  out-dir)
      (throw (Exception. "Wrong input file")))))

(defn transform-all-in [folder-name]
  (map transform-file (get-sub-dir folder-name)))
