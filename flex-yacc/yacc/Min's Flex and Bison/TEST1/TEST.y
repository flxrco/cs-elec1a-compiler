%token INT_T PLUS MINUS MULTIPLY DIVIDE MODULO SEMICOLON ASSIGN LESS_THAN LESS_THAN_EQUAL GREATER_THAN GREATER_THAN_EQUAL NOT_EQUAL EQUAL AND OR 
SINGLE_LINE_COMMENT MULTI_LINE_OPEN MULTI_LINE_CLOSE IF_T THEN_T ELSE_T ENDIF_T WHILE_T ENDWHILE_T 
%token ID NUMBER

%%

statements : statement
			| statement SEMICOLON statements
			;
			
statement : if_statement
			| while_statement
			| assignment_statement
			;
			
expression : value arithmethic_operator expression
			| value
			;
			
if_statement : IF_T condition THEN_T statement 
				|IF_T condition THEN_T ELSE_T statement ENDIF_T
				;
				
while_statement : WHILE_T condition THEN_T statement ENDWHILE_T
				;
				
assignment_statement : INT_T ID ASSIGN expression
						;
					
condition : expression relation_operator expression
			| expression logical_operator expression
			;
			
relation_operator : LESS_THAN
					| LESS_THAN_EQUAL
					| GREATER_THAN
					| GREATER_THAN_EQUAL 
					| NOT_EQUAL 
					| EQUAL
					;
					
logical_operator : AND
					| OR
					;
					
arithmethic_operator : PLUS 
						| MINUS 
						| MULTIPLY 
						| DIVIDE 
						| MODULO
						;
						
value : NUMBER
		| ID
		;
%%

#include "lex.yy.c"
