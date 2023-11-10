# Eksamen PGR301 2023

## Krav til leveransen

* Eksamensoppgaven er tilgjengelig i GitHub-repositoriet: https://github.com/glennbechdevops/eksamen_2023.
* Når du leverer inn oppgaven via WiseFlow, vennligst opprett et tekstdokument som kun inneholder en kobling til ditt
  repository.
* Vennligst bruk et tekstdokumentformat, ikke PDF, Word eller PowerPoint.
* Du skal ikke opprette en fork av dette repositoryet, men heller kopiere innholdet til et nytt repository.
* Hvis du er bekymret for plagiat fra medstudenter, kan du arbeide i et privat repository og deretter gjøre det
  offentlig tilgjengelig like før innleveringsfristen.

Når sensoren evaluerer oppgaven, vil han/hun:

* Sjekke ditt repository og gå til fanen "Actions" på GitHub for å bekrefte at Workflows faktisk fungerer som de skal.
* Vurdere drøftelsesoppgavene. Du må opprette en "Readme" for besvarelsen i ditt repository. Denne "Readme"-filen skal
  inneholde en grundig beskrivelse og drøfting av oppgavene.
* Sensoren vil opprette en "fork" (en kopi) av ditt repository og deretter kjøre GitHub Actions Workflows med sin egen
  AWS- og GitHub-bruker for å bekrefte at alt fungerer som forventet.

## Om GitHub Free Tier

- I oppgaven blir du bedt om å opprette GitHub Actions Workflows.
- Med GitHub Free Tier har du 2000 minutter med gratis byggetid per måned i private repository
- I ekstreme situasjoner, hvor du trenger mer byggetid, har du alternativet å gjøre repositoryet offentlig. Dette vil gi
  deg ubegrenset byggetid. GitHub gir ubegrenset byggetid for offentlige repoer.
- Hvis du er bekymret for at andre kan kopiere arbeidet ditt når repositoryet er offentlig, kan du opprette en ny
  GitHub-bruker med et tilfeldig navn for anonymitet.

## Spesielle hensyn knyttet til Cloud 9

- Løsning på problem med diskplassmangel - informasjon bli delt på Canvas-plattformen.
- Informasjon om rettigheter og sikkerhet i Cloud 9 vil også bli delt på Canvas

# Oppgavebeskrivelse

I et pulserende teknologisamfunn på Grünerløkka, Oslo, har en livlig oppstart ved navn 'VerneVokterne' begynt å meisle
ut sitt eget nisjeområde innenfor helsesektoren. De utvikler banebrytende programvare for bildebehandling som er
designet
for å sikre at helsepersonell alltid bruker personlig verneutstyr (PPE). Med en lidenskap for innovasjon og et sterkt
ønske om å forbedre arbeidssikkerheten, har 'VerneVokterne' samlet et team av dyktige utviklere, engasjerte designere og
visjonære produktledere.

Selskapet hadde tidligere en veldig sentral utvikler som heter Kjell. Kjell hadde en unik tilnærming til kode,
Dessverre var kvaliteten på Kjells kode, for å si det pent, "kreativ."

Som nyansatt har du blitt gitt den utfordrende oppgaven å overta etter "Kjell," som ikke lenger er en del av selskapet.

![Logo](img/logo.png "Assignment logo")

# Litt om AWS Rekognition

I denne oppgaven skal dere bli kjent med en ny AWS tjeneste

AWS Rekognition er en tjeneste fra Amazon Web Services som tilbyr avansert bilde- og videoanalyse ved hjelp av
maskinlæringsteknologi. Den har en rekke funksjoner for å gjenkjenne og analysere ulike elementer i bilder og videoer,
inkludert ansiktsgjenkjenning, objektgjenkjenning, tekstgjenkjenning og mer.

