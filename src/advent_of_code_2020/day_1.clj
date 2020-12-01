(ns advent-of-code-2020.day-1)

(defn find-target [expenses target]
  (loop [e expenses]
    (when (seq e)
      (let [a (first e)
            b (- target a)
            r (rest e)]
        (if (some #{b} r)
          (* a b)
          (recur r))))))

(defn day-1-1 [expenses]
  (find-target expenses 2020))

(defn day-1-2 [expenses]
  (loop [e expenses]
    (when (seq e)
      (let [a (first e)
            b (- 2020 a)
            c (find-target (rest e) b)
            r (rest e)]
        (if c
          (* a c)
          (recur r))))))
