# Übungsprojekt: CoWorkingSpace

CoWorkingSpace ist ein tool um Coworkingspaces zu buchen, welches mit Quarkus entwickelt wird. Dieses Projekt wurde für die LB2 des ÜkS 223 erstllt.

# Setup

1. Erstelle eine Kopie (fork) von diesem Projekt.
2. Stelle sicher, dass Docker installiert ist und läuft.
3. Stelle sicher, dass Visual Studio Code und die Erweiterung Remote Container installiert ist.
4. Klone (clone) das Projekt lokal, um damit arbeiten zu können.
5. Öffne das Projekt mit Visual Studio Code.
6. Stelle sicher, dass du die Quarkus extension heruntergeladen hast.
7. Öffne das Projekt im Entwicklungscontainer.
8. Starte das Projekt mit dem Kommando Quarkus: Debug current Quarkus Project
9. Schaue die API auf http://localhost:8080/q/swagger-ui/ an. 


## Datenbank

Die Daten werden in einer PostgreSQL-Datenbank gespeichert. In der Entwicklungsumgebung wird diese in der [docker-compose-yml](./.devcontainer/docker-compose.yml) konfiguriert.

### Datenbankadministration

Über http://localhost:5050 ist PgAdmin4 erreichbar. Damit lässt sich die Datenbank komfortabel verwalten. Der Benutzername lautet `zli@example.com` und das Passwort `zli*123`. Die Verbindung zur PostgreSQL-Datenbank muss zuerst mit folgenden Daten konfiguriert werden:
 - Host name/address: `db`
 - Port: `5432`
 - Maintenance database: `postgres`
 - Username: `postgres`
 - Password: `postgres`

## Automatische Tests

Die automatischen Tests können mit `./mvnw quarkus:test` ausgeführt werden. Für die automatischen Tests wird nicht die PostgreSQL-Datenbank verwendet, sondern eine H2-Datenbank, welche sich im Arbeitsspeicher während der Ausführung befindet. Die Testdaten werden bei start der Applikation eingelesen und getestet

## Anderungen 

Während der umsetzung ist mir aufgefallen, dass ich einige wichtigen Features oder Teile nicht beachtet habe in der Planugnsphase. 
Hinzugegekommen ist: 
 - TimeframeEnume.java (damit ich ordentlich die halb oder ganzen Tage labeln und abspeichern kann)
 - Im Member.java noch die Entries verknüpft
 - Im Workspace.java noch die Entries verknüpft
 - Die Kategorie booked in Workplace.java wurde zu Status geändert und ist jetzt ein TimeframeEnum anstatt ein Boolean
 - Im Entry.java sind die Kategorien status und timeframe hinzugekommen
 

