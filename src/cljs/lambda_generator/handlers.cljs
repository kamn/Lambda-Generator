(ns lambda-generator.handlers
    (:require [re-frame.core :as re-frame]
              [lambda-generator.db :as db]))

;;TODO Move out
(defn re-calc-lps [db]
  (let [tool-1     (get-in db [:tool-1 :count])
        tool-1-lps (get-in db [:tool-1 :lps])]
    (* tool-1-lps tool-1))) 

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   (re-frame/dispatch [:tick])
   db/default-db))

(re-frame/register-handler
  :tick
  (fn [db _]
    (let [lps (:lambda-per-sec db)
          past-time (:last-time db)
          curr-time (/ (.getTime (js/Date.)) 1000)
          delta (- curr-time past-time)
          lambda-remainder (+ (* lps delta) (:lambda-remainder db))
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

(re-frame/register-handler
  :re-calc-tick-value
  (fn [db _]
    (-> db
      (assoc :lambda-per-sec (re-calc-lps db)))))

(re-frame/register-handler
  :tool-1
  (fn [db _]
    (re-frame/dispatch [:re-calc-tick-value])
    (let [lambdas (:lambda-count db)
          base-cost (get-in db [:tool-1 :base-cost])
          cost (get-in db [:tool-1 :cost])
          count (get-in db [:tool-1 :count])]
      (-> db
          (update-in [:lambda-count] #(- lambdas cost))
          (update-in [:tool-1 :cost] #(js/Math.floor (+ base-cost (inc count) (js/Math.pow 1.3 (inc count))))) 
          (update-in [:tool-1 :count] inc)))))
