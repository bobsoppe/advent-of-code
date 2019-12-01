(ns advent-of-code-2019.day-01
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.edn :as edn]))

(def input
  (-> "advent_of_code_2019/day_01.txt"
      io/resource io/file slurp
      (->>
        (str/split-lines)
        (map edn/read-string))))

(defn calculate-fuel-for-mass [mass]
  (-> mass (/ 3) (int) (- 2)))

(defn calculate-total-fuel [total-fuel last-fuel]
  (if (<= last-fuel 0)
    total-fuel
    (recur (+ total-fuel last-fuel) (calculate-fuel-for-mass last-fuel))))

(println "Solution part 1\n"
         (->> input
              (map calculate-fuel-for-mass)
              (reduce + 0)))

(println "Solution part 2\n"
         (->> input
              (map #(->> %
                         (calculate-fuel-for-mass)
                         (calculate-total-fuel 0)))
              (reduce + 0)))
