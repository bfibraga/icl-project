/* Test - Functions */
{
    let result = new(0);
    let f : func(int,int)->int = lambda(x : int, y) {
        x + y
    };
    result -> f(1,2);
    println(val(result))
},,