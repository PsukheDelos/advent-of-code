(ns advent-of-code-2020.day-4-test
  (:require [clojure.test :refer :all])
  (:require [advent-of-code-2020.day-4 :refer :all]))

(deftest day-4-sample-test
  (testing "day-4-1"
    (is (= 2
           (day-4-1 "resources/day_4_1_sample.txt"))))

  (testing "day-4-2"
    (is (= 4
           (day-4-2 "resources/day_4_2_sample.txt")))))

(deftest day-4-test
  (testing "day-4-1"
    (is (= 192
           (day-4-1 "resources/day_4.txt"))))

  (testing "day-4-2"
    (is (= 101
           (day-4-2 "resources/day_4.txt")))))
