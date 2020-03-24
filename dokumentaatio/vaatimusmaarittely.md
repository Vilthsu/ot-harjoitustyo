# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksella käyttäjät voivat liittyä eri projekteihin (ja tulevaisuudessa lisätä esimerkiksi tunteja tehdyille projekteille). Sovellukseen on mahdollista rekiströityä ja käyttää sitä usealla rekisteröityneellä käyttäjällä sekä pääkäyttäjällä / pääkäyttäjillä. Jokaisella "normaali tason" käyttäjällä on omat relaationsa mielivaltaiseen määrään projekteja ja pääkäyttäjä voi hallita kaikkia käyttäjiä. Normaleilla käyttäjillä on mahdollisuus liittyä julkisiin projekteihin mutta pääkäyttäjän oikeuksilla myös yksityisiin projekteihin.

## Käyttäjät

Sovelluksessa tulevat olemaan normaalin tason käyttäjät perusoikeuksilla sekä korkeamman tason oikeudet omaava(t) pääkäyttäjä(t).

## Käyttöliittymäluonnos

(tulossa)

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla pituudeltaan vähintään viisi (5) merkkiä ja sen täytyy olla uniikki
  - sähköpostiosoitteen tulee olla uniikki
  - käyttäjä voi halutessaan syöttää myös etu- ja sukunimensä
  - käyttäjän tulee syöttää oma salasana

- käyttäjä voi kirjautua järjestelmään
  - kirjautuminen onnistuu syötettäessä olemassaoleva käyttäjätunnus/sähköpostiosoite sekä salasana kirjautumislomakkeelle
  - jos käyttäjää ei olemassa, järjestelmä informoi käyttäjää tästä

### Kirjautumisen jälkeen

- käyttäjä näkee projektit, joihin hän on liittynyt / hänet on liitetty.

- käyttäjä voi luoda uuden henkilökohtaisen projektin
  - luotu projekti näkyy ainoastaan sen luoneelle käyttäjälle
  - projektin voi myös asettaa julkiseksi

- käyttäjä voi myös erota projektista, jolloin projekti häviää listalta

- käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideoita

Perusversiota voidaan jatkokehittää esimerkiksi seuraavilla ominaisuuksilla

- yksinkertainen tuntien syöttö
- henkilökohtaisten projektien osalta alkuperäinen tekijä saa pääkäyttäjäoikeudet kyseisen projektin osalta  
- projektin tietojen muokkaaminen
- lisää käyttäjätasoja
- käyttäjäryhmät
- käyttäjätunnuksen poisto
...