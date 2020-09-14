(ns test-helper
  (:require [clojure.test :refer :all]
            [clojure.test.check.generators :as gen]))

(defn generate-tests
  [data solution]
  (doseq [{expected :expected arguments :arguments} data]
    (is (= expected (apply solution arguments)))))

(def type-map
  {java.lang.String "string"
   java.lang.Long "integer"
   java.lang.Integer "integer"
   clojure.lang.PersistentList "array"
   clojure.lang.PersistentVector "array"
   clojure.lang.PersistentArrayMap "hash"})

(defn prepare-signature [signature]
  (map #(dissoc % :argument-name) (signature :input)))

(defn contains-val? [coll val]
  (reduce
   #(if (= val %2) (reduced true) %1)
   false coll))

(defn nested? [element]
  (contains-val?
   [clojure.lang.PersistentList
    clojure.lang.PersistentArrayMap
    clojure.lang.PersistentVector]
   (type element)))

(defn prepare-arguments [arguments]
  (reverse
   (reduce (fn [acc arg]
             (if (nested? arg)
               (conj acc {:type {:name (type-map (type arg)), :nested {:name (type-map (type (first arg)))}}})
               (conj acc {:type {:name (type-map (type arg))}})))
           ()
           arguments)))

(defn prp-arg [arguments]
  (reverse
   (reduce (fn [acc arg]
             (print arg)
             (if (nested? arg)
               (do (print (type (first arg)))
                 (conj acc {:type {:name (type-map (type arg)), :nested {:name (type-map (type (first arg)))}}}))
               (conj acc {:type {:name (type-map (type arg))}})))
           ()
           (gen/generate (arguments)))))

;(defn eqq [] (do (print (prepare-signature signature)) (print (prp-arg arguments-generator)) (= (prepare-signature signature) (prp-arg arguments-generator))))


(defn generate-signatures [signature arguments]
  (let [prepared-sign (prepare-signature signature)
        prepared-arg (prepare-arguments arguments)]
    (= prepared-sign prepared-arg)))