# Mini Todo List - Présentation du Projet

Bienvenue dans le projet **Mini Todo List**, une application de gestion de tâches robuste et performante construite avec **Spring Boot**.

## 📝 Description
Ce projet est une application backend conçue pour aider les utilisateurs à organiser leurs activités quotidiennes de manière simple et efficace. Elle expose une API REST pour la gestion complète du cycle de vie des tâches.

## ✨ Fonctionnalités Principales
- **Gestion des Tâches (CRUD) :** Créer, lire, mettre à jour et supprimer des tâches.
- **Suivi des États :** Gestion des statuts des tâches.
- **Filtrage Avancé :** Recherche et filtrage de tâches via QueryDSL.
- **Validation des Données :** Intégrité des données assurée par validation Jakarta.
- **Documentation Interactive :** Interface Swagger/OpenAPI intégrée.

## 🛠️ Stack Technique
- **Framework :** [Spring Boot 3.4.2](https://spring.io/projects/spring-boot) (Java 17)
- **Base de Données :** PostgreSQL
- **Persistance :** Spring Data JPA / Hibernate
- **Recherche :** QueryDSL
- **Mapping :** MapStruct
- **Utilitaires :** Lombok
- **Documentation API :** Springdoc OpenAPI (Swagger UI)

## 📁 Structure du Projet
Le projet suit une architecture multicouche standard :
- `controller/` : Points d'entrée de l'API REST.
- `services/` : Logique métier de l'application.
- `repository/` : Couche d'accès aux données.
- `entity/` : Modèles de données JPA.
- `mapper/` : Conversion entre entités et DTOs.
- `model/` : Objets de transfert de données (DTOs).
- `configurations/` : Configuration de l'application et de ses composants.

## 🚀 Démarrage Rapide

### Prérequis
- Java 17 installé
- Maven installé
- PostgreSQL configuré

### Accès à la Documentation
Une fois l'application lancée, vous pouvez accéder à la documentation interactive des APIs à l'adresse suivante :
[http://localhost:7070/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
