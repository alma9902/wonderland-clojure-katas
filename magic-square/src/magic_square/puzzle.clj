(ns magic-square.puzzle)

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])

(defn magic? [values]
  (let [[v1 v2 v3 v4 v5 v6 v7 v8 v9] values]
    ;;[v1 v2 v3]
    ;;[v4 v5 v6]
    ;;[v7 v8 v9]
    (=
      (+ v1 v2 v3)
      (+ v4 v5 v6)
      (+ v7 v8 v9)
      (+ v1 v4 v7)
      (+ v2 v5 v8)
      (+ v3 v6 v9)
      (+ v1 v5 v9)
      (+ v3 v5 v7))))

(defn permutation [values]
  (lazy-seq
    (if (next values)
      (for [head values
            tail (permutation (disj values head))]
        (cons head tail))
      [values])))

(defn magic-square [values]
  (let [answers (filter magic? (permutation (into #{} values)))
        magic []]
    ((fn [x]
       (let [[v1 v2 v3 v4 v5 v6 v7 v8 v9] x]
         [[v1 v2 v3] [v4 v5 v6] [v7 v8 v9]])) (first answers))))
