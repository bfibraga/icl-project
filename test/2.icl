{
    let x = 2;
    let y = {
        let x = x + 1;
        x % 2
    };
    {
        let z = 5;
        let k = 9 + y;
        {
            let w = {
                let x = 4;
                let y = 3;
                15 % -( x * y )
            };
            let p = 6;
            x + y + z + k + w + p
        }
    } + x
};
