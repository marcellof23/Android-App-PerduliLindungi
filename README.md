# IF3210-2022-Android-35
# PerluDilindungi
## Anggota:
- Farhan Nur Hidayat Denira (13519071)
- Nizamixavier Rafif Lutvie (13519085)
- Marcello Faria            (13519086)

## Deskripsi
PerluLindungi merupakan aplikasi android yang menyediakan fitur-fitur untuk membantu melacak dan menghentikan penyebaran COVID-19 di Indonesia. Aplikasi dibuat menggunakan Android Studio dengan bahasa pemrograman Kotlin.
### Fitur dari aplikasi ini:
1. Menampilkan berita mengenai COVID-19. Aplikasi ini menyediakan ratusan berita yang dapat dibaca agar 
penggunanya lebih aware mengenai kondisi COVID-19 di Indonesia.
2. Menampilkan Daftar Faskes untuk Vaksinasi. Aplikasi ini menampilkan daftar faskes yang menyediakan fasilitas
vaksinasi. Pengguna dapat mencari faskes terdekat dan menyimpan faskes tersebut pada bookmark. 
3. Melakukan check-in. Untuk melacak penyebaran COVID-19, aplikasi ini memiliki fitur check-in, lokasi pengguna
akan dikirim ke server untuk disimpan ketika pengguna ingin memasuki suatu tempat seperti cafe atau restoran. 
Apabila pengguna telah melakukan vaksin dan tidak sedang terkena penyakit COVID-19, check-in akan berhasil.
Sebaliknya, apabila check-in gagal maka pengguna tidak diperbolehkan untuk memasuki tempat tersebut sehingga
tidak menyebarkan virus.

## Cara kerja
Ketika aplikasi dibuka, akan ada tiga halaman utama yang dapat diakses menggunakan navigation bar, yaitu halaman berita, lokasi faskes, dan bookmark faskes. 
1. Membaca berita
    - Untuk membaca berita, pengguna masuk ke halaman berita, lalu menekan salah satu berita dari list yang disediakan. Halaman berita akan ditampilkan dan pengguna dapat membaca berita yang diinginkan.
2. Melihat faskes untuk vaksinasi
    - Untuk melihat informasi faskes, pengguna masuk ke halaman faskes, lalu menekan salah satu faskes dari list yang disediakan. Halaman informasi detil mengenai faskes tersebut akan ditampilkan. Pengguna dapat menyimpan faskes yang dipilih ke dalam bookmark dengan cara menekan tombol "bookmark" pada halaman tersebut.
3. Mencari faskes terdekat
    - Untuk mencari faskes terdekat, pengguna masuk kehalaman faskes, lalu memilih provinsi dan kota pada menu dibagian atas. Setelah menekan tombol "search", maka akan ditampilkan faskes yang tersedia di kota tersebut.
4. Melihat list bookmark faskes
    - Apabila pengguna telah menyimpan bookmark, maka pengguna dapat melihat daftar faskes yang sudah disimpan pada halaman bookmark yang dapat diakses dari halaman utama, setelah itu pindah ke halaman bookmark menggunakan bottom navigation bar yang tersedia dibagian bawah aplikasi. Pengguna dapat memilih faskes dari halaman bookmark untuk melihat informasinya. Pengguna juga dapat melakukan unbookmark apabila ingin menghapus faskes tersebut dari list.
5. Melakukan check-in.
    - Pada setiap halaman terdapat tombol dengan logo QR code. Ketika ditekan, maka pengguna akan masuk ke halaman "check-in". Untuk melakukan check-in, pengguna dapat menekan tombol QR code pada halaman tersebut. Kamera dari handphone pengguna akan terbuka dan pengguna dapat melakukan scan pada QR code. Pastikan izin untuk mengakses kamera dan lokasi telah diberikan. Setelah QR code di-scan, maka akan ditampilkan hasilnya. Gambar ceklis berwarna hijau atau kuning akan muncul apabila check-in berhasil. Jika gagal, maka akan muncul gambar silang berwarna merah atau hitam serta alasan mengapa check-in gagal. 

## Library yang digunakan
- ZXing                 : QR code scanner
- Google Play services  : Akses lokasi pengguna
- Retrofit              : Melakukan akses Aplikasi
- Gson                  : Melakukan operasi pada file json

## Pembagian kerja
- 13519071: Melakukan check-in, readme.
- 13519085: Menampilkan berita COVID-19, menampilkan daftar faskes untuk vaksinasi.
- 13519086: Menampilkan detail informasi faskes, menampilkan daftar bookmark faskes.

