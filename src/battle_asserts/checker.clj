(ns battle-asserts.checker
  (:require [clojure.string :as string])
  (:require [clojure.set :as s]))

(defn validate
  [data]
  (let [actual-keys (set (keys data))
        actual-check-keys (-> (:checks data) keys set)]

    (assert (s/subset? actual-keys
                          #{:level
                            :tags
                            :description
                            :checks
                            :author
                            :multicode_checks})
            data)

    (assert (contains? #{"elementary" "easy" "medium" "hard"}
                       (:level data))
            data)

    (when (:multicode_checks data)
      (assert (= (-> (:multicode_checks data) keys set)
                 #{:langs})
              data))))
