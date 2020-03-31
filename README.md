# Ohjelmistotekniikka, harjoitustyö

Sovelluksen avulla käyttäjä voi etsiä projekteja, liittyä projekteihin ja luoda omia projekteja. Sovellusta on mahdollista laajentaa esimerkiksi tuntisyöttöön ja muihin vastaaviin ominaisuuksiin.

Sovellus on Helsingin yliopiston Tietojenkäsittelytieteen kurssin Ohjelmistotekniikan menetelmät harjoitustyönä.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Vilthsu/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/Vilthsu/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuudesta generoidaan raportti komennolla.

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella selaimella _target/site/jacoco/index.html_-tiedoston.

### Suoritettavan jar-tiedoston generointi

Komento

```
mvn package
```

Generoitu suoritettava jar-tiedosto _ProjectManager-1.0-SNAPSHOT.jar_ löytyy hakemistosta _target_.

### JavaDoc

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella selaimella tiedoston _target/site/apidocs/index.html_ avulla.


_Käytän pääasiassa Windows 10 Pro työasemaa aivan kaikessa (sekä opinnoissa että töissä ja omissa projekteissa). Ensimmäisen viikon tehtävät ovat tehty virtuaalikoneella, Ubuntu Desktop 16.04 LTS -käyttöjärjestelmällä, käyttäen komentoriviä PuTTY-ohjelmistolla._

<sub>Muutokset "synkronoidaan" useimmiten yksityisestä GitLabista tänne GitHubiin push-operaatiolla pienellä viiveellä, joka on keskimäärin viisi (5) minuuttia, [Repository Mirroring](https://docs.gitlab.com/ee/user/project/repository/repository_mirroring.html#setting-up-a-push-mirror-from-gitlab-to-github-core) -ominaisuudella. Mahdollisesti myöhemmin lisättävän `gitlab-ci.yml` voi jättää huomiotta mikäli käytössä ei ole GitLabia. Kyseinen tiedosto tulee sisältämään asetukset [GitLab CI/CD pipelineja](https://docs.gitlab.com/ee/ci/yaml/#gitlab-cicd-pipeline-configuration-reference) varten, joiden avulla tulen toteuttamaan yksityisellä, Ubuntu Server 18.04 LTS, palvelimella ainakin automaattisen testaamisen push-operaatioiden osalta.</sub>
