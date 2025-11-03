# 🏦 Bank Cashier System Simulator

> **A desktop application developed in Java that visually simulates a customer queue at a bank using a custom-built, array-based Circular Queue data structure.**

---

## ✨ Key Features & Implementation Highlights

This project demonstrates core computer science concepts and robust Java Swing programming practices.

* **Custom FIFO Queue:** The core logic uses a **custom-implemented, array-based Circular Queue** (`Queue.java`) to manage customers, strictly adhering to the **First-In-First-Out (FIFO)** principle without using Java's built-in collection libraries.
* **Responsive UI with Java Swing:** Built with Java Swing, the application provides a user-friendly Graphical User Interface (GUI), designed to run in a maximized, **full-screen window**.
* **Non-Blocking Service Simulation:** The "Serve Customer" action uses **`SwingWorker`** to execute a realistic **5-second service delay** on a background thread. This ensures the main UI thread remains **fully responsive** (non-freezing) while the cashier is busy.
* **Real-Time Feedback:** The UI provides continuous updates, showing the current customer list and the cashier's availability status (e.g., "Cashier is busy.").
* **Input Validation:** Includes client-side validation to prevent adding customers with empty names.

---

## 🛠️ Technology Stack

* **Language:** Java
* **GUI Library:** Java Swing
* **Concurrency:** `javax.swing.SwingWorker`

---

## 📂 Project Structure

The project is divided into logical components for clarity and maintainability:

| File | Description |
| :--- | :--- |
| `BankApp.java` | **Main application class** containing the Java Swing UI setup, event handling, and the **`SwingWorker`** logic for service simulation. Includes the `main` method. |
| `BankCashier.java` | Acts as a **controller/facade** layer, managing the state (`isServing`) and providing thread-safe, synchronized methods to interact with the underlying `Queue`. |
| `Queue.java` | **Core data structure:** Custom implementation of the array-based **Circular Queue** with methods like `enqueue`, `dequeue`, `isFull`, and `getQueueContents`. |
| `Customer.java` | A simple data class representing a customer, storing their name and arrival time. |

---

## 🚀 Getting Started

### Prerequisites

You need the **Java Development Kit (JDK)** installed on your machine.
* **JDK 8** or newer is recommended.

### Installation and Run

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/itz-abhijeet/Bank-Cashier-System.git](https://github.com/itz-abhijeet/Bank-Cashier-System.git)
    cd Bank-Cashier-System
    ```

2.  **Compile the Java files:**
    ```bash
    # (Assuming all files are in the root directory)
    javac BankApp.java BankCashier.java Customer.java Queue.java
    ```

3.  **Run the application:**
    ```bash
    java BankApp
    ```
    The application will launch in a **full-screen** desktop window.

---

## 📖 Usage Guide

The application is controlled via the UI:

1.  **Adding a Customer:**
    * Enter a name in the "Customer Name" field.
    * Click **"Add Customer"**. The new customer appears at the bottom of the queue list.
2.  **Serving a Customer:**
    * Click **"Serve Customer"**.
    * The **Cashier Status** immediately changes to **"Cashier is busy."**
    * A **5-second** service delay begins in the background. The application remains responsive.
    * Once the delay is complete, the first customer is removed from the queue, and the status reverts to **"Cashier is available."**

---

## 📜 License

Distributed under the **MIT License**. See the repository for `LICENSE.txt` for more information.

---

## 📧 Contact

Abhijeet Pawar

Project Link: **[https://github.com/itz-abhijeet/Bank-Cashier-System.git](https://github.com/itz-abhijeet/Bank-Cashier-System.git)**
