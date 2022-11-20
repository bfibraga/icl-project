{
    let const = 6;
    let mut name = 'L';
    let x = new([
        mut id: const,
        name: match const {
            0: 'I',
            1 | 4 | 6: 'C',
            2 | 7: 'L',
            _: 'P'
        }
    ]);
    if val(name) = x.name {
        x.id -> val(x.id) * 2
    } else if val(x.id) = 0 {
        {
            let mut c = 0;
            while val(c) < const {
                x.id -> val(x.id) + 3;
                c -> val(c) + 1
            };
            val(x.id)
        }
    } else {
        x.id -> val(x.id) * 4
    };
    val(x)
},,
