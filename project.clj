(defproject edn-json-converter-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [compojure "1.6.1"]
                 [com.taoensso/timbre "4.10.0"]
                 [com.grammarly/omniconf "0.3.2"]
                 [ring "1.7.0"]
                 [ring-server "0.5.0"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]
                 [jumblerg/ring-cors "2.0.0"]
                 [clj-http "3.9.1"]]
  :plugins [[lein-ring "0.12.4"]]
  :ring {:handler edn-json-converter-api.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}

   :uberjar {:aot :all
             :omit-source true
             :main edn-json-converter-api.main
             :uberjar-name "edn-json-converter-api.jar"}})
