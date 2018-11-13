(ns edn-json-converter-api.main

  "Responsible for starting application from command line"
  (:gen-class)
  (:require [taoensso.timbre :as log]
            [clojure.java.io :as io]
            [omniconf.core :as cfg]
            [ring.adapter.jetty :refer [run-jetty]]

            [edn-json-converter-api.handler :refer [app]]))

(cfg/define {:port {:description "HTTP port"
                    :type :number
                    :default 8081}})

(defn -main [& args]
  (cfg/populate-from-env)
  (cfg/verify :quit-on-error true)
  (log/info "Starting server")
  (run-jetty app {:port (cfg/get :port) :join? false}))
