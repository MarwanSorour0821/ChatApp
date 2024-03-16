import com.prolink.GUI.LoginProLinkGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//
//        try{
//            transaction.begin();
//
//            UsersEntity Karim = new UsersEntity();
//            Karim.setUserId(0);
//            Karim.setUserName("KarimMohamed951");
//            Karim.setUserEmail("k.mohamed1633@gmail.com");
//            Karim.setUserPassword("yasserYasser12");
//            entityManager.persist(Karim);
//
//            transaction.commit();
//        } finally {
//            if (transaction.isActive()){
//                transaction.rollback();
//            }
//            entityManager.close();
//            entityManagerFactory.close();
//        }
        SwingUtilities.invokeLater(new Runnable() {
            //Overriding the run method in the runnable interface
            @Override
            public void run() {
                // Creating an instance of the MusicPlayerGUI class and setting its visibility to true.
                new LoginProLinkGUI().setVisible(true);
//                Song song = new Song("src/assets/Slime You Out.mp3");
//                System.out.println(song.getSongTitle());
//                System.out.println(song.getSongArtist());
            }
        });


    }
}
