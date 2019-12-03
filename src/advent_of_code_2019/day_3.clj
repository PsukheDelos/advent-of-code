(ns advent-of-code-2019.day-3
  (:require [clojure.set :as set])
  (:import (clojure.lang LazySeq)))


(defn parse-direction [direction x1 y1]
  (let [dist (Integer/parseInt (subs direction 1))]
    (case (first direction)
      \U (for [y (range (inc y1) (+ y1 (inc dist)))] [x1 y])
      \D (for [y (range (dec y1) (- y1 (inc dist)) -1)] [x1 y])
      \R (for [x (range (inc x1) (+ x1 (inc dist)))] [x y1])
      \L (for [x (range (dec x1) (- x1 (inc dist)) -1)] [x y1]))))


(defn wire-to-points [wire]
  (loop [w wire
         [x1 y1] [0 0]
         wire-points []]
    (if (empty? w)
      wire-points
      (let [line-points (parse-direction (first w) x1 y1)]
        (recur
          (rest w)
          (last line-points)
          (concat wire-points line-points))))))


(defn intersections [wire-1-pts wire-2-pts]
  (set/intersection
    (into #{} wire-1-pts)
    (into #{} wire-2-pts)))


(defn distance-to-nearest-intersection [[wire-1 wire-2]]
  (apply min
         (map #(reduce (fn [a b] (+ (Math/abs ^int a) (Math/abs ^int b))) %)
              (intersections (wire-to-points wire-1)
                             (wire-to-points wire-2)))))


(defn fewest-steps-to-intersection [[wire-1 wire-2]]
  (let [wire-1-pts (wire-to-points wire-1)
        wire-2-pts (wire-to-points wire-2)]
    (apply min
           (map
             #(+ (inc (.indexOf ^LazySeq wire-1-pts %))
                 (inc (.indexOf ^LazySeq wire-2-pts %)))
             (intersections wire-1-pts wire-2-pts)))))
