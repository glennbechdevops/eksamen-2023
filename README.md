# Eksamen PGR301 2023

## Oppgave #1 DevOps Prinsipper

### Det Første Prinsippet - Flow

### Det Andre Prinsippet - Feedback

Oppgave: "Tenk deg at du har implementert en ny funksjonalitet i en applikasjon du jobber med. Beskriv hvordan du vil
etablere og bruke teknikker vi har lært frea "feedback" for å sikre at den nye funksjonaliteten møter brukernes behov.
Behovene Drøft hvordan feedback bidrar til kontinuerlig forbedring og hvordan de kan integreres i ulike stadier av
utviklingslivssyklusen."

### Det Tredje Prinsippet - Kontinuerlig forbedring

# Oppgave #2 Kjell's Python kode

I en ikke så fjern fortid, på en arbeidsplass dynket i det blå lyset fra uendelige kode-editorer, jobbet en mann ved
navn Kjell. Kjell var en kløpper til å kode Python, og kunne danse over tastaturet med en slik nåde at selv rubber
ducks (du vet, de som Devs snakker til når de er fastlåst) begynte å følge med.

Hans mesterverk var kode som kunne oppdage om mennesker på bilder har på seg ansiktsmaske, for eksempel på vei inn på en  arbeidsplass
der det er påkrevet. Dette laget  ved hjelp av AWS Rekognition. Men så, på en helt vanlig tirsdag, etter å ha inntatt sin
tredje espresso, fikk Kjell et tilbud han ikke kunne avslå - å bli med i en startup, HiVis Holdings, som holdt på med
mistenkelig lik teknolgi.

Nå er det din tur til å ta over det tvilsomme arbeidet, og siden du ånder og lever for kontinuerlig forbedring må du
rydde opp i dette rotet av kode før noen kan tenke på å gjøre noe nytt med applikasjonen, og ta opp konkurransen med 
HiVis Holding. 

## GitHub actions workflow 

Koden er skrevet som en AWS SAM applikasjon, og ligger i mappen "fra_kjell" i dette repoet.

* Hvis du sjekker ut koden i ditt Cloud9-miljø eller på en lokal maskin og har AWS SAM installert, kan du bygge og deployere koden til AWS. Det anbefales å teste dette før du fortsetter.
* Du skal opprette en GitHub Actions-arbeidsflyt. For hver commit til hovedgrenen (main branch), skal arbeidsflyten bygge og deployere Lambda-funksjonen.
* Som respons på en sammenslåingsforespørsel (Merge Request) eller en push til en annen gren enn hovedgrenen, skal applikasjonen kun kompileres og bygges, uten deployment.

Oppgave 

* Du skal opprette en GitHub Actions-arbeidsflyt. For hver commit til hovedgrenen (main branch), skal arbeidsflyten bygge og deployere Lambda-funksjonen.
* Som respons på en sammenslåingsforespørsel (Merge Request) eller en push til en annen gren enn hovedgrenen, skal applikasjonen kun kompileres og bygges, uten deployment.
* Sensor vil lage en fork av ditt repository. Forklar hva sensor må gjør for å få GitHub Actions workflow til å kjøre i sin egen GitHub-konto.

### Docker container 

Python er ikke et veldig etablert språk i selskapet ditt, så du vil gjerne at også utviklere som ikke har et Pythonmiljø 
installert på sin maskin skal kunne bruke koden. 

Oppgave 

* Lag en Dockerfile for python koden. Sensor skal kunne gjøre følgende kommando 

```shell
docker build -t kjell . 
docker run kjell -e bucket=mybucker -e folder=myfolder 
```
Og får se analyse av bilder i gitt bucket og folder, med kun Docker installert på sin maskin 

### Docker og ECR 

Oppgave 

* Utvid GitHub actions workflow slik at hver commit på main publiserer en et nytt Container image til et ECR repository
* Hvilken kommando må sensor kjøre for å starte container direkte fra ditt ECR repository 

```shell
docker run .... 
```

## Terraform 

### Gjør forbedringer i kodekvalitet 

* Kjell har gjort forferdelig mange hardkodinger. Bruk terraform variabler til å gjøre infrastrukturkoden mer 
gjenbrukbar. 

Oppgave 

* Sensor skal kunne deploye infrastrukturen ved å kjøre terraform kommandoen 

```
    terraform apply --auto-approve --var prefix=glenn --var bucket=<bucket name>
```
* Beskriv hvilke endringer Sensor må gjøre i sin GitHub Actions workflow for å kjøre sin egen versjon av infrastrukturen 









