(ns lambda-generator.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
 :name
 (fn [db]
   (reaction (:name @db))))

(re-frame/register-sub
 :lambda-count
 (fn [db]
   (reaction (:lambda-count @db))))

(re-frame/register-sub
 :active-panel
 (fn [db _]
   (reaction (:active-panel @db))))

(re-frame/register-sub
 :tool-1
 (fn [db _]
   (reaction (:tool-1 @db))))

(re-frame/register-sub
 :tool-2
 (fn [db _]
   (reaction (:tool-2 @db))))

(re-frame/register-sub
 :tool-3
 (fn [db _]
   (reaction (:tool-3 @db))))

(re-frame/register-sub
 :tool-4
 (fn [db _]
   (reaction (:tool-4 @db))))

(re-frame/register-sub
 :tool-5
 (fn [db _]
   (reaction (:tool-5 @db))))

(re-frame/register-sub
 :tool-6
 (fn [db _]
   (reaction (:tool-6 @db))))

(re-frame/register-sub
 :tool-7
 (fn [db _]
   (reaction (:tool-7 @db))))

(re-frame/register-sub
 :tool-8
 (fn [db _]
   (reaction (:tool-8 @db))))
