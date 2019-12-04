(ns advent-of-code-2019.day-4)

(defn digits [n]
  (->> n str (map (comp read-string str))))

(defn how-many-passwords [range]
  (count (filter #(and (apply <= (digits %))
                       (re-find (re-pattern "(\\d)\\1+") (str %))) range)))

(defn how-many-passwords-with-one-more-detail [range]
  (count (filter #(and (apply <= (digits %))
                       (some (fn [a] (= 2 a))
                             (map count
                                  (partition-by identity (str %)))))
                 range)))
