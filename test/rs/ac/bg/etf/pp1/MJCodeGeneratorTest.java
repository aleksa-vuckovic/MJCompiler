package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.my.MyTab;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;

public class MJCodeGeneratorTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws IOException {
		Logger log = Logger.getLogger(MJParserTest.class);
		File sourceCode = new File(Test.getInputFile(args));
		File objFile = new File(Test.getOutputFile(args));
		log.info("Compiling source file: " + sourceCode.getAbsolutePath());
		
		try(BufferedReader br = new BufferedReader(new FileReader(sourceCode));) {
			
			Yylex lexer = new Yylex(br);
			MJParser parser = new MJParser(lexer);
	        
			Symbol s = parser.parse();
	        
	        if (lexer.error()) {
	        	log.info("Lekser je detektovao greske! Semanticka analiza se preskace.");
	        }
	        else if (parser.fatalError()) {
	        	log.info("Detektovane fatalne greske! Semanticka analiza se preskace.");
	        }
	        else {
	        	Program prog = (Program)s.value;
	        	log.info("===========================================================");
	        	log.info("APSTRAKTNO SINTAKSNO STABLO");
	        	log.info(prog.toString(""));
	        	log.info("===========================================================");
	        
	        	if (parser.error()) log.info("Parser je detektovao greske. Preskace se generisanje koda.");
				
				SemanticAnalyzer sem = new SemanticAnalyzer();
				prog.traverseBottomUp(sem);
	
				log.info("===========================================================");
				log.info("TABELA SIMBOLA");
				MyTab.dump();
				log.info("===========================================================");
				
				if (sem.error()) {
					log.info("Semanticki analizator je detektovao greske. Preskace se generisanje koda");
				}
				
				if (!parser.error() && !sem.error()) {
					if(objFile.exists()) objFile.delete();
					
					CodeGenerator codeGenerator = new CodeGenerator();
					prog.traverseBottomUp(codeGenerator);
					Code.dataSize = sem.getStaticSize();
					Code.mainPc = codeGenerator.getStartPc();
					Code.write(new FileOutputStream(objFile));
					log.info("Objektni fajl je uspesno generisan!");
				}
	        }
	        
		} catch (Exception e1) { log.error(e1.getMessage(), e1); }
	}
	
}
