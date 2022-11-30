{
    let mut c = {
        let mut value = 0;
        {
            inc = fn() { value -> val(value) + 1 };
            get = fn() { val(value) };
            set = fn(x : int) { value -> x };
            reset = fn() { value -> 0 };
            isEven = fn() { val(value) % 2 = 0 }
        }
    };
    let counter = val(c);
    let counter2 = val(c);
    counter.inc();
    counter.inc();
    counter.reset();
    counter.inc();
    println(counter.get());
    println(counter.isEven());
    counter.set(10);
    println(counter.get());
    println(counter.isEven());
    println(counter2.get())
},,