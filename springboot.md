Segregacja
menadzowalność entity (https://stackoverflow.com/questions/24788484/why-do-changes-to-my-jpa-entity-not-get-persisted-to-the-database)

Czemu używamy PHP - bo był w [dobrym miejscu i w dobrym czasie](https://softwareengineering.stackexchange.com/questions/110797/why-is-php-so-frequently-used-on-web-servers) i powstał stack LAMP.

Czym jest Bean - [To](https://pl.wikipedia.org/wiki/Enterprise_JavaBeans), lub [to](https://pl.wikipedia.org/wiki/JavaBeans) [TO?Spring](https://www.baeldung.com/spring-bean)

Circular ref (https://stackoverflow.com/questions/33342827/why-does-jacksons-propertygenerator-prevent-recursive-loop)

https://stackoverflow.com/questions/57284058/what-is-the-best-practice-for-restcontroller

http://zetcode.com/springboot/annotations/

https://stackoverflow.com/questions/7421474/how-can-i-tell-jackson-to-ignore-a-property-for-which-i-dont-have-control-over

https://stackoverflow.com/questions/22688402/delete-not-working-with-jparepository

# Wprowadzenie +- Web

**Servlet** - klasy Javy [^1], których celem jest **przetwarzanie requestów HTTP i generowanie response** na te żądania. Innymi słowy, 
servlety służą do implementacji dynamicznych aplikacji WWW. [SOA_LAB1]

Serwlety do poprawnego działania wymagają kontenera serwletów (Tomcat, Jetty)

**Servlet Container** - implementuje Java Servlet Specification (Można budować apki webowe, wykorzystujące Servlet API do przetwarzania request/response workflow)

|WebContainer, ServletContainer, ServletEngine|WebServer, HTTPServer|ApplicationServer, AppServer|
|---|---|---|
|Javova rzecz: Zarządza takimi rzeczami jak Servlety i JSP|Obsługuje HTTP request-response (tylko HTTP)| Obsługuje całą JaveEE EJB, JMS, CDI, JTA i nie tylko HTTP (RMI/RPC)|
| |głównie static content, są pluginy do dynamic ( wspieranie PHP, Perl)| wspiera takie rzeczy jak Connection Pooling, Object Pooling, Transaction Support, Messaging services itd|

ServerAplikacyjny ma zwykle WebServer w sobie.

Sources: [1](https://stackoverflow.com/questions/12689910/difference-between-web-server-web-container-and-application-server/12689989),
[2](https://stackoverflow.com/questions/5039354/difference-between-an-application-server-and-a-servlet-container),
[3](https://stackoverflow.com/questions/936197/what-is-the-difference-between-application-server-and-web-server)

---

[Dispatcher Servlet](https://stackoverflow.com/questions/2769467/what-is-dispatcher-servlet-in-spring) - ma podstawie URI, wie którą metodę zawołać i do jakiego JSP ją wysłać żeby otrzymać stronę.
[Steruje zapytaniami HTTP](https://www.baeldung.com/spring-dispatcherservlet)

**Komponent** - fragment oprogramowania nadający się do niezależnego montowania w większe programy.
Skompilowany element z precyzyjnie zdefiniowanym interfejsem. Bezstanowy.

**[JSP](https://pl.wikipedia.org/wiki/JavaServer_Pages) (_JavaServer Pages_)** - technologia umożliwiająca tworzenie dynamicznych dokumentów WWW w formatach HTML ... z wykorzystaniem Javy.
Strona JSP jest zamieniana na servlet.

**JSF _(JavaServer Faces)_** - framework na Javie, który upraszcza tworzenie UI dla apek JavaEE. 

# Spring

## Spring VS SpringBoot
**Spring** - to framework do pisania apek Enterprise, duży, zlożony z wielu projektów (SpringBoot między innymi), konkurencja dla JEE/J2EE

**SpringBoot** - Pozwala tworzyć *Spring based apps*, które możesz **po prostu** odpalić. Standalone - nie potrzebujesz zainstalowanego serwera (przy odpalaniu prostej apki Tomcat jest jakby w środku SpringBoota)

## Co "złego" w Springu?
Generalnie Spring jest bardzo elastyczny, daje dużo flexibility. Ale przez te duże możliwości dostosowania, konfiguracja Springa jest złożona i nietrywialna.

---

## Rozpoczęcie
```xml
<parent>
	...
	spring-boot-starter-parent
	...
<parent>
```
**Parent** - projekt nadrzędny, dziedziczymy po nim różne rzeczy (konfiguracje itd)

Teraz potrzebujemy zależności: `spring-boot-starter-web` - zależności do aplikacji webowej


## Start Apki
`@SpringBootAplication` - adnotacja na główną klasę, tę z `main`'em. `main` startuje kontener serwletów (Tomcat, można skonfigurować inny).

Uruchomienie:
```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

Statyczna metoda z klasy SpringApplication:
1. Ustawia podstawową konfigurację
2. Startuje kontekst aplikacji - kontener dla całego mojego kodu
3. Skanuje klasy w poszukiwaniu adnotacji
4. Startuje server (Tomcat. Taki zintegrowany Tomcat jest też w sumie dobry do Mikroserwisów (jedna komenda i działa))


## Controller

**Controller** - Controller z MVC (data flow & update the View). Klasa Java z adnotacjami, ma zapisane który URL triggeruje którą metodę.

Spring MVC sam zamieni odpowiedź na JSON w klasie oznaczonej jako `@RestController`. Muszą mieć gettery, żeby SpringBoot mógł je zczytać.

Jak chcę XML-a, to wystarczy dodać dependency `jackson-dataformat-xml`. A potem w nagłówku zapytania HTTP się zmienia - content negotiation


PLIK: `src/main/java/com/github/demo/TestMapping.java`

Adnotacje:
- `@Component` - kandydat do auto wykrycia przy skanowaniu classpath
- `@Controller` - MVC Controller
- `@RestController` (spring.mvc) - adnotacja na klasę która jest
kontrolerem. (`@RestController = @Controller` na klasie + `@ResponseBody` na każdej metodzie)
- `@Autowired` - do DI, szuka po typie
    - `@Qualifier` - szuka po nazwie - `@Component("name")` - nadawanie nazwy
    - `@Scope(value="prototype")` - adnotacja na komponent. Może zmieniać sposób tworzenia obiektu przy DI (SpringBoot domyślnie tworzy jeden obiekt do DI +-)
- `@RequestMapping("URL")` - adnotacja na metodę (defaultowo jedynie `GET`'a mapuje). `URL` - URL który triggeruje tę metodę.
    - `@GetMapping()` - [@GetMapping vs @RequestMapping](https://stackoverflow.com/questions/39077787difference-between-the-annotations-getmapping-and-requestmappingmethod-requ) - używaj @GetMapping
- `@PostMapping()` - POST
    - `@RequestMapping(method=RequestMapping.POST,value="/topic")` - ustawiamy matodę HTTP na POST. [Ale lepiej używać `@PostMapping()`](https://stackoverflow.com/questions/57025328/what-is-the-difference-between-requestmapping-and-postmapping)
    - `@PathVariable()` - oznaczamy w nawiasach `{}` np: `@RequestMapping("/URL/{id}")`. Odwołanie w deklaracji funkcji: `@PathVariable("foo")`
    - `@RequestBody` - jeżeli coś ma przyjść w Request `void test(@RequestBody Clazz a) ...`
    - `@ResponseBody` - serializacja do JSON-a, Spring defaultowo oznacza, jak jest [@RestController](https://www.baeldung.com/spring-request-response-body)
    
Status HTTP, przez `ResponseEntity` [(info)](https://www.baeldung.com/spring-response-entity), [ale można też inaczej](https://www.baeldung.com/spring-mvc-controller-custom-http-status-code)

## Dependency Injection - Design Pattern
- **What?** - Tworzą obiekt i wstrzykują go. 
- **Why?** - Loosely Coupling (np: mocki do testowania można stworzyć tylko wtedy, jeżeli są loosely coupled)

## Business Service

PLIK: `src/main/java/com/github/demo/PersonService.java`

**Business Service** - typowo Singletony, adnotacja `@Service`

`@Service` - posiada logikę biznesową

```java
@AutoWired // oznaczenie, że potrzeba Dependency Injection
private TopicService topicService; // TopicService powinno mieć adnotację @Service
```
[Injection przez @Autowired powinno być unikane?](https://stackoverflow.com/questions/39890849/what-exactly-is-field-injection-and-how-to-avoid-it)

## JPA

**Java Persistance API** - oficjalny standard ORM dla Java (część specyfikacji EJB).
Definiuje JPA Query Language. Nie wymaga robienia DAO. Implementacje standardu:
- Hibernate (framework/biblioteka)
- EclipseLink

Wszystko z javax.persistance jest JPA (@Entity, @Table, @Id ...)

**ACID** - zbiór właściwości gwarantujących poprawne zasadt transakcji w bazach danych:
- **A (Atomicity; Niepodzielność)** - albo wszystko przejdzie, alebo nic.
- **C (Consistency; Spójność)** - system będzie spójny (np: nie będzie salda poniżej 0)PLN
- **I (Isolated; Odizolowany)** - dwie transakcje nie widzą siebie nawzajem, swoich zmian. Poziomy izolacji:
    - read uncommited - transakcja może czytać dane które nie są jeszcze zapisane (najniższy poziom)
    - read commited - możemy czytać tylko zapisane wiersze
    - repeatable read - transakcja nie może read/write na wierszach read/write przez inną transakcję
    - serializable - wynik współbieżnych transakcji musi być taki sam jak sekwencyjnie wykonanych. (pełna izolacja)
- **D (Durable; trwałość)** - jak commited to commited i są zapisane.

**HQL - Hibernate Query Language** - Dostarczany przez Hibernate. Zapytania niezależne od bazy danych.

**JPQL - Jakarta/Java Persistance Query Language** - bazuje na  HQL. Operuje na JPA entity objects.

**Criteria API** - tworzenie obiektu. Jakby bardzo Javowe rozwiązanie.

**Apache Derpy** - relacyjna BD zaimplementowana w Javie. Wbudowana w SpringBoota.

**h2** - baza w pamięci (w ramie, nie w storage)


**Persistance Unit** - grupuje klasy do danej BD. Połączenie z BD i JDBC.

**Mapowanie relacji:**
- Single Table Inheritance - jedna tabela. Kolumna z 'rozróżnikiem'
- Concrete Table Inheritance - dla każdej _konkretnej_ klasy, osobna tabela ze wszystkimi polami
- Class Table Inheritance - _każda_ klasa ma swoją tabelę i przechowuje swoje pola i odwołania do parents 

## CLI
- `mvn clean install` - wyczyści projekt (target) i stworzy .jar.
- `java -jar [plik]` - odpalenie apki 

Zmień `packaging` na `war` w `pom.xml` i deploy na Twojego Tomcata. Jak już masz swojego Tomcata, to nie potrzebujesz tamtego wbudowanego.

# Inne
- Bill of materials - listy JARów które dobrze działają ze sobą (gdzieś na necie ...?)
- Groovy CLI Scripts - da się pisać skrypty Groovy'ego które stworzą apkę SpringBoota. Przydatne do mały testów, jak chcesz coś sprawdzić?
- SpringBoot domyślnie nie wspiera JSP -> (Tomcat Jasper)
- Do folderu `resources` można dać plik `.sql` który się wykona przy starcie
- domyślnie SpringBoot używa Jacksona (JSON processor for Java)
- `@RequestMapping` produkuje = {"applicaiton/json""}

`java/webapp` - miejsce na `home.jsp`

`application.properties` - plik z konfiguracją SpringBoota
- spring.mvc.view.prefix=ABC - java/webapp/ABC
- spring.mvc.view.suffix=.jsp - dodaje .jsp do zwracanych stringów? (ale do obsługi JSP potrzebujesz Jaspera)

Połączenie z bazą przez dodanie :
```conf
spring.h2.console.enabled=true
spring.datasource.platform=h2
spring.datasource.url=jdbc:h2:mem:[nazwaBazyDanych]
```

Można używać sesji i requestów jak w [JavaEE](https://github.com/MarcinKozak005/SOA_AGH/blob/master/Lab2/Zad3/src/LoginServlet.java)

```java
@Controller
public class JSPController
{
    @RequestMapping("home")
        public String function(HttpServletRequest request)
        {
            HttpSession session = request.getSession();
            session.setAttribute("name", request.getParameter("name"));
            return "home.jsp";
        }
}
```
W SpringBoot nie trzeba się bawić tymi requestami i response'ami. Da się prościej (klasa ModelAndView). Spring zrobi mappingi. [Apka nr 2]

DispatcherServlet - potrzebuje Data i View name  (model i view) Możemy własnie użyć tego za pomocą klasy ModelAndView. Steruje zapytaniami HTTP.

[Spring Data JPA](https://www.youtube.com/watch?v=x67yiTHxn00)

1. No-code repositories
    - tworzymy interfejs rozszerzając CrudRepository<Klasa,ID> i mamy tam już podstawowe operacje na BazieDanych
2. Redukuje boilerplate code
3. Generuje zapytania na podstawie nazw
    - Cuda z generacją metod-zapytań do bazy:
```java
List<Person> findByName(String name);
List<Person> findByIdGreaterThan(int id);
```

Generalnie Spring Data jest troche kotem xD

**Repozytoria**
- CrudRepository
- PagingAndSortingRepository
- JpaRepository

```java
@Query("from Person where name = ?1 order by name ")
List<Person> findByNameSorted(String Name);
```
[I wiele wiele więcej](https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html#repositories.query-methods.query-creation). Można też dodawać `and` itp itd ...


# Hibernate Mappings

Każda z relacji (1-1, 1-oo, oo-oo) może być zarówno 'uni' jak i 'bi' directional.
Nie ma to wpływu na bazę danych, ale ma na to skąd ma się dostęp do czego.

- Jak jest unidirectional, do dostęp jest tylko w jedną stronę. (a -> b)
- Jak jest bidirectional, to dostęp jest w dwie strony.
 (a -> b i b -> a [Uważaj z StackOverflow przy wypisywaniu])

- Jak coś jest `...ToMany` to robi tablicę asocjacyjną. Chyba że dam [@JoinColumn(name="...")], 
to połączenie będzie przez kolumnę `name`. (No nie przy `ManyToMany`)
- `ManyToMany` używaj seta nie listy - performance!
- `@JoinTable` - zmiany tablicy asocjacyjnej.
    - inverseJoinColumns - nadanie nazwy temu co nie own asociacji
    - joinColumns - nadanie nazwy temu co own asocjację
- Jak nie ma określonego owning side to nie robi tego jako bidirectional, 
tylko 2 x unidirectional

Java a Hibernate:
- Hibernate zapisuje do bazy danych takie obiekty jakie mu podałem - nie doda obiektu do listy sam z siebie. Nawet jeśli relacja jest bidirectional.
- Przy bidirectional wymagane jest podwójne dodawanie i usuwanie (modyfikacje, utility functions).
- Nie wszystkie własności widać w tabelach => Mogę otrzymać dwie różne rzeczy z 2 takich samych widoków (jak nie dodałem np: obiektu do listy w jednym)

Linki:
- [Ultimate Guide to Assoc. mapping](https://thorben-janssen.com/ultimate-guide-association-mappings-jpa-hibernate/)
- [What is owning side](https://stackoverflow.com/questions/2749689/what-is-the-owning-side-in-an-orm-mapping)
- [JoinColumn vs JoinTable](https://stackoverflow.com/questions/30288464/when-should-i-use-joincolumn-or-jointable-with-jpa)
- [Javax Persistance](https://docs.jboss.org/hibernate/jpa/2.1/api/javax/persistence)
- [Why no mappedBy in ManyToOne](https://developer.jboss.org/thread/107265)

Zalążki:

Spring Data Rest - @RepositoryRestResource() - adnotacja która robi wszystko powyższe xD - HATEOAS trochę?


```properties
# Database configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/shop_database
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.initialization-mode=always
# JPA/Hibernate
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
```

Linki:

1. [JavaBrains QuickStart (pierwsze 20kilka odcinków)](https://www.youtube.com/watch?v=msXL2oDexqw&list=PLqq-6Pq4lTTbx8p2oCgcAQGQyqN8XeA1x&index=1)
2. [Telusko - Navin Reddy](https://www.youtube.com/watch?v=35EQXmHKZYs)


[^1] .NET nie ma bezpośredniego odpowiednika Servletów