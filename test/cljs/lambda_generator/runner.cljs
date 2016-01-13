(ns lambda-generator.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [lambda-generator.core-test]))

(doo-tests 'lambda-generator.core-test)
