# [Git commit messages](https://chris.beams.io/posts/git-commit/)

_A diff will tell you **what** changed, but only the commit message can properly tell you **why**_

_Use the body to explain **what** and **why** vs. **how**_

_If applied, this commit will **your subject line here**_

**git log -p** - pokazuję część(?) zmian przy logach


**[Amortized-time](https://stackoverflow.com/questions/200384/constant-amortized-time)** - typowy czas dla operacji jeżeli wykonuje ich dużo


## Travis vs Jenkins
[StackOverflow](https://stackoverflow.com/questions/32422264/jenkins-vs-travis-ci-which-one-would-you-use-for-a-open-source-project)

[Continuous Integration](https://www.guru99.com/jenkins-vs-travis.html):

- piszemy kod i wrzucamy na repo
- potem server CI, monitoruje repo, buduje system i przeprowadza test (integracyjne, unit test)
- serwer wypuszcza deployowalne artefakty, przypisuje tag i version number
- reportuje błędy/sukces. Team musi naprawić jak jest błąd.

|Travis|Jenkins|
|---|---|
|Comercial|OpenSource|
|mało konfiguracji|dużo konfiguracji|
|małe możliwości dostosowania|duże możliwości dostosowania|
|configuracja w YAMLach|user konfiguruje(?)|


[AWS](https://www.youtube.com/watch?v=3XFODda6YXo) - Global Cloud Computing Platform (IaaS, PaaS, SaaS)
