a = int(input("Masukkan nilai a: "))
b = int(input("Masukkan nilai b: "))

count = 0
max = -999

for i in range(a, b+1):
    if i > 1:
        for j in range(2, i):
            if (i % j) == 0:
                break
        else:
            count += 1
            if i > max:
                max = i
        
if count == 0:
    print(f'Tidak ditemukan bilangan prima pada selang [{a}, {b}].')
else:
    print(f'Banyaknya bilangan prima pada selang [{a}, {b}] adalah {count}. Bilangan prima terbesar di selang tersebut adalah {max}.')