package by.tc.parser.parser.impl;

import by.tc.parser.bean.WebApp;
import by.tc.parser.parser.Parser;
import by.tc.parser.parser.exception.ParserException;
import by.tc.parser.parser.impl.dom.WebAppDOM;
import by.tc.parser.parser.impl.sax.WebAppSAX;
import by.tc.parser.parser.impl.stax.WebAppStAX;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ParserImpl implements Parser {
    public WebApp useSAX(String path) throws ParserException {
        XMLReader reader = null;
        WebApp webApp = null;
        try {
            reader = XMLReaderFactory.createXMLReader();
            WebAppSAX handler = new WebAppSAX();
            reader.setContentHandler(handler);
            reader.parse(new InputSource("res/web-app.xml"));
            webApp = handler.getWebApp();
        } catch (SAXException e) {
            throw new ParserException(e.getMessage(),e.getCause());
        } catch (IOException e) {
            throw new ParserException(e.getMessage(),e.getCause());
        }
        return webApp;
    }

    public WebApp useStAX(String path) throws ParserException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        WebApp webApp = null;
        try {
            InputStream input = new FileInputStream("res/web-app.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            WebAppStAX webAppStAX = new WebAppStAX();
            webApp = webAppStAX.process(reader);
        } catch (FileNotFoundException e) {
            throw new ParserException(e.getMessage(),e.getCause());
        } catch (XMLStreamException e) {
            throw new ParserException(e.getMessage(),e.getCause());
        }
        return webApp;
    }

    public WebApp useDOM(String path) throws ParserException {
        DOMParser parser = new DOMParser();
        WebApp webApp = null;
        try {
            parser.parse(new InputSource("res/web-app.xml"));
            WebAppDOM webAppDOM = new WebAppDOM(parser);
            webApp = webAppDOM.parse();
        } catch (SAXException e) {
            throw new ParserException(e.getMessage(),e.getCause());
        } catch (IOException e) {
            throw new ParserException(e.getMessage(),e.getCause());
        }
        return webApp;
    }
}
