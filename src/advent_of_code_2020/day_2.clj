(ns advent-of-code-2020.day-2)

(defn day-2-1 [passwords]
  (reduce
    (fn [result password]
      (let [[min-l max-l l pw] password
            f (frequencies pw)
            c (get f l 0)]
        (if (<= min-l c max-l)
          (inc result)
          result)))
    0
    passwords))


(defn day-2-2 [passwords]
  (reduce
    (fn [result password]
      (let [[i j l pw] password
            first-l? (= l (get pw (dec i)))
            second-l? (= l (get pw (dec j)))]
        (if (and (or first-l? second-l?)
                 (not (and first-l? second-l?)))
          (inc result)
          result)))
    0
    passwords))
