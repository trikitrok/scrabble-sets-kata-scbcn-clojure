(ns scrabble.core-test
  (:require [midje.sweet :refer :all]
            [scrabble.core :as scrabble]))

(facts
  "about scrabble"

  (facts
    "showing the tiles left in the bag"

    (fact
      "when there are no tiles in play"
      (scrabble/tiles-in-bag "") => (str "12: E\n"
                                         "9: A, I\n"
                                         "8: O\n"
                                         "6: N, R, T\n"
                                         "4: D, L, S, U\n"
                                         "3: G\n"
                                         "2: B, C, F, H, M, P, V, W, Y, _\n"
                                         "1: J, K, Q, X, Z"))

    (facts
      "when there are some tiles in play"
      (scrabble/tiles-in-bag
        "PQAREIOURSTHGWIOAE_") => (str "10: E\n"
                                       "7: A, I\n"
                                       "6: N, O\n"
                                       "5: T\n"
                                       "4: D, L, R\n"
                                       "3: S, U\n"
                                       "2: B, C, F, G, M, V, Y\n"
                                       "1: H, J, K, P, W, X, Z, _\n"
                                       "0: Q")

      (scrabble/tiles-in-bag
        "LQTOONOEFFJZT") => (str "11: E\n"
                                 "9: A, I\n"
                                 "6: R\n"
                                 "5: N, O\n"
                                 "4: D, S, T, U\n"
                                 "3: G, L\n"
                                 "2: B, C, H, M, P, V, W, Y, _\n"
                                 "1: K, X\n"
                                 "0: F, J, Q, Z"))

    (fact
      "when some tiles are overconsumed"
      (scrabble/tiles-in-bag
        "AXHDRUIOR_XHJZUQEE") => "Invalid input. More X's have been taken from the bag than possible."

      (scrabble/tiles-in-bag
        "KKBBB") => "Invalid input. More K, B's have been taken from the bag than possible.")))

