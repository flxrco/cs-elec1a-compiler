#include <stdio.h>
#include "lexer.h"

extern int yylex();
extern int yylineno;
extern char* yytext;
extern char* yyin;

char* typeNames[] = {
	NULL,
	"PROGNAME",
	"DEL_CODE",
	"DEL_PAREN",
	"DEL_BRCKT",
	"DEL_CURLY",
	"ASSIGN_OP",
	"ARTH_OP_BI",
	"ARTH_OP_UN",
	"REL_OP_BI",
	"LOGI_OP_BI",
	"LOGI_OP_UN",
	"KEY_FLOW_LOOP",
	"KEY_FLOW_COND",
	"KEY_DEC",
	"KEY_DTYPE",
	"KEY_FUNC",
	"IDENTIFIER",
	"LIT_INT",
	"LIT_STRING",
	"SEMICOLON"
};

char* getTypeName(int typeValue) {
	return typeNames[typeValue];
}

int main() {
	int token = yylex();
	while (token) {
		printf("%d: %s -> %s\n", yylineno, yytext, getTypeName(token));
		token = yylex();
	}

}