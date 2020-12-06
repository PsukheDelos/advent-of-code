(ns advent-of-code-2020.day-6
  (:require [clojure.set :refer :all]))


(defn someone-yes [path]
  (with-open [r (clojure.java.io/reader path)]
    (loop [yes 0
           answered-questions #{}
           lines (doall (line-seq r))]
      (let [line (first lines)]
        (if (seq lines)
          (if (empty? line)
            (recur (+ yes (count answered-questions)) #{} (rest lines))
            (recur yes (apply conj answered-questions line) (rest lines)))
          (+ yes (count answered-questions)))))))


(defn everyone-yes [path]
  (with-open [r (clojure.java.io/reader path)]
    (loop [yes 0
           answered-questions []
           lines (doall (line-seq r))]
      (let [line (first lines)]
        (if (seq lines)
          (if (empty? line)
            (recur (+ yes (count (apply intersection answered-questions))) [] (rest lines))
            (recur yes (conj answered-questions (set line)) (rest lines)))
          (+ yes (count (apply intersection answered-questions))))))))



