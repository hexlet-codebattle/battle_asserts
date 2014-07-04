(ns battle-asserts.core
  (:import java.io.File)
  (:require [battle-asserts.checker :as checker]
            [battle-asserts.reader :as reader]))

(defn get-files-in-dir [folder]
  (map 
    #((re-find #"(.+?)(\.[^.]*$|$)" (.getName %)) 1) 
    (filter 
      #(not (.isDirectory %)) 
      (file-seq (clojure.java.io/file folder)))))


(defn transform-file [file-name]
  (let [src-dir "source/" 
        out-dir "issues/"
        src-url (str src-dir "/" file-name ".yml")]
    (if (checker/check-file src-url)
      (reader/transform file-name src-dir  out-dir)
      (throw (Exception. "Wrong input file")))))

(defn -main []
  (doall (map #(transform-file %) (get-files-in-dir "source"))))