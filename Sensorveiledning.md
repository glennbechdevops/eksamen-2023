# Kjells python-kode

## GitHub Actions Workflow

Sensor følger kandidatens beskrivelse for hvordan du skal gå frem for å få din fork av repository til å  kjøre workflow korrekt.
Se forøvrig løsningsforslag. Det viktige er at kandidaten lar workflow starte på push-hendelser. 
 
```shell
on:
  push:
```

Og på deploy steget sjekker branch, og bare start på main

```shell
  - name: Deploy
    if: github.ref == 'refs/heads/main'
    working-directory: ./kjell
```

Sensor kan teste dette ved å lage og pushe en vilkårlig branch til repository

### Høy oppnåelse

* Kandidaten har klart å fjerne hardkoding av Bucketnavn fra template.yml, og sender det inn som parameter
  fra ``sam deploy`` kommandonen med ```parameter-overrides```

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

Kandidaten endrer Pythonkoden slik at BUCKET_NAME leses fra miljøvariabel.

## Migrering til Java og Spring boot

* Følg studentens instruksjoner - hvordan få dette til å fungere i sensor sitt miljø
* Lag en commit mot main branch, se at nytt container image blir laget i AWS ECR
* Følg studenten sin instruksjon på hvordan starte en container, og se at du får et korrekt resultat ved å gjøre en GET
  request på http://localhost:8080/ppe-scan


