# To-Do List Management System

## Projektbeskrivning
Detta projektet är ett To-Do List Management System som kombinerar en Swing-baserad grafisk användargränssnitt (GUI) med en HTTP-baserad backend för att hantera uppgifter. Projektet är utvecklat i Java och innehåller grundläggande funktioner som att skapa, uppdatera, visa och ta bort uppgifter.

## Funktioner
- **GUI (Swing)**:
- Lägg till, uppdatera och visa uppgifter i en lista.
- Ta bort alla uppgifter med ett knapptryck.
- Intuitivt användargränssnitt för enkel användning.

- **HTTP-server**:
- API-endpoints för att hantera uppgifter via HTTP-metoder (GET, POST, PUT, DELETE).
- Kör en HTTP-server lokalt på port 8000.

- **Objektorienterad design**:
- Användbar abstrakta klasser och gränssnitt.
- Uppgiftshantering sker via en TaskManager-implementering.

  ## Teknisk arkitektur
  - **Frontend**: Swing GUI
  - **Backend**: HTTP-server byggd med `HttpServer` från Java SE.
  - **Databas**: Uppgifterna lagras i minnet med hjälp av en `ArrayList`.
 
    ## Installation och körning
    ### Förkrav
    - Java JDK 17 eller senare.
   
      ## Starta applikationen:
      1. Klona projekt från GitHub
        ```bash
        git clone https://github.com/iara Alrawi/Avancerad-Java-Iara-Alrawi-slutprojekt
        cd Avancerad-Java-Iara-Alrawi-sluprojekt  
