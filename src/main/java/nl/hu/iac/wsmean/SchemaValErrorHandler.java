package nl.hu.iac.wsmean;

import com.sun.xml.ws.developer.ValidationErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Created by Philip on 3/12/2017.
 */
public class SchemaValErrorHandler extends ValidationErrorHandler {
    @Override
    public void warning(SAXParseException exception) throws SAXException {
        handleException("Warning", exception);
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        handleException("Error", exception);
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        handleException("Fatal", exception);
    }

    private String handleException(String level, SAXParseException exception) throws SAXException {
        int line = exception.getLineNumber();
        int col = exception.getColumnNumber();
        String msg = exception.getMessage();
        throw new SAXException("[" + level + "] line nr: " + line + " column nr: " + col + " message: " + msg);
    }
}
