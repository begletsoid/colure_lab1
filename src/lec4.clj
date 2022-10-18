
;Нормальное сложение
(defmacro infix [a op b] (list op a b))
(println (infix 1 + 3))

(macroexpand-1 '(infix 1 + 3))

(defmacro unless [test then else] (if test else then))
(unless (> 2 3) (println "a") (println "b"))
(println (macroexpand-1 '(unless (> 2 3) (println "a") (println "b"))))

;Обратный if
(defmacro unless [test then else] `(if ~test ~else ~then))
(println (unless (> 2 3) (println "a") (println "b")))
(println (macroexpand-1 '(unless (> 2 3) (println "a") (println "b"))))

;Создаём функцию с метаданными
(defmacro defn-secure [name parameters & body]  `(defn ~name {:admin-only true} ~parameters ~@body)) 
(macroexpand-1 `(def-secure foo [a b] (+ a b)(println “foo called”))

;Пишем значение вызова и его навзание
(defmacro log [a]
  `(let [r# ~a] (println '~a "=" r#) r#))

(def a 5)
(+ 2 3 4 (log a))

(log (+ 2 3 4 (log a)))

(println (macroexpand '(log (+ 2 3 4 (log a)))))
(println (clojure.walk/macroexpand-all '(log (+ 2 3 4 (log a)))))

(filter #(> %2) (println (log (map inc [1 2 3 4]))))
(->> [1 2 3 4] (map inc) (log) (filter (filter #(> % 2))))

;Объявляем булеву функцию и обратную ей
(defmacro defn-with-not [name parameters & body]  
(list ‘do `(defn ~name ~parameters ~@body)
`(defn ~(symbol(str “not-” name)) ~parameters (not ~@body))
))
(macroexpand-1 `(defn-with-not list-contains [elem ls] (some #(= elem %) ls))

;Супер инфикс
(defmacro my-recursive-infix [form]
(if-not (seq? form)
form
(let [[x op y] form]
`(~op (my-recursive-infix ~x) (my-recursive-infix ~y))
)
)
