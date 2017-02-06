package by.tc.parser.main;

import by.tc.parser.bean.WebApp;
import by.tc.parser.parser.Parser;
import by.tc.parser.parser.exception.ParserException;
import by.tc.parser.parser.factory.ParserFactory;

public class Main {
    private final static String PATH = "res/web-app.xml";

    public static void main(String[] args) {
        ParserFactory parserFactory = ParserFactory.getInstance();
        Parser parser = parserFactory.getParser();

        WebApp webApp1 = null;
        WebApp webApp2 = null;
        WebApp webApp3 = null;

        try {
            webApp1 = parser.useDOM(PATH);
        } catch (ParserException e) {
            e.printStackTrace();
        }
        try {
            webApp2 = parser.useSAX(PATH);
        } catch (ParserException e) {
            e.printStackTrace();
        }
        try {
            webApp3 = parser.useStAX(PATH);
        } catch (ParserException e) {
            e.printStackTrace();
        }


        System.out.println(webApp1);
        System.out.println(webApp2);
        System.out.println(webApp3);


    }



}
