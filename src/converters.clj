(ns converters
  (:require [cheshire.core :as json]
            [clojure.edn :as edn]))


(defn to-json [edn]
 (-> (edn/read-string edn)
     (json/generate-string))
  )

(defn to-edn [json]
  (json/parse-string json true))