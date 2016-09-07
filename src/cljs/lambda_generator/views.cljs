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
      [re-com/v-box
        :justify :center
        :align :center
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
                                :children [[lambda-upgrade "Basic Functions" :tool-1 t1-data lambdas]
                                           [lambda-upgrade "Pure Functions" :tool-2 t2-data lambdas]
                                           [lambda-upgrade "First-Class Functions" :tool-3 t3-data lambdas]
                                           [lambda-upgrade "Higher-Order Functions" :tool-4 t4-data lambdas]
                                           [lambda-upgrade "Closures" :tool-5 t5-data lambdas]
                                           [lambda-upgrade "Immutability" :tool-6 t6-data lambdas]
                                           [lambda-upgrade "Macros" :tool-7 t7-data lambdas]
                                           [lambda-upgrade "Lisp2" :tool-8 t8-data lambdas]]]]]])))



;; main

(defmulti panels identity)
(defmethod panels :default [] [:div])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :children [(panels @active-panel)]])))