AWS Rekognition kan brukes til å identifisere om personer på bilder eller i videoer bruker riktig personlig
beskyttelsesutstyr som hjelmer, vernebriller,
hansker og verneklær. Dette kan være spesielt nyttig i situasjoner der det er viktig å sikre at arbeidere eller
besøkende følger sikkerhetskravene, for eksempel i byggebransjen, industrielle anlegg eller helsevesenet.

Tjenesten kan også tilpasse seg ulike bransjer og bruksområder ved å tillate brukerne å lage
egendefinerte modeller basert på sine egne datasett og krav.

For å bruke AWS Rekognition for PPE-deteksjon, laster du enkelt opp bilder eller videoer til tjenesten, og den vil
deretter analysere innholdet og gi deg informasjon om hvorvidt PPE er tilstede og eventuelt gi posisjonsdata for hvor
PPE er funnet.

# Oppgave 1 Kjell's Python kode

## A. SAM & GitHub actions workflow

Koden er skrevet som en AWS SAM applikasjon, og ligger i mappen "kjell" i dette repoet. Det er åpenbart at Kjell har
tatt utgangspunkt i et "Hello World" SAM prosjekt og bare brukt navnet sitt som applikasjonsnavn.

* I ditt Cloud9-miljø, eller på din egen maskin, kan du bygge og deploye koden til AWS ved å bruke ```sam cli```
* Det anbefales å teste dette før du fortsetter.

Advarsel! Se opp for hardkoding ! Du må kanskje endre noe for å få deployet selv.

### Oppgave

* Du skal opprette en GitHub Actions-arbeidsflyt for SAM applikasjonen. For hver push til main branch, skal
  arbeidsflyten bygge og deploye Lambda-funksjonen.
* Som respons på en push til en annen branch en main, skal applikasjonen kun kompileres og
  bygges.
* Sensor vil lage en fork av ditt repository. Forklar hva sensor må gjøre for å få GitHub Actions workflow til å kjøre i
  sin egen GitHub-konto.

## B. Docker container

Python er ikke et veldig etablert språk i VerneVokterene, så du vil gjerne at også utviklere som ikke har et Python
installert på sin maskin skal kunne bruke koden.

### Opppgave

Lag en Dockerfile for python koden. Du må løse og fjerne hardkoding av bucketnavn i python koden, slik at vi kan sende
verdien "BUCKET_NAME" inn som en
miljøvariabel.

Dockerfilen skal lages i mappen ```/kjell/hello_world```. Sensor skal kunne gjøre følgende kommando for å bygge et
container image og kjøre koden.

```shell
docker build -t kjellpy . 
docker run -e AWS_ACCESS_KEY_ID=XXX -e AWS_SECRET_ACCESS_KEY=YYY -e BUCKET_NAME=kjellsimagebucket kjellpy
```

Det ligger noen hint i filen app.py som vil hjelpe deg med å lage en Dockerfile

# Oppgave 2. Overgang til Java og Spring boot

Du innser raskt at Python ikke er veien videre for et konkurransedyktig produkt og har selv laget starten på en
Java-applikasjon som ligger i dette repoet. Applikasjonen er en Spring Boot applikasjon, som eksponerer et endepunkt

```http://<host>:<port>/scan-ppe?bucketName=<bucketnavn>```

Som du vil se bearbeider java-koden response fra tjenesten Rekognition litt mer en hva Python-varianten gjør.
En respons fra Java-applikasjonen kan se slik ut

```shell
{
    "bucketName": "kjellsimagebucket",
    "results": [
        {
            "fileName": "Man-in-PPE-kit-307511-pixahive.jpg",
            "violation": false,
            "personCount": 1
        },
        {
            "fileName": "almost_ppe.jpeg",
            "violation": false,
            "personCount": 1
        },
        {
            "fileName": "download.jpeg",
            "violation": true,
            "personCount": 1
        },
        {
            "fileName": "one_person_ppe.jpeg",
            "violation": false,
            "personCount": 1
        },
        {
            "fileName": "personnel-with-the-united-states-public-health-service-34a5d6-1024.jpg",
            "violation": false,
            "personCount": 2
        },
        {
            "fileName": "two_persons_one_no_ppe.jpeg",
            "violation": true,
            "personCount": 2
        }
    ]
}
```

