(ns lambda-generator.db)

(def default-db
  {:name "re-frame"
   :lambda-count 0
   :lambda-remainder 0.0
   :last-time (/ (.getTime (js/Date.)) 1000)})
