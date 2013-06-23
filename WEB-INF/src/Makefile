JAVAC=javac
CP1 = /usr/share/tomcat7/lib/servlet-api.jar
CP2 = /usr/share/tomcat7/lib/mysql-connector-java-5.1.25-bin.jar
JFLAGS = -g -cp .:./classes:$(CP1):$(CP2) -d ../classes

SRC = src
OUT = classes

sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: $(classes)

clean :
	rm *.class
	rm $(OUT)/*.class

%.class : %.java
	$(JAVAC) $(JFLAGS) $<
