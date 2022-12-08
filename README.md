# Übungsprojekt: CoWorkingSpace

CoWorkingSpace ist ein tool um Coworkingspaces zu buchen, welches mit Quarkus entwickelt wird. Dieses Projekt wurde für die LB2 des ÜkS 223 erstllt.


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

Die automatischen Tests können mit `./mvnw quarkus:test` ausgeführt werden. Für die automatischen Tests wird nicht die PostgreSQL-Datenbank verwendet, sondern eine H2-Datenbank, welche sich im Arbeitsspeicher während der Ausführung befindet.

## Anderungen 

Während der umsetzung ist mir aufgefallen, dass ich einige wichtigen Features oder Teile nicht beachtet habe in der Planugnsphase. 
Hinzugegekommen ist: 
 - TimeframeEnume.java (damit ich ordentlich die halb oder ganzen Tage labeln und abspeichern kann)
 - Im Member.java noch die Entries verknüpft
 - Im Workspace.java noch die Entries verknüpft
 - Die Kategorie booked in Workplace.java wurde zu Status geändert und ist jetzt ein TimeframeEnum anstatt ein Boolean
 - Im Entry.java sind die Kategorien status und timeframe hinzugekommen
 

