# Game of Life 2

Multi-module Maven project for The Game of Life 2 board game.

## Modules

- `core` - Game engine core library
- `games/life2` - Game of Life 2 specific implementation
- `infrastructure/api` - REST API server
- `infrastructure/persistence` - Database persistence layer

## Build

```bash
./mvnw clean install
```

## Run

```bash
cd infrastructure/api
./mvnw spring-boot:run
```