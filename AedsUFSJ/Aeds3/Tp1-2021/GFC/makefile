##
# source directory
##
SRC_DIR := src

##
# output directory
##
BIN_DIR := bin

##
# sources
##
SRCS := $(wildcard $(SRC_DIR)/*.java)

##
# classes
##
CLS := $(SRCS:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

##
# compiler and compiler flags
##
JC := javac
JAVA := java
JCFLAGS := -d $(BIN_DIR)/ -cp $(SRC_DIR)/

##
# suffixes
##
.SUFFIXES: .java

##
# targets that do not produce output files
##
.PHONY: all clean

##
# default target(s)
##
all: help

$(CLS): $(BIN_DIR)/%.class: $(SRC_DIR)/%.java util
	$(JC) $(JCFLAGS) $<

util:
	$(JC) $(JCFLAGS) $(SRC_DIR)/util/Console.java

run:
	cd $(BIN_DIR) && $(JAVA) ../$(SRC_DIR)/Master.java -i ../$(i)

help: $(CLS)
	@echo
	@echo "Execute -> make run i=exemplo.txt"
##
# clean up any output files
##
clean:
	rm -rf $(BIN_DIR)
