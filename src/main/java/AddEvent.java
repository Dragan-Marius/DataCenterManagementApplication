package main.java;

import java.io.PrintWriter;

public class AddEvent extends Command{
    String [] arg;
    public AddEvent(String [] arg,int nrLine){
        super(nrLine);
        this.arg=arg;
    }
    public void execute(PrintWriter pw){
        AlertType alertType=AlertType.valueOf(arg[1]);
        Severity alertSeverity=Severity.valueOf(arg[2]);
        String ip_address=arg[3];
        String meesage=arg[4];
        Alert alertNew=new Alert(alertType,alertSeverity,meesage,ip_address);
        Database db=Database.getInstance();
        db.addAlert(alertNew);
        Server server=null;
        for(Server temp: db.getServers()){
            if(temp.getIpAddress().equals(ip_address))
                server=temp;
        }
        ResourceGroup group=null;
        for(ResourceGroup temp: db.getResourceGroups()){
            if(temp.getIpAddress().equals(ip_address))
                group=temp;
        }
        //notificarea grupului pentru a le permite membrilor grupului sa trateze alerta
        group.observer(server);
        server.addAlert(alertNew,pw);
    }
}
