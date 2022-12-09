/* Comment BLock */

{
    /* Creates a new "class" Counter */
    let counter = {
        let DEFAULT : int = 0;
        let mut value : int = 0;
        struct {
            fn get() {
                val(value)
            };
            fn inc() {
                value -> val(value) + 1
            };
            fn set(x: int) {
                value -> x
            };
            fn reset() {
                value -> DEFAULT
            };
            fn times(n : int){
                for mut i : int = 0 to n - 1 {
                    value -> val(value) + 1
                }
            }
        }
    };

    /* Lamp class */
    let lamp = {
        let mut state : bool = false;
        struct {
            fn switch() { state -> !val(state) };
            fn set( newState : bool ) { state -> newState };
            fn get() { val(state) }
        }
    };

    /* Factorial Function */
    fn fat(n : int) {
       if n < 0 {
            0
       } else if n == 1 or n == 0 {
            1
       } else {
            /* n * fat( n-1 ) */
            n-1
       }
    };

    counter.times(3);
    println(counter.get());
    counter.inc();
    println(counter.get());
    counter.times(-2);
    println(counter.get())

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