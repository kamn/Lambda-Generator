(ns lambda-generator.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [lambda-generator.handlers]
              [lambda-generator.subs]
              [lambda-generator.routes :as routes]
              [lambda-generator.views :as views]
              [lambda-generator.config :as config]))

(when config/debug?
  (println "dev mode"))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init [] 
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
