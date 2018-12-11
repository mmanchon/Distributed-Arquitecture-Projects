/**
 * @Author: Gabriel Cammany Ruiz
 * @Date:   25-10-2017
 * @Email:  ls30652@salleurl.edu
 * @Project: Práctica LSEat
 * @Filename: utils.h
 * @Last modified by:   Manel Manchón Gascó / Gabriel Cammany Ruiz
 * @Last modified time: 30-10-2017
 */

#ifndef _UTILS_H_
#define _UTILS_H_

//system includes
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <fcntl.h>

//own constant
#define WELCOME "Quin comportament tindrà el teu servidor?\n\t1. Lector\n\t2. Escriptor\nOpcio: "
#define BAD_OPTION "ERROR: Opció no reconeguda\n"
#define DEBUG 1


#include "Network.h"

int UTILS_mostraMenu();
/**
 * Funcion encargada de leer dinamicamente de un file descriptor
 * @param input Estructura donde se volcara los datos
 * @param fd File descriptor
 * @return Retorna 0 si no ha leido nada, -1 en caso de error de memoria y mayor a 0 cuando la lectura es correcta.
 */
int UTILS_readDynamic(char **input, int fd);

/**
 * With a given buffer extracts each word delimited by a delimiter
 * @param buffer
 * @param num
 * @param ...
 */

void UTILS_readerSlave(int port);

int UTILS_getCurrentValue(int port);

void UTILS_updaterSlave(int port);

void UTILS_updateCurrentValue(int port, int i);

#endif
