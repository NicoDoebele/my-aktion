# My-Aktion

## Usage

### My-Aktion

Start the service "my-aktion".

```
cd my-aktion
gradle bootRun
```

Web URLs to use with this service:

http://localhost:8080/h2-console
http://localhost:8080/swagger-ui/index.html#

### My-Aktion-Monitor

Start the microservice "my-aktion-monitor".

```
cd my-aktion-monitor
gradle bootRun
```

Web  URLs to use with this microservice:

http://localhost:8081/monitor.html

### My-Aktion-Bank

Start redis to enable communication between the services.

Docker Desktop needs to be running.

Pull redis if it is not already existant.

```
docker pull redis
```

Start redis.

```
docker run --name my-aktion-redis -p 6379:6379 -d redis
```

Start the microservice "my-aktion-bank".

```
cd my-aktion-bank
gradle bootRun
```


## Available API Calls

```
GET /campaigns
```

Returns the list of all campaigns.

```
POST /campaigns
```

Receives the data of a campaign in the request body and adds it to the database.

```
GET /campaigns/{id}
```

Returns the campaign with the given id.

```
PUT /campaigns/{id}
```

Updates the campaign with the given id with the data from the request body.

```
DELETE /campaigns/{id}
```

Deletes the campaign with the given id.

```
POST /campaigns/{id}/donations
```

Adds a donation to the campaign with the given id.

```
GET /campaigns/{id}/donations
```

Returns the list of the donations of the campaign with the given id.