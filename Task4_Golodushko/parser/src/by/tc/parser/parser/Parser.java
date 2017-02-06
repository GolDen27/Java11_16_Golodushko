package by.tc.parser.parser;

import by.tc.parser.bean.WebApp;
import by.tc.parser.parser.exception.ParserException;

public interface Parser {
    WebApp useSAX(String path) throws ParserException;
    WebApp useStAX(String path) throws ParserException;
    WebApp useDOM(String path) throws ParserException;
}
