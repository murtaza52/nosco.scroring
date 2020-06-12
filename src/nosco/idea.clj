(ns nosco.idea)

(defn- calculate-average-score
  "Calculates the average score. Ignores any nil values in the scores. If no valid values are available then returns nil as average."
  [scores]
  (let [scores* (remove nil? scores)]
    (if (seq scores*)
      (float (/ (apply + scores*)
                (count scores*)))
      ;; ideas with no scores will have nil as average value.
      nil)))

(comment
  (calculate-average-score [0 1 2 3 nil])
  (calculate-average-score [nil]))

(defn- add-average-score
  "Given an idea hash-map, it calculates the average score and associates it under the key :average-score."
  [idea]
  (assoc idea
         :average-score
         (calculate-average-score (:scores idea))))

(defn- valid-score?
  [idea]
  "Returns truthy if there is a non nil score for the idea."
  (and (:scores idea)
       (seq (remove nil? (:scores idea)))))

(comment
  (valid-score? {:a 1 :scores [nil]}))

;;;;;;;;;;;;;;;;;;;;
;;;; Public API ;;;;
;;;;;;;;;;;;;;;;;;;;

(defn process-ideas
  "Processes a collection of ideas, adds average score to each idea."
  [ideas]
  (->> ideas
       (map add-average-score)))

(comment
  (process-ideas [{:scores [1 2 3]} {:scores [nil]} {:scores [1 23 nil]}]))
