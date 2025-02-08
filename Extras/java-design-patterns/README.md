# Java Design Patterns
A design pattern is a general reusable solution to a commonly occurring problem within a given context in software design. A design pattern isn't a finished design that can be transformed directly into code. It is a description or template for how to solve a problem that can be used in many different situations.
I put down some of the most common design patterns in Java. (and will be adding more in the future :smile:)

---

## Creational Patterns
- **Singleton** – Ensures a class has only one instance and provides a global point of access.
- **Factory Method** – Defines an interface for creating an object but lets subclasses alter the type of objects that will be created.
- **Abstract Factory** – Provides an interface for creating families of related or dependent objects without specifying their concrete classes.
- **Builder** – Separates the construction of a complex object from its representation.
- **Prototype** – Creates new objects by copying an existing object.

## Structural Patterns
- **Adapter** – Allows objects with incompatible interfaces to work together.
- **Bridge** – Decouples an abstraction from its implementation so the two can vary independently.
- **Composite** – Composes objects into tree structures to represent part-whole hierarchies.
- **Decorator** – Adds new functionalities to an object dynamically.
- **Facade** – Provides a unified interface to a set of interfaces in a subsystem.
- **Flyweight** – Reduces memory usage by sharing as much data as possible with similar objects.
- **Proxy** – Controls access to another object by acting as a surrogate or placeholder.

## Behavioral Patterns
- **Chain of Responsibility** – Passes a request along a chain of handlers.
- **Command** – Encapsulates a request as an object, allowing parameterization and queuing of requests.
- **Interpreter** – Defines a grammar for interpreting expressions.
- **Iterator** – Provides a way to access elements of a collection sequentially without exposing its underlying structure.
- **Mediator** – Defines an object that encapsulates how a set of objects interact.
- **Memento** – Captures an object's internal state to restore it later.
- **Observer** – Defines a dependency between objects so that when one changes state, all dependents are notified.
- **State** – Allows an object to change its behavior when its internal state changes.
- **Strategy** – Defines a family of algorithms, encapsulates each one, and makes them interchangeable.
- **Template Method** – Defines the skeleton of an algorithm, letting subclasses fill in the details.
- **Visitor** – Allows adding new operations to objects without modifying them.

## Concurrency Patterns
- **Active Object** – Decouples method execution from method invocation.
- **Monitor Object** – Ensures mutual exclusion and synchronization.
- **Thread Pool** – Manages a collection of worker threads to optimize resource usage.
- **Read-Write Lock** – Allows multiple readers but only one writer at a time.

## Architectural Patterns
- **MVC (Model-View-Controller)** – Separates concerns in software applications.
- **MVVM (Model-View-ViewModel)** – Similar to MVC but used for UI frameworks.
- **Microservices** – Structures an application as a collection of loosely coupled services.
- **Event-Driven Architecture** – Uses events to trigger and communicate between services.
