(ns battle-solutions.check-brackets-test
  (:require [clojure.test :refer :all]
            [clojure.string :refer [blank?]]
            [clojure.set :refer [union]]))

(defn correct-brackets? [s]
  (let [brackets {\( \) \[ \] \{ \} \< \>}
        opening (set (keys brackets))
        closing (set (vals brackets))]
    (->> s
         (reduce #(cond
                    (opening %2) (conj %1 %2)
                    (closing %2) (cond
                                   (= (brackets (last %1)) %2) (pop %1)
                                   :else (reduced (conj %1 %2)))
                    :else %1)
                 [])
         empty?)))

(deftest test-asserts
  (assert (correct-brackets? " "))
  (assert (correct-brackets? "()[]{}<>"))
  (assert (correct-brackets? "<(){[]}>"))
  (assert (not (correct-brackets? "())")))
  (assert (not (correct-brackets? "()(")))
  (assert (not (correct-brackets? "({)}")))
  (assert (not (correct-brackets? "{)][(}"))))
