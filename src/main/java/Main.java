//Dragan Marius 322CB
package main.java;

import java.io.*;
public class Main {
    public static void executieComenziFisier(String filename){
        PrintWriter pw=null;
        try {
            //creating the input file
            File file = new File(filename+".in");
            FileReader fileReader = new FileReader(file);
            BufferedReader fileBuffered = new BufferedReader(fileReader);
            //creating the output file
            filename=filename+".out";
            File fileOut=new File(filename);
            FileWriter fw=new FileWriter(fileOut);
            pw=new PrintWriter(fw);
            //reading the header
            String line=fileBuffered.readLine();
            line=fileBuffered.readLine();
            CommandFactory f=new CommandFactory();
            int nrLine=1;
            while(line!=null){
                //for storing empty elements as well
                String [] arg=line.split("\\|",-1);
                Command command=null;
                String nameCommand=arg[0];
                //creating the command
                command=f.create(nameCommand,arg,nrLine);
                if(command!=null)
                    command.execute(pw);
                nrLine++;
                line=fileBuffered.readLine();
            }
            pw.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(pw!=null){
                pw.close();
            }
        }
    }
    public static void main(String[] args) {
        Database.getInstance().reset();
        //to have a new database for each test
        args[0]=args[0].toUpperCase();
        PathTypes type=PathTypes.valueOf(args[0]);
        if(args.length==2) {
            if(type==PathTypes.SERVERS)
                executieComenziFisier(args[1]);
            if(type==(PathTypes.GROUPS))
                executieComenziFisier(args[1]);
        } else if(args.length==4){
            if(type==(PathTypes.LISTENER)) {
                executieComenziFisier(args[1]);
                executieComenziFisier(args[2]);
                executieComenziFisier(args[3]);
            }
        }
    }
}