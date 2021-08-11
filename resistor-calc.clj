(defn k [n]
   "x1,000"
    (* n 1e3))

(defn M [n]
   "x1,000,000"
    (* n 1e6))

(defn G [n]
   "x1,000,000,000"
    (* n 1e9))

(def color-band 
   "dictionary of color-integer encoding"
    {'bla 0
    'bro 1
    'red 2
    'ora 3
    'yel 4
    'gre 5
    'blu 6
    'vio 7
    'gra 8
    'whi 9})

(def multiplier-band
   "dictionary of color-multipliter encoding"
    {'blam 1
    'brom 10
    'redm 100
    'oram (k 1)
    'yelm (k 10)
    'grem (k 100)
    'blum (M 1)
    'viom (M 10)
    'gram (M 100)
    'whim (G 1)
    'golm 0.1
    'silm 0.01})

(defn four-color-resistor [band-1 band-2 multiplier]
    "calculate resistance of a 4-band resistor. colors = first 3 letters, multiplier = colors suffixed with m"
    (* (reduce + (map color-band [band-1 band-2])) (multiplier-band multiplier)))

;; color-bands
;;    0 black
;;    1 brown
;;    2 red
;;    3 orange
;;    4 yellow
;;    5 green
;;    6 blue
;;    7 violet
;;    8 grey
;;    9 white
        
;; multiplier-bands
;;    black 1 Ω
;;    brown 10 kΩ
;;    red 100 Ω
;;    orange 1 kΩ
;;    yellow 10 kΩ
;;    green 100 kΩ
;;    blue 1 MΩ
;;    violet 10 MΩ
;;    grey 100 MΩ
;;    white 1 GΩ
;;    gold 0.1 Ω
;;    silver 0.01 Ω


;; tolerances
;;     Brown ±1%
;;     Red ±2%
;;     Green ±0.5%
;;     Blue ±0.25%
;;     Violet ±0.1%
;;     Grey ±0.05%
;;     Gold ±5%
;;     Silver ±10%


;; ppm
;;     brown 100ppm
;;     red 50ppm
;;     orange 15ppm
;;     yellow 25ppm
;;     blue 10ppm
;;     violet 5ppm