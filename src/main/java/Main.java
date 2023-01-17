import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    private static SessionFactory sessionFactory = null;


    public static void main(String[] args) {

        try{
            setUp();
            leer(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }




    }



    protected static void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }


    private static void guardar(String nombre, double saldo) {
        PersonasEntity persona = new PersonasEntity(nombre, saldo);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(persona);
        transaction.commit();
        System.out.println(id);
        sessionFactory.close();
    }


    private static void leer(int id) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        PersonasEntity persona = session.load(PersonasEntity.class, id);//   PersonasEntity persona = session.get(PersonasEntity.class, id); // Esta línea también funcionaría como la anterior
        System.out.println(persona);
        transaction.commit();
        sessionFactory.close();
    }


    private static void actualizar(int id,String nombre, double saldo) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PersonasEntity persona = session.get(PersonasEntity.class,id);
        persona.setNombre(nombre);
        persona.setSaldo(saldo);
        // session.saveOrUpdate(persona);       // session.merge(persona);
        session.update(persona);
        transaction.commit();
        sessionFactory.close();
    }
}
