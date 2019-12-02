(ns advent-of-code-2019.day-02
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.edn :as edn]))

(def input
  (-> "advent_of_code_2019/day_02.txt"
      io/resource io/file slurp
      (str/split #",")
      (->>
        (map edn/read-string)
        (into []))))

(defn run-program [program position]
  (let [[op i j target] (subvec program position (+ position 4))
        i (get program i)
        j (get program j)]
    (case op
      99 program
      1 (recur (assoc program target (+ i j)) (+ position 4))
      2 (recur (assoc program target (* i j)) (+ position 4)))))

(println "Solution part 1\n"
         (-> input
             (assoc 1 12)
             (assoc 2 2)
             (run-program 0)
             (get 0)))

(println "Solution part 2\n"
         (filter some? (for [noun (range (count input))
                             verb (range (count input))]
                         (try (if (= 19690720 (-> input
                                                  (assoc 1 noun)
                                                  (assoc 2 verb)
                                                  (run-program 0)
                                                  (get 0)))
                                (+ (* 100 noun) verb)
                                nil)
                              (catch Exception e
                                (str ""))))))
