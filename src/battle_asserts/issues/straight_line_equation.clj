(ns battle-asserts.issues.straight-line-equation
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Create a function to describe a line passing through
                  two points with coordinates (x1, y1) and (x2, y2).
                  General view of the equation of the line is `y = kx + b`.
                  If the line cannot be built, then an empty string should be returned.")

(def signature
  {:input  [{:argument-name "point" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "string"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/tuple gen/small-integer gen/small-integer)
             (gen/tuple gen/small-integer gen/small-integer)))

(def test-data
  [{:expected "y = 0.86x + 3.86"
    :arguments [[6, 9], [-1, 3]]}
   {:expected "y = 0.43x - 2.57"
    :arguments [[6, 0], [-1, -3]]}
   {:expected "y = - 0.50x + 1.00"
    :arguments [[2, 0], [0, 1]]}
   {:expected "y = 3.33x"
    :arguments [[1.5, 5], [0, 0]]}
   {:expected ""
    :arguments [[0, 0], [0, 0]]}])

(defn solution [[x1, y1], [x2, y2]]
  (letfn [(format-b [num] (cond
                            (zero? num) ""
                            (neg? num) (format " - %.2f" (* -1 num))
                            :else (format " + %.2f" num)))
          (format-k [num] (cond
                            (zero? num) " x"
                            (neg? num) (format " - %.2fx" (* -1 num))
                            :else (format " %.2fx" num)))
          (k [x1, x2, y1, y2] (float (/ (- y1 y2) (- x1 x2))))
          (zero-division-protected? [x1, x2, y1, y2] (not (or (zero? (- y1 y2)) (zero? (- x1 x2)))))]
    (if (zero-division-protected? x1 x2 y1 y2)
      (str "y =" (format-k (k x1 x2 y1 y2)) (format-b (- y2 (* (k x1 x2 y1 y2) x2))))
      "")))

