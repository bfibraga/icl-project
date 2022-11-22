{
    let const = 0;
    let mut name = 'L';
    let x = new([
        mut id: const,
        name: match const {
            0: 'I',
            1 | 4 | 6: 'C',
            2 | 12: 'L',
            _: 'P'
        },
        mut active: if val(name) != 'L' or val(name) = 'C' {
            true
        } else if val(name) = 'P' or val(name) = 'A' and val(name) = 'I' or val(name) = 'B' {
            false
        } else {
            const < 10
        }
    ]);
    if val(name) = x.name {
        x.id -> val(x.id) * 2
    } else if val(x.id) = 0 {
        x.active -> !val(x.active);
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
