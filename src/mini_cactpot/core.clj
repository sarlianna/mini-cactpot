(ns mini-cactpot.core
  (:gen-class))

(use 'clojure.core.logic)


(def sum-value {6 10000
                7 36
                8 720
                9 360
                10 80
                11 252
                12 108
                13 72
                14 54
                15 72
                16 54
                17 180
                18 119
                19 36
                20 306
                21 1080
                22 144
                23 1800
                24 3600})


(def starting-board [[nil nil nil]
                    [nil nil nil]
                    [nil nil nil]])


(def possible-values (range 1 10))


(defn get-row
  "handy row-chart
  x represents a number in the grid
  lines represent the direction of the row the number represents
  1  2  3  4  5
   \ |  |  | /
 7 --x  x  x (vector at index 0)
 8 --x  x  x (vector at index 1)
 9 --x  x  x (vector at index 2)
ind. 0  1  2  of list
  calling with a list of lists (board) and a number will return
  a list of 3 corresponding to the above chart, with nil for unknown numbers
  order returned is ordered by list index first, then index inside the list
   "

  [board row-num]
  (case row-num
    ; left diagonal
    1 (conj [] (first (first board))
               (second (second board))
               (last (last board)))
    ; columns
    (2 3 4) (let [ind (- row-num 2)]
              (conj [] (get (first board) ind)
                       (get (second board) ind)
                       (get (last board) ind)))
    ; right diagonal
    5 (conj [] (last (first board))
               (second (second board))
               (first (last board)))
    ; rows
    (7 8 9) (get board (- row-num 7))))


(defn get-all-rows [board]
  (reduce conj [] (map get-row (range 1 10))))


(defn insert-known [board known]
  (reduce (fn [board val-pos]
            (let [row (nth board (:row val-pos))
                  new-row (vector (map (fn [x]
                                         (if (= x (:col val-pos))
                                           (:val val-pos)
                                           (nth row x)))
                                       [0 1 2]))]
              (vector (map (fn [x]
                             (if (= x (:row val-pos))
                               new-row
                               (nth board x)))
                           board))))
          board
          known))


(defn filter-known [known]
  (filter #(some (= %) known) possible-values))


(defn get-possible-rows [row remaining-nums]
  (let [non-nil (filter #(not nil? %))
        non-nil-count (count non-nil)]
  ))


(defn score-row
  "Given a row with some or no unknowns, returns the average (or actual) score possible with this row."
  [ind-row remaining-nums]
  (let [ind (first num-row)
        row (second num-row)]
    (if (not-any? nil? row)
      (reduce + row)
      (let [possibilities (get-possible-rows row remaining-nums)
            sums (map #(nth sum-value (reduce + %)) possibilities)]
        (/ sums (count possibilities))))))


(defn print-row [row-num]
  (let [help '(" 1  2  3  4  5"
             "  \ |  |  | /"
             "7 --x  x  x"
             "8 --x  x  x"
             "9 --x  x  x")]
    (doseq [msg help]
      (print msg))
    (print "Best risky row:"
    (print row-num))


(defn solve [known]
  (let [board (insert-known starting-board known)
        remaining-nums (filter-known known)
        ;this assumes the order stays the same
        all-rows (get-all-rows board)
        sums (map score-row all-rows remaining-nums)
        highest-score  (max sums)]
    (print-row (-> sums
                   (map-indexed vector)
                   (filter #(= highest-score (second %)))
                   (map first)
                   (first)))))


(defn test []
  (solve ({:val 2 :row 1 :col 1} {:val 3 :row 0 :col 0}
          {:val 7 :row 2 :col 0} {:val 9 :row 0 :col 2})))

(defn -main [& args]
  (test))
