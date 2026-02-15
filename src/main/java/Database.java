package main.java;

import java.util.*;

//design pattern singleton
public class Database {
   private static Database instance;
   private Set<Server> servers;
   private Set<ResourceGroup> resourceGroups;
   private Set<Alert> alerts;
   private Database(){
       this.servers=new HashSet<>();
       this.resourceGroups=new HashSet<>();
       this.alerts=new HashSet<>();
   }
   public void reset(){
       servers.clear();
       resourceGroups.clear();
       alerts.clear();
   }
   public Set<ResourceGroup> getResourceGroups(){
       return resourceGroups;
   }
   public Set<Server> getServers(){
       return servers;
   }
   public void removeGroup(ResourceGroup group){
       resourceGroups.remove(group);
   }
   //singleton
   public static Database getInstance(){
       if(instance==null)
           instance=new Database();
       return instance;
   }
   public void addServer(Server server){
       servers.add(server);
   }
   public void addServers(Collection<? extends Server> serversCollection){
       servers.addAll(serversCollection);
   }
   public void addResourceGroup(ResourceGroup group){
       resourceGroups.add(group);
   }
   public void addResourceGroups(Collection<? extends ResourceGroup> groupCollection){
       resourceGroups.addAll(groupCollection);
   }
   public void addAlert(Alert alert){
       alerts.add(alert);
   }

}
