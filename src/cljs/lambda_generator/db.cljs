(ns lambda-generator.db)

(def default-db
  {:name "re-frame"
   :lambda-count 0
   :lambda-remainder 0.0
   :last-time (/ (.getTime (js/Date.)) 1000)
   :lambda-per-sec 0
   :tool-1 {:count 0
            :lps 0.1
            :base-cost 10
            :cost 10
            :name "Upgrade 1"}
   :tool-2 {:count 0
            :lps 0.1
            :cost 10
            :name "Upgrade 2"}
   :tool-3 {:count 0
            :lps 0.1
            :cost 10
            :name "Upgrade 3"}
   :tool-4 {:count 0
            :lps 0.1
            :cost 10
            :name "Upgrade 4"}
   :tool-5 {:count 0
            :lps 0.1
            :cost 10
            :name "Upgrade 5"}
   :tool-6 {:count 0
            :lps 0.1
            :cost 10
            :name "Upgrade 6"}
   :tool-7 {:count 0
            :lps 0.1
            :cost 10
            :name "Upgrade 7"}
   :tool-8 {:count 0
            :lps 0.1
            :cost 10
            :name "Upgrade 1"}})
