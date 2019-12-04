(ns advent-of-code-2019.day-04
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.edn :as edn]))

(def input
  (-> "advent_of_code_2019/day_04.txt"
      io/resource io/file slurp
      (str/trim)
      (str/split #"-")))

(defn all-ascending? [number]
  (->> number
       (str)
       (vec)
       (map str)
       (map #(Integer/parseInt %))
       (apply <=)))

(defn valid-solution-part-1 [number]
  (let [count-of-unique-digits (count (set (str/split (str number) #"")))]
    (and (all-ascending? number)
         (<= count-of-unique-digits 5))))

(defn valid-solution-part-2 [number]
  (let [count-of-numbers (frequencies (str/split (str number) #""))
        count-of-doubles (->> count-of-numbers
                              (group-by val)
                              (#(get % 2))
                              (map key)
                              (count))]
    (>= count-of-doubles 1)))

(println "Solution part 1\n"
         (let [begin (read-string (get input 0))
               end   (read-string (get input 1))]
           (->> (range begin end)
                (filter valid-solution-part-1)
                (count))))

(println "Solution part 2\n"
         (let [begin (read-string (get input 0))
               end   (read-string (get input 1))]
           (->> (range begin end)
                (filter valid-solution-part-1)
                (filter valid-solution-part-2)
                (count))))
