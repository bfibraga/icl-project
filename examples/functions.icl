/* Test - Functions */
{
    println({
        fn f(x : int, y) {
            x + y
        };
        f(1,2)
    });
    println({
        fn f2(x : int, y : int) -> int {
            x * y
        };
        fn g2(x : int, y : int) -> int {
            x + y
        };
        fn comp2(f : func(int, int) -> int, g : func(int, int) -> int, x : int, y : int) -> int {
            g(f(x,y), f(y,x))
        };
        comp2(f2, g2, 2, 3)
    });
    println({
        let vec1 = struct {
            x : int = 0;
            y : int = 1
        };
        let vec2 = struct {
            x : int = 3;
            y : int = -1
        };
        fn distSqr(x1 : int, x2 : int, y1 : int, y2 : int) -> int {
            (x2-x1)^2 + (y2-y1)^2
        };
        distSqr(vec1.x, vec2.x, vec1.y, vec2.y)
    });
    println({
        fn f(n: int, b:int) -> int {
            {
                let mut x : int = n;
                let s : int = new(b);
                while val(x) > 0 {
                    s -> val(s) + val(x);
                    x -> val(x) - 1
                };
                val(s)
            }
        };
        f(10,0) + f(100,20)
    })
},,