JFLAGS = -classpath
JC = javac
CLASS = -classpath
P = Prg
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) . $*.java

CLASSES = *.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class