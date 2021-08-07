(ns battle-asserts.issues.zigzag-conversion
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as fk]))

(def level :hard)

(def tags ["strings"])

(def description "The string \"PAYPALISHIRING\" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
  P   A   H   N
  A P L S I I G
  Y   I   R
  And then read line by line: \"PAHNAPLSIIGYIR\"
  Write the code that will take a string and make this conversion given a number of rows:
  convert(\"PAYPALISHIRING\", 3) should return \"PAHNAPLSIIGYIR\".")

(def signature
  {:input [{:argument-name "line" :type {:name "string"}}
           {:argument-name "height" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn- gen-words []
  (let [words (fk/words {:n (gen/generate (gen/choose 2 6))})
        upper (map s/upper-case words)]
    (s/join upper)))

(defn arguments-generator []
  (let [samples (repeatedly 30 gen-words)]
    (gen/tuple (gen/elements samples) (gen/choose 3 10))))

(def test-data
  [{:expected "PAHNAPLSIIGYIR" :arguments ["PAYPALISHIRING" 3]}
   {:expected "Pnassvosnomciocieouriooclolnoosulcpicnmtricai" :arguments ["Pneumonoultramicroscopicsilicovolcanoconiosis" 5]}])

(defn solution [string height]
  (s/join (flatten (for [i (range height)]
                     (map #(nth string %)
                          (take-while
                           #(< % (count string))
                           (if (= i (quot height 2))
                             (iterate #(+ (inc (quot height 2)) %) i)
                             (iterate #(+ (inc height) %) i))))))))
