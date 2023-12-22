package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;

public class MJParserTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		
		Logger log = Logger.getLogger(MJParserTest.class);
		File sourceCode = new File(Test.getInputFile(args));
		log.info("Compiling source file: " + sourceCode.getAbsolutePath());
		
		try(BufferedReader br = new BufferedReader(new FileReader(sourceCode));) {
			
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();
	        
	        if (lexer.errorDetected) {
	        	log.info("Lekser je detektovao greske! Semanticka analiza se preskace.");
	        }
	        else if (p.fatalErrorDetected) {
	        	log.info("Detektovane fatalne greske! Semanticka analiza se preskace.");
	        }
	        else if (p.errorDetected) {
	        	log.info("Greske su detektovane! Preskace se generisanje koda.");
	        	Program prog = (Program)(s.value);
				log.info(prog.toString(""));
				log.info("===================================");
	        }
	        else {
	        	log.info("Parsiranje uspesno, 0 gresaka.");
	        	Program prog = (Program)(s.value);
				log.info(prog.toString(""));
				log.info("===================================");
	        }
	        
	        

			
			
		} catch (IOException e1) { log.error(e1.getMessage(), e1); }
	}
	
	
}
