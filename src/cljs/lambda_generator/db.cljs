(ns lambda-generator.db)

(def default-db
  {:name "re-frame"
   :lambda-count 0
   :lambda-remainder 0.0
   :last-time (/ (.getTime (js/Date.)) 1000)
   :lambda-per-sec 0
   :tool-1 {:count 0
            :lps 0.1
            :cost 10
            :name "Upgrade 1"}
   :tool-2 0
   :tool-2-lps 0.5
   :tool-3 0
   :tool-4 0
   :tool-5 0
   :tool-6 0
   :tool-7 0
   :tool-8 0})
