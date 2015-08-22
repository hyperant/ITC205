package datamanagement;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import java.io.FileWriter;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.IOException;

import org.jdom.JDOMException;

public class XMLManager {
	private static XMLManager self = null;

	private Document doc;

	/**
	 * Get a copy of of the current XML Manager class
	 * 
	 * @return XML manager
	 */
	public static XMLManager getXML() {
		if (self == null)
			self = new XMLManager();
		
		return self;
	}

	/**
	 * The constructor method for the class.
	 */
	private XMLManager() {
		this.init();
	}

	/**
	 * Initialize the xml manager
	 * @exception RuntimeException on JDOMEXception and IOException
	 * @see RuntimeException
	 * @see JDOMException
	 * @see IOException
	 */
	public void init() {
		String xmlFile = AppProperties.getInstance().getProperties().getProperty("XMLFILE");
		
		try {
			SAXBuilder builder = new SAXBuilder();
			builder.setExpandEntities(true);
			this.doc = builder.build(xmlFile);
		} catch (JDOMException e) {
			System.err.printf("%s", "DBMD: XMLManager : init : caught JDOMException\n");
			throw new RuntimeException("DBMD: XMLManager : init : JDOMException");
		} catch (IOException e) {
			System.err.printf("%s", "DBMD: XMLManager : init : caught IOException\n");
			throw new RuntimeException("DBMD: XMLManager : init : IOException");
		}
	}

	
	/**
	 * Get the current xml document
	 * 
	 * @return the xml document
	 * @see Document
	 */
	public Document getDocument() {
		return this.doc;
	}

	/**
	 * Saves the current xml document to file
	 * @exception RuntimeException on IOException
	 * @see RuntimeException
	 * @see IOException
	 */
	public void saveDocument() {
		String xmlFile = AppProperties.getInstance().getProperties().getProperty("XMLFILE");
		FileWriter fout =null;
		
		try {
			fout = new FileWriter(xmlFile);
			
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			outputter.output(this.doc, fout);
		} catch (IOException ioe) {
			System.err.printf("%s\n", "DBMD : XMLManager : saveDocument : Error saving XML to " + xmlFile);
			throw new RuntimeException("DBMD: XMLManager : saveDocument : error writing to file");
		} finally {
			if(fout !=null) {
				try {
					fout.close();
				} catch (IOException e) {
					System.err.printf("%s\n", "Failed to close file " + xmlFile);
					throw new RuntimeException("DBMD: XMLManager : saveDocument : error closing file");
				}
			}
		}
	}
}
