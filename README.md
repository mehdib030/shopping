# Shopping

A full-stack e-commerce application with an **Angular** frontend and a **Spring Boot** backend. Features JWT authentication, product catalog, categories, comments, and a shopping cart.

## Tech Stack

### Backend (`front/front/`)
- **Java 8** / **Spring Boot 2.1.1**
- **Spring Security** with JWT authentication
- **Spring Data JPA** (MySQL) + **Spring Data MongoDB Reactive**
- **Gradle**

### Frontend (`front/`)
- **Angular 7** with **Angular Material**
- **Angular Flex Layout**
- **RxJS**

## Features

- User registration and login with JWT
- Product listing and detail views
- Product categories
- User comments on products
- Shopping cart
- Side navigation with Material Design
- Role-based access control

## Project Structure

```
shopping/
├── front/                           # Angular frontend
│   ├── src/app/
│   │   ├── authentication/          # Login & register components
│   │   ├── product/                 # Product list, detail, comments
│   │   ├── cart/                    # Shopping cart
│   │   ├── header/                  # Header navigation
│   │   ├── side-navigation/         # Side nav component
│   │   └── material-design/         # Angular Material module
│   └── package.json
│
└── front/front/                     # Spring Boot backend
    └── src/main/java/com/store/retail/
        ├── auth/                    # Authentication (JWT)
        ├── product/                 # Product CRUD
        ├── category/                # Category CRUD
        ├── comment/                 # Comment CRUD
        ├── security/                # Spring Security config
        └── model/                   # Role, User entities
```

## Prerequisites

- Java 8+
- MySQL
- Node.js & npm
- Angular CLI (`npm install -g @angular/cli`)

## Setup

### Backend
1. Create a MySQL database and update `application.properties`
2. Build and run:
   ```bash
   cd front/front
   ./gradlew bootRun
   ```

### Frontend
1. Install dependencies and start:
   ```bash
   cd front
   npm install
   ng serve
   ```
2. Open http://localhost:4200
