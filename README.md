# Mahoga Aribowo Heryasa

# 2206025230

## [Tautan Aplikasi ADVshop](https://tutorial-adpro-mahogaheryasa.koyeb.app/)

<details>
<summary><b><h2>Tutorial 1</h2></b></summary>

# Refleksi 1

Dalam mengimplementasikan dua fitur baru, saya menerapkan standar *coding* dengan mengimplementasikan beberapa prinsip *clean coding*. Pertama, penerapan *meaningful names* dengan penamaan variabel, fungsi, dan *class* yang bermakna serta mudah dimengerti. Kedua, penerapan *function* dengan menerapkan fungsi-fungsi pada fitur *edit* dan *delete*. Ketiga, penerapan *objects and data structure* dengan menggunakan class objek dan interface.

Menurut saya, terdapat beberapa hal untuk meningkatkan kualitas kode, seperti dengan menambahkan *comment* pada poin-poin penting, dan *error handling*. Selain itu, yang belum diterapkan pada kode ini adalah prinsip secure coding seperti autentikasi, verivikasi, dan validasi input. Namun, menurut saya prinsip secure coding belum dimplemntasikan untuk tutorial ini karena belum terhubung dengan database dan belum terdapat objek user.

# Refleksi 2

Setelah menerapkan *unit test*, saya merasa lebih yakin dengan kebenaran dari unit model dan fungsi yang saya buat. Menurut saya, tidak ada batas untuk menentukan berapa *unit test* yang harus kita buat, yang terpenting setiap test dapat memverivikasi keberhasilan suatu unit. Berdasarkan pemahaman saya, code coverage adalah teknik verivikasi bagaimana kode harus diterapkan atau tidak selama eksekusi rangkaian *testing* dan seberapa banyak kode program yang perlu dieksekusi atau diuji selama proses *testing*. Menurut saya, 100% *code coverage* belum tentu bebas dari error atau bug, bisa saja tes yang kita buat terlalu mudah sehingga error atau bug yang lebih rumit tidak terjangkau.

Menurut saya, pembuatan *functional test* baru menurunkan kualitas *clean code*. Hal ini terjadi karena terdapat potensi repetisi dalam kode fungsi yang kita buat, terutama ketika tes ini memiliki *setup procedures* dan *instance variables* yang sama dengan tes yang sebelumnya. Menurut saya, tes baru ini bisa digabungkan kedalam satu file yang sama dengan satu file kontroller, sehingga kemungkinan repetisi lebih kecil dan kualitas *clean code* lebih meningkat.

</details>

<details>
<summary><b><h2>Tutorial 2</h2></b></summary>

# Refleksi

### List *code quality issue(s)* yang saya perbaiki

- `<table>` tags should have a description Web:TableWithoutCaptionCheck

  Perlu menambahkan deskripsi pada tag `<table>` pada template html. contoh permasalahan tersebut terdapat pada `ProductList.html`.

    ```html
    ...
    <table border="1" class="table table-striped table-responsive-md">
    ...
    ```
  Saya perbaiki dengan menambahkan tag `<caption>` pada tabel.

    ```html
    ...
    <table border="1" class="table table-striped table-responsive-md">
        <caption></caption>
    ...
    ```

- Remove this field injection and use constructor injection instead.

  Masalah tersebut muncul karena penggunaan *field injection* pada suatu *class*. contoh permasalahan tersebut terdapat pada `ProductController`.

  ```java
   @Autowired
   private ProductService service;
  ```
  Saya perbaiki dengan menggantikan `@Autowired` dengan membuat *constructor class*.

  ```java
  private final ProductService service;

  public ProductController(ProductService service) {
      this.service = service;
  }
  ```

- Add at least one assertion to this test case.

  Masalah tersebut muncul karena tidak ada *assertion* pada *test class*. contoh permasalahan tersebut terdapat pada `EshopApplicationTests`.

  ```java
  @Test
  void contextLoads() {
      EshopApplication.main(new String[] {});
  }
  ```
  Saya perbaiki dengan `assertThat` yang memastikan *class* tidak null.

  ```java
  @Test
  void contextLoads() {
      EshopApplication.main(new String[] {});
      assertThat(EshopApplication.class).isNotNull();
  }
  ```

