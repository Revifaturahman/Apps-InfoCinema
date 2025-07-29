# 🎬 InfoCinema

**InfoCinema** adalah aplikasi Android berbasis **Kotlin** yang menampilkan informasi film terkini. Aplikasi ini dibuat dengan menggunakan arsitektur **MVVM (Model-View-ViewModel)** untuk menjaga struktur kode yang bersih dan mudah di-maintain.

---

## 📱 Fitur Utama

- Menampilkan daftar film populer  
- Menampilkan detail film (judul, sinopsis, rating, dll.)  
- Pencarian film berdasarkan judul  
- Tampilan antarmuka yang bersih dan responsif  
- Dukungan loading dengan animasi  
- Menggunakan arsitektur MVVM dengan ViewModel dan LiveData  

---

## 🛠️ Teknologi & Tools

- **Kotlin**  
- **MVVM Architecture**  
- **Retrofit** – untuk komunikasi dengan API  
- **Coroutines** – untuk asynchronous tasks  
- **LiveData** dan **ViewModel** – untuk data binding dan lifecycle-aware  
- **RecyclerView** – untuk menampilkan daftar film  
- **Glide / Coil** – untuk memuat gambar poster film  
- **Material Design** – untuk tampilan antarmuka  

---

## 🏗️ Struktur Proyek (MVVM)

```
com.revifaturahman.infocinema/
│
├── adapter/              # Adapter RecyclerView
├── data/
│   ├── model/            # Data class (Movie, MovieResponse, dll.)
│   └── network/          # API Retrofit Service
├── di/                   # Dependency Injection (misalnya Hilt)
├── repository/           # Repository untuk mengambil data
├── splashscreen/         # Splash screen saat aplikasi dibuka
├── view/                 # Activity / Fragment (View)
├── viewmodel/            # ViewModel
└── MyApp.kt              # Class utama aplikasi (extends Application)
```

---

## 🧪 Cara Menjalankan Proyek

1. **Clone repositori**  
   ```bash
   git clone https://github.com/username/InfoCinema.git
   cd InfoCinema
   ```

2. **Buka di Android Studio**  
   Gunakan Android Studio versi terbaru yang mendukung Kotlin dan AndroidX.

3. **Tambahkan API Key TMDB**  
   Tambahkan API Key ke dalam `local.properties` atau gunakan cara aman lainnya.  
   ```properties
   TMDB_API_KEY=your_api_key_here
   ```

4. **Build dan Jalankan**  
   Tekan tombol ▶️ untuk menjalankan aplikasi di emulator atau perangkat fisik Anda.

---

## 🤝 Kontribusi

Kontribusi sangat terbuka! Ikuti langkah-langkah berikut:

1. **Fork repositori**

2. **Buat branch fitur baru**  
   ```bash
   git checkout -b fitur-baru
   ```

3. **Commit perubahan**  
   ```bash
   git commit -am "Menambahkan fitur baru"
   ```

4. **Push ke branch**  
   ```bash
   git push origin fitur-baru
   ```

5. **Buat pull request**

---

## 📄 Lisensi

Proyek ini dilisensikan di bawah [MIT License](LICENSE).

---

## ✨ Penulis

- **Revi Faturahman** – [GitHub](https://github.com/Revifaturahman)

<!-- Tambahkan kontributor lain di sini jika ada -->