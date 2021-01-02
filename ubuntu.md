POSEGREGUJ:


# Ubuntu

## Kilka słów o Bashu
- można używać potęgi jak w Pythonie **
- += i ++ też działają
- Bash nie daje sobie rady z Floatami xd (Python, lub powłoki Korna, zsh)
- da się zmienne lokalne `local`
- `return` ma chyba trochę inne zastosowanie niż normalnie
- `read -p "MojPrompt> " zmienna_wktorej_trzymamy_input` - czytanie info od usera
- Bash jest dziki

[If'y](https://linuxacademy.com/blog/linux/conditions-in-bash-scripting-if-statements/) i [nawiasowanie Bash](https://stackoverflow.com/questions/669452/is-double-square-brackets-preferable-over-single-square-brackets-in-ba)


To jest generalnie powłoka: pośrednik OS a użytkownik. Ale progmramy nie musza go używać.

## 0. Moje inne
- **xdg-open | browse** - odpalanie +- domyślną apką (przeglądarka plików też!)
- **tar xvzf _file_.tar.gz** - dokompresja.
    - **x** - extract
    - **v** - verbose (list files)
    - **z** - uncompress
    - **f** - podam Ci file do przetworzenia
- command substitution - `echo $(seq 1 5)` / `mkdir $(seq 1 6)`
## 1. Wprowadzenie

**Informacje**
- \<CTRL>D - wysyła EOF, zamkniesz terminal
- info (GNU) - taki man (UNIX), w sumie niby nowesze ...
- polecenie -h/--help -> każdy ma jakiegoś helpa

**Polecenia**
- **man X C** - strona X polecenia C
- **whoami** - nazwa urzytkownika
- **whatis** - podsumowanie stron mana (man -f)
- **groups, id, passwd**
- **w, who, finger** - z grubsza takie same, jakieśtam różnice w bezpieczeństwie.
- **tty** - printuje nazwę pliku terminala podpiętego do standard IN (+- z którego terminala korzystasz)
- **quota** - sprawdzanie pojemności dysku "twardego"
- **scp** - secure cp -> secure copy scp [user@host:]plik
- **wget** - służy w sumie do pobierania rzeczy z neta. Pobierze ci np www.wp.pl do pliku (xD) czy odpowiedź od API zapisze do pliku.
- **tac** - cat od tyłu xD

## 2. Praca z plikami

**Informacje**
- / - root
- ~ - home, mechanizm powłoki
- ? - zastępuje pojedyńczy znak
- SUID - odpalasz z uprawnieniami właściciela
- SGID - odpalasz z uprawnieniami grupy
- StickyBit - pliki w katalogu ze sticki bit mogą być usuwane i modyfikowane tylko przez ich właściciela

Dowiązania miękkie i sztywne

|Miękkie|Sztywne|
|:---:|:---:|
|obojętne do czego|nie do folderu|
|może być inny FileSystem|ten sam FileSystem|
|+-Windowsowy skrót. "Dowiązanie do pliku"|Jakby kopia, ale oszczędzająca pamięć ;)|

W **pwd** da się ustawić opcje -P/-L które ustawiaja jaką ścieżkę chcemy widzeć fizyczną/logiczna

**Polecenia**
- **du** - szacuje miejsce na dysku zajmowane przez flik/folder rekurencyjnie
- **umask** -  Maska tworzenia plików, wyklucza exec. Plik tworzony nie jest z domysłu wykonywalny
- **chown** - zmień wlaściciela
- **chgrp** - zmień grupę
- **ln** - tworzy dowiązania
    - -s dow miękkie
- **ls**
    - -R - recursieve 
    - -r - reverse order printowania
    - -d - info o samym folderze, nie zawartość
    - -h - size w human-readable
- **mkdir** -p - tworzy nieistniejące foldery

