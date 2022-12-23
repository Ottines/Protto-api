# Protto-api backend

## Dependances
Cette application dépend de maven, java 17.

Elle est dévellopé grâce à SpringBoot


## Lancement de l'application

### Avec mvnw
Ce dépôt dispose d'un fichier nommé mvnw.cmd, il est possible depuis un terminal d'executer ce fichier.
Pour construire l'application depuis cette commande dans un terminal, on peut tapper :
./mvnw.cmd clean install
Pour ignorer les tests on peut ajouter "-DskipTests" à la suite de la commande.
Pour l'executer rendez vous dans le dossier target formé puis tapper dans le terminal : java -jar application-VERSION-APP.jar

### Avec maven
Il est aussi possible d'utiliser votre maven préalablement installé.
Pour construire l'application on peut tapper : mvn clean install
Pour ignorer les tests on peut ajouter "-DskipTests" à la suite de la commande.
Pour l'executer rendez vous dans le dossier target formé puis tapper dans le terminal : java -jar application-VERSION-APP.jar