Vi får tilbake ett element per fil som inneholder

* Filename - Navnet på filen i S3 bucketen
* violation - true hvis det er person, eller personer på bildet uten nødvendig utstyr
* personCount - hvor mange personer Rekognition fant på bildet.

### A. Dockerfile

Lag en workflow fil for Java/Spring-Boot applikasjonen.

* Test java-applikasjonen lokalt i ditt cloud9 miljø ved å stå i rotmappen til ditt repository, og kjøre
  kommandoen ```mvn spring-boot:run```,
* Du kan teste applikasjonen i en terminal med ```curl localhost:8080/scan-ppe?bucketName=kjellsimagebucket``` og se på
  responsen
* Lag en Dockerfile for Java-appliksjonen. Du skal lage en multi stage Dockerfile som både kompilerer og kjører
  applikasjonen.

Sensor vil lage en fork av ditt repository, og skal kunne kjøre følgende kommandoer for å starte en docker container

```shell
docker build -t ppe . 
docker run -p 8080:8080 -e AWS_ACCESS_KEY_ID=XXX -e AWS_SECRET_ACCESS_KEY=YYY -e BUCKET_NAME=kjellsimagebucket ppe
```

### B. GitHub Actions workflow for Container image og ECR

* Lag en GitHub actions workflow som ved hver push til main branch lager og publiserer en et nytt Container image til et
  ECR repository.
* Du må selv lage et ECR repository i AWS miljøet, du trenger ikke automatisere prosessen med å lage
  dette.
* Container image skal ha en tag som er lik commit hash i Git. For eksempel; glenn-ppe:b2572585e.
* Den siste versjonen av container image som blir pushet til ECR, skal i tillegg få en tag "latest"
* Lag en ny Workflow fil, ikke gjenbruk den du lagde for Pythonkoden.

## Oppgave 3- Terraform, AWS Apprunner og Infrastruktur som kode

Se på koden som ligger i infra katalogen, den inneholder kun en app_runner_service og en IAM roller som gjør denne i
stand til å gjøre API kall mot AWS Rekognition og lese fra S3.

### A. Kodeendringer og forbedringer

* Fjern hardkodingen av service_name, slik at du kan bruke dit kandidatnummer eller noe annet som service navn.
* Se på dokumentasjonen til aws_apprunner_service ressursen, og reduser CPU til 256, og Memory til 1024

### B. Terraform i GitHub Actions

* Utvid din GitHub Actions workflow til også å kjøre terraformkoden
* På hver push til main, skal både Terraformkoden kjøres, dette skal skje etter jobber som bygger Docker container
* Du må skrive en provider/backend konfigurasjon som lagrer en state-fil på en S3 bucket. Du kan bruke samme S3 bucket
  som vi har brukt til det formålet i øvingene.
* Beskriv hvilken Terraform kommandoer sensor må gjøre for å kunne opprette infrastrukturen i sin egen AWS konto

```
    terraform init 
    terraform apply --auto-approve --var prefix=<prefix> --var bucket=<bucket name>
```

* Beskriv også eventuelt hvilke endringer Sensor må gjøre i din GitHub Actions workflow eller kode.

## Oppgave 4. Feedback

### A. Utvid applikasjonen og legg inn "Måleinstrumenter"

I denne oppgaven får dere stor kreativ frihet i å utforske tjenesten Rekognition og se om
dere kan lage ny og relevant funksjonalitet.Lag minst et nytt endepunkt. https://aws.amazon.com/rekognition/

Nå som dere har en litt større kodebase. Gjør nødvendige endringer i Java-applikasjonen til å bruke Micrometer rammeverket for Metrics, og konfigurer
for leveranse av Metrics til CloudWatch
Dere kan detetter selv velge hvordan dere implementerer målepunkter i koden.

