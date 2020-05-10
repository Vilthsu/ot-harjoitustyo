# Käyttöohje
Tämä dokumentti sisältää lyhyen käyttöohjeen sovelluksen tämän hetkisen version käyttöön.

Tämän dokumentin voi myös avata sovelluksen yläreunasta päänäkymässä polusta _Ohje_ -> _Käyttöohje_.

Kaikki sovelluksen tallentama tieto tallennetaan sovelluksen sen hetkiseen hakemistoon automaattisesti luotavaan SQLite-tietokantaan. Sovelluksen tallentamien tietojen täydellinen nollaus onnistuu, kun poistaa tiedoston _project-manager-sqlite-database.db_.

## Rekisteröityminen
Jotta Käyttäjä voi käyttää sovellusta, hänen täytyy ensin rekisteröidä uusi käyttäjä mikäli hänellä ei ole olemassa käyttäjää. Rekisteröitymissivu avautuu kirjautumisnäkymän alareunassa olevasta _luo uusi käyttäjä_ linkistä. 

Rekisteröintiin vaaditaan vähintään yksiköllinen **käyttäjätunnus**, jonka Käyttäjä saa itse valita, ja **salasana**. Käyttäjätunnuksen tulee olla vähintään viisi (5) merkkiä pitkä ja se voi olla korkeintaan 15 merkkiä pitkä. Salasanan tulee olla vähintään kahdeksan (8) merkkiä pitkä.

Sähköpostiosoite on vapaaehtoinen eikä sovellus tee kyseisellä tiedolla tässä versiossa vielä mitään. Mikäli sähköpostiosoite syötetään, sen tulee olla muotoa _user@example.com_ ja sen pituuden tulee olla vähintään viisi (5) merkkiä ja sen tulee olla korkeintaan 35 merkkiä pitkä.

Mikäli Käyttäjä syöttää virheellisessä muodossa olevia tietoja, lomake informoi käyttäjää kyseisestä virheestä suomeksi. Rekisteröinnin yhteydessä Käyttäjälle avautuu ilmoitus rekisteröinnin onnistumisesta ja tämän jälkeen Käyttäjä voi kirjautua sisään rekisteröimillään tunnuksilla.

Kaikista syötteistä poistetaan etu- ja loppuosien välilyönnit.

Ensimmäinen rekisteröitynyt käyttäjä rekisteröidään automaattisesti **pääkäyttäjäksi**, jolla on laajennetut käyttöoikeudet.

## Kirjautuminen
Ennen sisäänkirjautumista Käyttäjän tulee olla rekisteröitynyt eli Käyttäjällä tulee olla käyttäjä sovellukseen.

Sisäänkirjautumiseen vaaditaan Käyttäjän rekisteröimän käyttäjän tunnus (5-15 merkkiä pitkä) sekä salasana. Käyttäjän syöttäessä tiedot oikein, hänet ohjataan automaattisesti projektilistat-näkymään, muussa tapauksessa käyttäjää informoidaan _Käyttäjää ei löytynyt_ viestillä. Kirjautuessa sisään tarkistetaan myös syötteen pituus poistaen siitä etu- ja loppuosien välilyönnit.

## Päänäkymä, projektilistat

Päänäkymässä Käyttäjälle avautuu kaksi välilehteä, _Selaa projekteja_ ja _Omat projektit_. _Selaa projekteja_ välilehti sisältää kaikki sovellukseen tallennetut projektit. Käyttäjä voi halutessaan valita projektin listalta, jolloin listan oikealle puolelle ilmestyy painike _Liity projektiin_ tai vastaavasti, mikäli hän on jo kyseisessä projektissa, voi hän _Poistu projektista_ painikkeen kautta poistua projektista. Kaikki projektit, joihin käyttäjä on liittynyt, löytyvät _Omat projektit_ välilehdeltä. Käyttäjä voi täällä hallita omia projektejaan ja poistaa itse luotuja projekteja. Poistotoiminto toimii ainoastaan niissä projekteissa, joiden oikeassa yläkulmassa lukee käyttäjän oma tunnus. Painike tullaan piilottamaan myöhemmin muiden projektien osalta.

Kun projekti on poistettu, projektin kaikki _käyttäjäsidokset_ poistuvat automaattisesti tietokannasta.

## Projektin luominen
_Omat projektit_ välilehden näkymän oikeassa reunassa Käyttäjä voi luoda omia projektejaan. Tämä onnistuu painamalla _Luo uusi projekti_ painiketta. Uuden projektin luomiseksi Käyttäjän tulee syöttää vähintään vaadittu projektin nimi. Projektin kuvaus on vapaaehtoinen kuvaava teksti projektista. Kuvaus voi olla tyhjä tai korkeintaan 255 merkkiä pitkä. Tässä versiossa kielet-kentän tietoja ei tallenneta. Kielet-kenttä tulee sisältämään projektin ohjelmointikielet, esim. Java, C++, C#, JavaScript, Python jne..

Projektin luomisen jälkeen Käyttäjän oma projektilista päivittyy automaattisesti mahdollistaen projektikohtaiset sidokset ja _Liity projektiin_ sekä _Poistu projektista_ painkkeiden tulostuksen oikein.

## Uloskirjautuminen
Uloskirjautuminen tapahtuu _Tiedosto_ valikosta vasemmasta yläkulmasta. _Tiedosto_ valikko sisältää myös _Sulje_ painikkeen, joka sulkee sovelluksen ja sulkee kirjautumisistunnon automaattisesti. _Kirjaudu ulos_ painike taas kirjaa Käyttäjän ulos ja siirtää Käyttäjän kirjautumisnäkymään.
