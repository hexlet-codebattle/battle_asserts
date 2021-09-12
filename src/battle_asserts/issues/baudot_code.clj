(ns battle-asserts.issues.baudot-code
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as fk]
            [clojure.string :as s]))

(def level :hard)

(def tags ["strings" "decryption"])

(def description
  {:en "The Baudot code or International Teleprinter Code was invented by Emile Baudot in 1870. It was used for teleprinter messages instead of the morse code. Baudot uses five bits per character, thus allowing up to 32 distinct characters. As a technique used to extend this limitation, the code uses up-shift and down-shift modes as is used on a typewriter. In the Baudot code, each five bits transmitted must be interpreted according to whether they are up-shifted (figures) or down-shifted (letters). For example, the bit pattern 11111 represents up-shift and the bit pattern 11011 represents down-shift characters. All characters transmitted after the sequence 11111 but before the shifted sequence 11011 are treated as up-shift characters. All characters transmitted after the sequence 11011 are treated as down-shift characters until the pattern 11111 is recognized.
  Code   Letter  Figure
  .o.o.  R       4
  .o.oo  J       Not allocated
  .ooo.  C       :
  oo.o.  G       Not allocated
  .o..o  D       Not allocated
  .oooo  K       (
  .oo..  N       ,
  ...oo  A       -
  .....  BLANK
  oo.oo  Figures(F) SHift
  ..oo.  I       8
  oo...  O       9
  oooo.  V       =
  o..o.  L       )
  .oo.o  F       Not allocated
  ....o  E       3
  ..ooo  U       7
  oo..o  B       ?
  ..o..  SPACE
  ooooo  Letters(L) shift
  ooo..  M       :
  o..oo  W       2
  o.oo.  P       0
  ..o.o  S       ,
  ooo.o  X       /
  o...o  Z       +
  o.ooo  Q       1
  o.o..  H       Not allocated
  o.o.o  Y       6
  o....  T       5"
   :ru "Код Бодо или Международный код телепринтера был изобретен Эмилем Бодо в 1870 году. Он использовался для сообщений телепринтера вместо азбуки Морзе. Бодо использует пять битов на символ, что позволяет использовать до 32 отдельных символов. Чтобы расширить это ограничение, в коде используются режимы сдвига вверх и вниз, как в пишущей машинке. В коде Бодо каждые пять передаваемых битов должны интерпретироваться в зависимости от того, являются ли они сдвинутыми вверх (цифры) или вниз (буквы). Например, последовательность битов 11111 представляет символы со сдвигом вверх, а последовательность битов 11011 - символы со сдвигом вниз. Все символы, передаваемые после последовательности 11111, но до сдвинутой последовательности 11011, рассматриваются как символы со сдвигом вверх. Все символы, передаваемые после последовательности 11011, рассматриваются как символы сдвига вниз, пока не будет распознан шаблон 11111.
  Код    Буква   Фигура
  .o.o.  R       4
  .o.oo  J       Не выделенно
  .ooo.  C       :
  oo.o.  G       Не выделенно
  .o..o  D       Не выделенно
  .oooo  K       (
  .oo..  N       ,
  ...oo  A       -
  .....  ПРОПУСК
  oo.oo  Фигуры(Ф) Сдвиг
  ..oo.  I       8
  oo...  O       9
  oooo.  V       =
  o..o.  L       )
  .oo.o  F       Не выделено
  ....o  E       3
  ..ooo  U       7
  oo..o  B       ?
  ..o..  ПРОБЕЛ
  ooooo  Буквы(Б) Сдвиг
  ooo..  M       :
  o..oo  W       2
  o.oo.  P       0
  ..o.o  S       ,
  ooo.o  X       /
  o...o  Z       +
  o.ooo  Q       1
  o.o..  H       Не выделено
  o.o.o  Y       6
  o....  T       5"})

(def signature
  {:input [{:argument-name "code" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn- encrypt [word]
  (let [dict {"R" " .o.o."
              "J" ".o.oo"
              "C" ".ooo."
              "G" "oo.o."
              "D" ".o..o"
              "K" ".oooo"
              "N" ".oo.."
              "A" "...oo"
              " " "....."
              "" "oo.oo"
              "I" "..oo."
              "O" "oo..."
              "V" "oooo."
              "L" "o..o."
              "F" ".oo.o"
              "E" "....o"
              "U" "..ooo"
              "B" "oo..o"
              "M" "ooo.."
              "W" "o..oo"
              "P" "o.oo."
              "S" "..o.o"
              "X" "ooo.o"
              "Z" "o...o"
              "Q" "o.ooo"
              "H" "o.o.."
              "Y" "o.o.o"
              "T" "o...."}]
    (s/join #"" (map dict (s/split word #"")))))

(defn arguments-generator []
  (let [words (map s/upper-case (fk/words {:n 40}))
        encrypted (map encrypt words)]
    (gen/tuple (gen/elements encrypted))))

(def test-data
  [{:expected "640K ENOUGH" :arguments ["oo.ooo.o.o.o.o.o.oo.ooooo.oooo..o......o.oo..oo.....ooooo.o.o.o.."]}
   {:expected "1KB = 1000BYTES" :arguments ["oo.ooo.oooooooo.oooooo..o..o..ooooooo.oooooo...o..o.oooo.oo.o.oo.o.oo.ooooooo..oo.o.oo........o..o.o"]}
   {:expected "1KIB = 1024BYTES" :arguments ["oo.ooo.oooooooo.oooo..oo.oo..o..o..oo.oooooo.ooooo..o..oo.ooo.oooo.oo.o..oo.o.o.ooooooo..oo.o.oo........o..o.o"]}])

(defn solution [code]
  (let [dict {".o.o." ["R" "4"]
              ".o.oo" ["J" ""]
              ".ooo." ["C" ":"]
              "oo.o." ["G" ""]
              ".o..o" ["D" ""]
              ".oooo" ["K" "("]
              ".oo.." ["N" ","]
              "...oo" ["A" "-"]
              "....." [" " " "]
              "oo.oo" ["" ""]
              "..oo." ["I" "8"]
              "oo..." ["O" "9"]
              "oooo." ["V" "="]
              "o..o." ["L" ")"]
              ".oo.o" ["F" ""]
              "....o" ["E" "3"]
              "..ooo" ["U" "7"]
              "oo..o" ["B" "?"]
              "..o.." [" " " "]
              "ooooo" ["" ""]
              "ooo.." ["M" ":"]
              "o..oo" ["W" "2"]
              "o.oo." ["P" "0"]
              "..o.o" ["S" ","]
              "ooo.o" ["X" "/"]
              "o...o" ["Z" "+"]
              "o.ooo" ["Q" "1"]
              "o.o.." ["H" ""]
              "o.o.o" ["Y" "6"]
              "o...." ["T" "5"]}
        state (atom 1)]
    (letfn [(change-state [word]
              (cond
                (= "ooooo" word) (reset! state 1)
                (= "oo.oo" word) (reset! state 2)))
            (decryptor [word]
              (change-state word)
              (cond
                (= @state 1) (first (dict word))
                (= @state 2) (second (dict word))))]
      (->> code
           (partition-all 5)
           (map #(s/join %))
           (map decryptor)
           (s/join)))))
