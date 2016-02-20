(ns OObuddies-bs.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.keyword-params :as keyword-params]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as middleware]
            [clojure.pprint :refer [pprint]]))


(defn move
  [request]
  ;(pprint (:params request)) ;; for debugging
  (pprint "snake?")
                                        ;  (pprint (:params (get request :snakes "nope")))
  (type request)
  (pprint "end")
  {:status 200
   :body {:move "north"
          :taunt "gayyyyymeboyz lmao"}})

(defn begin
  [request]
  (pprint (:params request)) ;; for debugging
  {:status 200
   :body {:name "battlesnake-clojure"
          :color "#ff00ff"
          :head_url "http://oobuddies-bs.herokuapp.com/resources/blakerino.png"}})

(defn end
  [request]
  {:status 200
   :body {}})

(defn start
  [request]
  (pprint (:params request)) ;; for debugging
  {:status 200
   :body {:taunt "Gameboyyyyyyzzzzz"}})

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
