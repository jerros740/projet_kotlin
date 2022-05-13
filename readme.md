# OUAP-4331_2022 - Projet Chuck NORRIS
## Jérémy ROS - E4 INFO

[![N|Solid](https://camo.githubusercontent.com/f1f778b8cef432673e137314f7b68afef7aff49a8595a33f23cb6c2a96bf3241/68747470733a2f2f6170692e636875636b6e6f727269732e696f2f696d672f636875636b6e6f727269735f6c6f676f5f636f6c6f757265645f736d616c6c4032782e706e67)](https://nodesource.com/products/nsolid)

Dans le cadre de l'unité OUAP-4331_2022, on a dû réaliser une application permettant d'afficher 
des blagues provenant d'un [API](https://api.chucknorris.io/) en utilisant la librairie [retrofit](https://square.github.io/retrofit/). Il sera possible de partager ou bien de sauvegarder les blagues. 



## Langages : 

- Kotlin

## Logiciel :
- Android studio

## Fichiers :
- MainActivity.kt : Cela correspond à ce que nous allons afficher en premier lors de l'ouverture de l'application. C'est ici où nous allons réaliser nos initialisations (recyclerView, requête api, etc...)

- Joke.kt : Lors de la requête api, nous recevons une réponse sous JSON. Cette classe nous permet directement de convertir la réponse sous JSON en un objet de type Joke. Dans un objet de type Joke, on peut accéder à des informations comme la date à laquelle la blague a été créer, son URL, son icône, la blague, son id, la date de mise à jour et sa catégorie.

- JokeAdapter.kt : Cette classe nous permet de faire la liaison entre nos données de type Joke et la recyclerView. 

- JokeApiService.kt : Création de l'interface permettant plus tard de réaliser nos requêtes.

- JokeApiServiceFactory : Cette classe nous permet de créer un objet nous permettant de faire une requête sur notre Api.
 
- JokeMemory : Cette classe nous permet de gérer le stockage de nos blagues avec l'utilisation de SharedPreferences. On peut sauvegarder, supprimer et récupérer nos blagues.

- JokeTouchHelper : Cette classe nous permet de gérer les évènements lorsqu'on déplace(haut/bas et droite/gauche) un élément de la recyclerView. 

- JokeView : Cette classe permet de gérer l'affichage d'un élément de la recyclerView. (Blague, boutons star et share) 




