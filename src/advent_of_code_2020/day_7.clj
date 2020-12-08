(ns advent-of-code-2020.day-7
  (:require [clojure.string :as str]))

(defn how-many-contain [path name-o-bag]
  (with-open [r (clojure.java.io/reader path)]

    (let [rule-map (reduce
                     (fn [rules line]
                       (let [[outer-bag inner-bags] (-> line
                                                        (clojure.string/replace #"bags" "bag")
                                                        (clojure.string/replace #"\." "")
                                                        (clojure.string/replace #"\d+ " "")
                                                        (str/split #" contain "))
                             inner-bags (remove #(= % "no other bag") (-> inner-bags
                                                                          (str/split #", ")))]
                         (merge-with
                           (fn [acc m]
                             (concat acc m))
                           rules
                           (into {} (map (fn [inner-bag] [inner-bag [outer-bag]]) inner-bags)))))
                     {}
                     (doall (line-seq r)))]
      (loop [rules rule-map
             no 0
             seen #{name-o-bag}
             [name & others] [name-o-bag]]
        (if name
          (recur
            (dissoc rules name)
            (if (contains? seen name) no (inc no))
            (conj seen name)
            (concat others (get rules name)))
          no)))))


(defn a [k mm]
  (reduce
    (fn [acc [k v]]
      (if (get mm k)
        (+ acc v (* v (a k mm)))
        acc))
    0
    (get mm k)))


(defn how-many-bags [path name-o-bag]
  (with-open [r (clojure.java.io/reader path)]

    (let [rule-map (reduce
                     (fn [rules line]
                       (let [[outer-bag inner-bags] (-> line
                                                        (clojure.string/replace #"bags" "bag")
                                                        (clojure.string/replace #"\." "")
                                                        (str/split #" contain "))
                             inner-bags (remove #(= % "no other bag") (-> inner-bags
                                                                          (str/split #", ")))]

                         (assoc rules outer-bag (reduce (fn [m inner-bag]
                                                          (let [[_ n b] (re-find #"(\d+) (\S.*)" inner-bag)]
                                                            (assoc m b (Integer/parseInt n))))
                                                        {}
                                                        inner-bags))))
                     {}
                     (doall (line-seq r)))]
      (a name-o-bag rule-map))))




