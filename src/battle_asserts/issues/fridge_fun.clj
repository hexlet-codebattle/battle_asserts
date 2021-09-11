(ns battle-asserts.issues.fridge-fun)

(def level :medium)

(def tags ["collections"])

(def disabled true)

(def description
  {:en "Seryoga was given a refrigerator for his birthday. Not just any fridge, but a magic one! Every day new dishes appear in it. In order for the magic not to run out of food, Seryoga has to eat it in a certain way. You can't eat the same dish more than once at one meal. Help Seryoga make a menu for the day so as to eat all dishes at the minimum possible number of meals. For simplicity, the dishes are numbered from 0 to 100. Create a function that takes a string of `n` numbers with numbers of dishes and returns the number - the minimum possible number of meals, so that Sergei will not eat the same dish more than once at a time."
   :ru "На день рождения Сереге подарили холодильник. Не простой, а волшебный! Каждый день в нем появляются новые блюда. Чтобы волшебство не иссякло и блюда не кончались, Серега должен есть их определенным образом. Нельзя за один прием пищи съесть одно и тоже блюдо больше одного раза. Помогите Сереге составить меню на день так, чтобы съесть все блюда за минимально возможное количество приемов пищи. Для простоты блюда пронумерованы от 0 до 100. Создайте фугкцию, которая принимает строку из `n` чисел с номерами блюд и возвращающая число - минимальное возможное количество приемов пищи, чтобы Сереге не есть одно и тоже блюдо больше одного раза за один прием."})

(def signature
  {:input  [{:argument-name "dishes" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(def test-data
  [{:expected 2 :arguments ["124332"]}
   {:expected 1 :arguments ["124"]}
   {:expected 3 :arguments ["12912352"]}])
