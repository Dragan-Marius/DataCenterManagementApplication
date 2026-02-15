package main.java;

import java.io.PrintWriter;

public class AddGroup extends Command {
    String [] arg;
    public AddGroup(String [] arg,int nrLine){
        super(nrLine);
        this.arg=arg;
    }
    public void execute(PrintWriter pw){
        Database database=Database.getInstance();
        try {
            //verificare existenta IP
            if (arg[1].isEmpty()) {
                throw new MissingIpAddressException();
            }
            String ipAddress=arg[1];
            //adaugare grup in baza de date
            ResourceGroup resourceGroup=new ResourceGroup(ipAddress);
            database.addResourceGroup(resourceGroup);
            //afisare mesaj succes
            pw.println(arg[0]+": "+ipAddress);

        }catch (MissingIpAddressException e){
            pw.println(arg[0]+": "+e.getMessage()+" ## line no: "+nrLine);
        }
    }
}
