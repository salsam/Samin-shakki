###Tuntikirjanpito
Päivät | Tunnit | Kuvaus
---------------- | -------- | --------------
16.05.2016 | 2.5h | Aiheen sunnittelu, repon luonti ja 1.viikon tehtävien aloitus
16.05.2016 | 1.5h | Aiheen suunnittelun viimeistely ja labtool-rekisteröinti
21.05.2016 | 5h | Luokkarakenteen sunnittelua ja ohjelmoinnin aloitus
21.05.2016 | 4h | Pawn, Knight ja King luokan perustoiminnalisuus ja testit
22.05.2016 | 2h | Pakkausrakenteen korjausyritys
23.05.2016 | 3h | Nappuloiden liikkuminen refaktoroitu
23.05.2016 | 4h | Korjattu nappuloiden luontivirhe, yksinkertainen versio valmis (puuttuu shakkaus, matittaminen ja grafiikka)
26.05.2016 | 0.5h | Checkstyle- ja pit-raporttien sekä luokkakaavion päivitys, lisätty linkit READMEen
29.05.2016 | 3h | Aloitettu shakkauksen lisääminen luomalla metodit selvittämään jokaisen nappulan uhkaamat ruudut
30.05.2016 | 3h | Git-sekoilee täysin, sain lopulta resetattua aiempaan versioon ja mergettyä
30.05.2016 | 2h | Korjattu uhattujen ruutujen selvitystä ja laitettu liikkuminen hyödyntämään tätä tietoa
31.05.2016 | 4h | Luotu looppi, joka palauttaa tilanteen siirtoa edeltäneeseen tilanteeseen, jossa shakattua kuningasta ei pelasteta
01.06.2016 | 2h | Nyt tilanne oikeasti palautuu siirtoa edeltäneeseen, eikä siirto jää voimaan. Shakkaus toimii!
05.06.2016 | 5h | Matitus lisätty, aloitettu ohestalyönnin toteutus
06.06.2016 | 3h | Javadocit lisätty, GUIn kertaaminen aloitettu.
07.06.2016 | 4h | GUIn aloitus, yritys saada GUI-builderillä tehtyä tarvittavat JFramet
08.06.2016 | 5h | GUI:N tekoa ilman GUI-builderia, yritys saada kuvat latautumaan
09.06.2016 | 5h | Kuvat latautuvat oikein, GameWindow eli peli-ikkunassa nappulat liikkuvat vuorotellen ja voivat syödä vastustajan nappuloita. Mahdolliset siirrot näkyvät punaisella pohjalla(ei huomioi shakkia).
11.06.2016 | 3h | GUIhin pääikkuna MainFrame, joka tuntee peli-ikkunan ja menee näkymättömäksi painettaessa new game ja laittaa peli-ikkunan näkyväksi.
12.06.2016 | 6h | GUI-luokka on runnable ja runnatessa luo mainFramen ja peli-ikkunan, sekä asettaa MainFramen näkyväksi. Shakkaus toimii, samoin matitus. Pelin loputtua pomppaa esiin pelaataanko uudellen/lopeta ikkuna.
13.06.2016 | 5h | Eriytetty nappuloiden tietosisältö niiden piirtämisestä ja liikkumislogiikasta. Korjattu suurin osa ilmenneistä bugeista, mutta peli väittää mattia myös kun pelaaja voi syödä uhkaavan nappulan.
14.06.2016 | 5h | Korjattu pelin logiikka jälleen toimimaan, siirretty luokan ChessBoardListener sisältämä syötteen käsittely omaan luokaan GUILogic. Lisätty javadocceja ja korjattu kaikki checkstyle-virheet.
15.06.2016 | 4h | Lisätty testejä huonosti katetuille luokille, peli ei ota vastaan syötteitä pelin loputtua. Refaktoroitu guita vähentäen yhteyksiä. ChessBoardCopier luokka luotu vähentämään ChessBoardin vastuita.
16.06.2016 | 7h | Päivitetty luokkakaavio, luotu kaksi sekvenssikaaviota (PS.websequencediagrams.com on huijaussivusto, joka pilaa tekemäsi kaaviot tallennusvaiheessa, jos et maksa premiumista).
20.06.2016 | 4h | Yritetty korjata koodikatselmoinnissa ilmennyttä bugia, jossa monimutkaisessa tilanteessa kuninkaan shakkaaminen suojatulla nappulalla aiheutti matin.
21.06.2016 | 6h | Jokaiselle nappulalle tunnistekoodi, nappuloiden tullessa syödyksi jäävät ne pelaajalle, mutta merkitäään syödyksi eikä vaikuta peliin. Korjattu katselmoinnissa ilmennyt bugi sekä refaktoroinnista seurannut bugi, jossa syödyt nappulat jäivät näkymättöminä laudalle.
22.06.2016 | 8h | Jaettu Game-luokka kahteen osaan: GameSituation vastaa tietosisällöstä ja CheckingLogic shakkauslogiikasta. Siirretty entiteetit domainiin. Luotu Jar-tiedosto ja kokeiltu sen toimiminen. Päivitetty luokkakaavio ajan tasalle. Päivitetty Pit- ja Checkstyleraportti ja korjattu niissä esiintyneet virheet.
23.06.2016 | 1h | Päivitetty sekvenssikaaviot ajan tasalle, päivitetty README, Javadoc ja tuntikirjanpito.
