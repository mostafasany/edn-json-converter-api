(ns converters
  (:require [cheshire.core :as json]))



(defn to-json [edn]
  (json/generate-string edn))


(defn to-edn [json]
  (json/parse-string json true))