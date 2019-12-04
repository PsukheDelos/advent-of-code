(ns advent-of-code-2019.day-4-test
  (:require [clojure.test :refer :all])
  (:require [advent-of-code-2019.day-4 :refer :all]))

(def day-4-input
  (range 367479 893699))

(deftest day-4-test
  (testing "day-4-1"
    (is (= 495 (how-many-passwords day-4-input))))

  (testing "day-4-1"
    (is (= 305 (how-many-passwords-with-one-more-detail day-4-input)))))
