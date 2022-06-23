import java.beans.PropertyChangeSupport;
import java.beans.VetoableChangeSupport;
import java.util.HashSet;

public class SampleClass extends HashSet<String>{
    public double balance;
    private int id = 0;
    private String word;
    public PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);
    private VetoableChangeSupport veto = new VetoableChangeSupport(this);

    public SampleClass() {
    }

    protected SampleClass(int i, double d) {
    }

    public SampleClass(String word){
        this.word=word;
    }

    public void method1(String argument) { System.out.println("Method1 " + argument); }
    public void method2(String argument) { System.out.println("Method2 " + argument); }
    public void method3(String argument) { System.out.println("Method3 " + argument); }
    public void method4() { System.out.println("Method4"); }

    public int liczba (int i ){
        return i;
    }

    public String sentence (String sampleWord) {
        return sampleWord + word;
    }

    public static void main(String args[]) {
    }
}
