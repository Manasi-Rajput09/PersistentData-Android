# 📱 Voting App — Android (Jetpack Compose)

<p align="center">
  A modern Android application demonstrating multi-screen navigation, local data persistence, and clean UI design using Jetpack Compose.
</p>

---

## 🚀 Overview

This project showcases a fully functional voting application built with **modern Android development tools**. It highlights the integration of **Room Database** for structured data storage and **Preference DataStore** for lightweight key-value persistence, all within a **Jetpack Compose** UI.

---

## ✨ Key Features

* 🧭 **Multi-Screen Navigation** using Navigation Compose
* 📝 **User Input Validation** (non-empty input required)
* 🔘 **Single Selection Voting System** (Yes / No / None)
* 💾 **Persistent Storage**

  * Room Database for storing votes
  * DataStore for tracking "none" vote count
* 📊 **Dynamic Data Display** of stored votes
* ⚠️ **Exit Confirmation Dialog** for better UX
* 🎨 **Consistent Purple-Themed UI**

---

## 🛠️ Tech Stack

| Category     | Technology                |
| ------------ | ------------------------- |
| Language     | Kotlin                    |
| UI Framework | Jetpack Compose           |
| Navigation   | Navigation Compose        |
| Database     | Room (SQLite abstraction) |
| Storage      | Preference DataStore      |
| Concurrency  | Kotlin Coroutines         |

---

## 📂 Project Architecture

```id="a2k9sl"
app/
 └── java/com.example.votingapp/
     ├── MainActivity.kt
     ├── data/
     │     ├── Vote.kt
     │     ├── VoteDao.kt
     │     ├── AppDb.kt
     │     └── DataStoreManager.kt
     ├── navigation/
     │     └── Screen.kt
     └── ui/
           ├── Screen1.kt
           ├── Screen2.kt
           ├── Screen3.kt
           └── Theme.kt
```

---

## 🔄 Application Flow

### 1️⃣ Screen 1 — User Input

* Accepts non-empty input
* Clear option resets DataStore and input
* Navigates to voting screen

### 2️⃣ Screen 2 — Voting

* User selects one option
* Vote is saved in Room Database
* "None" votes increment DataStore counter
* Navigates to results screen

### 3️⃣ Screen 3 — Results

* Displays all stored votes
* Shows "non-support" counter
* Back navigation and Exit dialog included

---

## 📊 Data Management Strategy

| Storage Type      | Purpose                                |
| ----------------- | -------------------------------------- |
| **Room Database** | Stores structured vote records         |
| **DataStore**     | Stores simple counter for "none" votes |

This separation demonstrates real-world Android architecture practices.

---

## ▶️ Getting Started

```id="l9j4ht"
git clone https://github.com/your-username/voting-app.git
```

1. Open the project in Android Studio
2. Sync Gradle dependencies
3. Run on emulator or physical device

---

## 📈 Learning Highlights

* Implemented declarative UI using Jetpack Compose
* Managed navigation across multiple screens
* Integrated Room and DataStore effectively
* Applied asynchronous programming using Coroutines
* Built a structured and maintainable Android project

---

## 📌 Future Improvements

* MVVM architecture with ViewModel & Repository
* UI enhancements with Material 3 components
* Unit & UI testing
* Dark/Light theme support

---

## 👩‍💻 Author

**Manasi Rajput**
Android Developer | Java & Kotlin Enthusiast

---

<p align="center">
  ⭐ If you found this project useful, consider giving it a star!
</p>
