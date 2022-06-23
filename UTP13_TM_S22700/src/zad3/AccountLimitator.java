package zad3;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class AccountLimitator implements VetoableChangeListener {

    private int min;

    public AccountLimitator(int min) {
        this.min = min;
    }

    public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException {
        double newVal = (double) e.getNewValue();

        if (newVal < min) {
            throw new PropertyVetoException(e.getPropertyName() + ": Unacceptable value change: " + e.getNewValue(), e);
        }
    }
}
