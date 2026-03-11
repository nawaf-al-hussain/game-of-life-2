# Game of Life 2

A board game engine implementation of The Game of Life with React frontend and Java Spring Boot backend.

## Project Structure

- `core/` - Game engine core library (Java)
- `frontend/` - React frontend application
- `docker/` - Docker configuration files

## Tech Stack

- **Backend**: Java 17, Spring Boot, Maven
- **Frontend**: React 18, Vite, TailwindCSS, Konva (canvas)
- **Real-time**: WebSocket with STOMP protocol

## Getting Started

### Prerequisites
- Java 17+
- Node.js 18+
- Maven 3.8+

### Build & Run

```bash
# Build the Java backend
./mvnw clean install

# Run the frontend
cd frontend
npm install
npm run dev
```

## License

MIT
