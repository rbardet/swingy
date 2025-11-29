SRC_DIR = srcs
OBJ_DIR = build
NAME = swingy.jar
MAIN_CLASS = en.swingy.Main

JAVAC = javac
JAR = jar
JFLAGS = -d $(OBJ_DIR)

SRCS = $(shell find $(SRC_DIR) -name "*.java")