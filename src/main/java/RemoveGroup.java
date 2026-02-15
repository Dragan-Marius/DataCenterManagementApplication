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
            //IP existence check
            if(ipAddress.isEmpty()){
                throw  new MissingIpAddressException();
            }
            ResourceGroup group = null;
            //searching for group with requested IP
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
            //delete group
            database.removeGroup(group);
        }catch (MissingIpAddressException e){
            pw.println(arg[0]+": "+e.getMessage()+" ## line no: "+nrLine);
        }
    }
}
