(ns lambda-generator.handlers
    (:require [re-frame.core :as re-frame]
              [lambda-generator.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   (re-frame/dispatch [:tick])
   db/default-db))

(re-frame/register-handler
  :tick
  (fn [db _]
    (let [past-time (:last-time db)
          curr-time (/ (.getTime (js/Date.)) 1000)
          delta (- curr-time past-time)
          lambda-remainder (+ (* 1 delta) (:lambda-remainder db))
          lambda-inc (js/Math.floor lambda-remainder)
          new-lambda (+ lambda-inc (:lambda-count db))
          new-remainder (- lambda-remainder lambda-inc)]
        (js/setTimeout #(re-frame/dispatch [:tick]) 30)
        (-> db 
            (assoc :last-time curr-time)
            (assoc :lambda-count new-lambda)
            (assoc :lambda-remainder new-remainder)))))

(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/register-handler
  :lambda-click
  (fn [db _]
    (assoc db :lambda-count (inc (:lambda-count db)))))
