(ns battle-solutions.shell-words-split-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn shell-words-split
  [string]
  (vec (map #(clojure.string/replace % #"(\\)|(\')" "")
            (clojure.string/split string #"(?<!\\)\s+(?!\w+\')"))))

(deftest test-asserts
  (let [input "here are 'two words'"
        output ["here" "are" "two words"]]
    (assert-equal output (shell-words-split input)))
  (let [input "foo bar 'foo bar'\\ baz 'foo bar'"
        output ["foo" "bar" "foo bar baz" "foo bar"]]
    (assert-equal output (shell-words-split input)))
  (let [input "grep Jan\\ 15 file\\ name\\ with\\ spaces"
        output ["grep" "Jan 15" "file name with spaces"]]
    (assert-equal output (shell-words-split input))))
