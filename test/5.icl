{
    let fun : rec = [
        f: fn(x : int){
            x*2
        },
        g: fn(x : int){
            x^3
        },
        mut id: 2
    ];
    fun.id -> 3;
    fun.f(fun.g(val(fun.id)))
},,