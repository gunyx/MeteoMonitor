# MeteoMonitor :sun_behind_rain_cloud:

## Cuprins
- [Introducere](#Introducere)
- [Configurare](#Configurare)
- [Input](#Input)
- [Utilizare](#Utilizare)
- [Limbaj](#Limbaj)
- [Realizator](#Realizator)


## Introducere
    Proiectul contine o aplicatie ce furnizeaza utilizatorului detaliile despre vremea curenta sau vremea in urmatoarea zi.<br>
	Aplicatia ofera o interfata grafica prin care utilizatorul selecteaza codul de tara dorit (ex: RU/RO/DE) si zona dorita(ex:Bucharest)<br>
	In urma selectarii datelor de mai sus, utilitarul ofera urmatoarele informatii:<br>
			->temperatura<br>
			->temperatura minima<br>
			->temperatura maxima<br>
			->presiunea<br>
			->viteza vantului<br>
			->prezenta norilor<br>
			->umiditatea<br>
			->descierea vremii pe scurt+imagine reprezentativa<br>
			->data informatiilor furnizate<br>


##Configurare
	In configurarea aplicatiei, au fost furnizate doua fisiere de intrare.<br>
	Primul fisier este cel de intrare in care sunt setate datele de intrare pentru aplicatie (id_oras-oras-long-lat-cod_tara).<br>
	Cel de-al doilea fisier este cel de iesire unde sunt inregistrate log-urile cautarilor folosind aplicatia.<br>
	Aplicatia furnizeaza datele meteorologice folosind un API (openweather) ce este interogat, el furnizand un json cu toate datele necesare<br>
	Pentru folosirea API-ului, este necesara o cheie ce este trimisa de fiecare data cand se face request la informatii.<br>
	Aceasta cheie poate fii modificata cu una premium ce ofera informatii mult mai precise si un mod mai usor de interogare al serverului<br>
	
	
## Input
Format : ID Oras(\t)Nume Oras(\t)Latitudine(\t)Longitudine(\t)Cod_Tara<br>
!unde (\t) reprezinta tab!<br>
Exemplu:<br>
819827	Razvilka	55.591667	37.740833	RU<br>
2986678	Bucharest	48.491409	-2.794580	RO<br>
524901	Moscow		55.752220	37.615555	RU<br>
2973393	Tarascon	43.805828	4.660280	FR<br>
2986678	Germany		52.520008	13.404954	DE<br>
<br>
->In aplicatie este stabilit numele fisierului de intrare "in.txt". Pentru modificare, in acest fisier se adauga noile informatii<br>



## Utilizare
    Pentru utilizarea aplicatiei, utilizatorului ii este oferita o interfata grafica ce contine doua combobox-uri ce furnizeaza codul de tara si orasele din tara selectata.<br>
	Utilizatorul seteaza codul si orasul, utilitarul urmand sa afiseaze datele meteorologice din ziua folosirii aplicatiei.<br>
	Se ofera si optiunea de obtinere a detaliilor meteorologice si pentru ziua urmatoare.<br>
	
	
## Limbaj
   :memo: JAVA -> SDK 15(Scene Builder+JavaFx)


## Realizator
	Gunyx(Neacsu Gabriel)
