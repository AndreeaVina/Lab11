package compulsory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager{
    private static EntityManagerFactory managerFactory = null;
    private Manager(){

    }
    public static EntityManagerFactory getInstance(){
        if(managerFactory != null)
            return managerFactory;
        else managerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        return managerFactory;
    }
    public static void setManagerFactory(EntityManagerFactory managerFactory){
      Manager.managerFactory = managerFactory;
    }
}