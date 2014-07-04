(ns battle-asserts.core
  (:require [battle-asserts.checker :as checker]
            [me.raynes.fs :as fs]
            [battle-asserts.reader :as reader]))

(defn transform-file [file]
  (if (checker/valid? file)
    (reader/transform file (str "issues" "/" (fs/base-name file)))
    (throw (Exception. "Wrong input file"))))

(defn -main []
  (doall (map transform-file
              (fs/find-files "source" #".+\.yml"))))
