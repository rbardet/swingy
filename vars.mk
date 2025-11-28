SRC_DIR = srcs
OBJ_DIR = build

NAME = swingy.jar

SRCS = $(shell find $(SRC_DIR) -name "*.java")
CLASSES = $(patsubst $(SRC_DIR)/%.java,$(OBJ_DIR)/%.class,$(SRCS))

JAVAC = javac
JAR = jar
JFLAGS = -d $(OBJ_DIR)

MAIN_CLASS = fr.swingy.Main