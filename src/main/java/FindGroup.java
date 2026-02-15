package main.java;

import java.io.PrintWriter;

public class FindGroup extends Command {
    String [] arg;
    public FindGroup(String [] arg,int nrLine){
        super(nrLine);
        this.arg=arg;
    }

    @Override
    public void execute(PrintWriter pw) {
        try{
            //verificare existenta IP
            String ipAddress=arg[1];
            if(ipAddress.isEmpty()){
                throw  new MissingIpAddressException();
            }
            //cautarea grupului cu ip ul cerut
            ResourceGroup group = null;
            Database database = Database.getInstance();
            for (ResourceGroup aux : database.getResourceGroups()) {
                if (aux.getIpAddress().equals(ipAddress))
                    group = aux;
            }
            if (group == null) {
                pw.println(arg[0] + ": Group not found: ipAddress = " + ipAddress);
                return ;
            }
            pw.println(arg[0]+": "+ipAddress);
        }catch (MissingIpAddressException e){
            pw.println(arg[0]+": "+e.getMessage()+" ## line no: "+nrLine);
        }
    }
}