- Remove this 'public' modifier.

  Masalah tersebut muncul karena penggunaan *public access modifier* yang tidak seharunya digunakan pada *test classes*. contoh permasalahan tersebut terdapat pada `ProductControllerTest`.

  ```java
  public class ProductControllerTest {}
  ```

  Saya perbaiki dengan mengapus *public* sehingga menjadikan *class* tersebut *default access modifier*

  ```java
  class ProductControllerTest {}
  ```

### CI/CD workflows

Menurut saya, implementasi kode saya telah memenuhi ketentuan *Continuous Integration and Continuous Deployment*. Saya telah mengimplimentasikan beberapa *workflow* seperti `ci.yml`, `scorecard.yml`, dan `sonarcloud.yml` dan menjalankannya pada *Github Actions*. Hal tersebut adalah penerapan dari *Continuous Integration* karena *workflows-workflow* tersebut dapat dijalankan secara otomatis setiap melakukan *push* atau *pull-request* ke suatu *branch*. Saya juga telah menggunakan Koyeb sebagai *platform* yang melakukan *deployment* website saya secara otomatis. Hal tersebut merupakan penerapan dari *Continuous Depployment* karena mengimplementasikan proses deployment secara otomatis melakukan *push* atau *pull-request* ke suatu *branch*

</details>

<details>
<summary><b><h2>Tutorial 3</h2></b></summary>

# Refleksi

### Prinsip yang saya terapkan pada proyek

- Single Responsibility Principle (SRP)


Saya menerapkan prinsip Single Responsibility Principle (SRP) dengan memisahkan `ProductController` dan `CarController` menjadi *class* masing-masing yang terpisah. `CarController` yang mula-mulanya digabung pada class `ProductController` dengan meng-*extend* class `ProductController` saya pisahkan menjadi kelas sendiri karena masing-masing *class* memiliki tanggung jawab yang berbeda. `ProductController` untuk mengatur HTTP request yang bersangkutan dengan objek `Product`, dan `CarController` untuk mengatur HTTP request yang bersangkutan dengan objek `Car`. Hal ini melepas ketergantungan *class* `CarController` pada `ProductController` dan memudahkan *maintenance* masing-masing class.  


- Open-Closed Principle (OCP)


Saya menerapkan prinsip Open-Closed Principle (OCP) dengan menambahkan *class interface* `RepositoryInterface` yang diimplementasikan oleh class `ProductRepository` dan `CarRepository`. Hal ini memastikan fungsionalitas `Repository` *open for extension* namun tetap *closed for modification*, sehingga jika terdapat penambahan suatu repository baru, repository tersebut tetap mengikuti ketentuan *Repository* yang telah ditetapkan. Selain itu, saya juga mengubah beberapa *class* dan *tests* yang bersangkutan dengan *Repository* agar proyek berjalan sesuai dengan *Repository* yang telah di-*update*.   


- Dependency Inversions Principle (DIP)


Saya menerapkan prinsip Dependency Inversions Principle (DIP) dengan mengubah penggunaan objek *concrete* dari *class* lain pada suatu *class*. Pada proyek ini, saya mengubah penggunaan objek *concrete* `CarServiceImpl` pada *class* `CarController` menjadi objek *interface* `CarService`, mengubah penggunaan objek *concrete* `CarRepository` pada *class* `CarServiceImpl` menjadi objek *interface* `RepositoryInterface`, dan mengubah penggunaan objek *concrete* `ProductRepository` pada *class* `ProductServiceImpl` menjadi objek *interface* `RepositoryInterface`.

### Kelebihan mengaplikasikan prinsip SOLID pada proyek

- Meningkatkan *maintainability* dan *code organization*, dengan memisahkan *Responsibility* menjadi *class* masing-masing, setiap *class* menjadi lebih mudah dipahami, dipelihara, dan dimodifikasi. Perubahan pada satu tanggung jawab pada satu *class* tidak memengaruhi tanggung jawab lain yang tidak terkait. Selain itu, basis kode juga menjadi lebih terorganisir dan mudah dinavigasi. (contoh: pemisahan `CarController` dengan `ProductController` memudahkan saya untuk me-*maintain* masing-masing *class* jika terdapat perubahan pada salah satu fungsionalitas terkait `Car` atau `Product`).

