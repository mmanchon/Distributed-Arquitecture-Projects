LOGIN1 = manel.manchon
LOGIN = samuel.tavares

CC = gcc
COP =
CFLAGS = -lpthread -Wall -Wextra -g

all: master


#Connection.o: MasterNode/Connection.c MasterNode/Connection.h
#	$(CC) -c MasterNode/Connection.c $(CFLAGS)

#Queue.o: MasterNode/Queue.c MasterNode/Queue.h
#	$(CC) -c MasterNode/Queue.c $(CFLAGS)

Utils.o: Utils.c Utils.h
	$(CC) -c Utils.c $(CFLAGS)
	
Network.o: Network.c Network.h Utils.h
	$(CC) -c Network.c $(CFLAGS)
	
main.o: main.c Network.h Utils.h
	$(CC) -c main.c $(CFLAGS)

master: main.o Network.o Utils.o
	$(CC) main.o Network.o Utils.o -o master $(CFLAGS)

clean:
	rm -rf *.o master slave

tar:
	tar -cvf $(LOGIN)_$(LOGIN1).tar ./
