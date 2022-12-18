/*
    Sandbox Program
*/
fn main() {
    // Single comment line
    let mut a : ref = 2;
    let b : ref = a;
    let mut y : ref = true;

    a -> {
        if val(a) > 0 {
            0
        } else if val(a) < -1{
            1
        } else {
            3
        }
    };
    println(val(y), val(a));
    println(val(b))
},,