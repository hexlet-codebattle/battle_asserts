(ns battle-asserts.checker
  (:require [clojure.string :as string])
  (:require [clojure.set :as s]))

(defn validate
  [data]
  (let [levels #{"elementary" "easy" "medium" "hard"}
        multicode-checks (:multicode_checks data)
        actual-keys (set (keys data))
        valid-keys #{:level
                     :tags
                     :description
                     :checks
                     :author
                     :multicode_checks}]

    (assert (s/subset? actual-keys valid-keys) data)
    (assert (contains? levels (:level data)) data)

    (when multicode-checks
      (assert (= (-> multicode-checks keys set) #{:langs}) data))))
