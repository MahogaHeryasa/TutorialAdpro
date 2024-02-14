# Mahoga Aribowo Heryasa

# 2206025230

## [Tautan Aplikasi ADVshop](https://tutorial-adpro-mahogaheryasa.koyeb.app/)

<details>
<summary><b><h2>Tutorial 1</h2></b></summary>

# Refleksi 1

Dalam mengimplementasikan dua fitur baru, saya menerapkan standar *coding* dengan mengimplementasikan beberapa prinsip *clean coding*. Pertama, penerapan *meaningful names* dengan penamaan variabel, fungsi, dan *class* yang bermakna serta mudah dimengerti. Kedua, penerapan *function* dengan menerapkan fungsi-fungsi pada fitur *edit* dan *delete*. Ketiga, penerapan *objects and data structure* dengan menggunakan class objek dan interface.

Menurut saya, terdapat beberapa hal untuk meningkatkan kualitas kode, seperti dengan menambahkan *comment* pada poin-poin penting, dan *error handling*. Selain itu, yang belum diterapkan pada kode ini adalah prinsip secure coding seperti autentikasi, verivikasi, dan validasi input. Namun, menurut saya prinsip secure coding belum dimplemntasikan untuk tutorial ini karena belum terhubung dengan database dan belum terdapat objek user.

# Refleksi 2

Setelah menerapkan *unit test*, saya merasa lebih yakin dengan kebenaran dari unit model dan fungsi yang saya buat. Menurut saya, tidak ada batas untuk menentukan berapa *unit test* yang harus kita buat, yang terpenting setiap test dapat memverivikasi keberhasilan suatu unit. Berdasarkan pemahaman saya, code coverage adalah teknik verivikasi bagaimana kode harus diterapkan atau tidak selama eksekusi rangkaian *testing* dan seberapa banyak kode program yang perlu dieksekusi atau diuji selama proses *testing*. Menurut saya, 100% *code coverage* belum tentu bebas dari erorr atau bug, bisa saja tes yang kita buat terlalu mudah sehingga error atau bug yang lebih rumit tidak terjangkau.

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