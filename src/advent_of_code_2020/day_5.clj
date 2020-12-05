(ns advent-of-code-2020.day-5
  (:require [clojure.data :as clojure.data]))


(defn find-seat-id [example]
  (reduce-kv
    (fn [acc idx instruction]
      (cond-> acc

              ;; we only need to worry about the upper half (e.g instructions B & R)
              (= \B instruction)
              (+ (* 8 (int (Math/pow 2 (- 6 idx)))))

              (= \R instruction)
              (+ (int (Math/pow 2 (- 9 idx))))))
    0
    (vec example)))


(defn highest-seat-id [boarding-passes]
  (apply max (map find-seat-id boarding-passes)))


(defn find-my-seat [boarding-passes]
  (let [seat-ids (set (map find-seat-id boarding-passes))]
    (first (clojure.data/diff
             (set (range (apply min seat-ids)
                         (inc (apply max seat-ids))))
             (set seat-ids)))))





