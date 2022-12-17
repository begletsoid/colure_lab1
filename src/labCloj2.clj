(ns labCloj2
  (:require [clojure.core.async :refer [chan <! >! <!! go]]))

(defn distribute-tasks [tasks-channel agents-pool]
  (let [output-channel (chan)]
    (doseq [agent agents-pool]
      (send agent (go(while true
                     (let [task (<! tasks-channel)]
                        (>! output-channel (inc task)))))))
  output-channel))

(def tasks-channel (chan))
(def agents-pool (map agent (range 5)))
(go
  (dotimes [i 5]
    (>! tasks-channel i)))
(def output-channel (distribute-tasks tasks-channel agents-pool))

(go
  (while true
    (let [result (<! output-channel)]
      (println result))))