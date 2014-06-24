(ns battle-asserts.core-test
  (:require [clojure.test :refer :all]
            [battle-asserts.core :refer :all]
            [multicode.core :as mc]))

(deftest test-multicode
  (is (= 
        "assert(=(1, fibo(a)))\nassert(=(2, fibo(b)))"
        (mc/prettify-code 
          :ruby 
          ['(assert (= 1 (fibo a)))
           '(assert (= 2 (fibo b)))])))) 
