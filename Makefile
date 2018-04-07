JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		Main.java \
		Menu.java \
		Reglage.java \
		Case.java \
		Demineur.java 
		

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class