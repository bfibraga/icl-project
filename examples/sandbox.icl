/*
    Sandbox Program
*/
{
    // Single comment line
    let value : int = 2;
    let f : func(int, int, int) -> bool = lambda(x : int, min : int, max : int ) -> bool {
        x >= min and x <= max
    };
    println(f(2, 3, 7))
},,