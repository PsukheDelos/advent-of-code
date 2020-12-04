(ns advent-of-code-2020.day-4
  (:require [clojure.string :as str]))

(def required-fields
  [:byr :iyr :eyr :hgt :hcl :ecl :pid])

(def optional-fields
  [:cid])

(defn valid-passport? [passport]
  (every? (set (keys passport)) required-fields))

(defn read-passport [passport line]
  (into passport
        (map (fn [kv] (let [[k v] (str/split kv #":")]
                        [(keyword k) v]))
             (str/split line #" "))))

(defn day-4-1 [path]
  (with-open [r (clojure.java.io/reader path)]
    (loop [valid 0
           passport {}
           lines (doall (line-seq r))]
      (let [line (first lines)]
        (if (seq lines)
          (if (empty? line)
            (if (valid-passport? passport)
              (recur (inc valid) {} (rest lines))
              (recur valid {} (rest lines)))
            (recur valid (read-passport passport line) (rest lines)))
          (if (valid-passport? passport)
            (inc valid)
            valid))))))

(defn valid-year? [year min max]
  (when (= 4 (count (re-matches #"\d+" year)))
    (<= min (Integer/parseInt year) max)))

(def valid-eye-colours
  ["amb" "blu" "brn" "gry" "grn" "hzl" "oth"])

(defn valid-height? [hgt]
  (when-let [[height measurement] (re-matches #"\d+(cm|in)" hgt)]
    (let [h (-> height
                (clojure.string/split #"in|cm")
                first
                Integer/parseInt)]
      (if (= "cm" measurement)
        (<= 150 h 193)
        (<= 59 h 76)))))

(defn valid-hair-colour? [hcl]
  (re-matches #"\#[0-9a-f]{6}" hcl))

(defn valid-eye-colour? [ecl]
  (some #{ecl} valid-eye-colours))

(defn valid-passport-id? [id]
  (= 9 (count (re-matches #"\d+" id))))

(defn valid-passport-2? [passport]
  (when (every? (set (keys passport)) required-fields)
    (let [{:keys [byr iyr eyr hgt hcl ecl pid]} passport]
      (and (valid-year? byr 1920 2002)
           (valid-year? iyr 2010 2020)
           (valid-year? eyr 2020 2030)
           (valid-height? hgt)
           (valid-hair-colour? hcl)
           (valid-eye-colour? ecl)
           (valid-passport-id? pid)))))

(defn day-4-2 [path]
  (with-open [r (clojure.java.io/reader path)]
    (loop [valid 0
           passport {}
           lines (doall (line-seq r))]
      (let [line (first lines)]
        (if (seq lines)
          (if (empty? line)
            (if (valid-passport-2? passport)
              (recur (inc valid) {} (rest lines))
              (recur valid {} (rest lines)))
            (recur valid (read-passport passport line) (rest lines)))
          (if (valid-passport-2? passport)
            (inc valid)
            valid))))))
