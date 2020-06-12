(ns nosco.user)

(defn- make-user-lookup
  "Creates a single hash-map of users, where the key is the user-id string."
  [users]
  (reduce (fn [user-lookup user]
            (assoc user-lookup (:id user) user))
          {}
          users))

(comment
  (make-user-lookup [{:id "1" :b 1} {:id "2" :b 2}]))

(defn- normalize-house
  "Given a user hash-map, it normalizes its house value. If the user does not have a house, then a default house \"Free Folk\" is associated."
  [user]
  (let [houses (:house user)]
    (assoc user :house (cond
                         (set? houses)    houses
                         (string? houses) #{houses}
                         :else            #{"Free Folk"}))))
(comment
  (normalize-house {:house "a"}))

;;;;;;;;;;;;;;;;;;;;
;;;; Public API ;;;;
;;;;;;;;;;;;;;;;;;;;

(defn process-users
  "Processes a collection of users, returns a hash-map keyed by the user id."
  [users]
  (->> users
       (map normalize-house)
       make-user-lookup))

(comment
  (process-users [{:id "a"} {:id "b" :house "1"} {:id "c" :house #{"2"}}]))
