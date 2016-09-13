(ns scrabble.core
  (:require [clojure.string :as string]))

(def ^:private initial-tile-distribution
  {"A" 9 "B" 2 "C" 2 "D" 4 "E" 12 "F" 2 "G" 3 "H" 2 "I" 9 "J" 1 "K" 1
   "L" 4 "M" 2 "N" 6 "O" 8 "P" 2 "Q" 1 "R" 6 "S" 4 "T" 6 "U" 4 "V" 2
   "W" 2 "X" 1 "Y" 2 "Z" 1 "_" 2})

(defn- group-and-sort-by-frequency [tiles-in-bag]
  (->> tiles-in-bag
       (group-by val)
       (map (fn [[freq freq-letter-pairs]]
              [freq (sort (map first freq-letter-pairs))]))
       (sort-by first >)))

(defn- format-tiles [sorted-tiles-by-freq]
  (->> sorted-tiles-by-freq
       (map (fn [[freq letters]]
              (str freq ": " (string/join ", " letters))))
       (string/join "\n")))

(defn- consume-one-tile [tiles-in-bag tile]
  (update tiles-in-bag tile dec))

(defn- consume [tiles-in-play tile-distribution]
  (reduce consume-one-tile tile-distribution tiles-in-play))

(defn- format-error-message [overconsumed-tiles]
  (str "Invalid input. More "
       (string/join ", " (map (comp str first) overconsumed-tiles))
       "'s have been taken from the bag than possible."))

(defn- format-result [tiles-in-bag]
  (let [overconsumed-tiles (filter #(neg? (second %)) tiles-in-bag)]
    (if (empty? overconsumed-tiles)
      (format-tiles
        (group-and-sort-by-frequency tiles-in-bag))
      (format-error-message overconsumed-tiles))))

(defn tiles-in-bag [tiles-in-play]
  (->> initial-tile-distribution
       (consume (map str tiles-in-play))
       format-result))