(ns battle-asserts.issues.string-format
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "#FIXME Написать функцию, принимающую на вход число и возвращающую строку
                 вида \"Values is \" плюс число дополненное до 5 символов нулями слева")

(defn arguments-generator []
  (gen/tuple (gen/one-of [(gen/choose 0 9)
                          (gen/choose 10 99)
                          (gen/choose 100 999)
                          (gen/choose 1000 9999)
                          (gen/choose 10000 99999)])))

(def test-data
  [{:expected "Value is 00123"
    :arguments [123]}
   {:expected "Value is 00005"
    :arguments [5]}
   {:expected "Value is 12345"
    :arguments [12345]}])

(defn solution [num]
  (format "Value is %05d" num))