## 3. Powłoka i środowisko pracy
**Informacje**
- sa różne powłoki (sh, bash, csh)
- zmienne środowiskowe - różnie sie ustawia w różnych powłokach
- BASH: `$: ZMIENNA=WARTOSĆ; export ZMIENNA; unset ZMIENNA` dodatkowo `printenv` taki ls na zmiennych
- PS1, PS2 - różne znaki zachęty
- alias ll='ls -lFa'
- Cytowania:
    - "tekst" - napis z możliwością interpretacji znaków specjalnych `echo "My name is $LOGNAME"`
    - 'tekst' - jak wyżej, ale bez możliwości interpretacji `echo 'My name is $LOGNAME'`
    - \`tekst\` - traktujemy `tekst` jako polecenie. Odpalamy `tekst` w nowej powłoce i wstawiamy tekstowy wynik. `echo "My name is \`echo $LOGNAME\`"`
- more, less, most

## 4. Praca z procesami i zadaniami 
- **jobs** - wypisywanie aktywnych zadań (w tym shellu, sprawdz ze `sleep 10` jobs i 
ps)
- **fg %n** - przeniesienie zadania n na pierwszy plan (jobs)
- **head** [-n 5] - 

kill - SIGTERM - prosze zabij się, zwolnij zasoby; sobie tam pozamykaj, ale się zabij. Może zostać zignorowane.

kill-9 - SIGKILL generalnie zabicie procesu. Proces nie ma czasu na jakiekolwiek sprzątanie po sobie itd itd. Nie może zignorować.

\>> dopisanie do pliku

\> nadpisanie/ stworzenie pliku

// przekierowania przed uruchomieniem polecenia są 'wykonywane'
sort a>a

### GREP
Global Regular Exp Print

`grep "pattern" plik` - tyle xD

Generalnie większość wyciągnięta z `grep --help`

Quotes, bo spacje: `grep hello world file1` szuka w plikach `world` i `file1` pasujacych do wzorca `hello`
A tak to normalnie zasady nawiasowania jak w Ubuntu:
```bash
$ echo $HOME > me
$ grep $HOME me 
/home/marcinkozak
$ grep '$HOME' me 
[nic się nie pojawi]
$ grep `whoami` me 
/home/marcinkozak
```
grep -E -> [extended regex](https://github.com/learnbyexample/Command-line-text-processing/blob/master/gnu_grep.md#basic-vs-extended-regular-expressions):
> In Basic Regular Expressions the meta-characters ? + { | ( ) lose their special meaning, instead use the backslashed versions \\? \\+ \\{ \\| \\( \\)

- **P** - Perl regex (? jako greedy)
- **H** - pokaż nazwę pliku
- **c** - policzy (dla danego pliku, dla folderów wyświetli ile w kazdym)
- **n** - pokaż numery
- **F** - fixed string (to nie jest regex tylko po prostu string ziom xD)
- **w** - whole words (tylko pełne słowa)
- **i** - ignore cases
- **v** - invert - pokaż niepasujące
- **q** - quite nie wypisuj wyniku, bo np tylko potrzebujesz sprawdzić czy coś takiego jest -> return value
- **f** - `grep -f FileWithRegex GdzieSzukamy`
- **r|R** - recursive (-R idzie za dowiązaniami)
- **l|L** - nazwy plików z liniami | bez tych linii

Sterowanie kontekstem:
- B X - X lini przed matchem
- A X - X linii po matchu
- C X - przed i po, równonoważnie -X (gdzie X to liczba -2 -6 -123)

# 5. Podstawy skryptów i filtrów

### Find
`find` defaultowo zwraca wszystkie dir i files pod cwd

`find Gdzie -name "NazwaFliku"` - podwstawowe użycie +-

Generalnie nie używa regexów!

Opcje:
- **-name**- (-iname ignoruje case)
- **-type** - (f file, d directory, l symbolic link)
- Opercaje logiczne
    - **!** - not
    - **-a** - AND (mozna pominąć, jakby z defaulta jest `-a`) `find . -name "*py" -a -type f` 
    - **-o** - OR
- **-?min -X** - ? mniej niż X min temu. (+X więcej niż ...)
- **-?time -X** - ? days 
    - **m** - zmodifikowany - zmiana contentu
    - **a** - access (read)
    - **c** - changed  (meta data modification)
- **-size +5X**
    - **k** kilo
    - **M** mega
    - **G** giga
- **-empty** - jest pusty
- **-perm** 777 - ustaw wprawnienia na 777 
- **-exec** -> wykonaj na znalezionych tę komendę {} + \; `find . -name "*sh" -exec cat {} +`
    - {} - podstawienie wyników find'a
    - \+ | \\; koniec polecenia 
- **-maxdepth** - jak głeboko należy szukać (analogicznie min)

# 6. SED (i AWK xD)
- SED (Stream EDitor) wyewoluował z GREPa

- `sed Opcje 's/Pattern/Pattern2/Flagi Komendy' Plik`

- SED domyslnie przetwarza linie: `echo "a a a \n b a \n b a a" | sed 's/a/A/'`

- `sed 'p' ...` - zduplikowane, ponieważ domyślnie sed printuje, a tu każemy mu na każdej lini wykonać polecenie p -> print. Trochę: bierz linię -> wykonaj polecenie -> printuj linię

- Delimeters mogą być np: | _ : [i prawie każde inne xD](https://stackoverflow.com/questions/33914360/what-delimiters-can-you-use-in-sed)

- Sed nie jest rekursywny: `echo loop | sed 's/loop/loop the loop/g'`

- Niektóre flagi i komendy są +- zduplikowane 

Opcje:
- **E|r** - extended regex
- **n** - nie drukuje - quiet (po wykonaniu akcji domyślnie nie drukuje)
- **e** - przed każdą komendą, jeżeli robię kilka
- **i** - in place (-i.tmp -> oryginal dostaje .tmp do nazwy)
- **f** - podajesz plik - comendy w osobnej linii
- **s** - seperate arguments (potraktuj padane pliki z osobna, norrmalnie to je konkatenuje)(przydatne przy ograniczeniach)

Patterns:
- uważaj tylko na różne regexy (? jako non-greedy nie działa czasami :<)
- **&** - to do czego regex sie przypasował
- **\1 ... \9** - grupy

Flagi:
- **/g** - global, nie pierwsze w linii, tylko całe
- **/I** - ignore case
- **/w file** - zapisz do pliku
- **/p** - print

Komendy:
- **=** - wypisz numery linii
- **s/.../.../** - substitute
- **d**- delete
- **p** - print
- **q** - quit
- **!** - te które nie zawierają wzorca

Ograniczenia:
- `sed 'X s/.../.../g ...'` - tylko w X linii
    - **3** - trzecia linia
    - **1,+4** - pierwsza i 4 w przód
    - **1~2p** - pierwsza i co druga
    - **1,100** - pierwsza do setnej
    - **223,$** - 223cia do końca
- `sed '/REGEX/ ...'` - tam gdzie REGEX pasuje
- `sed -e '/START/,/END/' command` - wykonaj komendę na liniach pomiędzy START i END
- `sed 's/a/w/2'` albo `.../2g` drugie wystąpienie (na Ubuntu mi dziwnie działa. Nie tak jakbym się spodziewał)

[SED Examples](https://ai.ia.agh.edu.pl/pl:dydaktyka:unix:lab_sed_awk#sed) :

1. sed '' /etc/passwd
2. sed 's/:/ /g' /etc/passwd
3. sed 's/:.*//' /etc/passwd
    - sed -E 's/([^:]*):.*/\1/' /etc/passwd
4.  sed -ne '4~3 p' -e '14 q' /etc/passwd
5. sed -n '3,5 p' /etc/passwd
6. sed -n '/^z/ p' /etc/passwd
8. sed -n '/lo/ !p' /etc/networks
10. sed -n '/.*www.*/ s/data/Marcin/gp' /etc/passwd
11. sed -E '/^$/ d' x
15. sed -E 's/(.)(.*)/\1X\2/g' /etc/passwd


# 7. Skrypty Bash

# 8. Szyfrowanie

**Symetryczne**:
- szyfrowanie/deszyfrowanie - te same klucze 
- lub generalnie proste przejście między tymi dwoma kluczami
- klucz musi jakos być przekazany
    - AES
    - DES

**Asymetryczne** (klucza publicznego):
- funkcje jednokierunkowe
- para kluczy: publiczny i prywatny
    - RSA

**Podpis elektroniczny**:
- A
    - pisze wiadomość M, liczy jej skrót SK
    - S_SK = PrivA(SK) [S_SK = zaszyfrowane SK]
    - X = PubB(M + S_SK)
- B
    - PrivB(X) = M + S_SK (Mamy wiadomośći)
    - liczy skrót M (SK1)
    - PubA(S_SK) = SK
    - Sk == Sk1 ? Tak: jest git  (wiadomość i tożsamość)

**Man in the middle**:
- generalnie trochę chodzi o przekierowanie ruchu
- ochronę zapewniają organizacje certyfikujące 

**md5** służy do wyliczania skrótów wiadomości


# 10. i dalej

Bootloadery - GRUB i LILO

|system|exec|
|---|---|
|robi call do sh|zastępują obecny proces nowym|
|mój program -> system cos zwraca -> cd moja część|exec zastępuje mój proces|
|można go zrobić z `fork()`, `wait()` i `exec()`||
|tworzy process potomny|

Good przykład:
```c
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
 
