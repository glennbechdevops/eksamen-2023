# Sensorveiledning 

* Et komplett løsningsforslag  https://github.com/glennbechdevops/eksamen-2023 - Medsensor eller klagesensor må legges til som collaborator 
* Vektig av de ulike oppgavene er oppgitt i Eksamensoppgaven
* Under retting av eksamne, vil hovedsensor lage et delt excelark for vurdering som automatisk vil kalkulere karakter og poengsum for kandidatene

# Oppgave 1. Kjells python-kode

## GitHub Actions Workflow

Sensor følger kandidatens beskrivelse for hvordan du skal gå frem for å få din fork av repository til å  kjøre workflow korrekt.
Se forøvrig løsningsforslag. Det viktige er at kandidaten lar workflow starte på push-hendelser. 
 
```shell
on:
  push:
```

Og på *deploy* steget sjekker branch, og bare starter dersom main branch er et som endres slik;

```shell
  - name: Deploy
    if: github.ref == 'refs/heads/main'
    working-directory: ./kjell
```

Sensor kan teste dette ved å lage og pushe en vilkårlig branch til repository på main og en feature branch.


### Høy oppnåelse

* Kandidaten har klart å fjerne hardkoding av Bucketnavn fra template.yml, og sender det inn som parameter
  fra ``sam deploy`` kommandonen med ```parameter-overrides``` Det er også Ok å å sette bucketname environment variabel
  i template.yml
  

## Python

Følgende Dockerfile vil bygge et container image som kan brukes for å kjøre Pythonapplikasjonen.  

````
FROM python:3.9-slim
COPY requirements.txt .
RUN pip3 install -r requirements.txt
COPY app.py .
ENTRYPOINT ["python3","app.py"]
````

Sensor kan teste kandidatens Dockerfile ved å skrive

```
docker build -t kjellpy . 
docker run -e AWS_ACCESS_KEY_ID=<sensor sin aws_access_key> -e AWS_SECRET_ACCESS_KEY=<sensor sin secret_access_key> -e BUCKET_NAME=<en bucket med bilder>
```

### Høy oppnåelse

* Kandidaten endrer Pythonkoden slik at BUCKET_NAME leses fra miljøvariabel
* dockerfile bør ikke inneholde vesentlig annet en som nevnt over, det kan da være at kandidaten har brukt generativ AI eller kopiert inn noe kandidaten ikke forsåtr. 

# Oppgave 2. Overgang til Java og Spring boot


## A. Dockerfile 

De fleste vil være i stand til å lage en Dockerfile, jeg forventer mye copy/paste fra 
lab-øvinger her. 

## B. Workflow 

* Følg studentens instruksjoner - hvordan få dette til å fungere i sensor sitt miljø
* En god løsning krever minimalt med endringer for å få workflow til å fungere for sensor
* Kandidaten må forstå repository secrets og informere at sensor må bruke sine egne. 
* Lag en commit mot main branch, se at nytt container image blir laget i AWS ECR, både med git commit hash og latest tag
* Følg studenten sin instruksjon på hvordan starte en container, og se at du får et korrekt resultat ved å gjøre en GET
  request på http://localhost:8080/ppe-scan

# Oppgave 3- Terraform, AWS Apprunner og Infrastruktur som kode

## A. Kodeendringer og forbedringer

### Høy oppnåelse 

* Kandidaten bør demonstrere at han/hun har forstått bruk av variabler og defaultverdier 
* Kandidaten bør legge til en "prefix" variabel eller tilsvarende for å lage alle ressursene sine med egne navn 

## B. Terraform i GitHub Actions

### Høy oppnåelse 

* State fil i S3 Backend, korrekt providerdeklarasjon 
* Kandidaten må lage en avhengighet mellom jobbene for Docker og Terraform

# Oppgave 4. Feedback

Her blir det noe subjektiv vurdering. 

### Høy oppnåelse 

* Hensikten med å utvide kodebasen er å få flere naturlige steder å legge inn måleinstrumenter. Kodevolum har ingen
  betydning, men en god besvarelse vil  legge til virkelig og nyttig funksjonalitet
* En god besvarelse registrer både tekniske, og foretningsmessig metrikker.
* En god besvarelse bør bruke minst tre ulike måleinstrumenter på en måte som gir mening



# Oppgave 5. Drøft

Her blir det litt subjektiv vurdering




