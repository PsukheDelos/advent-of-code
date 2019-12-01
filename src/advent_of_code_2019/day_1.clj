(ns advent-of-code-2019.day-1)

(def module-fuel-fn (comp #(- % 2) #(quot % 3)))

(defn module-and-fuel-fuel-fn [weight]
  (loop [w 0
         f (module-fuel-fn weight)]
    (if (pos? f)
      (recur (int (+ w f)) (module-fuel-fn f))
      w)))

(defn day-1 [input fuel-fn]
  (reduce (fn [acc w]
            (+ acc (fuel-fn w)))
          0
          input))
