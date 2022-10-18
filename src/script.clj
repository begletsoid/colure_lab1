(ns script)

(defn exp [x]
  (reduce * (repeat 2 x)))

(defn nums2 [start end]                                     ; Создание списка из N чисел и стак не переполняется
  (if (>= start end)
    [end]
    (cons start (lazy-seq (nums2 (inc start) end))))        ; lazy-seq откладывает вычисление до момента как оно понадобится, явный пример замыкания
  )

(defn variance
  [start end]
  (let [len (- (+ end 1) start)]
    (let [mean (/ (reduce + (nums2 start end)) len)]
      (let [sqDiff (reduce + (map exp (map #(- % mean) (nums2 start end))))]
        (println (/ sqDiff len))
        )
      )
    )
  )
(variance 3 8)