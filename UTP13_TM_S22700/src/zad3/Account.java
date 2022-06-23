package zad3;

import java.beans.*;

public class Account {

    private double balance;
    private int id = 0;
    private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);
    private VetoableChangeSupport veto = new VetoableChangeSupport(this);

    public Account(int balance) {
        this.balance = balance;
        ++id;
    }

    public Account() {
        this(0);
        ++id;
    }

    public void deposit(double polskiZloty) throws PropertyVetoException {
        double newBalance = balance + polskiZloty;
        veto.fireVetoableChange(String.valueOf(id), balance, newBalance);
        propertyChange.firePropertyChange(String.valueOf(id), balance, newBalance);
        balance = newBalance;
    }

    public void withdraw(double polskiZloty) throws PropertyVetoException {
        double newBalance = balance - polskiZloty;
        veto.fireVetoableChange(String.valueOf(id), balance, newBalance);
        propertyChange.firePropertyChange(String.valueOf(id), balance, newBalance);
        balance = newBalance;
    }

    public void transfer(Account acc, double polskiZloty) throws PropertyVetoException {
        withdraw(polskiZloty);
        acc.deposit(polskiZloty);
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChange.addPropertyChangeListener(listener);
    }

    public synchronized void addVetoableChangeListener(VetoableChangeListener listener) {
        veto.addVetoableChangeListener(listener);
    }

    @Override
    public String toString() {
        return "Acc " + id + ": " + balance;
    }
}
