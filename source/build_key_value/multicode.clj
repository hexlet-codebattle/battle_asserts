(ns build-key-value.multicode) 

(defn b-key-val [hash]  
  (letfn 
    [(func [acc [k v]] 
           (condp = (type v)
             clojure.lang.PersistentArrayMap (merge acc (b-key-val (map #(assoc % 0 (format "%s[%s]" k  (% 0))) v)))
             clojure.lang.PersistentVector (merge acc (b-key-val (map-indexed #(vector (format "%s[%d]" k %1) %2) v)))
             (assoc acc k v)))]    
    (reduce func {} (into [] hash)))) 

(defn check []
  (let [d {"a" {"b" 3 "c" 2 "d" [1 2]} "x" ["1" "2" "3"] }
        expected {"a[b]" 3 "a[c]" 2 "a[d][0]" 1 "a[d][1]" 2 "x[0]" "1" "x[1]" "2" "x[2]" "3"}]
    (assert (= expected (b-key-val d)))))  
