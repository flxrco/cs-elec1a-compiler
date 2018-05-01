
/* A Bison parser, made by GNU Bison 2.4.1.  */

/* Skeleton interface for Bison's Yacc-like parsers in C
   
      Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     LIT_INT = 258,
     LIT_STRING = 259,
     DEL_CODE_OP = 260,
     DEL_CODE_CL = 261,
     DEL_BRCKT_OP = 262,
     DEL_BRCKT_CL = 263,
     DEL_PAREN_OP = 264,
     DEL_PAREN_CL = 265,
     DEL_CURLY_OP = 266,
     DEL_CURLY_CL = 267,
     ARI_INC = 268,
     ARI_DEC = 269,
     ARI_ADD = 270,
     ARI_SUB = 271,
     ARI_DIV = 272,
     ARI_MOD = 273,
     ARI_MUL = 274,
     REL_GRTR_EQ = 275,
     REL_LESS_EQ = 276,
     REL_GRTR = 277,
     REL_LESS = 278,
     REL_EQ = 279,
     REL_NOT_EQ = 280,
     ASSIGN = 281,
     LOG_AND = 282,
     LOG_OR = 283,
     LOG_NOT = 284,
     KEY_WHILE = 285,
     KEY_FOR = 286,
     KEY_END = 287,
     KEY_STEP = 288,
     KEY_TO = 289,
     KEY_DO = 290,
     KEY_IF = 291,
     KEY_ELSE = 292,
     KEY_ELSEIF = 293,
     KEY_THEN = 294,
     KEY = 295,
     KEY_DECLARE = 296,
     KEY_STORE = 297,
     KEY_YIELD = 298,
     KEY_CLASS = 299,
     KEY_DATATYPE = 300,
     SEMICOLON = 301,
     COLON = 302,
     COMMA = 303,
     IDENTIFIER = 304,
     ANTITOKEN = 305
   };
#endif



#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

extern YYSTYPE yylval;


