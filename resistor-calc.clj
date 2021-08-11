(defn k [n]
    "x1,000"
    (* n 1e3))

(defn M [n]
    "x1,000,000"
    (* n 1e6))

(defn G [n]
    "x1,000,000,000"
    (* n 1e9))


(defn band-4 [band-1 band-2 band-3 multiplier] 
    "calculate resistance for 4 band resistor"
    (let 
        [bl 0
        br 1
        re 2
        or 3
        ye 4
        gr 5
        bl 6
        vi 7
        gr 8
        wh 9
        band-1 (* band-1 100)
        band-2 (* band-1 10)
        band-3 (* band-1 1)
        blm 1
        brm 10
        rem 100
        orm (k 1)
        yem (k 10)
        grm (k 100)
        blm (M 1)
        vim (M 10)
        gr (M 100)
        whm (G 1)
        gom 0.1
        sim 0.01]

        (* (+ band-1 band-2 band-3) multiplier)))