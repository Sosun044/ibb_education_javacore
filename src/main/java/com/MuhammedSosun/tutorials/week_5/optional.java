package com.MuhammedSosun.tutorials.week_5;
import java.util.Optional;
class User{
    private String name;
    private String email;

    public User(String name,String email){
        this.name = name;
        this.email = email;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }

}
class fakeDataBase{
    public static Optional<User> getUserByEmail(String email){
        return "johndoe@example.com".equals(email) ? Optional.of(new User("John Doe","johndoe@example.com")):Optional.empty();
    }
}


public class optional {
    public static void main(String[] args) {
        String email1 = "johndoe@example.com";
        String email2 = "unknown@example.com";

        processUser(email1);
        System.out.println("------------------------------");
        processUser(email2);

    }

    private static void processUser(String email){
        Optional<User> userOptional = fakeDataBase.getUserByEmail(email);
        if (userOptional.isPresent()){
            System.out.println("✅ Kullanıcı bulundu: " + userOptional.get().getName());
        }
        else {
            System.out.println("Kullanıcı bulunamadı" + email);
        }
        if (email.isEmpty()){
            System.out.println("Kullanıcı boş");
        }

        userOptional.ifPresent(user -> System.out.println("Kullanıcı Eposta" + user.getEmail()));


        User defaultUser = userOptional.orElse(new User("Default User", "default@example.com"));
        System.out.println("Kullanıcı Veya Varsayılan   " + defaultUser);

        User generatedUser = userOptional.orElseGet(() -> new User("Generated User","Generated@example.com"));
        System.out.println("Üretilen Kullanıcı"+generatedUser.getName());

        try {
            User requiredUser = userOptional.orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
            System.out.println("🔥 Gerekli kullanıcı: " + requiredUser.getName());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("------------------------------");
    }

}