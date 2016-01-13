(ns lambda-generator.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [lambda-generator.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
