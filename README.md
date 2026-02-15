Data Center Infrastructure Management API
Dragan Marius 
Project Overview
    This project is a Java-based backend system designed to simulate an IT infrastructure monitoring and alert
management platform. It processes dynamic system data to manage servers, resource group, and automated alerts
through a robust, scalable architecture.

Core Functionality
    The application features a command-driven logic handled by the "Main" class, which supports multiple execution
flows:
    Infrastructure Setup: Loading servers and monitoring groups into the system.
    Full Monitoring Cycle: Managing the complete flow from server ingestion to real-time event alerts.
    Dynamic Processing: Command names are processed via a "CommandFactory", which return specific 'Command' objects
to execute the required logic and interact with the database.

Design Pattern Implemented:
    To ensure clean code and high maintainability, the system utilizes a 5 core Design Patterns:
1. Singleton(Database.java): Ensures a single, globally accessible instance of the database. By using a private
constructor and a static 'getInstance()' method, I prevented multiple instantiations and ensured data consistency
across the system.
2. Factory Method(CommandFactory.java): Decouples object creation from execution logic. The factory decides which command
instantiate based on input strings, allowing the 'Main' class to remain agnostic of specific command types.
3. Command Pattern(Command hierarchy): Encapsulates each system request as an object. This makes it easy to add new commands
in the future without modifying existing execution logic, adhering to the Open-Closed Principle.
4. Observer Pattern: Implemented for the alert mechanism. The Server acts as the Subject, and the
ResourceGroup acts as the Observer. When an alert is generated, the server automatically notifies the relevant monitoring
groups to handle the event.
5. Builder Pattern(Server,Location):  Used to manage complex objects with numerous optional attributes (coordinates,
specific hardware specs). This eliminates "telescoping constructors" and improves code readability.

Technical Highlights
    OOP Principles: Applied strict inheritance hierarchies (`User` -> `Operator` -> `Admin`), encapsulation (private
attributes with public getters/setters), and polymorphism in command processing.
    Performance & Data Integrity: Utilized `HashSet` for storing collections of users, servers, and groups to ensure
uniqueness and O(1) average time complexity for lookups.
    Robust Error Handling: Developed custom exceptions (`MissingIpAddressException`, `LocationException`,
`UserException`) to validate input data and ensure system stability.
