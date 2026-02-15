package main.java;

import java.io.PrintWriter;

public class RemoveGroup extends Command {
    String [] arg;
    public RemoveGroup(String [] arg,int nrLine){
        super(nrLine);
        this.arg=arg;
    }

    @Override
    public void execute(PrintWriter pw) {
        try{
            String ipAddress=arg[1];
            //verificare existenta IP
            if(ipAddress.isEmpty()){
                throw  new MissingIpAddressException();
            }
            ResourceGroup group = null;
            //cautarea grup cu IP ul cerut
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
            //stergere grup
            database.removeGroup(group);
        }catch (MissingIpAddressException e){
            pw.println(arg[0]+": "+e.getMessage()+" ## line no: "+nrLine);
        }
    }
}
