{
    let x = 2;
    let y = x + 2;
    {
        let z = 3;
        {
            let y = x + 1;
            y % x + z
        }
    }
};
