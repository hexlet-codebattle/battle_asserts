(ns fizzbuzz.multicode)

(defn fizzbuzz
  [number]
  (str
    (when (= (mod number 3) 0) "Fizz")
    (when (= (mod number 5) 0) "Buzz")))

(defn check []
  (assert (= "Fizz" (fizzbuzz 3)))
  (assert (= "Buzz" (fizzbuzz 50)))
  (assert (= "FizzBuzz" (fizzbuzz 150)))
  (assert (= "FizzBuzz" (fizzbuzz 5175))))
