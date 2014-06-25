(use 'clojure.java.io)
(require '[clj-yaml.core :as yaml])

(defn- read-file
	[file-name]
	(println file-name) 
	(with-open [rdr (reader file-name)]
		(yaml/parse-string rdr)))

(defn- write-file
	[file-hash, file-name]
	(with-open [wrtr (writer file-name)]
		(.write wrtr (yaml/generate-string  file-hash))))

(defn transform 
  "input file name, output file name"
  [input-name, output-name]
  (write-file (read-file input-name) output-name))