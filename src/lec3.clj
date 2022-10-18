(defn removo [val [head & tail]]
  (when head
    (if (val head)
      (cons head (lazy-seq (removo val tail)))
      (recur val tail))))

(println (removo pos? [1 -2 -3 -4 3 4 3 4]))


(println (filter (fn [a] (> a 3)) [1 2 3 4 5 6 7 8 9 10]))


(println (filter #(> % 3) [1 2 3 4 5 6 7 8 10 9]))

(map inc [1 2 3 4])

(println (reduce conj [] [ 1 2 3 4]))
(println (reduce conj () [ 1 2 3 4]))

(println (reductions conj () [ 1 2 3 4]))                   ; показывает весь ход присоеденинения к списку чектора
(println (mapcat (fn [i] (range 0 i)) [1 2 3 4]))
(println (map (fn [i] (range 0 i)) [1 2 3 4]))
;(println (apply concat (fn [i] (range 0 i)) [1 2 3 4]))

(println (reduce + (filter #(> % 2) (map count["aesdrtjykuh" "wasg" "sadf" "aa" "d"]))))

(println (->> ["aesdrtjykuh" "wasg" "sadf" "aa" "d"] (map count) (filter #(> % 2)) (reduce +)))

(println (-> 1 (+ 2) (* 3)))

(println (for [str ["aesdrtjykuh" "wasg" "sadf" "aa" "d"]
           :let [l (count str)]
           :when (> l 2)]
           l))

(println (for [x [1 2 3] y [ 3 4 5 6] ][x y]))

(println (for [str ["aaaeeeesdrtjykuh" "wasg" "sadf" "aa" "d"]
               c (set str)
               :let [l (count (filter #(= % c) str))]
               :when (> l 1)]
           [c str]))

(println (for [str ["aaaeeeesdrtjykuh" "wasg" "sadf" "aa" "d"]
               c (distinct str)
               :let [l (count (filter #(= % c) str))]
               :when (> l 1)]
           [c str l]))


(defn sum "Calculates sum of two params " {:private true :author "Me" }
  [^long a ^long b]                                         ;Требует что бы  параметрыв были лонгами
  {:pre [(number? a) (number? b)]                     ;На входе проверит числа ли параметры
         :post [(number? %)]} (+ a b))                      ;На выходе проверит число ли

(doc sum)
(println (meta (var sum)))
;(println (sum "3" "3")) ошибка
;(println (sum 1 "s")) class cast exception


(defn zzz [^String str] (.indexOf str "abc"))
(println (zzz "ss"))

(defn sum
  ([& rest] (apply + rest))
  )

(def v [3 5 6 7])

(println(let [a v]))
(let [[a1 & rest] v]
(println "a1=" a1 "rest=" rest)
)

(let [[n1 n2 n3 [str1 str2] :as full] [1 2 3 ["a" "b"]]]
  (println str1 str2 n2 full)
  )

(def john {:name "John" :surname "Smith"})

(let [ p john] (:name p))

(println (let [{name :name surname :surname} john] [ name surname]))

(println (let [{:keys [name surname]} john] [ name surname]))

(println (let [{:strs [name surname]} {"name" "John", "surname" "Smirh"}] [ name surname]))

(println (let [{:keys [name surname age] :or {age 1}} john] [ name surname age]))

(def john {:name "John" :surname "Smith" :address {:city "MOscow" :street "Vadkovsky"}})

(println (let [{:keys [name surname] {:keys [city street]}:address}  john] [ name surname city street]))


(defmacro infix [a op b] (list op a b))
(println (infix 1 + 3))

(println (macroexpand-1 '(infix 1 + 3)))


(defmacro unless [test then else] `(if ~test ~else ~then))
(println (unless (> 2 3) (println "a") (println "b")))
(println (macroexpand-1 '(unless (> 2 3) (println "a") (println "b"))))

;------------------------------------------------------- след лекция
(defmacro log [a]
  `(println a "=" a)
  )

(log (+ 1 2))

(defn variance
  [start end]
  (println((/ (reduce + (nums2 start end)) (- (+ end 1) start))))
  (let [len (- (+ end 1) start)]
    (let [mean (/ (reduce + (nums2 start end)) len)]
      (let [sqDiff (reduce + (map #(- % mean) (nums2 start end)))]
        (println(/ sqDiff len))
        )
      )
    )
  )
(variance 1 24)