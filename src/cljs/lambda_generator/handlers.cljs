(ns lambda-generator.handlers
    (:require [re-frame.core :as re-frame]
              [lambda-generator.db :as db]))

(defn get-tool-lps [key db]
  (let [tool      (get-in db [key :count])
        tool-lps  (get-in db [key :lps])]
    (* tool tool-lps)))

;;TODO Move out
(defn re-calc-lps [db]
  (let [tool-1-lps (get-tool-lps :tool-1 db)
        tool-2-lps (get-tool-lps :tool-2 db)
        tool-3-lps (get-tool-lps :tool-3 db)
        tool-4-lps (get-tool-lps :tool-4 db)
        tool-5-lps (get-tool-lps :tool-5 db)
        tool-6-lps (get-tool-lps :tool-6 db)
        tool-7-lps (get-tool-lps :tool-7 db)
        tool-8-lps (get-tool-lps :tool-8 db)]
    (/ (js/Math.round (* 10 (+
      tool-1-lps
      tool-2-lps
      tool-3-lps
      tool-4-lps
      tool-5-lps
      tool-6-lps
      tool-7-lps
      tool-8-lps))) 10)))

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

(defn tool-update [key db]
  (let [lambdas (:lambda-count db)
        base-cost (get-in db [key :base-cost])
        cost (get-in db [key :cost])
        count (get-in db [key :count])]
    (-> db
        (update-in [:lambda-count] #(- lambdas cost))
        (update-in [key :cost] #(js/Math.floor (+ base-cost (inc count) (js/Math.pow 1.3 (inc count)))))
        (update-in [key :count] inc))))

(re-frame/register-handler
  :tool-1
  (fn [db _]
    (re-frame/dispatch [:re-calc-tick-value])
    (tool-update :tool-1 db)))

(re-frame/register-handler
  :tool-2
  (fn [db _]
    (re-frame/dispatch [:re-calc-tick-value])
    (tool-update :tool-2 db)))

(re-frame/register-handler
  :tool-3
  (fn [db _]
    (re-frame/dispatch [:re-calc-tick-value])
    (tool-update :tool-3 db)))

(re-frame/register-handler
  :tool-4
  (fn [db _]
    (re-frame/dispatch [:re-calc-tick-value])
    (tool-update :tool-4 db)))

(re-frame/register-handler
  :tool-5
  (fn [db _]
    (re-frame/dispatch [:re-calc-tick-value])
    (tool-update :tool-5 db)))

(re-frame/register-handler
  :tool-6
  (fn [db _]
    (re-frame/dispatch [:re-calc-tick-value])
    (tool-update :tool-6 db)))

(re-frame/register-handler
  :tool-7
  (fn [db _]
    (re-frame/dispatch [:re-calc-tick-value])
    (tool-update :tool-7 db)))

(re-frame/register-handler
  :tool-8
  (fn [db _]
    (re-frame/dispatch [:re-calc-tick-value])
    (tool-update :tool-8 db)))
