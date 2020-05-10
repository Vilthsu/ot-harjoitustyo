# Ohjelmistotekniikka, harjoitustyö

Sovelluksen avulla käyttäjä voi etsiä projekteja, liittyä projekteihin ja luoda omia projekteja. Sovellusta on mahdollista laajentaa esimerkiksi tuntisyöttöön ja muihin vastaaviin ominaisuuksiin.

Sovellus on Helsingin yliopiston Tietojenkäsittelytieteen kurssin Ohjelmistotekniikan menetelmät harjoitustyönä.

## Dokumentaatio

[Arkkitehtuurikuvaus](https://github.com/Vilthsu/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuurikuvaus.md)

[Vaatimusmäärittely](https://github.com/Vilthsu/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Testausdokumentti](https://github.com/Vilthsu/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

[Käyttöohje](https://github.com/Vilthsu/ot-harjoitustyo/blob/master/dokumentaatio/kaytto-ohje.md)

[Työaikakirjanpito](https://github.com/Vilthsu/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Toteutumattomat ominaisuudet](https://github.com/Vilthsu/ot-harjoitustyo/blob/master/dokumentaatio/toteutumattomat-ominaisuudet.md)

## Releaset
Viimeisin on listan kärjessä, toisin sanoen releaset ovat laskevassa järjestyksessä julkaisuajan mukaan.

- [v0.2-alpha](https://github.com/Vilthsu/ot-harjoitustyo/releases/tag/v0.2-alpha) - Loppupalautus kurssille su 10.5.2020.
- [v0.1.1-alpha](https://github.com/Vilthsu/ot-harjoitustyo/releases/tag/v0.1.1-alpha) - Korjausrelease viikon viisi releaseen.
- [v0.1-alpha](https://github.com/Vilthsu/ot-harjoitustyo/releases/tag/v0.1-alpha) - Ensimmäinen vaadittu release viikolla viisi.

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
