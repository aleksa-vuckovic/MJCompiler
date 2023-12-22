package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.my.MyTab;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.symboltable.Tab;

public class SemanticAnalyzerTest {

	
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
			
			MJParser parser = new MJParser(lexer);
	        Symbol s = parser.parse();
	        
	        if (lexer.errorDetected) {
	        	log.info("Lekser je detektovao greske! Semanticka analiza se preskace.");
	        }
	        else if (parser.fatalErrorDetected) {
	        	log.info("Detektovane fatalne greske! Semanticka analiza se preskace.");
	        	log.info(s.value.toString());
	        }
	        else {
	        	if (parser.errorDetected) log.info("Greske su detektovane! Preskace se generisanje koda.");
	        	Program prog = (Program)(s.value);
				/*log.info(prog.toString(""));
				log.info("===================================");*/
				
				SemanticAnalyzer sem = new SemanticAnalyzer();
				prog.traverseBottomUp(sem);
	
				MyTab.dump();
				log.info("======================================");
				
				if (sem.error()) {
					log.info("Semanticki analizator je detektovao greske. Preskace se generisanje koda");
				}
				
				if (!parser.errorDetected && !sem.error()) {
					//code generation
				}
	        }
		} catch (IOException e1) { log.error(e1.getMessage(), e1); }
	}
}
