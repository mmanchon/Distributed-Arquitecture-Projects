#include "Utils.h"

int main(int argc,char **argv){

    if(argc != 2){
        write(STDOUT_FILENO, "ERROR: No hi ha suficients parametres\n", sizeof("ERROR: No hi ha suficients parametres\n"));
        exit(0);
    }

    int port = atoi(argv[1]);

    NETWORK_createConnectionServer(atoi(argv[1]),"127.0.0.1");
    while(1) {
        //Mostramos el menu para escoger que comportamiento quieren tener
        switch (UTILS_mostraMenu()) {
            case 1:
                NETWORK_createConnectionNextSlave(port + 1, "127.0.0.1");

                //Para tener un comportamiento de solo lectura
                UTILS_readerSlave(port);
                exit(0);
            case 2:
                NETWORK_createConnectionNextSlave(port + 1, "127.0.0.1");

                //Para tener un comportamiento de solo esccritura
                UTILS_updaterSlave(port);
                exit(0);
            default:
                //En caso de que hayan escogido una opcion erronea
                write(STDOUT_FILENO, BAD_OPTION, sizeof(BAD_OPTION));
                break;
        }
    }
    return 0;
}