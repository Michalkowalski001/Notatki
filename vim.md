# VIM

## Ustawienia edytora
Żeby ustawić jakieś domyślne rzeczy po otwarciu Vim'a, zmieniamy plik .vimrc w $HOME. Czasami go nie ma, może być gdzie indziej (**:echo $MYVIMRC**):
- **:set number** - numery linii
- **:syntax on/off** -  podkreślanie składni
- **:set tabstop=2** - wielkość tabów
- **:set [no]autoindent** - auto wcięcia po \<ENTER>
- **:set hlsearch** - podkreślanie searchów

## Tryby pracy
Generalnie jest ich dużo (między 6-11, zależy jak liczyć dodatkowe tryby). Ważne dla mnie:
- **normal mode** - ten co na początku wchodzę
- **insert mode** - wchodzimy przez **i** (**a** też czasami działa, ale to dodatkowo czasami(?) skacze do końca - a'la append. Do pisania normalnego.
- **visual mode** - po kliknięciu **v** do takiego zaznaczania (ale jest też **select mode**)
- **replace mode** - po kliknięciu **R**, zamienia litery


## Nawigacja
- **w** - word, idź do początku następnego słowa (kompl. z **e**)
- **e** - idź do końca następnego słowa (kompl. z **w**)
- **b** -  back, idź do początku poprzedniego słowa
Są też wersje wielkoliterowe, które inaczej interpretują słowa (1.2.3.4 traktują jako jedno)

Te rzeczy można ładnie kombinować z cyframi: **2e** = 'dwukrotne naciśnięcie **e**'

- **%** - skok do pasującego nawiasu
- **0** - początek lini, **$** koniec linii
- **\*** - następne słowo pod kursorem
- **gg** - początek pliku
- **G** - koniec pliku
- **2G** - druga linia, także **:(numer)**
- **\#** - poprzednie słowo pod kursorem


## Bulk
_Nazwa trochę wymyślona przeze mnie_
- **(numer)i(co_chcesz_wstawić)\<ESC>** - **20ilubie placki\<ESC>** wstawi 20 razy tekst _lubie placki_
- **d[ile]{w,b,B,e}** - bulk delete

## Search & Regex
- **/(co)\<ENTER>** - czego szukamy
- **:%s/OLD/NEW/g** - Regex
- **n** - następny znaleziony wzorzec
- **N** - poprzedni znaleziony wzorzec


## Copy & paste
- **y** - kopiuj (yank) (**yy**, **Y** całą linię)
- **d** - delete
- **u** - undo


## Pozostałe
- **q:** - pokaż ostatnie komendy (tak, q: nie inaczej)
- **o** - robi \n i insert poniżej
- **O** - robi \n i insert powyżej
- **r** - replace jednej litery pod kursorem
- **.** - robi powtórzenie ostatniej komendy


## Linki
- https://pl.wikibooks.org/wiki/Vim/%C5%9Aci%C4%85ga
- https://www.openvim.com/
- https://vim-adventures.com/



## Słowniczek 
- \<ESC> jaki klawisz nacisnąć
- **kompl** - komplementarne

---
Charityware - darmowe, ale miło jak dacie dotację
