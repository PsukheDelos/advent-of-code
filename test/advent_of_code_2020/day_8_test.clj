(ns advent-of-code-2020.day-8-test
  (:require [clojure.test :refer :all])
  (:require [advent-of-code-2020.day-8 :refer :all]))

(deftest day-8-sample-test
  (testing "day-8-1"
    (is (= 5
           (execute-boot-code sample))))

  (testing "day-8-2"
    (is (= 8
           (fix-boot-code sample 0 0 #{} false)))))

(deftest day-8-test
  (testing "day-8-1"
    (is (= 1723
           (execute-boot-code input))))

  (testing "day-8-2"
    (is (= 846
           (fix-boot-code input 0 0 #{} false)))))
