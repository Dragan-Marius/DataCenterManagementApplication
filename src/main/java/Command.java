package main.java;
//command
import java.io.PrintWriter;

public abstract class Command {
    protected int nrLine;
    public Command(int nrLine){
        this.nrLine=nrLine;
    }
    //metoda pentru executia comenzii primite
    public abstract void execute(PrintWriter pw);
}
