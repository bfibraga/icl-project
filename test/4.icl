{
    let mut x = 10;
    let mut y = 5;
    if val(x) < 0 {
        val(y)
    } else if val(x) > 5 {
        {
            let mut c = 0;
            while val(c) < val(x){
                y -> val(y) + val(c)
            };
            val(y)
        }
    } else {
        val(y)^3
    } > 10
},,