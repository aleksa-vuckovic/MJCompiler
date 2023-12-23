package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.util.Log4JUtils;

public class MJLexerTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws IOException {
		Logger log = Logger.getLogger(MJLexerTest.class);
		File sourceCode = new File(Test.getInputFile(args));	
		log.info("Compiling source file: " + sourceCode.getAbsolutePath());
		
		try(Reader br = new BufferedReader(new FileReader(sourceCode));) {
			Yylex lexer = new Yylex(br);
			Symbol currToken = null;
			while ((currToken = lexer.next_token()).sym != sym.EOF) {
				if (currToken != null && currToken.value != null)
					log.info("Token string: " + currToken.toString() + " Token value: " + currToken.value.toString());
			}
			if (lexer.error()) {
				log.info("Lekser je detektovao greske!");
			}
		} catch (IOException e1) {
			log.error(e1.getMessage(), e1);
		}
	}
	
}
