package personLab01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<Person> people = new ArrayList<>();

        while(n-- >0){
            String[] tokens = scanner.nextLine().split("\\s+");
            try{
                Person person = new Person(tokens[0],tokens[1],Integer.parseInt(tokens[2]),Double.parseDouble(tokens[3]));
                people.add(person);
            }catch (IllegalArgumentException ex){
                System.out.println(ex.getLocalizedMessage());
            }
        }
        for (Person person : people) {
            System.out.println(person.toString());
        }
    }
}
