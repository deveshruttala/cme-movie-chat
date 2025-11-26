# 🎬 The Movie Chat

A cloud-native **Text-to-SQL** AI assistant that converts natural language questions into SQL queries. Built with **Spring Boot**, **OpenAI**, and deployed on **Google Kubernetes Engine (GKE)**.

## 📄 Full Documentation
> **[View the Complete Project Report & Documentation (PDF)](https://github.com/deveshruttala/cme-movie-chat/blob/main/CME%20-%20Demo%20-%20Movie%20Chat%20-Documentation%20-%20By%20-%20Devesh%20Ruttala%20(49385).pdf)**

## 🛠 Tech Stack
* **Core:** Java 21, Spring Boot 3.3, Spring AI
* **Database:** PostgreSQL 16
* **Cloud:** Docker, Kubernetes (GKE), Google Artifact Registry
* **Automation:** Python 3 (Agent Tool)

## ⚡ Quick Start
1.  **Start Database:**
    ```bash
    docker run --name movie_db -e POSTGRES_PASSWORD=password -p 5433:5432 -d postgres:16
    ```
2.  **Run App:**
    ```bash
    mvn spring-boot:run
    ```
3.  **Chat:** Open `http://localhost:8080`

---
*Developed by Devesh Ruttala*
