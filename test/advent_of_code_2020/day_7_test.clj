(ns advent-of-code-2020.day-7-test
  (:require [clojure.test :refer :all])
  (:require [advent-of-code-2020.day-7 :refer :all]))

(deftest day-7-sample-test
  (testing "day-7-1"
    (is (= 4
           (how-many-contain "resources/day_7_1_sample.txt" "shiny gold bag"))))

  (testing "day-7-2"
    (is (= 126
           (how-many-bags "resources/day_7_2_sample.txt" "shiny gold bag")))))

(deftest day-7-test
  (testing "day-7-1"
    (is (= 148
           (how-many-contain "resources/day_7.txt" "shiny gold bag"))))

  (testing "day-7-2"
    (is (= 24867
           (how-many-bags "resources/day_7.txt" "shiny gold bag")))))
