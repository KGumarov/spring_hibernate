package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User carUser1 = new User(
              "CarUser3",
              "LastNameCarUser3",
              "car_user3@mail.ru",
              new Car("car1", 1));
      userService.add(carUser1);

      User carUser2 = new User(
              "CarUser4",
              "LastNameCarUser4",
              "car_user4@mail.ru",
              new Car("car2", 2));
      userService.add(carUser2);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         String strCar = user.getCar() == null ? "User without car" : user.getCar().toString();
         System.out.println("Car = " + strCar);
         System.out.println();
      }
      User user = userService.getUserByCar("car1", 1);
      System.out.println(user.getFirstName());

      context.close();
   }
}
