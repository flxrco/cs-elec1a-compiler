%{
#include <stdio.h>
int yylex();
void yyerror(const char *s);
%}

%token LIT_INT LIT_STRING 
%token DEL_CODE_OP DEL_CODE_CL 
%token DEL_BRCKT_OP DEL_BRCKT_CL 
%token DEL_PAREN_OP DEL_PAREN_CL 
%token DEL_CURLY_OP DEL_CURLY_CL 
%token ARI_INC ARI_DEC ARI_ADD ARI_SUB ARI_DIV ARI_MOD ARI_MUL 
%token REL_GRTR_EQ REL_LESS_EQ REL_GRTR REL_LESS REL_EQ REL_NOT_EQ 
%token ASSIGN 
%token LOG_AND LOG_OR LOG_NOT 
%token KEY_WHILE KEY_FOR KEY_END KEY_STEP KEY_TO KEY_DO
%token KEY_IF KEY_ELSE KEY_ELSEIF KEY_THEN KEY
%token KEY_DECLARE KEY_STORE KEY_YIELD KEY_CLASS KEY_DATATYPE 
%token SEMICOLON COLON COMMA
%token IDENTIFIER
%token ANTITOKEN

%locations

%%

program	:	DEL_CODE_OP class DEL_CODE_CL {printf("Parsing successful."); return 1;}

class	:	KEY_CLASS DEL_BRCKT_OP IDENTIFIER DEL_BRCKT_CL DEL_CURLY_OP statements DEL_CURLY_CL

statements 	:	statement statements_prime
statements_prime 	:	statement statements_prime
					|	/*epsilon*/

statement 	:	if_statement
			|	for_statement
			|	while_statement
			|	declaration_statement
			|	inner_statement SEMICOLON

inner_statement	:	assignment_statement
				|	yield_method

if_statement	:	KEY_IF conditions KEY_THEN inner_statement SEMICOLON elseif_statements

elseif_statements 	:	elseif_statement elseif_statements_prime
elseif_statements_prime	:	elseif_statement elseif_statements_prime
						|	/*epsilon*/

elseif_statement 	:	KEY_ELSEIF conditions KEY_THEN inner_statement SEMICOLON

while_statement	:	KEY_WHILE conditions KEY_DO inner_statement SEMICOLON

for_statement	:	KEY_FOR arithmetic_expression KEY_TO arithmetic_expression KEY_DO inner_statement KEY_STEP arithmetic_expression KEY_END SEMICOLON

declaration_statement	:	KEY_DECLARE COLON KEY_DATATYPE COLON IDENTIFIER ASSIGN assignment_value SEMICOLON

assignment_statement	:	IDENTIFIER ASSIGN assignment_value
assignment_value	:	arithmetic_expression
					|	store_method

arithmetic_expression 	: arithmetic_term arithmetic_expression_prime
arithmetic_expression_prime 	:	ARI_ADD arithmetic_term arithmetic_expression_prime
								|	ARI_SUB arithmetic_term arithmetic_expression_prime
								|	/*epsilon*/

arithmetic_term		:	arithmetic_factor arithmetic_term_prime
arithmetic_term_prime 	:	ARI_MUL arithmetic_factor arithmetic_term_prime
						|	ARI_MOD arithmetic_factor arithmetic_term_prime
						|	ARI_DIV arithmetic_factor arithmetic_term_prime
						|	/*epsilon*/

arithmetic_factor 	:	IDENTIFIER
					|	LIT_INT


relational_expression	:	arithmetic_expression relational_operator arithmetic_expression relational_expression_prime
relational_expression_prime	:	relational_operator arithmetic_expression relational_expression_prime
							|	/*epsilon*/

relational_operator	:	REL_GRTR_EQ
					|	REL_LESS_EQ
					|	REL_LESS
					|	REL_GRTR
					|	REL_EQ
					|	REL_NOT_EQ

logical_expression	:	logical_term logical_expression_prime
logical_expression_prime	:	logical_operator logical_term logical_expression_prime
							|	/*epsilon*/

logical_term	:	relational_expression

logical_operator	:	LOG_AND
					|	LOG_OR

conditions	:	logical_expression

store_method	:	KEY_STORE DEL_PAREN_OP DEL_PAREN_CL

yield_method	:	KEY_YIELD DEL_PAREN_OP yield_param DEL_PAREN_CL
yield_param	:	arithmetic_expression
			|	LIT_STRING

%%