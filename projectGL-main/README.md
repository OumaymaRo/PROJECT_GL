# Gestion de Projet GL

## Rôles et Utilisateurs

Le système comprend cinq types d'utilisateurs, chacun avec des responsabilités spécifiques :

### 1. Fournisseur
- **Rôle :** Gestion des fournitures et des commandes
- **Responsabilités :**
  - Soumission des offres
  - Gestion des commandes
  - Suivi des livraisons
  - Facturation

### 2. Enseignant
- **Rôle :** Utilisation des ressources
- **Responsabilités :**
  - Demande de ressources
  - Utilisation des équipements
  - Signalement des besoins
  - Retour des ressources

### 3. Technicien
- **Rôle :** Maintenance et réparation
- **Responsabilités :**
  - Maintenance préventive
  - Réparation des pannes
  - Installation des équipements
  - Diagnostic technique

### 4. Responsable
- **Rôle :** Gestion des ressources
- **Responsabilités :**
  - Validation des demandes
  - Gestion des stocks
  - Attribution des ressources
  - Suivi des utilisations

### 5. Chef de Département
- **Rôle :** Administration du département
- **Responsabilités :**
  - Gestion du budget
  - Validation des achats
  - Supervision des activités
  - Prise de décisions stratégiques

## Authentification

Le système utilise Spring Security pour gérer l'authentification. Chaque utilisateur est associé à un rôle spécifique qui détermine ses accès et permissions dans le système.

### Rôles Disponibles
- `ROLE_FOURNISSEUR` : Accès aux fonctionnalités des fournisseurs
- `ROLE_ENSEIGNANT` : Accès aux fonctionnalités des enseignants
- `ROLE_TECHNICIEN` : Accès aux fonctionnalités des techniciens
- `ROLE_RESPONSABLE` : Accès aux fonctionnalités des responsables
- `ROLE_CHEF_DEPARTEMENT` : Accès aux fonctionnalités des chefs de département

### Utilisateurs de Test
Des utilisateurs de test sont automatiquement créés au démarrage de l'application :

#### Fournisseur
- **Nom d'utilisateur :** fournisseur
- **Mot de passe :** fournisseur123
- **Email :** fournisseur@example.com

#### Enseignant
- **Nom d'utilisateur :** enseignant
- **Mot de passe :** enseignant123
- **Email :** enseignant@example.com

#### Technicien
- **Nom d'utilisateur :** technicien
- **Mot de passe :** technicien123
- **Email :** technicien@example.com

#### Responsable
- **Nom d'utilisateur :** responsable
- **Mot de passe :** responsable123
- **Email :** responsable@example.com

#### Chef de Département
- **Nom d'utilisateur :** chefdepartement
- **Mot de passe :** chefdepartement123
- **Email :** chefdepartement@example.com

## Installation et Configuration

1. Cloner le projet
2. Configurer la base de données dans `application.properties`
3. Exécuter l'application avec Maven : `mvn spring-boot:run`
4. Accéder à l'application via : `http://localhost:8080`

## Structure du Projet

```
src/
├── main/
│   ├── java/
│   │   └── com/example/appgl/
│   │       ├── config/         # Configuration et initialisation
│   │       ├── controllers/    # Contrôleurs MVC
│   │       ├── models/         # Entités JPA
│   │       ├── repositories/   # Repositories JPA
│   │       ├── security/       # Configuration Spring Security
│   │       └── AppglApplication.java
│   └── resources/
│       ├── static/            # Ressources statiques (CSS, JS)
│       └── templates/         # Templates Thymeleaf
``` 