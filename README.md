
# To-Do List Application

## Projektbeskrivning

Denna applikation är en enkel To-Do List-system som består av en backend (server) och en frontend (GUI). Backend hanterar API-anrop (GET, POST, PUT, DELETE) och lagrar uppgifter i minnet. Frontend-applikationen, byggd med Java Swing, tillåter användaren att interagera med systemet genom att visa, lägga till, redigera och ta bort uppgifter.

Projektet är uppdelat i två delar:
1. **Backend (API)**: Hanterar CRUD-operationer för uppgifter (Create, Read, Update, Delete) med hjälp av en enkel HTTP-server.
2. **Frontend (Swing)**: En GUI-applikation där användare kan lägga till, uppdatera, visa och ta bort uppgifter.

## Funktioner

- **Backend:**
  - GET /tasks – Hämta alla uppgifter.
  - POST /tasks – Skapa en ny uppgift.
  - PUT /tasks/{id} – Uppdatera en befintlig uppgift.
  - DELETE /tasks/{id} – Ta bort en specifik uppgift.

- **Frontend:**
  - Visa en lista med alla uppgifter.
  - Lägga till en ny uppgift.
  - Redigera en uppgift.
  - Ta bort en uppgift.

## Installation och Körning

För att köra projektet, följ dessa steg:

### Backend (Server)
1. Klona eller ladda ner projektet.
2. Se till att Java är installerat på din dator. Du kan ladda ner det från [Java's officiella webbplats](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
3. Öppna terminalen och navigera till mappen som innehåller backend-koden.
4. Kompilera servern:
    ```bash
    javac SimpleHttpServer.java
    ```
5. Kör servern:
    ```bash
    java SimpleHttpServer
    ```
    Servern startar och lyssnar på `http://localhost:8000`.

### Frontend (Swing)
1. Öppna terminalen och navigera till mappen som innehåller frontend-koden.
2. Kompilera frontend-koden:
    ```bash
    javac TodoListApp.java
    ```
3. Kör frontend-applikationen:
    ```bash
    java TodoListApp
    ```
    GUI-applikationen startar och du kan interagera med den genom att lägga till, uppdatera och ta bort uppgifter.

## API-anrop Exempel

Här är exempel på hur du kan använda API-anropen från frontend eller via ett HTTP-klientverktyg som Postman.

### GET /tasks
Hämta alla uppgifter:
```bash

GET http://localhost:8000/tasks

 ## Installation och körning
### Förkrav
  - Java JDK 17 eller senare.
   
 ## Starta applikationen:
 1. Klona projekt från GitHub
  ```bash
 git clone https://github.com/iara Alrawi/Avancerad-Java-Iara-Alrawi-slutprojekt
 cd Avancerad-Java-Iara-Alrawi-sluprojekt  
