#include <stdio.h>

int yyparse();
int yyparseContainer();
extern int yylineno;

int main(void)
{
	#if YYDEBUG == 1
	extern int yydebug;
	yydebug = 1;
	#endif
    return(yyparse());
}

int yyparseContainer() {
	return(yyparse());
}

void yyerror(char *s)
{
    fprintf(stderr, "Syntax error encountered on line %d. Terminating. %s\n", yylineno, s);
}