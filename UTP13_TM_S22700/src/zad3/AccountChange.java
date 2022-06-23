package zad3;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AccountChange implements PropertyChangeListener {


    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String message = ((double) e.getNewValue() < 0) ? ", balance < 0!" : "";
        System.out.println(e.getPropertyName()+": Value changed from " + e.getOldValue() + " to " + e.getNewValue() + message);
    }
}
