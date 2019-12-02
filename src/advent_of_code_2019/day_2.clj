(ns advent-of-code-2019.day-2)


(defmacro op-instruction [op i in]
  `(recur (assoc ~in (nth ~in (+ ~i 3))
                     (~op
                       (->> (+ ~i 1)
                             (nth ~in)
                             (nth ~in))
                       (->> (+ ~i 2)
                            (nth ~in)
                            (nth ~in))))
         (+ ~i 4)))


(defn run-intcode
  ([input noun verb]
   (run-intcode (assoc input
                  1 noun
                  2 verb)))
  ([input]
   (loop [in input
          i 0]
     (case (int (nth in i))
       1 (op-instruction + i in)
       2 (op-instruction * i in)
       99 in))))


(defn find-noun-verb [input result]
  (first (for [noun (range 100)
               verb (range 100)
               :when (= result (first (run-intcode input noun verb)))]
           (+ verb (* noun 100)))))
