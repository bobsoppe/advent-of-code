(ns advent-of-code-2019.day-03
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.edn :as edn]))

(def input
  (->> "advent_of_code_2019/day_03.txt"
       io/resource io/file slurp
       (str/split-lines)
       (map #(str/split % #","))))

(defn calculate-steps [[x y] direction distance]
  (map (fn [step]
         (case direction
           "U" [x (+ y step)]
           "R" [(+ x step) y]
           "D" [x (- y step)]
           "L" [(- x step) y]))
       (range 1 (+ 1 distance))))

(defn calculate-vector-from-step [[direction & distance]]
  [(str direction) (read-string (apply str distance))])

(defn calculate-path-from-step [coordinates [direction distance]]
  (let [last-coordinate (last coordinates)]
    (->>
      (calculate-steps last-coordinate direction distance)
      (concat coordinates)
      (vec))))

(defn calculate-coordinates [line]
  (->> line
       (map calculate-vector-from-step)
       (reduce calculate-path-from-step [[0 0]])))

(defn calculate-intersections [line-1 line-2]
  (let [line-2 (set line-2)]
    (->> (filter (fn [point] (contains? line-2 point)) line-1)
         (drop 1))))

(defn calculate-manhattan-distance [[x y]]
  (+ (Math/abs x) (Math/abs y)))

(defn calculate-steps-for-intersection [line-1 line-2 intersection]
  (let [line-1-steps (.indexOf line-1 intersection)
        line-2-steps (.indexOf line-2 intersection)]
    (+ line-1-steps line-2-steps)))

(println "Solution part 1\n"
         (let [[line-1 line-2] (map calculate-coordinates input)]
           (->> (calculate-intersections line-1 line-2)
                (map calculate-manhattan-distance)
                (reduce min))))

(println "Solution part 2\n"
         (let [[line-1 line-2] (map calculate-coordinates input)]
           (->> (calculate-intersections line-1 line-2)
                (map (fn [intersection] (calculate-steps-for-intersection line-1 line-2 intersection)))
                (reduce min))))
