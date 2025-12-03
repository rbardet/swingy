include colors.mk
include vars.mk

all:
	@echo "$(BLUE)Creating executable...$(RESET)"
	@mvn package
	@echo "${BLUE}Executable created${RESET}"

clean:
	@echo "${RED}Cleaning project ...${RESET}"
	@mvn clean
	@echo "${RED}Project Cleaned${RESET}"

console:
	${JAVA} ${EXE} console

gui:
	${JAVA} ${EXE} gui

help:
	@echo "${GREEN}make all: create the project executable"
	@echo "make clean: clean the project"
	@echo "make console: run the game in console mode"
	@echo "make gui: run the game in GUI mode"
	@echo "make help: show this text"
	@echo "make re: run make clean and make all${RESET}"

re: clean all

.PHONY: all clean console gui re