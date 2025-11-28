include colors.mk
include vars.mk

all: $(NAME)

$(NAME): $(CLASSES)
	@echo "${GREEN}[BUILD]${RESET} Packaging JAR $(NAME)"
	@jar cfe $(NAME) fr.swingy.Main -C $(OBJ_DIR) .
	@echo "${BLUE}[DONE]${RESET} $(NAME) created"

$(OBJ_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(dir $@)
	@echo "${BROWN}[JAVAC]${RESET} Compiling $< → $@"
	@$(JAVAC) $(JFLAGS) $<

clean:
	@echo "${RED}[CLEAN]${RESET} Removing class files"
	@rm -rf $(OBJ_DIR)

fclean: clean
	@echo "${RED}[FCLEAN]${RESET} Removing JAR $(NAME)"
	@rm -f $(NAME)

re: fclean all
	@echo "${GREEN}[REBUILD]${RESET} Full rebuild complete"

.PHONY: all clean fclean re
