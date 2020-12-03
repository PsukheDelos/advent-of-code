(ns advent-of-code-2020.day-3)

(defn day-3-1 [tree-map x-step y-step]
  (let [width (count (first tree-map))
        height (count tree-map)]
    (loop [x 0
           y 0
           trees 0]
      (let [tree? (get-in tree-map [y x])]
        (if (> y height)
          trees
          (recur
            (long (rem (+ x x-step) width))
            (long (+ y y-step))
            (if tree? (inc trees) trees)))))))

(defn day-3-2 [tree-map slopes]
  (reduce
    (fn [result slope]
      (let [[x-step y-step] slope]
        (* result (day-3-1 tree-map x-step y-step))))
    1
    slopes))