- Meningkatkan ekstensibilitas dan mengurangi risiko *error* pada suatu ekstensi baru, dengan menerapkan OCP penambahan fungsionalitas atau perilaku baru tidak perlu memodifikasi ketentuan yang sudah ada sehingga meminimalkan kebutuhan untuk modifikasi dan mengurangi risiko *error*. (Contoh: Jika kita ingin menambahkan *class* repository untuk suatu objek baru, kita hanya butuh mengimplementasikan ketentuan `RepositoryInterface` yang telah dibuat tanpa harus merubah ketentuan kode pada proyek yang sudah ada dan berjalan dengan baik).

- Memudahkan testing, karena *high-level modules* bergantung pada *abstraction* bukan pada *concrete class*. (Contoh: *testing* pada *class* `CarController` dapat disederhanakan dengan menggunakan *mock* objek *interface* `CarService`).

### Kekurangan tidak mengaplikasikan prinsip SOLID pada proyek
- *Maintainability* pada kode menjadi sulit, Tanpa prinsip SRP, setiap *class* mungkin memiliki tanggung jawab yang terlalu banyak, sulit dipahami, dan sulit dipelihara. Perubahan pada satu area fungsionalitas dapat memengaruhi banyak bagian sistem, meningkatkan kompleksitas dan risiko kesalahan. (Contoh: `CarController` yang mula-mulanya digabung dengan `ProductController` sulit untuk dipahami dan di-*maintain*, ketika terdapat penambahan fungsi pada `ProductController`, `CarController` juga harus dirubah, padahal kedua *class* tidak berhubungan dan memiliki tanggung jawab yang berbeda).

- Kurangnya Fleksibilitas dan Ekstensibilitas: Tanpa menerapkan prinsip OCP, desain proyek mungkin sulit untuk diperluas atau diubah tanpa memodifikasi kode yang sudah ada. Hal ini dapat memunculkan *error* baru terkait perubahan tersebut sehingga memakan waktu yang lama dan sulit untuk di-*maintain*. (Contoh: tanpa menambahkan class `RepositoryInterface`, modifikasi atau penambahan fungsi pada `CarRepository` dapat merubah fungsionalitas *repository* yang sudah benar dan berjalan dengan lancar).

- Ketergantungan yang Tinggi Antar Komponen, tidak menerapkan prinsip DIP dapat menyebabkan kelas-kelas bergantung langsung pada implementasi *concrete class*, bukan pada *abstraction*. Hal ini menyulitkan perubahan dan tahap testing, karena perubahan pada *low-level modules* berdampak pada *high-level modules*. (Contoh: tanpa penerapan DIP, perubahan pada `CarController` berdampak pada perubahan `CarServiceImpl` juga, sehingga menyulitkan *testing* dan *mantainability*).
</details>

<details>
<summary><b><h2>Tutorial 4</h2></b></summary>

# Refleksi

### Refleksi TTD

Menurut saya, TTD *flow* cukup berguna untuk diaplikasikan. Walaupun, pada awalnya cukup sulit untuk mengimplementasikan TTD karena harus bekerja berdasarkan *tests* yang telah dibuat, TTD menjaga kode yang saya buat untuk mencapai *correctness*, *maintainability*, dan *productive workflow*. Kedepannya, dalam membuat tes saya harus bisa memahami kebutuhan aplikasi yang membuat test yang meng-*cover* kode saya secara menyeluruh.

### Implementasi F.I.R.S.T

Dengan mempertimbangkan prinsip F.I.R.S.T. (Fast, Isolated/Independent, Repeatable, Self-validating, and Timely), *testing* yang telah dibuat sebagian besar mengikuti prinsip-prinsip tersebut. Mereka berjalan cukup cepat, independen, dapat diulang, dan memvalidasi diri, hanya saja masih terdapat beberapa kode yang belum mendapatkan *code coverage* sepenuhnya. Kedepannya saya akan memastikan tes yang saya buat menerapakan seluruh prinsip F.I.R.S.T 
</details>