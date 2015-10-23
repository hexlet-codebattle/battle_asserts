(ns battle-asserts.issues.move-zeros
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write a function to move all 0's to the end of it while maintaining
                 the relative order of the non-zero elements")

(defn arguments-generator []
  (gen/tuple (gen/one-of [(gen/vector gen/int)
                          (gen/bind (gen/vector gen/int)
                                    #(gen/shuffle (concat % (repeat (inc (rand-int 5)) 0))))])))

(def test-data
  [{:expected [1 3 12 0 0]
    :arguments [[0 1 0 3 12]]}
   {:expected [4 3 5 0 0 0]
    :arguments [[4 0 3 0 5 0]]}
   {:expected [4 3 5 0 0 0]
    :arguments [[0 0 0 4 3 5]]}])

(defn solution [array]
  (concat
   (remove zero? array)
   (filter zero? array)))
