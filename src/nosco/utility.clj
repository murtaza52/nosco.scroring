(ns nosco.utility
  "Utility functions"
  (:require [clojure.edn :as edn]))

(defn read-file
  "Eagerly reads an edn file into a clojure data structure."
  [file-name]
  (edn/read-string (slurp file-name)))

(defn median
  "Median calculation (source - Rosetta Stone)"
  [ns]
  (let [ns  (sort (remove nil? ns)) ;; account for the any nil scores
        cnt (count ns)
        mid (bit-shift-right cnt 1)]
    (when (seq ns) ;; account for the use case where there are no valid scores
      (if (odd? cnt)
        (nth ns mid)
        (float (/ (+ (nth ns mid) (nth ns (dec mid))) 2))))))

(comment
  (median [1 2 3 4 5 6 nil])
  (median [nil]))

(defn kv->coll
  "Given a hash-map {k v, .....} and keys k1 and k2, returns a collection of hash-maps such that {k v, ..} -> [{k1 k, k2 v}, ..]"
  [k1 k2 m]
  (reduce-kv (fn [acc k v]
               (conj acc {k1 k k2 v}))
             []
             m))

(comment
  (kv->coll :a :b {1 2 3 4 5 6}))

