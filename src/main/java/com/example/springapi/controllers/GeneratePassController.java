package com.example.springapi.controllers;
import com.example.springapi.models.ErrorMessage;
import com.example.springapi.models.UserBean;
import com.example.springapi.models.daos.UserDAO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/rest")

public class GeneratePassController {

    private final UserDAO userdao;

    public GeneratePassController(UserDAO userdao) {
        this.userdao = userdao;
    }

    //http://localhost:8080/generatepass
    @PostMapping("/generatepass")
    Object register(@RequestBody UserBean user) {

        ErrorMessage error = new ErrorMessage();
        System.out.println("/login user" + user.toString());
        try {
            if (user.getLogin().isEmpty() || user.getLogin().isBlank()) {
                error.setError("Le pseudo est vide ou manquant");
                error.setCode(411);
                throw new Exception(error.getError());
            }
            if (user.getMail().isEmpty() || user.getMail().isBlank()) {
                error.setError("Le mail est vide ou manquant");
                error.setCode(415);
                throw new Exception(error.getError());
            }
            // SELECT * FROM userbean WHERE login = "user.getLogin()";
            List<UserBean> users = userdao.findAllByLogin(user.getLogin());


            if (users.isEmpty()) {
                error.setError("Il n'y a pas d'utilisateur s'appelant " + user.getLogin());
                error.setCode(410);
                throw new Exception(error.getError());
            }
            if(!users.get(0).getMail().equals(user.getMail())){
                error.setError("Mail incorrect!");
                error.setCode(416);
                throw new Exception(error.getError());
            }
            String randomPassword = newPass();
            //public void sendMail(String randomPassword);


            users.get(0).setTestPass(1);
            String randomPassHashed = BCrypt.hashpw(randomPassword, BCrypt.gensalt(12));
            users.get(0).setPassword(randomPassHashed);
            //UPDATE userbean SET password = "randomPassHashed" WHERE id_user = "users.get(0).getIdUser()";
            userdao.save(users.get(0));
            return users.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return error;
        }
    }
    public String newPass() {
        int length = 10;
        String symbol = "-/.^&*_!@%=+>)";
        String cap_letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String small_letter = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String finalString = cap_letter + small_letter + numbers + symbol;
        Random random = new Random();
        char[] password = new char[length];
        for (int i = 0; i < length; i++) {
            password[i] = finalString.charAt(random.nextInt(finalString.length()));
        }
        //String pass = new String(password);
        System.out.println(password);
        return String.valueOf(password);
    }

}