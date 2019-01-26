//
// Created by Samuel on 19/09/2018.
//

#include "Network.h"


int NETWORK_createConnectionServer(int portInput, char *ipInput) {

    int retVal;

    // we check if the port is alright
    uint16_t port;
    if (portInput < 1 || portInput > 65535) {
        fprintf(stderr, ERR_PORT, portInput);
        return -1;
    }
    port = portInput;

    struct in_addr ip_addr;
    if (inet_aton(ipInput, &ip_addr) != 1) {
        fprintf(stderr, ERR_ATON, ipInput, strerror(errno));
        return -1;
    }

    // now its time to create the socket

    socket_server = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (socket_server < 0) {
        perror("socket TCP");
        return -1;
    }

    struct sockaddr_in s_addr;
    bzero(&s_addr, sizeof(s_addr));
    s_addr.sin_family = AF_INET;
    char aux[200];
    sprintf(aux, "\n\nppp-->%s\n\n", ipInput);
    s_addr.sin_port = htons(port);
    s_addr.sin_addr.s_addr = inet_addr(ipInput);//INADDR_ANY;

    int opt = 1;
    setsockopt(socket_server, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt));

    retVal = bind(socket_server, (void *) &s_addr, sizeof(s_addr));
    if (retVal < 0) {
        close(socket_server);
        perror("bind");
        return -1;
    }

    listen(socket_server, 1);
    return 0;
}

void NETWORK_serialHandler() {

    struct sockaddr_in c_addr;
    socklen_t c_len = sizeof(c_addr);


    if (socket_server < 0) {
        exit(EXIT_FAILURE);
    }
    //printf("ESPERO ACCEPT\n");
    socket_connect = accept(socket_server, (void *) &c_addr, &c_len);

    if (socket_connect < 0) {
        perror("accept");
        close(socket_server);
        exit(0);
    }

    NETWORK_reciveMessage();
}

int NETWORK_connect(int old_port, int portInput, char *ipInput) {
    int num_intents = 0;

    close(socket_client);

    // comprovem la validesa del port
    if (portInput < 1 || portInput > 65535) {
        fprintf(stderr, ERR_PORT, portInput);
        return -1;
    }
    uint16_t port = portInput;

    // comprovem la validesa de l'adre￿a ip
    // i la convertim a format binari
    struct in_addr ip_addr;

    if (inet_aton(ipInput, &ip_addr) == 0) {
        fprintf(stderr, ERR_ATON, ipInput, strerror(errno));
        return -1;
    }
    // creem el socket
    socket_client = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (socket_client < 0) {
        perror("socket TCP");
        return -1;
    }


    // especifiquem l'adre￿a del servidor
    struct sockaddr_in s_addr;
    inet_aton(ipInput, &ip_addr);
    bzero(&s_addr, sizeof(s_addr));
    s_addr.sin_family = AF_INET;
    s_addr.sin_port = htons(port);
    s_addr.sin_addr = ip_addr;

    printf("INTENTO RECONECTAR AL PUERTO %d\n", port);

    while (connect(socket_client, (void *) &s_addr, sizeof(s_addr)) < 0) {

        num_intents++;
        sleep(2);

        if (num_intents > 2) {

            if (portInput - old_port == 5) {

                NETWORK_connect(8463, 8463, "127.0.0.1");

            } else {

                NETWORK_connect(old_port, port + 1, "127.0.0.1");

            }
        }
    }
    return 0;
}

int NETWORK_createConnectionNextSlave(int portInput, char *ipInput) {

    // comprovem la validesa del port
    if (portInput < 1 || portInput > 65535) {
        fprintf(stderr, ERR_PORT, portInput);
        return -1;
    }
    uint16_t port = portInput;

    // comprovem la validesa de l'adre￿a ip
    // i la convertim a format binari
    struct in_addr ip_addr;

    if (inet_aton(ipInput, &ip_addr) == 0) {
        fprintf(stderr, ERR_ATON, ipInput, strerror(errno));
        return -1;
    }
    // creem el socket
    socket_client = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (socket_client < 0) {
        perror("socket TCP");
        return -1;
    }

    // especifiquem l'adre￿a del servidor
    struct sockaddr_in s_addr;
    bzero(&s_addr, sizeof(s_addr));
    s_addr.sin_family = AF_INET;
    s_addr.sin_port = htons(port);
    s_addr.sin_addr = ip_addr;

    if (connect(socket_client, (void *) &s_addr, sizeof(s_addr)) < 0) {
        close(socket_client);
        NETWORK_createConnectionNextSlave(8463, "127.0.0.1");
        last = 1;
        return 8463;
    }

    return portInput;
}

void NETWORK_sendMessage(int port) {
    int num = 1;

    send(socket_client, &valor, sizeof(valor), MSG_DONTWAIT);

    num = recv(socket_client, &num, sizeof(num), MSG_WAITALL);

    if (num <= 0) {
        //printf("ERROR AL ENVIAR %d\n", num);

        if (last == 1) {
            NETWORK_connect(8464, 8464, "127.0.0.1");
        } else {
            NETWORK_connect(port, port + 2, "127.0.0.1");
        }
        NETWORK_sendMessage(port);
    }
}

int NETWORK_reciveMessage() {
    int value, ret;

    if ((ret = recv(socket_connect, &value, sizeof(value), MSG_WAITALL)) <= 0) {
        //printf("ERROR AL RECIBIR %d\n", ret);
        close(socket_connect);
        NETWORK_generateError();
        value = -1;
    } else {

        if(value < 0){
            //printf("TRAMA DE ERROR\n");

            if(error == 0){
                error = value;
                send(socket_connect, &error, sizeof(error), MSG_DONTWAIT);
                NETWORK_sendError();

            }else{
                if(value>error){

                    error = value;
                    send(socket_connect, &error, sizeof(error), MSG_DONTWAIT);
                    NETWORK_sendError();

                } else if (error == value){
                    //printf("ES IGUAL\n");
                    send(socket_connect, &error, sizeof(error), MSG_DONTWAIT);
                }
            }
            NETWORK_reciveMessage();

        }else{
            valor = value;
            printf("VALOR LEIDO %d\n", valor);
            send(socket_connect, &valor, sizeof(valor), MSG_DONTWAIT);

        }


    }

    return value;
}

void NETWORK_sendSimple() {
    send(socket_client, &valor, sizeof(valor), MSG_DONTWAIT);

}

void NETWORK_generateError(){
    time_t rawtime;
    time(&rawtime);
    error = rawtime * -1;
    send(socket_client, &error, sizeof(error), MSG_DONTWAIT);
    //printf("RAWTIME %d\n",(int) rawtime);
    NETWORK_serialHandler();
}

void NETWORK_sendError(){
    int num = 1;
    int value = 0;

    send(socket_client, &error, sizeof(error), MSG_DONTWAIT);
    sleep(2);
    num = recv(socket_client, &value, sizeof(value), MSG_DONTWAIT);

    if (num <= 0) {
        //printf("ERROR AL ENVIAR %d\n", num);

        if (last == 1) {
            NETWORK_connect(8464, 8464, "127.0.0.1");
        } else {
            NETWORK_connect(port_global, port_global + 2, "127.0.0.1");
        }

        send(socket_client, &error, sizeof(error), MSG_DONTWAIT);
        sleep(2);
        num = recv(socket_client, &value, sizeof(value), MSG_DONTWAIT);

        if(num > 0){
            if(error == value){
                //NETWORK_sendMessage(port_global);
                NETWORK_sendSimple();
            }
        }
    }
}