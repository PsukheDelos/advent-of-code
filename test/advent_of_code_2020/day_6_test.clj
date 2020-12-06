(ns advent-of-code-2020.day-6-test
  (:require [clojure.test :refer :all])
  (:require [advent-of-code-2020.day-6 :refer :all]))

(deftest day-6-sample-test
  (testing "day-6-1"
    (is (= 11
           (someone-yes "resources/day_6_sample.txt"))))

  (testing "day-6-2"
    (is (= 6
           (everyone-yes "resources/day_6_sample.txt")))))

(deftest day-6-test
  (testing "day-6-1"
    (is (= 6387
           (someone-yes "resources/day_6.txt"))))

  (testing "day-6-2"
    (is (= 3039
           (everyone-yes "resources/day_6.txt")))))
