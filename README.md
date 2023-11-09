# Eksamen PGR301 2023


# Krav til leveranse


* Det er ikke krav til at noe infrastruktur skal kjøre, eller at en applikasjon skal kjøre når du leverer oppgaven. 
  Sensor vil lage lage en fork av ditt repository og lage all infrastruktur selv ved å bruke infrastrukturkode, workflows og dine instruksjoner 


# Spesielle hensyn relatert til Cloud 9 

* Diskplass 
* Rettigheter

# Oppgavebeskrivelse 

I et pulserende teknologisamfunn i Grünerløkka, Oslo, har en livlig oppstart ved navn 'VerneVokterne' begynt å utmeisle
sitt eget nisjeområde i helsesektoren. De utvikler banebrytende bildebehandlingsprogramvare designet for å sikre at
helsepersonell alltid bruker personlig verneutstyr (PPE). Med en pasjon for innovasjon og et brennende ønske om å
forbedre arbeidssikkerheten, samler 'VerneVokterne' et team av skarpe utviklere, engasjerte designere og visjonære
produktledere.

Som nyansatt har du fått den utfordrende oppgaven å ta over etter "Kjell" som ikke lenger jobber i selskapet

![Logo](img/logo.png "Assignment logo")

# Litt om AWS Rekognition

I denne oppgaven skal dere bli  kjent med en ny AWS tjeneste

AWS Rekognition er en tjeneste fra Amazon Web Services (AWS) som tilbyr avansert bilde- og videoanalyse ved hjelp av
maskinlæringsteknologi. Den har en rekke funksjoner for å gjenkjenne og analysere ulike elementer i bilder og videoer,
inkludert ansiktsgjenkjenning, objektgjenkjenning, tekstgjenkjenning og mer.

AWS Rekognition kan brukes til å identifisere om personer på bilder eller i videoer bruker riktig personlig beskyttelsesutstyr som hjelmer, vernebriller,
hansker, og verneklær. Dette kan være spesielt nyttig i situasjoner der det er viktig å sikre at arbeidere eller
besøkende følger sikkerhetskravene, for eksempel i byggebransjen, industrielle anlegg eller helsevesenet.

Tjenesten kan også tilpasse seg ulike bransjer og bruksområder ved å tillate brukerne å lage 
egendefinerte modeller basert på sine egne datasett og krav.

For å bruke AWS Rekognition for PPE-deteksjon, laster du enkelt opp bilder eller videoer til tjenesten, og den vil
deretter analysere innholdet og gi deg informasjon om hvorvidt PPE er tilstede og eventuelt gi posisjonsdata for hvor
PPE er funnet. 

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

* I ditt Cloud9-miljø, eller på din egen maskin med AWS SAM installert, kan du bygge og deploye koden til AWS.
* Det anbefales å teste dette før du fortsetter.

Advarsel! Se opp for hardkoding ! 

Oppgave

* Du skal opprette en GitHub Actions-arbeidsflyt for SAM applikasjonen. For hver push til main branch, skal
  arbeidsflyten bygge og deploye Lambda-funksjonen.
* Som respons på en Merge Request, eller en push til en annen branch en main skal applikasjonen kun kompileres og
  bygges. Altså ingen deployment.
* Sensor vil lage en fork av ditt repository. Forklar hva sensor må gjør for å få GitHub Actions workflow til å kjøre i
  sin egen GitHub-konto.

## Docker container

Python er ikke et veldig etablert språk i selskapet ditt, så du vil gjerne at også utviklere som ikke har et Python
installert på sin maskin skal kunne bruke koden.

Oppgave

Lag en Dockerfile for python koden. Sensor skal kunne gjøre følgende kommando for å bygge og kjøre koden.
Du må fjerne hardkoding av bucketnavn i python koden, slik at vi kan sende verdien "BUCKET_NAME" inn som en
miljøvariabel.

Dockerfilen skal lages i mappen /kjell/hello_world

```shell
docker build -t kjellpy . 
docker run -e AWS_ACCESS_KEY_ID=XXX -e AWS_SECRET_ACCESS_KEY=YYY -e BUCKET_NAME=kjellsimagebucket kjellpy
```

Det ligger noen hint i app.py for hvordan du kan lage en Dockerfile

