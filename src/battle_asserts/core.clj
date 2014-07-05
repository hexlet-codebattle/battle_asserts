(ns battle-asserts.core
  (:require [battle-asserts.checker :as checker]
            [clj-yaml.core :as yaml]
            [me.raynes.fs :as fs]
            [battle-asserts.reader :as reader]))

(defn transform-file [file]
  (let [data (yaml/parse-string (slurp file))
        test-file (str "test/battle_solutions" "/" (fs/name file) "_test.clj")
        test-code (when (fs/readable? test-file) (read-string (str "(" (slurp test-file) ")")))]

    (checker/validate data)
    (let [result (reader/transform data test-code)
          yaml (yaml/generate-string result)
          result-file (str "issues" "/" (fs/base-name file))]
      (spit result-file yaml))))

(defn -main [& args]
  (doall (map transform-file
              (fs/find-files "source" #".+\.yml"))))
