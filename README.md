# Système de gestion de films

## Membres du groupe

- Yvan (颜令健 1201024044): [yannyvan0909@qq.com](mailto:yannyvan0909@qq.com)
- Yoann (黄杰 1201024053): [1762773179@qq.com](mailto:1762773179@qq.com)
- Olivia (石虹婷 1201024038): [877815585@qq.com](mailto:877815585@qq.com)
- 
## Exigences du Projet

- **Version de JDK :** 1.8
- **Eclipse :** 2019
- **Version de Tomcat :** 9.0
- **Version de CXF :** 2.7

## Configuration de l'Environnement

Assurez-vous que votre environnement local dispose des logiciels suivants installés, et que les versions sont conformes aux exigences du projet:

- [JDK 1.8](https://www.oracle.com/java/technologies/downloads/#java8)
- [Eclipse IDE for Enterprise Java Developers 2019-12 (4.14.0)](https://www.eclipse.org/downloads/packages/release/2019-12/r/eclipse-ide-enterprise-java-developers)
- [Tomcat 9.0](https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.14/bin/apache-tomcat-9.0.14.zip)
- [CXF 2.7](https://archive.apache.org/dist/cxf/2.7.18/apache-cxf-2.7.18.zip)

## Présentation du projet

### Objectif global

Notre projet vise à concevoir un système de gestion de films permettant des opérations telles que la création (POST), la récupération (GET), la suppression (DELETE) et la mise à jour (PUT) des films sur un serveur local. De plus, nous avons intégré une interface API de films externe, permettant la recherche de films liés par titre et année.

### Composants

Nous avons créé deux branches sur GitHub, `movie_server` et `movie_client`.

#### Branche `movie_server`

1. **Classe `Movie`**

   - Cette classe est une classe de données représentant l'entité du film, avec les attributs du titre (`title`), de l'année (`year`) et de l'identifiant unique (`id`).

   ```java
   public class Movie {
       // Autres attributs et méthodes omis
   }
   ```

2. **Classe `MovieResource`**

   - Classe de ressource JAX-RS, définissant une interface RESTful pour traiter les ressources de films, incluant des méthodes pour ajouter, mettre à jour, supprimer et récupérer des films.

   ```java
   @Path("/movies")
   public class MovieResource {
       // Autres attributs et méthodes omis
   }
   ```

3. **Classe `MovieService`**

   - Classe de service simple pour gérer les opérations CRUD sur les informations de films, avec des méthodes pour ajouter, supprimer, mettre à jour et récupérer des informations de films, utilisant une Map statique pour stocker les informations.

   ```java
   public class MovieService {
       // Autres attributs et méthodes omis
   }
   ```

#### Branche `movie_client`

1. **Classe `OMDbAPICaller`**

   - Classe d'appel à l'API OMDb, utilisée pour appeler l'API OMDb pour rechercher des informations sur les films.

   ```java
   public class OMDbAPICaller {
       // Autres attributs et méthodes omis
   }
   ```

2. **Interface `MovieServer`**

   - Définit des méthodes d'interface pour supprimer, récupérer et ajouter des informations de films.

   ```java
   public interface MovieServer {
       // Autres attributs et méthodes omis
   }
   ```

3. **Classe `MovieServerImpl`**

   - Classe d'implémentation du service de films, utilisant Apache CXF pour la communication avec le serveur, implémente les méthodes de l'interface `MovieServer` pour supprimer, récupérer et ajouter des informations de films.

   ```java
   public class MovieServerImpl implements MovieServer {
       // Autres attributs et méthodes omis
   }
   ```

4. **Classe `Test`**

   - Utilisée pour tester les fonctionnalités de la classe `MovieServerImpl`, notamment l'ajout, la récupération, la recherche et la suppression d'informations sur les films.

   ```java
   MovieServerImpl movieServer = new MovieServerImpl(webServiceUrl);

   Movie alice = new Movie("Alice", "2019",
         movieServer.add("Alice", "2019"));

   Movie bob = new Movie("Test", "2019",
         movieServer.add("Test", "2019"));

   Movie movie = movieServer.get(bob.getId());

   String titleMovie = movie.getTitle();
   int yearMovie = Integer.parseInt(movie.getYear());

   //Extra Api
   OMDbAPICaller omDbAPICaller = new OMDbAPICaller(OMDbApiKey_STRING);
   String movieAbstract = omDbAPICaller.searchMovie(titleMovie, yearMovie);
   System.out.println(movieAbstract);


   movieServer.delete(bob.getId());
   ```

   Résultat de sortie :

   ```json
   Adding Alice... OK.
   Adding Test... OK.
   Getting 9...
   {"Title":"Test","Year":"2019","Rated":"N/A","Released":"06 May 2019","Runtime":"90 min","Genre":"Drama","Director":"Umashankar Gummadidala","Writer":"Umashankar Gummadidala","Actors":"Johnny Ray Campbell, Leon Wayne Corley, Vanzell Haire","imdbVotes":"14","imdbID":"tt6988290","Type":"movie","Response":"True"}
   Deleting 9... OK.   


Ce sont les composants principaux de notre système de gestion de films, permettant une gestion complète des informations sur les films et l'appel à une API externe.
