/* Comment BLock */

{
    /* Creates a new "class" Counter */
    let counter = {
        let DEFAULT : int = 0;
        let mut value : int = 0;
        struct {
            fn get() { val(value) };
            fn inc() { value -> val(value) + 1 };
            fn set(x: int) { value -> x };
            fn reset() { value -> DEFAULT }
        };
    };

    /* Factorial Function */
    fn fat(n : int) {
       if n < 0 {
            0
       } else if n = 1 or n = 0 {
            1
       } else {
            {
                let mut result = 1;
                while counter.get() < n{
                    result -> val(result) * (n - counter.get());
                    counter.inc()
                };
                counter.reset();
                val(result)
            }
       }
    };
    let a : array = [1, true, false];
    print(fat(3), #a)
/*
    let rand = {
        let mut seed : int = 2;
        {
            setSeed = fn(n : int){
                seed -> n
            };
            gen = fn(){
                seed -> (25173 * val(seed) + 13844 % 65536);
                val(seed)
            }
        }
    }*/
},,