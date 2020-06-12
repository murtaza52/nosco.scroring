(ns nosco.scoring
  (:require [nosco.utility :refer [read-file]]
            [nosco.user :refer [process-users]]
            [nosco.idea :refer [process-ideas]]
            [nosco.house :refer [process-houses]]))

;; Parse the ideas file for further processing.
(def ideas (read-file "resources/ideas.edn"))

;; Parse the users file for further processing.
(def users (read-file "resources/users.edn"))

(defn prepare-innovation-report
  "Given a collection of users and ideas, returns an innovation report for the respective houses."
  [users ideas]
  (process-houses (process-users users) (process-ideas ideas)))

(comment
  (prepare-innovation-report [{:id "a"} {:id "b" :house "1"} {:id "c" :house #{"2"}}]
                             [{:author-id "a" :scores [1 2 3]}
                              {:author-id "b" :scores [nil]}
                              {:author-id "c" :scores [1 23 nil]}])
  (prepare-innovation-report users ideas))
