# Coding Week 2023 - Telecom Nancy Flashcards

## Description

Flashcards est une application qui permet l'étude de cartes via la méthode Active Recall, très efficace pour la mémorisation. L'application supporte notamment les fonctionnalitées suivantes :
- Gestions de cartes
- Gestions de piles de cartes
- Etude de piles
- Paramètrage de l'étude
- Consulter les statistiques
- Importer/exporter des piles

## Lancement

Il suffit d'exécuter le .jar disponible à la racine du projet, avec par exemple la commande `java -jar Flashcards.jar`

## Utilisation

Merci de consulter la vidéo explicative à venir !

## Compilation

Configuration Gradle inspirée du projet MOCI DP de M. Charoy :

"Ce projet est auto-suffisant dans le sans où il propose d'utiliser l'outil Gradle (https://gradle.org/) pour construire l'application fournie.
Un simple `./gradlew run` dans un terminal devrait vous télécharger l'outil gradle, télécharger les dépendances nécessaires (JavaFX en autre), lancer la compilation et exécuter le programme. 

Il se peut que vous rencontriez des problèmes. Voici quelques solutions :
- il faut que le script `./gradlew` soit exécutable (un simple `chmod a+x ./gradlew` devrait faire l'affaire)
- Gradle ne supportant pas encore Java 19, il faut utiliser une version 17 ou 18 du Java Development Kit (JDK) (utiliser la commande `java --version` ou un `./gradlew --version` pour vérifier votre version actuelle du JDK.)

Cette configuration peut également être importer dans IntelliJ pour créer un projet fonctionnelle à partir de celle-ci. Pour cela, lors de la création de votre projet IntelliJ, il faut sélectionner le fichier `build.gradle` et non pas uniquement le répertoire du projet."

## Tests

Pour  lancer les tests dans le dossier test/, lancer la commande `./gradlew cleanTest test` (méthode inspirée de celle des TP java de M. Oster).

Il a été tenté d'intégrer la librairie TestFX pour les tests, mais cela a mené à des problemes d'intégration avec gradle et de dépendances. Cela a donc été abandonné (malgré les heures passées dessus !), et les tests sur l'interface graphique sont effectués manuellement.

## Remerciements

Merci à notre cliente pour ses retours et aux contributeurs du projet pour leur dévouement durant 4 jours.