# Pokedex-search-engine

A simple and fast Pokémon search engine built using **Spring Boot** (backend) and **React (Vite)** (frontend).  
The app fetches real-time Pokémon data from the official **PokéAPI** and displays details such as image, abilities, stats, type, height, and weight.

---

## 🚀 Tech Stack

### **Backend (Spring Boot)**
- Java  
- Spring Boot  
- WebClient (API calls to PokéAPI)  
- REST API  
- Model–Service–Controller Architecture  

### **Frontend (React + Vite)**
- React  
- Axios  
- CSS / basic styling  
- Component-based UI  

---

## 📂 Project Structure

```
Pokedex-search-engine/
│
├── backend
│   ├── src/main/java/...
│   │   ├── controller
│   │   ├── service
│   │   ├── model
│   │   └── PokedexApplication.java
│   └── pom.xml
│
└── frontend
    ├── src
    │   ├── components
    │   ├── pages
    │   ├── App.jsx
    │   └── main.jsx
    ├── index.html
    └── package.json
```

---

## 🛠️ How to Run

### **Backend**
```bash
cd backend
mvn spring-boot:run
```
Runs at:  
**http://localhost:8080**

---

### **Frontend**
```bash
cd frontend
npm install
npm run dev
```
Runs at:  
**http://localhost:5173**

---

## 📡 API Endpoint

### **Get Pokémon Details**
```
GET /pokemon/{name}
```

### Example Response:
```json
{
  "name": "pikachu",
  "type": ["electric"],
  "abilities": ["static", "lightning-rod"],
  "image": "url",
  "stats": {
    "hp": 35,
    "attack": 55
  }
}
```

---

## 🎯 Features

- Search Pokémon by name or ID  
- View abilities, stats, image, type, and other details  
- Faster loading using Spring Boot backend  
- Clean and responsive React UI  
- PokéAPI integrated  

---

## 🌱 Future Improvements
- Add dark/light theme  
- Add "Favorite Pokémon" feature  
- Add Pokémon list page  
- Improve UI styling  

---

## 🤝 Contributing

Contributions are welcome.  
Feel free to open an issue or submit a pull request.

---

## ⭐ Support

If you like this project, give it a **star** on GitHub!

