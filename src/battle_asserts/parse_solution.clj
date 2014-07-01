(ns battle-asserts.parse-solution)

(defn- get-solution-code
  [path] 
  (read-string (str \((slurp path)\))))

(defn- get-function-check
  [solution-code]
  (first (filter #(= 'check (nth % 1)) solution-code)))

(defn- get-asserts
  [solution-code] 
  (let [function-check (get-function-check solution-code)]
    (into [] (drop 3 function-check))))

(defn get-asserts-from-file
  [path]
  (let [solution-code (get-solution-code path)]
    (get-asserts solution-code)))