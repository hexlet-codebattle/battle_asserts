(ns array-interleave.multicode) 

(defn interleave
  [arr & args]
  (if (= args nil) 
    arr
    (sort 
      #(compare (read-string (str %1)) (read-string (str %2)))
      (vec (flatten (concat arr (vec args)))))))

(defn check []
  (let [arr [1 3 5]]
    (assert (= [1 2 3 4 5 6] (interleave arr 2 4 6)))
    (assert (= [1 2 3 4 5] (interleave arr [2 4])))
    (assert (= [1 "2" 3 "4" 5 ] (interleave arr "2" "4")))
    (assert (= [2 4 6] (interleave [] 2 4 6)))
    (assert (= [1 3 5] (interleave arr)))))
