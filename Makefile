#------------------------------------------------------------------------------
# Bowen Brooks
# CMPS 101
# Makefile with macros 
#------------------------------------------------------------------------------

JC = 
JVM= java 
.SUFFIXEX: .java .class

.java.class:
	$(JC) $*.java

CLASSES = \
	TestDriver.java \
	UIMS.java \
	List.java \
	SITem.java \
	Conversion.java 

Main = TestDriver

default: classes

classes: $(CLASSES:.java=.class)

run: %(Main).class
	$(JVM) $(MAIN) 

clean:
		$(RM) *.class


