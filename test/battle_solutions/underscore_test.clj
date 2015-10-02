(ns battle-solutions.underscore-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn underscore [string]
  ((comp
    clojure.string/lower-case
    #(clojure.string/replace % #"::" "/")
    #(clojure.string/replace % #"([A-Z+])([A-Z][a-z])" "$1_$2")
    #(clojure.string/replace % #"([a-z\d])([A-Z])" "$1_$2")
    #(clojure.string/replace % #"-" "_"))
   string))

(deftest test-asserts
  (assert-equal "hello_how_are_you" (underscore "HelloHowAreYou"))
  (assert-equal "my_name_is_bond_james_bond" (underscore "MyNAMEIsBOND-JamesBond"))
  (assert-equal "active_model/errors" (underscore "ActiveModel::Errors"))
  (assert-equal "main_company/best_main_user" (underscore "MAINCompany::BEST-MAINUser")))
