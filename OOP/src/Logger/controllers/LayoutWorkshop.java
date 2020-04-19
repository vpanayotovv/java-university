package Logger.controllers;

import Logger.Interfaces.Layout;
import Logger.Interfaces.LayoutFactory;

public class LayoutWorkshop implements LayoutFactory {
    @Override
    public Layout produce(String type) {
        switch (type){
            case "SimpleLayout":
            return new SimpleLayout();
            case "XmlLayout":
                return new XmlLayout();
            default:
                throw new IllegalStateException("Now valid type of appender for "+ type + " param");
        }
    }
}
