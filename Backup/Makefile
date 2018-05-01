JFLAGS = -g
JC = javac
J = java
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		Main.java \
		Dessin.java\
		Dessin2.java\
		Menu.java \
		Menu2.java \
		Reglage.java \
		Algo.java\
		Case.java \
		Demineur.java 
		

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class

test:
		$(J) Main
		
		