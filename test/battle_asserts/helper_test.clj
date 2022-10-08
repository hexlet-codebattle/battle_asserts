(ns battle-asserts.helper-test
  (:require [test-helper :as h]
            [clojure.test.check :as tc]
            [clojure.test :refer [deftest is testing]]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]))

(defn generate-args [] (gen/tuple
                        gen/string-ascii
                        gen/boolean
                        (gen/choose -100 100)
                        (gen/double* {:min -10.0 :max 10.0 :NaN? false :infinite? false})
                        (gen/hash-map :test gen/string-ascii)
                        (gen/hash-map :test gen/boolean)
                        (gen/hash-map :test (gen/choose -100 100))
                        (gen/vector gen/string-ascii 1 5)
                        (gen/vector gen/boolean 1 5)
                        (gen/vector (gen/choose -100 100) 1 5)
                        (gen/vector (gen/vector gen/string-ascii 1 5) 1 5)
                        (gen/vector (gen/vector gen/boolean 1 5) 1 5)
                        (gen/vector (gen/vector (gen/choose -100 100) 1 5) 1 5)
                        (gen/vector (gen/hash-map :test gen/string-ascii) 1 5)
                        (gen/vector (gen/hash-map :test gen/boolean) 1 5)
                        (gen/vector (gen/hash-map :test (gen/choose -100 100)) 1 5)
                        (gen/vector (gen/vector (gen/vector gen/string-ascii 1 5) 1 5) 1 5)
                        (gen/vector (gen/vector (gen/vector (gen/choose -100 100) 1 5) 1 5) 1 5)
                        (gen/vector (gen/vector (gen/vector gen/boolean 1 5) 1 5) 1 5)
                        (gen/vector (gen/vector (gen/hash-map :test gen/string-ascii) 1 5) 1 5)
                        (gen/vector (gen/vector (gen/hash-map :test (gen/choose -100 100)) 1 5) 1 5)
                        (gen/vector (gen/vector (gen/hash-map :test gen/boolean) 1 5) 1 5)
                        (gen/hash-map :test (gen/hash-map :test gen/string-ascii))
                        (gen/hash-map :test (gen/hash-map :test (gen/choose -100 100)))
                        (gen/hash-map :test (gen/hash-map :test gen/boolean))
                        (gen/vector (gen/hash-map :test (gen/hash-map :test gen/string-ascii)) 1 5)
                        (gen/vector (gen/hash-map :test (gen/hash-map :test (gen/choose -100 100))) 1 5)
                        (gen/vector (gen/hash-map :test (gen/hash-map :test gen/boolean)) 1 5)
                        (gen/vector (gen/hash-map :test (gen/vector gen/string-ascii 1 5)) 1 5)
                        (gen/vector (gen/hash-map :test (gen/vector (gen/choose -100 100) 1 5)) 1 5)
                        (gen/vector (gen/hash-map :test (gen/vector gen/boolean 1 5)) 1 5)
                        (gen/vector (gen/hash-map :test (gen/hash-map :test (gen/hash-map :test (gen/vector gen/string-ascii 1 5)))) 1 5)
                        (gen/vector (gen/hash-map :test (gen/hash-map :test (gen/hash-map :test (gen/vector (gen/choose 1 5) 1 5)))) 1 5)
                        (gen/vector (gen/hash-map :test (gen/hash-map :test (gen/hash-map :test (gen/vector gen/boolean 1 5)))) 1 5)))

(def signatures (list
                 {:type {:name "string"}}
                 {:type {:name "boolean"}}
                 {:type {:name "integer"}}
                 {:type {:name "float"}}
                 {:type {:name "hash" :nested {:name "string"}}}
                 {:type {:name "hash" :nested {:name "boolean"}}}
                 {:type {:name "hash" :nested {:name "integer"}}}
                 {:type {:name "array" :nested {:name "string"}}}
                 {:type {:name "array" :nested {:name "boolean"}}}
                 {:type {:name "array" :nested {:name "integer"}}}
                 {:type {:name "array" :nested {:name "array" :nested {:name "string"}}}}
                 {:type {:name "array" :nested {:name "array" :nested {:name "boolean"}}}}
                 {:type {:name "array" :nested {:name "array" :nested {:name "integer"}}}}
                 {:type {:name "array" :nested {:name "hash" :nested {:name "string"}}}}
                 {:type {:name "array" :nested {:name "hash" :nested {:name "boolean"}}}}
                 {:type {:name "array" :nested {:name "hash" :nested {:name "integer"}}}}
                 {:type {:name "array" :nested {:name "array" :nested {:name "array" :nested {:name "string"}}}}}
                 {:type {:name "array" :nested {:name "array" :nested {:name "array" :nested {:name "integer"}}}}}
                 {:type {:name "array" :nested {:name "array" :nested {:name "array" :nested {:name "boolean"}}}}}
                 {:type {:name "array" :nested {:name "array" :nested {:name "hash" :nested {:name "string"}}}}}
                 {:type {:name "array" :nested {:name "array" :nested {:name "hash" :nested {:name "integer"}}}}}
                 {:type {:name "array" :nested {:name "array" :nested {:name "hash" :nested {:name "boolean"}}}}}
                 {:type {:name "hash" :nested {:name "hash" :nested {:name "string"}}}}
                 {:type {:name "hash" :nested {:name "hash" :nested {:name "integer"}}}}
                 {:type {:name "hash" :nested {:name "hash" :nested {:name "boolean"}}}}
                 {:type {:name "array" :nested {:name "hash" :nested {:name "hash" :nested {:name "string"}}}}}
                 {:type {:name "array" :nested {:name "hash" :nested {:name "hash" :nested {:name "integer"}}}}}
                 {:type {:name "array" :nested {:name "hash" :nested {:name "hash" :nested {:name "boolean"}}}}}
                 {:type {:name "array" :nested {:name "hash" :nested {:name "array" :nested {:name "string"}}}}}
                 {:type {:name "array" :nested {:name "hash" :nested {:name "array" :nested {:name "integer"}}}}}
                 {:type {:name "array" :nested {:name "hash" :nested {:name "array" :nested {:name "boolean"}}}}}
                 {:type {:name "array" :nested {:name "hash" :nested {:name "hash" :nested {:name "hash" :nested {:name "array" :nested {:name "string"}}}}}}}
                 {:type {:name "array" :nested {:name "hash" :nested {:name "hash" :nested {:name "hash" :nested {:name "array" :nested {:name "integer"}}}}}}}
                 {:type {:name "array" :nested {:name "hash" :nested {:name "hash" :nested {:name "hash" :nested {:name "array" :nested {:name "boolean"}}}}}}}))

(deftest helper-test
  (testing "Type checker for generators."
    (tc/quick-check 10 (prop/for-all [v (generate-args)]
                                     (is (= signatures
                                            (h/prepare-arguments v)))))))
