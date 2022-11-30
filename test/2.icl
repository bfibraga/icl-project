/* Comment BLock */

{
    /* Creates a new "class" Counter */
    let counter = {
        let DEFAULT : int = 0;
        let mut value : int = 0;
        {
            get = fn () { val(value) };
            inc = fn () { value -> val(value) + 1 };
            set = fn (x: int) {value -> x};
            reset = fn () { value -> DEFAULT }
        }
    };

    /* Factorial Function */
    let fat = fn(n : int) {
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
    };
},,