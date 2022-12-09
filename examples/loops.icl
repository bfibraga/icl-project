/* Test 1 - Loops */
{
    let mut x : int = 0;
    let MAX : int = 5;
    let result : bool = {
        let mut result : int = 0;
        while val(x) <= MAX {
            result -> val(result) + 1;
            while val(result) < MAX*2 {
                result -> val(result) + 3
            };
            x -> val(x) + 1
        };
        while val(x) >= 0 {
            result -> val(result) * 2;
            x -> val(x) - 1
        };
        val(result) <= MAX - 2 and val(result) >= 0
    };
    let mut boolean : bool = result;
    while !val(boolean) {
        boolean -> val(x) % 2 == 0;
        x -> val(x) + 1
    };
    println(result)
},,