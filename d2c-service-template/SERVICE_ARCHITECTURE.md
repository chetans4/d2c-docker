                                           +-------------------+
                                           |      Database     |
                                           |      (MySQL)      |
                                           +---------+---------+
                                                     |
                                                     |
                              +----------------------+----------------------+
                              |                                             |
                   +----------+-----------+                     +-----------+-----------+
                   |     Repository       |                     |       Repository      |
                   |   (Spring Data JPA)  |                     |     (Spring Data JPA) |
                   +----------+-----------+                     +-----------+-----------+
                              |                                             |
                    +---------+----------+                       +----------+----------+
                    |     Service        |                       |      Service        |
                    +---------+----------+                       +----------+----------+
                              |                                             |
                    +---------+----------+                       +----------+-----------+
                    |     Controller     |                       |      Controller      |
                    +--------------------+                       +----------------------+
                              |                                             |
                              |                                             |
                              |                                             |
                   +----------+----------+                    +------------+-----------+
                   |   Spring Security   |                     |   Spring Security      |
                   +---------------------+                     +------------------------+
                              |                                             |
                              +---------------------+-----------------------+
                                                    |           
                                                    |
                                           +-------------------+
                                           |      Client       |
                                           +-------------------+

- Application Architecture Overview
-- Modular Design: The application is divided into two identical modules.
-- Layered Architecture: Each module follows a four-layer architecture.
-- Data Access: The Repository layer, using Spring Data JPA, interacts with the database.
-- Business Logic: The Service layer handles the application's core functionality.
-- API Gateway: The Controller layer manages HTTP requests and responses.
-- Security: Spring Security provides authentication and authorization.
-- Dependency Structure: Each layer communicates with the layers above and below it.