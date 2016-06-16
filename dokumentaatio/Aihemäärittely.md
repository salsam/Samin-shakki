#Aihemäärittely

###Aihe:
Shakki, jossa kaikki shakin yleiset säännöt voimassa mukaanlukien linnoittautuminen, ohestalyönti ja sotilaan korotus laudan toiseen päätyyn päästessä. Vain kahden ihmispelaajan välinen peli mahdollista.

###Käyttäjät: 
  * Pelaajat

###Pelaajan toiminnot:
- Pelin aloitus valitsemalla aloita peli

- Pelitilanteen näkeminen ruudukkona
  * Ruudukko vaalean-tummanharmaa, nappulat toisella pelaajista valkoiset, toisella mustat

- Nappuloiden siirtäminen
  1. Nappulan valitseminen
    * Jos nappula on pelaajan, valinta onnistuu. Muutoin ilmoitetaan pelaajalle virheestä.
    * Jos ruutu on tyhjä, ilmoitetaan myös silloin pelaajalle virheestä.
  2. Ruudun valitseminen pelaajalle näytetyistä vaihtoehdoista
    * Vaihtoehdot riippuvat nappulan liikkumistavasta, nappulan sijainnista ja kuningas ei voi siirtyä uhattuun ruutuun
  3. Siirtokehotuksen lähettäminen
     * Jos pelaajan kuningas on uhattu, tarkastetaan muuttaako siirto tilannettaa
       * Jos ei muuta, palataan takaisin aiempaan tilanteeseen ja ilmoitetaan siirron olevan laiton
       * Muutoin suoritetaan siirto
     * Jos siirto on laiton, ilmoitetaan käyttäjälle. Muutoin siirrytään.
  4. Jos ruudussa on vastustajan nappula, poistetaan se laudalta

- Pelin lopetus
  * Vuoron aluksi katsotaan onko pelaajan kuningas uhattu, jos on täytyy pelaajan korjata tilanne tällä vuorolla
  * Vuoron aluksi katsotaan myös onko pelaajalla yhtään laillista siirtoa, jos ei niin peli loppuu
  * Jos peli loppuu ja kuningas uhattu, on pelaaja hävinnyt ja vastustaja voittanut, muutoin tasapeli (patti)
  * Ilmoitetaan pelin lopputulos ja kysytään pelataanko uudelleen

- Päätyynastipäässeen sotilaan korottaminen kuningattareksi (Oikeasti nappulan saa valita, mutta 99% tapauksista valitaan kuningatar ja lopuissa valinnallaa ei väliä)
  1. Kun sotilas siirtyy viimeiseen ruutuun, korvataan sotilas kuningattarella

- Pelin lopetus

- Pelin uudelleenaloitus
  

