(ns battle-asserts.issues.book-authors
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [battle-asserts.utility :as util]
            [faker.generate :as faker]))

(def level :elementary)

(def tags ["training" "strings" "collections" "hash-maps"])

(def description
  {:en "Implement a function that extracts authors from books."
   :ru "Создайте функцию, которая извлекает имена авторов из книг."})

(def signature
  {:input [{:argument-name "books" :type {:name "array" :nested {:name "hash" :nested {:name "string"}}}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (let [single-authors (repeatedly 30 util/gen-name)
        multiple-authors (repeatedly 30 #(s/join #", "
                                                 (repeatedly (gen/generate (gen/choose 2 4)) util/gen-name)))
        combined-authors (apply conj single-authors multiple-authors)
        book-names (repeatedly 60 faker/sentence)
        pages (repeatedly 30 #(str (gen/generate (gen/choose 100 600))))
        books (repeatedly 40 (fn [] {:name (rand-nth book-names) :authors (rand-nth combined-authors) :pages (rand-nth pages)}))]
    (gen/tuple (gen/vector (gen/elements books) 2 8))))

(def test-data
  [{:expected ["Some authors" "Authors"]
    :arguments [[{:name "Some book" :authors "Some authors" :pages "123"} {:name "Sample" :authors "Authors" :pages "321"}]]}
   {:expected ["Harold Abelson, Gerald Jay Sussman, Julie Sussman" "Pat Shaughnessy" "Matthias Felleisen, Robert Bruce Findler, Matthew Flatt, Shriram Krishnamurthi"]
    :arguments [[{:name "SICP" :authors "Harold Abelson, Gerald Jay Sussman, Julie Sussman" :pages "657"} {:name "Ruby Under a Microscope: An Illustrated Guide to Ruby Internals" :authors "Pat Shaughnessy" :pages "360"} {:name "How to Design Programs." :authors "Matthias Felleisen, Robert Bruce Findler, Matthew Flatt, Shriram Krishnamurthi" :pages "720"}]]}])

(defn solution [books]
  (mapv #(% :authors) books))
