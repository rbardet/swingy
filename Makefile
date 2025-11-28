include colors.mk
include vars.mk

all: $(NAME)

$(OBJ_DIR):
	@mkdir -p $(OBJ_DIR)

$(NAME): $(OBJ_DIR)
	@echo "${BLUE}[JAVAC]${RESET} Compiling all Java sources..."
	@$(JAVAC) $(JFLAGS) $(SRCS)
	@echo "${GREEN}[JAR]${RESET} Packaging $(NAME)..."
	@$(JAR) cfe $(NAME) $(MAIN_CLASS) -C $(OBJ_DIR) .
	@echo "${GREEN}[DONE]${RESET} $(NAME) created"

clean:
	@echo "${RED}[CLEAN]${RESET} Removing class files..."
	@rm -rf $(OBJ_DIR)

fclean: clean
	@echo "${RED}[FCLEAN]${RESET} Removing JAR $(NAME)..."
	@rm -f $(NAME)

re: fclean all
	@echo "${GREEN}[REBUILD]${RESET} Full rebuild complete"

.PHONY: all clean fclean re