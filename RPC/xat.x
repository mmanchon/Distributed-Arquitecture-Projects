struct linea_xat{
	char *linea;
};


program PROGRAMA_XAT{
	version VERSION_XAT{
		int write (linea_xat) = 1;
		linea_xat getChat(void) = 2;
	}=1;
} = 0x20000001; 
