#include <stdio.h>
#include "lexer.h"

extern int yylex();
extern int yylineno;
extern char* yytext;
extern char* yyin;

char* typeNames[] = {
	NULL,
	"DEL_CODE_OP",
	"DEL_CODE_CL",

	"DEL_PAREN_OP",
	"DEL_PAREN_CL",

	"DEL_BRCKT_OP",
	"DEL_BRCKT_CL",

	"DEL_CURLY_OP",
	"DEL_CURLY_CL",

	"ASSIGN",

	"ARI_INC",
	"ARI_DEC",

	"ARI_ADD",
	"ARI_SUB",
	"ARI_DIV",
	"ARI_MUL",
	"ARI_MOD",

	"REL_GRTR_EQ",
	"REL_LESS_EQ",
	"REL_GRTR",
	"REL_LESS",
	"REL_EQ",
	"REL_NOT_EQ",

	"LOG_AND",
	"LOG_OR",

	"LOG_NOT",

	"KEY_WHILE",
	"KEY_FOR",

	"KEY_IF",
	"KEY_ELSE",

	"KEY_DECLARE",
	"KEY_STORE",
	"KEY_YIELD",
	"KEY_CLASS",
	"KEY_DATATYPE",

	"IDENTIFIER",

	"LIT_INT",
	"LIT_STRING",

	"SEMICOLON",
	"COLON"
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