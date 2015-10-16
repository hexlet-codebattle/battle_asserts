(ns battle-asserts.issues.join-url-params-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.join-url-params :as issue]))

(deftest test-solution
  (let [url "http://www.foobar.com?first_param=123&second_param=456&third_param=678"]
    (is (= url (issue/solution "http://www.foobar.com"
                               {:first_param 123
                                :second_param 456
                                :third_param 678}))))
  (let [url "http://www.example.com/search?q=findme&useragent=chrome"]
    (is (= url (issue/solution "http://www.example.com/search"
                               {:q "findme"
                                :useragent "chrome"})))))