## Overgang til Java og Spring boot

Du innser raskt at Python ikke er veien videre for et konkurransedyktig produkt og har laget et skall av en
Java-applikasjon som ligger i dette repoet. Applikasjonen er en Spring Boot applikasjon, som eksponerer et enkeltpunk

```http://<host>:<port>/scan-ppe?bucketName=<bucketnavn>``` 

Koden vil sjekke alle bilder i oppgitt bucket, og se om bildene inneholder mennesker med- eller uten verneutstyr. 

Leg en egen workflow fil for Java/Sprinb-Boot applikasjonen.

* Test java-applikasjonen lokalt i ditt cloud9 miljø ved å stå i rotmappen til ditt repository, og kjøre kommandoen ```mvn spring-boot:run```,
* Du kan teste applikasjonen i en terminal med ```curl localhost:8080/scan-ppe?bucketName=kjellsimagebucket``` og se på responsen

Oppgave

* Lag en Dockerfile for Java-appliksjonen. Du skal lage en "multi stage" Dockerfile som både kompilerer og kjører applikasjonen. 

Sensor skal kunne kjøre følgende kommandoer for å teste applikajonen din 

```shell
docker build -t ppe . 
docker run -p 8080:8080 -e AWS_ACCESS_KEY_ID=XXX -e AWS_SECRET_ACCESS_KEY=YYY -e BUCKET_NAME=kjellsimagebucket ppe
```

* Lag en GitHub actions workflow som ved hver push til main branch lager og publiserer en et nytt Container image til et 
  ECR repository. Du må selv lage et ECR repository i AWS miljøet, du trenger ikke automatisere prosessen med å lage
  dette. Container image skal ha en tag som er lik commit hash i Git. For eksempel; glenn-ppe:b2572585e. 
* Lag en ny Workflow fil, ikke gjenbruk den du lagde for Pythonkoden.

```shell
docker run .... 
```

## Terraform, AWS Apprunner og Infrastruktur som kode

Se på koden som ligger i infra katalogen, den inneholder kun en app_runner_service og en IAM rolle som gjør denne i
stand til å gjøre API kall mot AWS Rekognition.

Oppgave

* Fjern hardkodingen av service_name, slik at service_name kan settes lik ditt kandidatnummer
* Utvid din GitHub Actions workflow til å kjøre terraformkoden
* På hver push til main, skal både Docker container image lages, publiseres til ECR, og kjøre Terraform. 

Oppgave

* Sensor skal kunne deploye infrastrukturen ved å kjøre Terraform kommandoen

```
    terraform init 
    terraform apply --auto-approve --var prefix=glenn --var bucket=<bucket name>
```

* Beskriv hvilke endringer Sensor må gjøre i sin GitHub Actions workflow for å kjøre sin egen versjon av infrastrukturen

## Feedback

Vi har jobbet med å gjøre metrikker og målepunkter for applikasjonen vår synlige, og vi har også laget alarmer og Dashboards basert på
metrikkene.

Oppgave

* Gjør nødvendige endringer i Java-applikasjonen til å bruke Micrometer rammeverket for Metrics
* Lag en eller flere nye endepunkter for tjenesten. Her får dere stor kreativ frihet, utforsk tjenesten Rekognition og se om dere kan  
  lage ny og relevant funksjonalitet. 
* Legg deretter til kode som leverer Metrics til AWS CloudWatch. 
* Dere kan selv velge hvordan dere implementerer målepunktene, men implementasjonen må være relevant og gi mening 

Dere skal skrive minst 

* En metrikk som teller 
* En metrik som måler tid 

# Drøfteoppgaver

### Det Første Prinsippet - Flow

### Det Andre Prinsippet - Feedback

Oppgave: "Tenk deg at du har implementert en ny funksjonalitet i en applikasjon du jobber med. Beskriv hvordan du vil
etablere og bruke teknikker vi har lært frea "feedback" for å sikre at den nye funksjonaliteten møter brukernes behov.
Behovene Drøft hvordan feedback bidrar til kontinuerlig forbedring og hvordan de kan integreres i ulike stadier av
utviklingslivssyklusen."

### Det Tredje Prinsippet - Kontinuerlig forbedring
