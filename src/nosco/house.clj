(ns nosco.house
  (:require [nosco.utility :refer [read-file median kv->coll]]))

(defn- get-house-scores
  "Given a hash-map of users and a collection of ideas, returns a hash-map where the key is the house and value is a collection of average scores for all ideas associated with that house."
  [id->user ideas]
  ;; reduce over all the ideas
  (reduce (fn [house->score idea]
            (let [houses (-> idea :author-id id->user :house)]
              ;; reduce over all houses associated with the author for the given idea
              (reduce (fn [acc house]
                        (update-in acc [house] conj (:average-score idea)))
                      house->score
                      houses)))
          {}
          ideas))

(comment
  (get-house-scores {"1" {:house #{"b"}}
                     "2" {:house #{"b" "c"}}}
                    [{:author-id     "1"
                      :average-score 1}
                     {:author-id     "2"
                      :average-score 2}
                     {:author-id     "3"
                      :average-score 3}]))

(defn- add-innovation-score
  "Given a house hash-map, calculates and associates the innovation score."
  [house-m]
  (assoc house-m
         :innovation-score
         (median (:average-scores house-m))))

(defn- add-idea-count
  "Given a house hash-map, calculates and associates the idea count."
  [house-m]
  (assoc house-m
         :idea-count
         (count (:average-scores house-m))))

(defn- prep-house
  "Prepares a house hash-map for reporting, removes keys not needed for reporting."
  [house-m]
  (dissoc house-m :average-scores))

(defn- sort-houses
  "Sort houses based on the innovation score and idea count."
  [houses]
  (reverse
   (sort-by (juxt :innovation-score :idea-count) houses)))

(def house-map->coll-of-maps
  "Given a single hash-map of houses, returns a collection of hash-maps, where each hash-map represents a house."
  (partial kv->coll :house-name :average-scores))


;;;;;;;;;;;;;;;;;;;;
;;;; Public API ;;;;
;;;;;;;;;;;;;;;;;;;;


(defn process-houses
  "Given a hash-map of users and a collection of ideas, returns a collection of houses hash-maps. These are sorted by the innovation score and idea count."
  [id->user ideas]
  (->> (get-house-scores id->user ideas)
       house-map->coll-of-maps
       (map add-innovation-score)
       (map add-idea-count)
       sort-houses
       (map prep-house)))

(comment
  (process-houses {"1" {:house #{"a"}} "2" {:house #{"b"}}}
                  [{:author-id "1" :average-score 2}
                   {:author-id "1" :average-score 5}
                   {:author-id "2" :average-score 3}]))
