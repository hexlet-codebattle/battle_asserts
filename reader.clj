(ns transform-yaml
  (:require [clojure.java.io :as io])
  (:require [clj-yaml.core :as yaml]))

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

(defn- create-yml
  [file-hash]
  (yaml/generate-string  file-hash))

(defn transform 
  "input file name, output file name"
  [input-name output-name]
  (write-file (create-yml (parse-file (read-file input-name))) output-name))