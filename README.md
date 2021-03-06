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
* Save, update and delete methods are saved to the file with `logback.xml` and `reflection`.
* With `triggers`, data is added to the relevant log table after updating and deleting operations.
* After adding the product, a deletion link was given using `Hateoas`.
* Generic base model implemented in for services and entities.
* General exception handling structing was created.

> **Test**
 * Unit
 * Integration 
 
 `87% classes, 70% covered in entire project.`

> **Runing Tests**
* Before running tests, make sure the resource package is shown as test resources under test resources in the project structure.

```
          ????????????src
                ?????????main
                ?????????test
                     ?????????resources
  
```

### Frontend
* `Redux` structure installed. Tested using for variable product list .
* `Material-UI` library were used for desings.
* `Axios` library were using for requests.


# File Structure
```
* Backend
???   pom.xml
???  
????????????resources 
???       application.properties # Change port and database settings
???  
????????????src                                                     
    ????????????main  
        ????????????java  
        ???   ????????????com  
        
* Frontend
???   package.json
???  
????????????src                                                     
    ????????????index.js    # You can change url
```

## Api
### To make a request in Swagger, do not forget to register and then log in and be authenticed with the incoming token.
![Image](https://github.com/165-Softtech-Patika-Java-Spring/bitirmeprojesi-ismaildemircann/blob/main/README%20Resources/Swagger_Login.png?raw=true)

### You can test the api from Swagger.
![Image](https://github.com/165-Softtech-Patika-Java-Spring/bitirmeprojesi-ismaildemircann/blob/main/README%20Resources/swagger.png?raw=true)


## Demo
![Image](https://github.com/165-Softtech-Patika-Java-Spring/bitirmeprojesi-ismaildemircann/blob/main/README%20Resources/reactGif.gif?raw=true)

# Bitirme Projesi

Projenin Konusu:
Bir marketteki ??r??nlerin sat???? fiyatlar??na g??re son fiyatlar??n?? belirleyen servisin Spring Boot Framework
kullan??larak yaz??lmas?? ve iste??e ba??l?? olarak ??ny??z??n??n yaz??lmas??.

> **Gereksinimler:**

> **Backend:**

- Kullan??c??dan kullan??c?? ad??, ??ifre, isim, soy isim bilgilerini alarak sisteme kay??t yap??l??r.
- Sisteme kay??tl?? kullan??c??lar market ??r??nlerinin veri giri??ini yapabilir.
- ??r??n t??rlerine g??re KDV oranlar?? de??i??iklik g??stermektedir. Bu oranlar a??a????daki tabloda
belirtilmi??tir. __**Zaman zaman KDV oranlar?? de??i??iklik g??sterebilmektedir.**__

![Image](https://www.linkpicture.com/q/Untitled_395.png)


- ??r??n i??in veri giri??i yapacak kullan??c??; ??r??n??n ad??, ??r??n??n t??r?? ve vergisiz sat???? fiyat?? alanlar??n??
doldurur. Her bir ??r??n i??in KDV Tutar?? ve ??r??n??n son fiyat?? da hesaplanarak sisteme kaydedilir.
> **Kurallar ve gereksinimler:**
- Sisteme yeni kullan??c?? tan??mlanabilir, g??ncellenebilir ve silinebilir.
- Sisteme yeni ??r??nler tan??mlanabilir, g??ncellenebilir ve silinebilir.
- ??r??nlerin fiyatlar?? g??ncellenebilir.
- KDV oranlar?? de??i??ti??inde sistemdeki ??r??nlere de bu de??i??iklik yans??t??lmal??d??r. Herhangi bir hata
durumunda t??m i??lemler geri al??nmal??d??r.
- T??m ??r??nler listelenebilmelidir.
- ??r??n t??rlerine g??re ??r??nler listelenebilmelidir.
- Belirli bir fiyat aral??????ndaki ??r??nler listelenebilmelidir.
- ??r??n t??rlerine g??re a??a????daki gibi detay veri i??eren bir bilgilendirme al??nabilmelidir.

![Image](https://www.linkpicture.com/q/22_57.png)

> Validasyonlar:
- Ayn?? kullan??c?? ad?? ile kullan??c?? tan??m?? yap??lamaz.
- Kullan??c?? giri??i kullan??c?? ad?? & ??ifre kombinasyonu ile yap??l??r.
- ??r??n t??r??, fiyat??, ad?? gibi alanlar bo?? olamaz.
- ??r??n fiyat?? s??f??r ya da negatif olamaz.
- KDV oran?? negatif olamaz.
> Authentication:
- G??venli endpointler kullan??n??z. (jwt, bearer vs. )
> Response:
- Ba??ar??l?? ve ba??ar??s??z responselar i??in modeller tan??mlay??n ve bunlar?? kullan??n.
> D??k??mantasyon:
- Open API Specification (Swagger tercih sebebi)
> Exception Handling:
- Hatal?? i??lemler i??in do??ru hata kodlar??n??n d??n??ld??????nden emin olunuz.
> Test:
- Unit ve integration testleri yaz??n??z. 
