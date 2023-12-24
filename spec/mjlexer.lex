
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;
import org.apache.log4j.*;

%%

%{
	private Logger log = Logger.getLogger(getClass());

	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}
	
	private boolean errorDetected = false;
	
	public boolean error() {
		return errorDetected;
	}

%}

%cup
%line
%column

%xstate COMMENT
%xstate MULTICOMMENT

%eofval{
	return new_symbol(sym.EOF, "EOF");
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n"	{ }
"\r"	{ }
"\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext()); }
"class" 	{ return new_symbol(sym.CLASS, yytext()); }
"namespace"	{ return new_symbol(sym.NAMESPACE, yytext()); }
"extends"	{ return new_symbol(sym.EXTENDS, yytext()); }
"void"		{ return new_symbol(sym.VOID, yytext()); }
"static"	{ return new_symbol(sym.STATIC, yytext()); }
"const"		{ return new_symbol(sym.CONST, yytext()); }
"return"	{ return new_symbol(sym.RETURN, yytext()); }
"break"		{ return new_symbol(sym.BREAK, yytext()); }
"continue"	{ return new_symbol(sym.CONTINUE, yytext()); }
"print"		{ return new_symbol(sym.PRINT, yytext()); }
"read"		{ return new_symbol(sym.READ, yytext()); }
"new"		{ return new_symbol(sym.NEW, yytext()); }
"if"		{ return new_symbol(sym.IF, yytext()); }
"else"		{ return new_symbol(sym.ELSE, yytext()); }
"for"		{ return new_symbol(sym.FOR, yytext()); }
"("   		{ return new_symbol(sym.LPAREN, yytext()); }
")"   		{ return new_symbol(sym.RPAREN, yytext()); }
"{"   		{ return new_symbol(sym.LBRACE, yytext()); }
"}"   		{ return new_symbol(sym.RBRACE, yytext()); }
"["   		{ return new_symbol(sym.LBRACK, yytext()); }
"]"   		{ return new_symbol(sym.RBRACK, yytext()); }
"."   		{ return new_symbol(sym.DOT, yytext()); }
","   		{ return new_symbol(sym.COMMA, yytext()); }
";"   		{ return new_symbol(sym.SEMI, yytext()); }
"::"   		{ return new_symbol(sym.SCOPE, yytext()); }
"+"   		{ return new_symbol(sym.ADD, yytext()); }
"-"   		{ return new_symbol(sym.SUB, yytext()); }
"*"   		{ return new_symbol(sym.MUL, yytext()); }
"/"   		{ return new_symbol(sym.DIV, yytext()); }
"%"   		{ return new_symbol(sym.MOD, yytext()); }
"=="   		{ return new_symbol(sym.EQ, yytext()); }
"!="   		{ return new_symbol(sym.NE, yytext()); }
">"   		{ return new_symbol(sym.GT, yytext()); }
">="   		{ return new_symbol(sym.GE, yytext()); }
"<"   		{ return new_symbol(sym.LT, yytext()); }
"<="   		{ return new_symbol(sym.LE, yytext()); }
"&&"   		{ return new_symbol(sym.AND, yytext()); }
"||"   		{ return new_symbol(sym.OR, yytext()); }
"="   		{ return new_symbol(sym.ASSIGN, yytext()); }
"++"   		{ return new_symbol(sym.INC, yytext()); }
"--"   		{ return new_symbol(sym.DEC, yytext()); }
"true"		{ return new_symbol(sym.BOOL, new Integer(1)); }
"false"		{ return new_symbol(sym.BOOL, new Integer(0)); }


"//" 		{ yybegin(COMMENT); }
"/*"		{ yybegin(MULTICOMMENT); }

[0-9]+  	{ return new_symbol(sym.NUM, new Integer (yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{ return new_symbol(sym.IDENT, yytext()); }
['].[']			{ return new_symbol(sym.CHAR, new Integer(yytext().charAt(1))); }
[']"\\n"[']		{ return new_symbol(sym.CHAR, new Integer(10)); }

. { log.error("Leksicka greska ("+yytext()+") u liniji "+(yyline+1) + "i koloni " + yycolumn + "."); errorDetected = true; }


<COMMENT> "\r\n" { yybegin(YYINITIAL); }
<COMMENT> "\n"	{ yybegin(YYINITIAL); }
<COMMENT> "\r"	{ yybegin(YYINITIAL); }
<COMMENT> . { }

<MULTICOMMENT> "\n"	{ }
<MULTICOMMENT> "\r"	{ }
<MULTICOMMENT> "\r\n" { }
<MULTICOMMENT> "*/"	{ yybegin(YYINITIAL); }
<MULTICOMMENT> . { }






