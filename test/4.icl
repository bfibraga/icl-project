{
    let MAX : int = 10;
    let mut x : int = MAX;
    let y : str = new('Hello world');
    let mut w = arr[MAX, x, y];
    let a = fn(x : int){
        x^2
    };
    let b = fn(x : int){
    {
        let y = x + 3;
        y
    }};
    let comp = fn(f : func, g : func, x : int) {
        f(g(x))
    };
    comp(a, b, 2)
},,