# Digital Banking - Spring Boot Application

## Description
Digital Banking est une application back-end développée avec **Spring Boot**, visant à gérer les opérations bancaires, les comptes clients, et les transactions financières de manière sécurisée et efficace.  
Ce projet expose une API REST pour :  

- La gestion des clients
- La gestion des comptes bancaires
- Les opérations financières (débit, crédit, virement)
- La consultation de l’historique des opérations

---

## Fonctionnalités principales

### Gestion des comptes bancaires
- Liste de tous les comptes : `GET /accounts`
- Détail d’un compte : `GET /accounts/{accountId}`
- Historique des opérations : `GET /accounts/{accountId}/operations`
- Historique paginé : `GET /accounts/{accountId}/pageOperations?page=0&size=5`
- Débit sur un compte : `POST /accounts/debit`
- Crédit sur un compte : `POST /accounts/credit`
- Virement entre comptes : `POST /accounts/transfer`

### Gestion des clients
- Liste de tous les clients : `GET /customers`
- Détail d’un client : `GET /customers/{id}`
- Création d’un client : `POST /customers`
- Mise à jour d’un client : `PUT /customers/{id}`
- Suppression d’un client : `DELETE /customers/{id}`

---

## Technologies utilisées
- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- Spring Security
- MySQL / PostgreSQL (ou H2 pour tests)
- Maven
- Lombok
- Git / GitHub

---

## Installation

1. Cloner le dépôt :
```bash
git clone https://github.com/hammouzin/-e-banking-springboot-backend.git
