/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "xat.h"

int *
write_1_svc(linea_xat *argp, struct svc_req *rqstp)
{
	static int  result;

	/*
	 * insert server code here
	 */

	return &result;
}

linea_xat *
getchat_1_svc(void *argp, struct svc_req *rqstp)
{
	static linea_xat  result;

	/*
	 * insert server code here
	 */

	return &result;
}