Med måleinstrumenter menes i denne sammenhengen ulike typer "meters" i micrometer rammeverket feks,

* Meter
* Gauge
* Timer
* LongTaskTimer
* DistributionSummary



Dere skal skrive en kort begrunnelse for hvorfor dere har valgt målepunktene dere har gjort, og valgene må gi mening.
Eksempelvis vil en en teller som øker hver gang en metode blir kalt ikke bli vurdert som en god løsning. Pass på å få med både tekniske, og foretningsmessig metrikker.

### B. CloudWatch Alarm

Lag minst en CloudWatch alarm som varsler på Epost. Derre velger selv kriteriet for når alarmen skal løses ut, men dere
må skrive en kort
begrunnelse for hvorfor dere har gjort valget.

Alarmen skal lages ved hjelp av Terraformkode. Og lages som en separat Terrafomr modul. Legg vekt på å unngå hardkoding
av verdier i modulen.

# Oppgave 4. Drøfteoppgaver

## Det Første Prinsippet - Flyt

###  A. Kontinuerlig Integrering

Forklar hva kontinuerlig integrasjon (CI) er og diskuter dens betydning i programvareutviklingsprosessen. I ditt svar,
vennligst inkluder:

- En definisjon av kontinuerlig integrasjon.
- Fordelene med å bruke CI i et utviklingsprosjekt.
- Hvordan CI kan forbedre kodekvaliteten og effektivisere utviklingsprosessen.
- Hvordan opplever en utvikler hverdagen i et prosjekt som har sterkt fokus på CI?

### B. Sammenligning av Scrum/Smidig og DevOps fra et Utviklers Perspektiv

I denne oppgaven skal du som utvikler reflektere over og sammenligne to sentrale metodikker i moderne programvareutvikling: Scrum/Smidig og DevOps. Målet er å forstå hvordan valg av metodikk kan påvirke kvaliteten og leveransetempoet i programvareutviklingsprosjekter.

### Oppgavens Krav

1. **Scrum/Smidig Metodikk:**
  - Beskriv hovedtrekkene i Scrum/Smidig metodikk og dens tilnærming til programvareutvikling.
  - Diskuter eventuelle utfordringer og styrker ved å bruke Scrum/Smidig i programvareutviklingsprosjekter.

2. **DevOps Metodikk:**
  - Forklar grunnleggende prinsipper og praksiser i DevOps, spesielt med tanke på integrasjonen av utvikling og drift.
  - Analyser hvordan DevOps kan påvirke kvaliteten og leveransetempoet i programvareutvikling.
  - Reflekter over styrker og utfordringer knyttet til bruk av DevOps i utviklingsprosjekter.

3. **Sammenligning og Kontrast:**
  - Sammenlign Scrum/Smidig og DevOps i forhold til deres påvirkning på programvarekvalitet og leveransetempo.
  - Diskuter hvilke aspekter ved hver metodikk som kan være mer fordelaktige i bestemte utviklingssituasjoner.
  
#### Forventninger til Besvarelsen

- Din analyse bør være balansert, kritisk og godt underbygget med eksempler eller teoretiske argumenter.
- Reflekter over egne erfaringer eller hypotetiske scenarier for å støtte dine argumenter og konklusjoner.

### C. Det Andre Prinsippet - Feedback

Tenk deg at du har implementert en ny funksjonalitet i en applikasjon du jobber med. Beskriv hvordan du vil
etablere og bruke teknikker vi har lært fra "feedback" for å sikre at den nye funksjonaliteten møter brukernes behov.
Behovene Drøft hvordan feedback bidrar til kontinuerlig forbedring og hvordan de kan integreres i ulike stadier av
utviklingslivssyklusen."


## LYKKE TIL OG HA DET GØY MED OPPGAVEN!