extern char **environ;
 
int main (int argc, char **argv, char **envp) {
 
    printf("Poczatek procesu.\n");
 
    system("ls");
    printf("Dalszy ciag kodu...\n");
 
    execl("/bin/ls", "ls", NULL);
    // To już się nie wykona
    printf("Koniec kodu...\n");
 
    return(0);
}
```

`fork` tworzy kopie aktualnego procesu.

```c
#include <stdio.h>
#include <unistd.h>
 
extern char **environ;
 
int main (int argc, char **argv, char **envp) {
    int p=0;
 
    printf("Poczatek procesu...\n");
    p = fork();
    printf("Tu jestem: %d\n", p);
 
    return(0);
}
```

# Linki
- [Labki Unix](https://ai.ia.agh.edu.pl/pl:dydaktyka:unix:start)
- [Komendy #TODO?](https://www.astrouw.edu.pl/~jskowron/pracownia/komendy/)
- [GREP](https://github.com/learnbyexample/Command-line-text-processing/blob/master/gnu_grep.md)
- [Sed](https://www.grymoire.com/Unix/Sed.html)
- [Sed 2](https://www.digitalocean.com/community/tutorials/the-basics-of-using-the-sed-stream-editor-to-manipulate-text-in-linux)
- [Find](https://www.youtube.com/watch?v=KCVaNb_zOuw)
