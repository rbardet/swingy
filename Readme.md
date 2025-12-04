# Swingy

## Description

_Swingy_ is a minimalist RPG game developed in Java as part of the 42 curriculum. The game runs in both console and GUI modes, following a Model-View-Controller (MVC) architecture. Hero progress and game data are stored in a SQL database.

## Features

- **Hero Creation & Selection**: Multiple hero classes with scalable stats inspired by Diablo 3.
- **Artifacts**: Weapons, armor, and helmets that modify hero stats.
- **Dynamic Map**: Map size scales with hero level.
- **Exploration & Combat**: Move across the map, engage in simulated battles against enemies (with Diablo 3-inspired names).
- **Experience System & Leveling**: Gain experience and loot based on combat outcomes.
- **Save & Load Progress**: Heroes and their progress are stored in a SQL database *(bonus)*.
- **Two Game Modes**:
  - Console Mode: `java -jar swingy.jar console` or `make console`
  - GUI Mode: `java -jar swingy.jar gui` or `make gui`

### Bonus

- **SQL Database Save**: Hero data is reliably stored in a relational SQL database rather than a text file, for increased reliability and flexibility.

## Project Structure & Rules

- **Maven**: Build and dependency management (except for `javax.validation` and JDBC).
- **Java LTS**: Latest LTS version only.
- **Custom Packages**: Compliant with Java conventions.
- **No external dependencies** *except for `javax.validation` and bonus SQL*.

## Makefile Commands

```text
make all      # Compile and build the jar executable
make clean    # Clean the project
make console  # Run the game in console mode
make gui      # Run the game in GUI mode
make help     # Display help for available commands
make re       # Clean then compile the project
```

## Compilation & Launch

```bash
make all
make console        # Or: java -jar swingy.jar console
make gui            # Or: java -jar swingy.jar gui
```

## Dependencies

- JDBC for SQL database connection (bonus)

## Author

- **rbardet**
