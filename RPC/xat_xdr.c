/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include "xat.h"

bool_t
xdr_linea_xat (XDR *xdrs, linea_xat *objp)
{
	register int32_t *buf;

	 if (!xdr_pointer (xdrs, (char **)&objp->linea, sizeof (char), (xdrproc_t) xdr_char))
		 return FALSE;
	return TRUE;
}
