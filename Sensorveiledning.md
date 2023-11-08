Kjells python 

FROM python:3.9-slim
COPY requirements.txt .
RUN pip3 install -r requirements.txt
COPY app.py .
ENTRYPOINT ["python3","app.py"]

docker build -t kjellpy .
docker run -e AWS_ACCESS_KEY_ID=XXX -e AWS_SECRET_ACCESS_KEY=YYY -e BUCKET_NAME=kjell kjellpy

## Migrering til Java og Spring boot

* Følg studentens instruksjoner - hvordan få dette til å fungere i sensor sitt miljø
* Lag en commit mot main branch, se at nytt container image blir laget i AWS ECR 
* Følg studenten sin instruksjon på hvordan starte en container, og se at du får et korrekt resultat ved å gjøre en GET request på http://localhost:8080/ppe-scan
