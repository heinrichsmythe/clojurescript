(ns cljs.npm-deps-test
  (:refer-clojure :exclude [array vector])
  (:require [cljs.test :refer [deftest is]]
            ["lodash/array" :as array :refer [slice] :rename {slice slc}]
            [calculator :as vector :refer [add] :rename {add plus}]
            [es6_calc]
            [es6_default_hello :as es6hello]))

(def array #js [1 2 3])

(def vector [1])

(deftest test-module-processing
  (is (= (array/nth #js [1 2 3] 1) 2))
  ;; rename works
  (is (= (array-seq (slc #js [1 2 3] 1)) [2 3])))

(deftest test-global-exports
  (is (= (plus 1 2) 3)))

(deftest test-cljs-2224
  ;; array should be correctly resolved in the current NS (shadows module)
  (is (= (array-seq array) [1 2 3]))
  ;; same should happen with global-exports
  (is (= vector [1])))

(deftest test-cljs-2286
  (is (= 3 (es6_calc/calculator.add 1 2))))

(deftest test-cljs-1620
  (is (= "Hello, world!" (es6hello/default))))
