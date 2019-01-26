//
// Created by Samuel on 19/09/2018.
//

#ifndef _NETWORK_H_
#define _NETWORK_H_


//include system
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <memory.h>
#include <errno.h>
#include <stdlib.h>
#include <errno.h>
#include <stdint.h>
#include <stdio.h>
#include <fcntl.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <time.h>
//constantes propias
#define ERR_PORT "Error: %d es un port invalid\n"
#define ERR_ATON "inet_aton (%s): %s\n"


int socket_client;
int socket_server;
int socket_connect;
int enviat;
int rebut;
int valor;
int last;
int error;
int port_global;

int NETWORK_createConnectionNextSlave(int portInput, char *ipInput);

int NETWORK_createConnectionServer(int portInput, char *ipInput);

void NETWORK_serialHandler();

int NETWORK_connect(int old_port, int portInput, char *ipInput);

void NETWORK_sendMessage(int port);

int NETWORK_reciveMessage();

void NETWORK_sendSimple();

void NETWORK_generateError();

void NETWORK_sendError();
#endif //MASTERNODE_NETWORK_H
