### API Usage Guide / Guida all'uso dell'API

## 1. **Create a New Employee with Photo**  
**Endpoint:** `POST /api/dipendenti/create-with-photo`

**Request (Form-Data):**
```
Key: file        -> Value: (Upload employee photo)
Key: dipendente  -> Value: {
    "name": "John",
    "surname": "Doe",
    "email": "john.doe@example.com",
    "phone": "1234567890"
}
```

**Response:**
```json
{
    "id": 1,
    "name": "John",
    "surname": "Doe",
    "email": "john.doe@example.com",
    "phone": "1234567890",
    "photoUrl": "https://res.cloudinary.com/..."
}
```

---

## 2. **Get Employee by ID**  
**Endpoint:** `GET /api/dipendenti/{id}`

**Example:** `GET /api/dipendenti/1`

**Response:**
```json
{
    "id": 1,
    "name": "John",
    "surname": "Doe",
    "email": "john.doe@example.com",
    "phone": "1234567890",
    "photoUrl": "https://res.cloudinary.com/..."
}
```

---

## 3. **Delete Employee by ID**  
**Endpoint:** `DELETE /api/dipendenti/{id}`

**Example:** `DELETE /api/dipendenti/1`

**Response:**
```json
{
    "message": "Employee deleted successfully."
}
```

⚠️ *An employee cannot be deleted if they have active reservations.*

---

## 4. **Create a New Reservation**  
**Endpoint:** `POST /api/prenotazioni`

**Request:**
```json
{
    "dipendenteId": 1,
    "viaggioId": 5,
    "data": "2025-03-15",
    "note": "Window seat preference."
}
```

**Response:**
```json
{
    "id": 10,
    "dipendenteId": 1,
    "viaggioId": 5,
    "data": "2025-03-15",
    "note": "Window seat preference."
}
```

---

## 5. **Check if Employee Has a Reservation on a Specific Date**  
**Endpoint:** `GET /api/prenotazioni/exists?dipendenteId=1&data=2025-03-15`

**Response:**
```json
{
    "exists": true
}
```

---

## 6. **Delete a Reservation by ID**  
**Endpoint:** `DELETE /api/prenotazioni/{id}`

**Example:** `DELETE /api/prenotazioni/10`

**Response:**
```json
{
    "message": "Reservation deleted successfully."
}
```

---

## 7. **Upload Employee Photo (Separate Endpoint)**  
**Endpoint:** `POST /api/dipendenti/{id}/upload-photo`

**Request (Form-Data):**
```
Key: file -> Value: (Upload employee photo)
```

**Response:**
```json
{
    "message": "Photo uploaded successfully.",
    "photoUrl": "https://res.cloudinary.com/..."
}
```

---

### Notes:
- All responses are **in JSON format**.
- Ensure that **valid IDs** are provided for employees and trips.
- If an employee **already has a reservation on a given date**, creating a new reservation for that date will fail.
- Use **Postman** or any HTTP client to test the API.

---

# 🇮🇹 Guida all'uso dell'API

## 1. **Creare un nuovo dipendente con foto**  
**Endpoint:** `POST /api/dipendenti/create-with-photo`

**Richiesta (Form-Data):**
```
Key: file        -> Valore: (Carica foto dipendente)
Key: dipendente  -> Valore: {
    "name": "John",
    "surname": "Doe",
    "email": "john.doe@example.com",
    "phone": "1234567890"
}
```

**Risposta:**
```json
{
    "id": 1,
    "name": "John",
    "surname": "Doe",
    "email": "john.doe@example.com",
    "phone": "1234567890",
    "photoUrl": "https://res.cloudinary.com/..."
}
```

---

## 2. **Ottenere un dipendente per ID**  
**Endpoint:** `GET /api/dipendenti/{id}`

**Esempio:** `GET /api/dipendenti/1`

**Risposta:**
```json
{
    "id": 1,
    "name": "John",
    "surname": "Doe",
    "email": "john.doe@example.com",
    "phone": "1234567890",
    "photoUrl": "https://res.cloudinary.com/..."
}
```

---

## 3. **Eliminare un dipendente per ID**  
**Endpoint:** `DELETE /api/dipendenti/{id}`

**Esempio:** `DELETE /api/dipendenti/1`

**Risposta:**
```json
{
    "message": "Dipendente eliminato con successo."
}
```

⚠️ *Un dipendente non può essere eliminato se ha prenotazioni attive.*

---

## 4. **Creare una nuova prenotazione**  
**Endpoint:** `POST /api/prenotazioni`

**Richiesta:**
```json
{
    "dipendenteId": 1,
    "viaggioId": 5,
    "data": "2025-03-15",
    "note": "Preferenza per posto finestrino."
}
```

**Risposta:**
```json
{
    "id": 10,
    "dipendenteId": 1,
    "viaggioId": 5,
    "data": "2025-03-15",
    "note": "Preferenza per posto finestrino."
}
```

---

## 5. **Verificare se un dipendente ha una prenotazione in una data specifica**  
**Endpoint:** `GET /api/prenotazioni/exists?dipendenteId=1&data=2025-03-15`

**Risposta:**
```json
{
    "exists": true
}
```

---

## 6. **Eliminare una prenotazione per ID**  
**Endpoint:** `DELETE /api/prenotazioni/{id}`

**Esempio:** `DELETE /api/prenotazioni/10`

**Risposta:**
```json
{
    "message": "Prenotazione eliminata con successo."
}
```

---

Se hai bisogno di modifiche, fammi sapere! 😉

