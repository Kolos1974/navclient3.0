package utils;

import exception.XmlPrettifyException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlFormatter {

    public static String prettify(String xml) throws XmlPrettifyException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number", 2);

        try {
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StringWriter stringWriter = new StringWriter();
        StreamResult xmlOutput = new StreamResult(stringWriter);

        Source xmlInput = new StreamSource(new StringReader(xml));
        transformer.transform(xmlInput, xmlOutput);
        return xmlOutput.getWriter().toString();
        } catch (TransformerException e) {
            e.printStackTrace();
            throw new XmlPrettifyException(e.getMessage());
        }
    }
}
