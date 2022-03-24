# SOFTTECH SPRING BOOTCAMP GRADUATION PROJECT

## Requirements

For building and running the application you need:

- [JDK 11.0.13](https://www.oracle.com/tr/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven 4](https://maven.apache.org)
- [PostgreSql 4](https://www.postgresql.org/download/)

## Running the application locally

First of all, dml and ddl scripts should be run after cloning the project.Afterwards, the relevant packages should be installed by `maven install`. After these procedures you can run application.

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `BitirmeprojesiIsmaildemircannApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Client App

I have developed a simple react project to show that the web service application is working correctly. I have shown the user and auth operations as well as the product listing function with simple designs.

For building and running the application you need:
[Node v16.10.0](https://nodejs.org/de/blog/release/v16.10.0/)

In order to run the client application, the packages related to the "npm install" command must be installed first.

In the project directory, you can run:

npm run start
Runs the app in the development mode.
Open http://localhost:3000 to view it in your browser.

## Structures Used In The Project
### Backend
* `H2` database was created for the integration test
* `Mockito` and `Junit` were used for testing.
* `Jwt` was used for security.
* Save, update and delete methods are saved to the file with `logback.xml`.
* With `triggers`, data is added to the relevant log table after updating and deleting operations.

### Frontend
* `Redux` structure installed. Tested using for variable product list .
* `Material-UI` library were used for desings.


# File Structure
```
* Backend
│   pom.xml
│  
├───resources 
│       application.properties # Change port and database settings
│  
└───src                                                     
    └───main  
        ├───java  
        │   └───com  
        
* Frontend
│   package.json
│  
└───src                                                     
    └───index.js    # You can change url
```
# Bitirme Projesi

Projenin Konusu:
Bir marketteki ürünlerin satış fiyatlarına göre son fiyatlarını belirleyen servisin Spring Boot Framework
kullanılarak yazılması ve isteğe bağlı olarak önyüzünün yazılması.

> **Gereksinimler:**

> **Backend:**

- Kullanıcıdan kullanıcı adı, şifre, isim, soy isim bilgilerini alarak sisteme kayıt yapılır.
- Sisteme kayıtlı kullanıcılar market ürünlerinin veri girişini yapabilir.
- Ürün türlerine göre KDV oranları değişiklik göstermektedir. Bu oranlar aşağıdaki tabloda
belirtilmiştir. __**Zaman zaman KDV oranları değişiklik gösterebilmektedir.**__

![Image](https://www.linkpicture.com/q/Untitled_395.png)


- Ürün için veri girişi yapacak kullanıcı; ürünün adı, ürünün türü ve vergisiz satış fiyatı alanlarını
doldurur. Her bir ürün için KDV Tutarı ve ürünün son fiyatı da hesaplanarak sisteme kaydedilir.
> **Kurallar ve gereksinimler:**
- Sisteme yeni kullanıcı tanımlanabilir, güncellenebilir ve silinebilir.
- Sisteme yeni ürünler tanımlanabilir, güncellenebilir ve silinebilir.
- Ürünlerin fiyatları güncellenebilir.
- KDV oranları değiştiğinde sistemdeki ürünlere de bu değişiklik yansıtılmalıdır. Herhangi bir hata
durumunda tüm işlemler geri alınmalıdır.
- Tüm ürünler listelenebilmelidir.
- Ürün türlerine göre ürünler listelenebilmelidir.
- Belirli bir fiyat aralığındaki ürünler listelenebilmelidir.
- Ürün türlerine göre aşağıdaki gibi detay veri içeren bir bilgilendirme alınabilmelidir.

![Image](https://www.linkpicture.com/q/22_57.png)

> Validasyonlar:
- Aynı kullanıcı adı ile kullanıcı tanımı yapılamaz.
- Kullanıcı girişi kullanıcı adı & şifre kombinasyonu ile yapılır.
- Ürün türü, fiyatı, adı gibi alanlar boş olamaz.
- Ürün fiyatı sıfır ya da negatif olamaz.
- KDV oranı negatif olamaz.
> Authentication:
- Güvenli endpointler kullanınız. (jwt, bearer vs. )
> Response:
- Başarılı ve başarısız responselar için modeller tanımlayın ve bunları kullanın.
> Dökümantasyon:
- Open API Specification (Swagger tercih sebebi)
> Exception Handling:
- Hatalı işlemler için doğru hata kodlarının dönüldüğünden emin olunuz.
> Test:
- Unit ve integration testleri yazınız. 
