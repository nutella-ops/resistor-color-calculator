;; scientific abbreviations
(defn k [n]
   "input x1,000"
    (* n 1e3))

(defn M [n]
   "input x1,000,000"
    (* n 1e6))

(defn G [n]
   "input x1,000,000,000"
    (* n 1e9))

;; data structures representing resistor color bands
;; python has cartesian product function for interleaving to build color/int data struct
(def color-band 
   "dictionary of color-integer encodings"
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
   "dictionary of color-multipliter encodings"
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

(def tolerance-band
    "dictionary of color-tolerance encoding"
    {'brot 0.01
    'redt 0.02
    'gret 0.005
    'blut 0.0025
    'viot 0.001
    'grat 0.0005
    'golt 0.05
    'silt 0.1})

;; resistor band decoding functions
(defn four-band [[band-1 band-2 multiplier tolerance]]
    "translate colors of a 4-band into numbers. colors are all first 3 letters; suffixed with m or t for multiplier or tolerance."
      (let [band-1 (* (color-band band-1) 10) 
            band-2 (* (color-band band-2) 1)
            resistor (* (reduce + [band-1 band-2]) (multiplier-band multiplier))
            neg-tol (* (tolerance-band tolerance) -1)
            pos-tol (* (tolerance-band tolerance) 1)
            neg-ohm (+ resistor (* neg-tol resistor))
            pos-ohm (+ resistor (* pos-tol resistor))
            percent-display (* pos-tol 100)]
        [resistor [neg-ohm pos-ohm] percent-display]))

(defn five-band [[band-1 band-2 band-3 multiplier tolerance]]
    "translate colors of a 5-band into numbers. colors are all first 3 letters; suffixed with m or t for multiplier or tolerance."
      (let [band-1 (* (color-band band-1) 100) 
            band-2 (* (color-band band-2) 10)
            band-3 (* (color-band band-3) 1)
            resistor (* (reduce + [band-1 band-2 band-3]) (multiplier-band multiplier))
            neg-tol (* (tolerance-band tolerance) -1)
            pos-tol (* (tolerance-band tolerance) 1)
            neg-ohm (+ resistor (* neg-tol resistor))
            pos-ohm (+ resistor (* pos-tol resistor))
            percent-display (* pos-tol 100)]
        [resistor [neg-ohm pos-ohm] percent-display]))

(defn spread [[R tol]]
    "takes a vector [resistance tolerance] and returns a lazy sequence (Rmin Rmax)"
    (let 
        [tol-float (/ tol 100.0)
        delta (* R tol-float)
        min-val (+ R (- delta))
        max-val (+ R delta)]
    (map float [min-val max-val])))





;;;;;;;;;;;;;;;
;;;; NOTES ;;;;
;;;;;;;;;;;;;;;

;; template for using resistor min-max as input to practical equations like V = IR
;; 3.3 = Vcc and 1e3 = mV
;; (map #(* 1e3 %) (map #(/ 3.3 %) (spread [330 5])))


;; later create (four-band-number-to-color) && (five-band-number-to-color)
;; six-band = five-band + ppm

;; input overloading enabled B)
;; user=> (pprint (map five-band 
;;                     [['bro 'red 'bla 'brom 'brot] 
;;                     ['bro 'bla 'bla 'blam 'brot] 
;;                     ['bro 'bla 'bla 'yelm 'brot]]))

;; ([1200 [1188.0 1212.0] 1.0]
;;  [100 [99.0 101.0] 1.0]
;;  [1000000.0 [990000.0 1010000.0] 1.0])

;; make it int output and ideally add numeric commas
    ;; user=> (four-color-resistor 'yel 'vio 'redm 'silt)
    ;; [4700 [4230.0 5170.0] 10.0]

    ;; user=> (four-color-resistor 'yel 'vio 'viom 'silt)
    ;; [4.7E8 [4.23E8 5.17E8] 10.0]


;; REFERENCE VALUES
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


;; tolerance-bands
;;     Brown ±1%
;;     Red ±2%
;;     Green ±0.5%
;;     Blue ±0.25%
;;     Violet ±0.1%
;;     Grey ±0.05%
;;     Gold ±5%
;;     Silver ±10%


;; ppm-bands
;;     brown 100ppm
;;     red 50ppm
;;     orange 15ppm
;;     yellow 25ppm
;;     blue 10ppm
;;     violet 5ppm
