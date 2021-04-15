(ns battle-asserts.issues.vasya-and-sword
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["collections"])

(def disabled true)

(def description "Vasya has earned a lot of virtual coins and wants to buy for them a unique sword in the in-game store of his favorite MMORPG. The store is arranged in such a way that the prices of items in it change every day. Today a sword can cost 100 coins, tomorrow - 70, the day after tomorrow - 150.
                  Vasya sword is only needed for a while to make a quest with friends, in the future he is going to sell it with the same store. However, there is another trick in the store: once a player buys an item for a certain price, he cannot sell it in the future for a higher price or equal to the one he made the purchase for. For example, if Vasya buys a sword for 100 coins, he can't sell it for 150 coins.
                  Vasya has got his hands on the sword's price schedule for the next few days, so help him make a deal where he will suffer the minimum loss from resale.
                  The function accepts an array of integer prices for the nearest sequence of `n` days (n < 1000).
                  Returns the minimum loss from resale.")

(def signature
  {:input  [{:argument-name "prices" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose 50 1000) 4 12)))

(def test-data
  [{:expected 0 :arguments [[198 494 341 509 126 788 258 885 426 426]]}
   {:expected 30 :arguments [[210 130 50 175 100]]}
   {:expected 47 :arguments [[804 106 757 963 680]]}])

(defn solution [prices]
  (let [len (count prices)
        sorted-prices (vec (sort prices))
        minimum (- (second sorted-prices) (first sorted-prices))]
    (loop [iter 2
           min-price minimum]
      (if (= iter len)
        min-price
        (recur (inc iter)
               (min min-price (- (sorted-prices iter) (sorted-prices (dec iter)))))))))
