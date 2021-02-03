package animals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        while(!command.equals("Beast!")){
            String[] animalData= scanner.nextLine().split("\\s+");
            String name = animalData[0];
            int age = Integer.parseInt(animalData[1]);
            String gender = animalData[2];
            try {
                if (age <= 0) {
                    throw new IllegalArgumentException("Invalid input!");
                }
            }catch (IllegalArgumentException ex){
                System.out.println("Invalid input!");
                command=scanner.nextLine();
                continue;
            }
            try {
                if (!gender.equals("Male") && !gender.equals("Female")) {
                    throw new IllegalArgumentException("Invalid input!");
                }
            }catch(IllegalArgumentException ex){
                System.out.println("Invalid input!");
                command=scanner.nextLine();
                continue;
            }


            switch (command){
                case "Frog":
                    Frog frog = new Frog(name,age,gender);
                    System.out.println(frog.toString());
                    break;
                case "Dog":
                    Dog dog = new Dog(name,age,gender);
                    System.out.println(dog.toString());
                    break;
                case "Cat":
                    Cat cat = new Cat(name,age,gender);
                    System.out.println(cat.toString());
                    break;
                case "Kitten":
                    Kitten kitten = new Kitten(name,age);
                    System.out.println(kitten.toString());
                    break;
                case "Tomcat":
                    Tomcat tomcat = new Tomcat(name,age);
                    System.out.println(tomcat.toString());
                    break;
            }

            command=scanner.nextLine();
        }
    }
}
