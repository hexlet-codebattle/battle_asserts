(ns battle-solutions.join-url-params-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn join-url-params [url params]
  (let [ordered-params (into (sorted-map) params)]
    (str url "?"
         (clojure.string/join "&"
                              (map #(str % "=" (ordered-params (keyword %)))
                                   (map name (keys ordered-params)))))))

(deftest test-asserts
  (let [url "http://www.foobar.com?first_param=123&second_param=456&third_param=678"]
    (assert-equal url (join-url-params "http://www.foobar.com"
                                       {:first_param 123,
                                        :second_param 456,
                                        :third_param 678})))
  (let [url "http://www.example.com/search?q=findme&useragent=chrome"]
    (assert-equal url (join-url-params "http://www.example.com/search"
                                       {:q "findme"
                                        :useragent "chrome"}))))
