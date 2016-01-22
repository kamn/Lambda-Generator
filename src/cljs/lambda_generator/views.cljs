(ns lambda-generator.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com]))


;; home

(defn home-lambda-generator []
  (fn []
    [re-com/box
      :child [re-com/title
                :label "λ"
                :class "main-lambda"
                :level :level1]]))

(defn home-lambda-generator2 []
  (fn []
    [re-com/button
      :label "λ"
      :class "main-lambda"
      :style {:outline "none"}
      :on-click #(re-frame/dispatch [:lambda-click])]))



(defn home-title []
  (let [lambda-count (re-frame/subscribe [:lambda-count])]
    (fn []
      [re-com/h-box
        :children [[home-lambda-generator2]
                   [re-com/box
                    :child [re-com/title
                              :label (str @lambda-count " λ")
                              :level :level1]]]])))

(defn link-to-about-page []
  [re-com/hyperlink-href
   :label "go to About Page"
   :href "#/about"])

(defn lambda-upgrade-button [s key data lambdas]
  (fn []
    [re-com/button
      :label (str (:cost @data) " λ")
      :disabled? (> (:cost @data) @lambdas)
      :class "btn btn-primary btn-lg"
      :on-click #(re-frame/dispatch [key])]))
    

(defn lambda-tools []
  (let [t1-data (re-frame/subscribe [:tool-1])
        lambdas (re-frame/subscribe [:lambda-count])]
    (fn []
      [re-com/h-box
        :children [
                    [re-com/box 
                      :size "1"
                      :justify :center
                      :child [re-com/v-box
                                :align :center
                                :children [[lambda-upgrade-button "λ Upgrade 1" :tool-1 t1-data lambdas]
                                           [lambda-upgrade-button "λ Upgrade 2" :tool-1 t1-data lambdas]
                                           [lambda-upgrade-button "λ Upgrade 3" :tool-1 t1-data lambdas]
                                           [lambda-upgrade-button "λ Upgrade 4" :tool-1 t1-data lambdas]
                                           [lambda-upgrade-button "λ Upgrade 5" :tool-1 t1-data lambdas]
                                           [lambda-upgrade-button "λ Upgrade 6" :tool-1 t1-data lambdas]
                                           [lambda-upgrade-button "λ Upgrade 7" :tool-1 t1-data lambdas]
                                           [lambda-upgrade-button "λ Upgrade 8" :tool-1 t1-data lambdas]]]]
                    [re-com/box 
                      :child "Box2"
                      :size "1"]]])))


(defn home-panel []
  [re-com/v-box
   :gap "1em"
   :children [[home-title] [lambda-tools]#_[link-to-about-page]]])


;; about

(defn about-title []
  [re-com/title
   :label "This is the About Page."
   :level :level1])

(defn link-to-home-page []
  [re-com/hyperlink-href
   :label "go to Home Page"
   :href "#/"])

(defn about-panel []
  [re-com/v-box
   :gap "1em"
   :children [[about-title] [link-to-home-page]]])


;; main

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :default [] [:div])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :children [(panels @active-panel)]])))
