(ns battle-asserts.reader
  (:require [clojure.java.io :as io]
            [me.raynes.fs :as fs]
            [clj-yaml.core :as yaml]
            [battle-asserts.parse-solution :as parse-solution]
            [multicode.core :as mc]))

(defn- parse-file
  [file-input]
  (yaml/parse-string file-input))

(defn- write-file
  [file-yml file-name]
  (with-open [wrtr (io/writer file-name)]
    (.write wrtr file-yml)))

(defn- generate-check-for-lang [lang file]
  (mc/prettify-code
    (keyword lang)
    (parse-solution/get-asserts-from-file
      (str "test/battle_solutions/" (fs/name file) "_test.clj"))))

(defn- generate-check-for-langs [source-hash file-name]
  (let [langs ((or (source-hash :multicode_checks) {}) :langs)]
    (reduce #(assoc %1 (keyword %2) (generate-check-for-lang %2 file-name)) {} langs)))

(defn- generate-hash [source-hash file-name]
  (dissoc
    (assoc
      source-hash
      :checks
      (merge
        (source-hash :checks)
        (generate-check-for-langs source-hash file-name)))
    :multicode_checks))

(defn- create-yml
  [file-hash file-name]
  (yaml/generate-string (into {} (reverse (generate-hash (into {} (reverse file-hash))
                                                         file-name)))))

(defn transform
  [src dest]
  (write-file (create-yml (parse-file (slurp src)) dest) dest))
