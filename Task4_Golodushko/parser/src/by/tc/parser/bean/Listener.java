package by.tc.parser.bean;

public class Listener {

    String listenerClass;


    public String getListenerClass() {

        return listenerClass;
    }

    public void setListenerClass(String listenerClass) {
        this.listenerClass = listenerClass;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        Listener listener = (Listener) o;


        if (getListenerClass()==null) {
            if (listener.getListenerClass() != null) {
                return false;
            }
        } else if (!getListenerClass().equals(listener.getListenerClass())) {
            return false;
        }

        return true;

    }

    @Override
    public int hashCode() {
        return getListenerClass().hashCode();
    }

    @Override
    public String toString() {
        return "Listener{" +
                "listenerClass='" + listenerClass + '\'' +
                '}';
    }
}
