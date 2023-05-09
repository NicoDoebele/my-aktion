# My-Aktion

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