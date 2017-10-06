SRCDIR = src
BINDIR = bin
JAVAC = javac
JAR = jar
JAVAFLAGS = -d $(BINDIR)
JARFLAG = cvfm
MAIN = main
MANIFEST = MANIFEST.MF
JARFILE = miso.jar

all:
	mkdir -p $(BINDIR); \
	find $(SRCDIR) -name "*\.java" | xargs $(JAVAC) $(JAVAFLAGS); \
	$(JAR) $(JARFLAG) $(JARFILE) $(MANIFEST) -C $(BINDIR) $(MAIN)

cleanbin:
	rm -rf $(BINDIR)

clean:
	rm -rf $(BINDIR) $(JARFILE)
