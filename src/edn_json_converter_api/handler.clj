(ns edn-json-converter-api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as r]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :refer [wrap-json-response]]
            [converters :as converters]
            [jumblerg.middleware.cors :refer [wrap-cors]]
            [cheshire.core :as json]
            [clj-http.client :as http]))


(def ednObject {:description nil,
                :name "spotify-outfit",
                :parameters [],
                :variants [{:layout {:children [{:component_id "d45s6",
                                                 :options {:multicolumn ""},
                                                 :type "zalando-list",
                                                 :position 1,
                                                 :children [{:component_id "yxF8ULoJy",
                                                             :options {:image_url "/images/random-image-2.png"},
                                                             :type "zalando-image"}]}
                                                {:component_id "b26a4",
                                                 :options {:text "{{ DYNAMIC BUTTON TEXT }}"},
                                                 :type "zalando-button",
                                                 :position 2}],
                                     :component_id "layout",
                                     :type "zalando-layout"},
                            :binding {:links [{:inlet {:name "text", :node_id "b26a4"},
                                               :outlet {:name "message", :node_id "5m1W5B1Ya"}}
                                              {:inlet {:name "event - (zalando://WISHLIST)", :node_id "7KmICr_cS"},
                                               :outlet {:name "tap", :node_id "b26a4"}}
                                              {:inlet {:name "item", :node_id "G0zhL72Xr"},
                                               :outlet {:name "playlists", :node_id "5m1W5B1Ya"}}
                                              {:inlet {:name "items", :node_id "lmBpqOGpk"},
                                               :outlet {:name "items", :node_id "G0zhL72Xr"}}
                                              {:inlet {:name "items", :node_id "Q-evMd3Zq"},
                                               :outlet {:name "images", :node_id "lmBpqOGpk"}}
                                              {:inlet {:name "image_url", :node_id "d45s6"},
                                               :outlet {:name "url", :node_id "Q-evMd3Zq"}}],
                                      :nodes [{:kind "component", :name "zalando-button", :node_id "b26a4"}
                                              {:kind "data-source", :name "sp_featured_playlists", :node_id "5m1W5B1Ya"}
                                              {:kind "function", :name "iterator", :node_id "lmBpqOGpk"}
                                              {:kind "event",
                                               :meta {:url "zalando://WISHLIST"},
                                               :name "deeplink",
                                               :node_id "7KmICr_cS"}
                                              {:kind "function", :name "object", :node_id "G0zhL72Xr"}
                                              {:kind "component",
                                               :meta {:children [{:type "zalando-image",
                                                                  :inlets ["image_url"],
                                                                  :outlets [],
                                                                  :component_id "yxF8ULoJy"}]},
                                               :name "zalando-list",
                                               :node_id "d45s6"}
                                              {:kind "function", :name "first", :node_id "Q-evMd3Zq"}]},
                            :variant_id "main"}],
                :screen_id "spotify-outfit"})

(def jsonObject (json/generate-string ednObject))

(defroutes app-routes

           (GET "/" []
             (str "Service Started ......... Post or get /toJson or /toEdn"))
  (GET "/toJson" []
    (converters/to-json ednObject))

  (GET "/toEdn" []
    (-> (r/response (converters/to-edn jsonObject))
        (r/header "Content-Type" "text/html"))
      )

  (POST "/toJson" [edn]
    (converters/to-json edn))

   (POST "/toEdn" [json]
     (-> (r/response (converters/to-edn json))
         (r/header "Content-Type" "text/html"))
     )

  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
      (wrap-cors routes #".*")
      (wrap-json-response)))

(comment

  (http/post "http://localhost:3000/toJson" {:body ednObject})

  )
