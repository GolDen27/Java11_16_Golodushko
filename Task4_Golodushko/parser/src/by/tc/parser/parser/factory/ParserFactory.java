package by.tc.parser.parser.factory;

import by.tc.parser.parser.Parser;
import by.tc.parser.parser.impl.ParserImpl;

public class ParserFactory {
    private static final ParserFactory instance = new ParserFactory();
    private final Parser parser = new ParserImpl();

    private ParserFactory () {}

    public static ParserFactory getInstance() {
        return instance;
    }

    public Parser getParser() {
        return parser;
    }
}
