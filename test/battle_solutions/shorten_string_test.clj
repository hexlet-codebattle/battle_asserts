(ns battle-solutions.shorten-string-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn shorten
  [s]
  (str (first s)
       (- (count s)
          2)
       (last s)))

(deftest test-asserts
  (assert-equal "L10n" (shorten "Localization"))
  (assert-equal "M17n" (shorten "Multilingualization"))
  (assert-equal "I18n" (shorten "Internationalization")))
