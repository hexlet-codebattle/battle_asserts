(ns battle-asserts.reader
  (:require [clojure.java.io :as io] 
            [clj-yaml.core :as yaml]
            [battle-asserts.parse-solution :as parse-solution]
            [multicode.core :as mc]))

(defn- read-file
  [file-name] 
  (slurp file-name))

(defn- parse-file
  [file-input]
  (yaml/parse-string file-input))

(defn- write-file
  [file-yml file-name]
  (with-open [wrtr (io/writer file-name)]
    (.write wrtr file-yml)))

(defn- generate-check-for-lang [lang input-dir]
  (clojure.string/split-lines (clojure.string/replace 
                                (mc/prettify-code 
                                  (keyword lang )
                                  (parse-solution/get-asserts-from-file 
                                    (str input-dir "multicode.clj"))) 
                                #"'"   "\""))) 

(defn- generate-check-for-langs [source-hash input-dir]
  (let [langs ((source-hash :multicode_checks) :langs)]
    (reduce #(assoc %1 (keyword %2) (generate-check-for-lang %2 input-dir)) {} langs)))

(defn- generate-hash [source-hash input-dir]
  (dissoc 
    (assoc 
      source-hash 
      :checks 
      (merge 
        (source-hash :checks) 
        (generate-check-for-langs source-hash input-dir)))
    :multicode_checks))

(defn- create-yml
  [file-hash input-dir]
  (yaml/generate-string  (into {} (reverse (generate-hash file-hash input-dir)))))

(defn transform 
  "input file name, output file name"
  [file-name input-dir output-dir]
  (let [input-path (str input-dir file-name ".yml")
        output-path (str output-dir file-name ".yml")]
    (write-file (create-yml (parse-file (read-file input-path)) input-dir) output-path)))