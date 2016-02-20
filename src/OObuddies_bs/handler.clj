(ns OObuddies-bs.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as middleware]
            [clojure.pprint :refer [pprint]]))


(defn move
  [request]
  (pprint (:params request)) ;; for debugging
  {:status 200
   :body {:move "north"
          :taunt "battlesnake-clojure!"}})

(defn begin
  [request]
  (pprint (:params request)) ;; for debugging
  {:status 200
   :body {:name "battlesnake-clojure"
          :color "#ff00ff"
          :head_url "http://example.co"}})

(defn end
  [request]
  {:status 200
   :body {}})

(defn start
  [request]
  (pprint (:params request)) ;; for debugging
  {:status 200
   :body {:taunt "From hell's heart I stab at thee!"}})

(defroutes app-routes
  (GET "/" [] begin)
  (POST "/start" [] start)
  (POST "/move" [] move)
  (POST "/end" [] end)
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (middleware/wrap-json-body {:keywords? true})
      (middleware/wrap-json-params)
      (middleware/wrap-json-response)))
