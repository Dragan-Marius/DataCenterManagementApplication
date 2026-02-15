package main.java;
//command
import java.io.PrintWriter;

public abstract class Command {
    protected int nrLine;
    public Command(int nrLine){
        this.nrLine=nrLine;
    }
    //method for executing the received command
    public abstract void execute(PrintWriter pw);
}
