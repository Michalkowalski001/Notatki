# Docker


Generalnie lepszy od VM bo mniejsze wymagania zasobowe +-

**Dockerfile** -> **Docker image** -> **Docker container**

**Image** - templatka do tworzenie contenerów

**Container** - pracująca instancja obrazu

- **docker run** - uruchom kontener z obrazu
    - **-d** - detached mode- moje CLI nie jest od razu podłączone do kontenera
    - **--name=** - ustawianie nazwy
    - **-e PI=3** - eksport zmiennej środowiskowej
- **docker attach** - attach CLI do kontenera
- **docker ps** - listuje uruchomione kontenery i basic info o nich
    - **-a** - all
    - **-q** - quite - printuje tylko IDs
- **docker pull** - tylko ściąga obraz (jakby część docker run)
- **docker stop [name albo id]** - stopuje kontener (stopowanie to nie remove)
- **docker rm** -  usuń kontener
- **docker images** - pokaż obrazy lokalne   
- **docker rmi** - remove image (nie może nic na tym obrazie jechać w danym momencie)
- **decker exec Container Command** - uruchom polecenie na pracującym kontenerze
- **docker inspect** - lepsze info (JSON)
- **docker logs** - zobaczenie logów

`docker run redis:4.0` - tag to `4.0`, bez tego bierze `latest` - czyli najnowszą wersję

Normalnie docker nie czyta ze STDIN
- **-i** - interactive mode: map STDIN do kontenera
- **-t** - podłącza się do terminala dockera

Container żyje tylko tak długo, jak proces który się w nich dzieje. One mają za zadanie runować określone zadanie:
- `docker ubuntu` -> stopuje się od razu. Mogę dopisać po tym komendę: `docker run ubuntu sleep 5`

### Port mapping

Kontenery znajdują sie w Docker Engine/Host. Mapowanie portów kontenera na porty Docker Engine:
- `docker run -p 80:5000 ...` - `5000` kontenera na `80` naszą


### Volume mapping

Pliki z contenera mapujemy na Docker Hosta. Jak usunuiesz kontener to dane pozostają w Docker Engine. Pliki z Docker engine są podmontowywane pod kontener +-.

Dwie metody montowania:
- bind i volume
- -v jest trochę stare


## Tworzenie  własnego obrazu

`docker build ... ` - robi image z Dockerfile.

Dockerfile:
Składnia na zasadzie: `INSTRUKCJA ARGUMENT`:
- `FROM ...` - bazowy obraz. Musi to byc pierwsza linia
- `RUN` - wykonuje określoną komendę na obrazie 
- `COPY` - kopiuje pliki lokalne do obrazu
- `ENTRYPOINT` - jaka komenda ma się wykonać jak odpalimy image przez run.

- `CMD` - program odpalony jak kontener wystartuję tę komendę. To jest trochę to co podajemy po docker run np: `sleep 5`
- `CMD` - zastępuje całkowicie komendę
- `ENTRYPOINT` - robi appenda
- `CMD` oraz `ENTRYPOINT` jeżeli chcemy domyślne parametry wywołania

## Sieci

Docker robi normalnie 3 sieci:
- **bridge** - domyślne, trzeba mapowac jeżeli chcesz się dostać z zewnątrz
- **none** - wyizolowane, nie ma do nich dostępu z zewnątrz ...?
- **host** - porty są mapowane jakby automatycznie X:X (80:80)\

Można robić swoje sieci
- **docker network ls** - pokaż sieci

Kontenery mogą się odnajdywać w sieci po nazwach (jakby wewnętrzny DNS ...)

## Architektura Dockera
Buduje obrazy szybciej, bo wykorzystuje obrazy już stworzone (jeżeli są wspólne)

Layered architecture:
- warstwa obrazu(read only)
- warstwy kontenera (read write)
- image może być szerowany pomiędzy kontenerami
- jak w kontenerze edytuję coś z image, to Docker tworzy kopie tego z image zmodifikowanego, przeze mnie w read write layer (copy-on-write)

Komendy
- **docker volume create data_volume** - tworzy volumin *(/var/lib/docker/volumes/data_volume)*
- **docker run -v data_volume:/var/lib/mysql mysql** - kontener z podmonowanym voluminem, jak nie ma to stworzy i podmontuje sam.


## DockerCompose
Generalnie służy do jakby zbiorczego runowania kontenerów. Używamy YAMLa żeby zrobić jakby plik konfiguracyjny - docker-compose.yml

`links` - mechanizm jakby łączenia kontenerów. Ale też trochę przestarzały.

- są różne wersje i się od siebie troszkę rożnią
- Links są niepotrzebne w wersji +2
- 3 ma coś jeszcze z DockerSwarmem

Sa sposoby ograniczania CPU i Memory dla kontenerów

## Orkiestracja
- Docker Swarm, Kubernetes, Apache Mesos
- skalowanie jak duży/mały ruch ... itd itd
- wykrywanie jak będzie awaria i stawianie kontenera na nowo 

# Linki
[Docker in 5 min](https://www.youtube.com/watch?v=_dfLOzuIg2o)

[2h główne źródło wiedzy](https://www.youtube.com/watch?v=fqMOX6JJhGo) - [Laborki/Ćwiczenia Online](https://kodekloud.com/p/docker-labs)