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
  (let [lambda-count (re-frame/subscribe [:lambda-count])
        lambda-per-sec (re-frame/subscribe [:lambda-per-sec])]
    (fn []
      [re-com/h-box
        :children [[home-lambda-generator2]
                   [re-com/v-box
                    :children [
                                [re-com/title
                                  :label (str @lambda-count " λ")
                                  :level :level1]
                                [re-com/title
                                  :label (str @lambda-per-sec " λ per second")
                                  :level :level3]]]]])))


(defn lambda-upgrade-button [s key data lambdas]
  (fn []
    [re-com/button
      :label (str (:cost @data) " λ")
      :disabled? (> (:cost @data) @lambdas)
      :class "btn btn-primary btn-lg"
      :on-click #(re-frame/dispatch [key])]))
    

(defn lambda-upgrade [s key data lambdas]
  (fn []
    [re-com/h-box
      :align :center
      :gap "5px"
      :children [
                  [re-com/title :label s :level :level2]
                  [lambda-upgrade-button s key data lambdas]]]))


(defn lambda-tools []
  (let [t1-data (re-frame/subscribe [:tool-1])
        t2-data (re-frame/subscribe [:tool-2])
        t3-data (re-frame/subscribe [:tool-3])
        t4-data (re-frame/subscribe [:tool-4])
        t5-data (re-frame/subscribe [:tool-5])
        t6-data (re-frame/subscribe [:tool-6])
        t7-data (re-frame/subscribe [:tool-7])
        t8-data (re-frame/subscribe [:tool-8])
        lambdas (re-frame/subscribe [:lambda-count])]
    (fn []
      [re-com/h-box
        :children [
                    ;;[:p.absolute "λ"]
                    [re-com/box  
                      :size "1"
                      :justify :center
                      :child [re-com/v-box
                                :align :start
                                :gap "5px"
                                :children [[lambda-upgrade "First-Class Functions" :tool-1 t1-data lambdas]
                                           [lambda-upgrade "Curried Functions" :tool-2 t2-data lambdas]
                                           [lambda-upgrade "Pure Functions" :tool-3 t3-data lambdas]
                                           [lambda-upgrade "λ Upgrade 4" :tool-4 t4-data lambdas]
                                           [lambda-upgrade "λ Upgrade 5" :tool-5 t5-data lambdas]
                                           [lambda-upgrade "λ Upgrade 6" :tool-6 t6-data lambdas]
                                           [lambda-upgrade "λ Upgrade 7" :tool-7 t7-data lambdas]
                                           [lambda-upgrade "λ Upgrade 8" :tool-8 t8-data lambdas]]]]
                    [re-com/box 
                      :child "Box2"
                      :size "1"]]])))


(defn home-panel []
  [re-com/v-box
   :gap "1em"
   :children [[home-title] [lambda-tools] #_[link-to-about-page]]])


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
