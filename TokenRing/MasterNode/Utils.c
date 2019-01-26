/**
 * @Author: Manel Manchón Gascó / Gabriel Cammany Ruiz
 * @Date:   25-10-2017
 * @Email:  ls31343@salleurl.edu ls30652@salleurl.edu
 * @Project: Práctica LSEat
 * @Filename: utils.c
 * @Last modified by:   Manel Manchón Gascó / Gabriel Cammany Ruiz
 * @Last modified time: 27-10-2017
 */

#include <memory.h>
#include <ctype.h>
#include <stdarg.h>
#include "Utils.h"

int UTILS_mostraMenu() {
    char *buffer = NULL;
    int fd = 0;

    write(STDOUT_FILENO, WELCOME, sizeof(WELCOME));
    UTILS_readDynamic(&buffer, STDIN_FILENO);

    while ((fd = open("start", O_RDONLY)) == -1) {}
    close(fd);
    return *buffer - '0';
}

int UTILS_readDynamic(char **input, int fd) {

    char *temporal = NULL;
    char buffer = ' ';
    int indice = 0;

    while (read(fd, &buffer, sizeof(char)) > 0 && buffer != '\n') {
        temporal = (char *) realloc(*input, sizeof(char) * (indice + 1));

        if (temporal == NULL) {

            if (*input != NULL) {
                free(*input);
            }
            return -1;

        }

        *input = temporal;
        (*input)[indice] = buffer;
        indice++;
    }

    if (*input != NULL) {
        temporal = (char *) realloc(*input, sizeof(char) * (indice + 1));
        if (temporal == NULL) {

            free(*input);
            return -1;

        }

        *input = temporal;
        (*input)[indice] = '\0';
    }

    return indice;

}

void UTILS_readerSlave(int port) {
    int i = 0;

    port_global = port;
    //primero creamos la conexion con el servidor
    for (i = 0; i < 10; i++) {
        printf("------VUELTA BUCLE %d-----\n", i);
        UTILS_getCurrentValue(port);

        if (i != 0) {
            NETWORK_sendMessage(port);

        } else {

            if (port == 8463) {
                NETWORK_sendSimple();

            } else {
                NETWORK_sendMessage(port);

            }
        }
        error = 0;
        sleep(10);
    }
}


int UTILS_getCurrentValue(int port) {

    if (port == 8463 && enviat == 0) {

        NETWORK_sendMessage(port);
        NETWORK_serialHandler();

        enviat = 1;
        rebut = 1;

        return valor;

    } else if (rebut == 0) {

        NETWORK_serialHandler();

        rebut = 1;
        enviat = 1;

    } else {
        if (NETWORK_reciveMessage() == -1) {
            //NETWORK_sendMessage(port);
            //NETWORK_sendSimple();
            //NETWORK_serialHandler();

        }
    }

    return 0;
}

void UTILS_updaterSlave(int port) {
    int i = 0;
    port_global = port;
    for (i = 0; i < 10; i++) {
        printf("------VUELTA BUCLE %d-----\n", i);
        UTILS_getCurrentValue(port);
        UTILS_updateCurrentValue(port, i);
        error = 0;
        sleep(10);
    }
}

void UTILS_updateCurrentValue(int port, int i) {
    valor = valor + 1;

    printf("VALOR INCREMENTADO %d\n", valor);

    if (i != 0) {
        NETWORK_sendMessage(port);

    } else {

        if (port == 8463) {
            NETWORK_sendSimple();

        } else {
            NETWORK_sendMessage(port);

        }
    }
}
