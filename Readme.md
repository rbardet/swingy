# Swingy

## Description

_Swingy_ est un jeu RPG minimaliste développé en Java dans le cadre du cursus 42. Le jeu fonctionne en mode console et en GUI, selon une architecture Model-View-Controller (MVC). La sauvegarde des héros et de leur progression s’effectue dans une base de données SQL. 

## Fonctionnalités

- **Création et sélection de héros** : Plusieurs classes de héros avec des statistiques évolutives (inspirées de Diablo 3).
- **Artefacts** : Armes, armures et casques modifiant les statistiques du héros.
- **Carte dynamique** : Taille dépendant du niveau du héros.
- **Exploration & Combat** : Déplacement sur une carte, combats simulés avec des ennemis à noms Diablo 3.
- **Système d’expérience & montée de niveau** : Gain d’expérience et artefacts selon le résultat du combat.
- **Sauvegarde et chargement** : Les héros et leur progression sont conservés dans une base de données SQL *(bonus)*.
- **Deux modes de jeu** :
  - Mode console : `$ java -jar swingy.jar console` ou `make console`
  - Mode GUI : `$ java -jar swingy.jar gui` ou `make gui`
- **Validation des inputs** : Validation robuste grâce à Hibernate Validator (javax.validation).

### Bonus

- **Sauvegarde en base SQL** : Les données des héros ne sont plus stockées dans un fichier texte mais dans une base SQL relationnelle pour davantage de fiabilité et de flexibilité.

## Structure et Règles du Projet

- **Maven** : Gestion du build et dépendances (hors javax.validation et JDBC).
- **Java LTS** : Dernière version LTS acceptée.
- **Packages personnalisés** : Respect de la convention Java.
- **Aucune dépendance externe** *hors javax.validation et bonus SQL*.

## Utilisation des commandes Makefile

```text
make all      # Compile et crée l'exécutable jar
make clean    # Nettoie le projet
make console  # Lance le jeu en mode console
make gui      # Lance le jeu en mode graphique (GUI)
make help     # Affiche l’aide des commandes
make re       # Nettoie puis compile le projet
```

## Compilation & Lancement

```bash
make all
make console        # Ou : java -jar swingy.jar console
make gui            # Ou : java -jar swingy.jar gui
```

## Dépendances

- [Hibernate Validator](https://hibernate.org/validator/) (javax.validation)
- JDBC pour la connexion à une base SQL (bonus)

## Auteur

- **rbardet**