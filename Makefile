all:	
	rm -f *.class dbProject/*.class
	javac tennis.java -cp ".":servlet-api.jar
	cp tennis.class ../tennis/webapps/ROOT/WEB-INF/classes/
	cp dbProject/*.class ../tennis/webapps/ROOT/WEB-INF/classes/dbProject/
	rm *.class
	rm dbProject/*.class
	cp -r jsps/* ../tennis/webapps/ROOT/
	cd ../tennis/bin;\
	./shutdown.sh;\
	./startup.sh
	rm -f *~
	rm -f */*~
	rm -f */*/*~
	rm -f */*/*/*~
