(use 'clojure.java.io)
(require '[clj-yaml.core :as yaml])


(with-open [rdr (reader "issue.example.yml") wrtr (writer "test.yml")]
	(.write wrtr (yaml/generate-string (yaml/parse-string rdr))))
