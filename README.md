# Eksamen PGR301 2023

I et pulserende teknologisamfunn i Grünerløkka, Oslo, har en livlig oppstart ved navn 'VerneVokterne' begynt å utmeisle
sitt eget nisjeområde i helsesektoren. De utvikler banebrytende bildebehandlingsprogramvare designet for å sikre at
helsepersonell alltid bruker personlig verneutstyr (PPE). Med en pasjon for innovasjon og et brennende ønske om å
forbedre arbeidssikkerheten, samler 'VerneVokterne' et team av skarpe utviklere, engasjerte designere og visjonære
produktledere.

Som nyansatt, har du fått den utfordrende oppgaven å ta over etter "Kjell" som ikke lenger jobber i selskapet

![Logo](img/logo.png "Assignment logo")

# Oppgave Kjell's Python kode

Kjell var en kløpper til å kode Python, og kunne danse over tastaturet med en slik nåde at selv rubber
ducks (du vet, de som Devs snakker til når de er fastlåst) begynte å følge med. Hans mesterverk var kode som kunne
oppdage om mennesker på bilder har på seg ansiktsmaske, for eksempel på vei inn på en
arbeidsplass der påkrevet.

Dette ble laget ved hjelp av AWS Rekognition. Men så, på en helt vanlig tirsdag, etter å ha inntatt sin
tredje espresso, fikk Kjell et tilbud han ikke kunne avslå - å bli med i en startup, HiVis Holdings, som holder på med
et konkurrerende produkt.

Når du ser på arbeidet til Kjell, som ligger i dette repositoryet, vil det bli klart for deg at kjell var mer opptatt av
tempo en kvalitet.

Nå er det din tur til å ta over det arbeidet av tvilsom karaketer, og siden du ånder og lever for kontinuerlig
forbedring må du rydde opp i dette rotet av kode før noen kan tenke på å gjøre noe nytt med applikasjonen,
og ta opp konkurransen med HiVis Holding.

## Oppgave GitHub actions workflow

Koden er skrevet som en AWS SAM applikasjon, og ligger i mappen "kjell" i dette repoet. Det er åpenbart an har tatt
ugangspunkt i et "Hello World" SAM prosjekt og bare brukt navnet sitt som applikasjonsnavn.

* Hvis du sjekker ut koden i ditt Cloud9-miljø eller på en lokal maskin og har AWS SAM installert, kan du bygge og
  deploye koden til AWS. Det anbefales å teste dette før du fortsetter.

Oppgave

* Du skal opprette en GitHub Actions-arbeidsflyt for SAM applikasjonen. For hver commit til main branch, skal
  arbeidsflyten
  bygge og deploye Lambda-funksjonen.
* Som respons på en Merge Request, eller en push til en annen branch en main skal applikasjonen kun kompileres og
  bygges. Altså ingen deployment.
* Sensor vil lage en fork av ditt repository. Forklar hva sensor må gjør for å få GitHub Actions workflow til å kjøre i
  sin egen GitHub-konto.

## Docker container

Python er ikke et veldig etablert språk i selskapet ditt, så du vil gjerne at også utviklere som ikke har et Pythonmiljø
installert på sin maskin skal kunne bruke koden.

Oppgave

Lag en Dockerfile for python koden. Sensor skal kunne gjøre følgende kommando for å bygge og kjøre koden.
Du må fjerne hardkoding av bucketnavn i python koden, slik at vi kan sende verdien inn som en miljøvariabel.

```shell
docker build -t kjellpy . 
docker run -e AWS_ACCESS_KEY_ID=XXX -e AWS_SECRET_ACCESS_KEY=YYY -e BUCKET_NAME=kjellsimagebucket kjellpy
```
Det ligger noen hint i app.py for hvordan Dockerfile kan lages.

### Migrering til Java og Spring boot

Du inser raskt at Python ikke er veien videre for et konkurransedyktig produkt og har laget et skall av en Java-applikasjon 
som ligger i dette repoet.

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

## Oppgave  DevOps Prinsipper

### Det Første Prinsippet - Flow

### Det Andre Prinsippet - Feedback

Oppgave: "Tenk deg at du har implementert en ny funksjonalitet i en applikasjon du jobber med. Beskriv hvordan du vil
etablere og bruke teknikker vi har lært frea "feedback" for å sikre at den nye funksjonaliteten møter brukernes behov.
Behovene Drøft hvordan feedback bidrar til kontinuerlig forbedring og hvordan de kan integreres i ulike stadier av
utviklingslivssyklusen."

### Det Tredje Prinsippet - Kontinuerlig forbedring




