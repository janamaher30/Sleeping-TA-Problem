# Sleeping Teaching Assistant Problem

A Java-based multithreaded simulation of the **Sleeping Teaching Assistant (TA) Problem**, demonstrating thread management, synchronization, and resource sharing through a graphical user interface.

## 📌 Project Overview

The Sleeping TA Problem is a classic Operating Systems synchronization problem that demonstrates how multiple student threads interact with a limited number of Teaching Assistants (TAs) and waiting-room chairs.

The simulation models students requesting help, TAs providing assistance, and students waiting or returning later when no resources are available.

## 🎯 Objectives

- Apply multithreading concepts using Java Threads.
- Demonstrate synchronization using Semaphores and Mutex.
- Manage shared resources safely between concurrent threads.
- Simulate TA sleeping and waking behavior.
- Visualize simulation activities through a graphical user interface.

## ⚙️ How It Works

1. If a TA is available, a student receives help.
2. If all TAs are busy, the student waits if a chair is available.
3. If no waiting chair is available, the student leaves and returns later.
4. TAs sleep when there are no students waiting.
5. When a student needs help, a sleeping TA is notified.

## 🛠️ Technologies Used

- Java
- Java Threads
- Semaphores
- Mutex
- Java Swing (GUI)

## 📂 Project Structure

```text
Sleeping-TA-Problem/
│
└── Source Packages/
    │
    ├── app/
    │   └── Main.java
    │
    ├── core/
    │   ├── SynchronizationManager.java
    │   └── SharedState.java
    │
    ├── threads/
    │   ├── Student.java
    │   └── TA.java
    │
    ├── waitingroom/
    │   └── WaitingRoom.java
    │
    ├── simulation/
    │   └── SimulationController.java
    │
    └── gui/
        ├── MainFrame.java
        ├── DashboardPanel.java
        ├── StudentPanel.java
        ├── TAPanel.java
        ├── WaitingRoomPanel.java
        └── ActivityLogPanel.java
```

## 🖥️ GUI Features

The graphical interface provides real-time monitoring of:

- Working Teaching Assistants.
- Sleeping Teaching Assistants.
- Waiting students.
- Students returning later.
- Simulation activity log.

## 🚀 Getting Started

1. Clone this repository.
2. Open the project in NetBeans IDE.
3. Build the project.
4. Run `Main.java` to launch the application.

## 📚 Academic Context

Developed as part of an **Operating Systems 2 course** to demonstrate practical applications of concurrency, thread synchronization, and resource management.

---

