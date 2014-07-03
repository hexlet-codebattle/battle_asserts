(ns fizzbuzz.multicode)

(defn fizzbuzz 
  [number]
  (cond 
    (and (= (mod number 5) 0) (= (mod number 3) 0)) "FizzBuzz"
    (= (mod number 5) 0) "Buzz"
    (= (mod number 3) 0) "Fizz"))

(defn check []
  (assert (= "Fizz" (fizzbuzz 3)))
  (assert (= "Buzz" (fizzbuzz 50)))
  (assert (= "FizzBuzz" (fizzbuzz 150)))
  (assert (= "FizzBuzz" (fizzbuzz 5175))))