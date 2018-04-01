import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {

    public static void main(String[] args) {
//        oef1();
//        oef2();
//        oef3();
//        oef4();
//        oef5();
//        System.out.println(oef6());
//        oef7();
//        oef8();
//        oef9();
//        oef10();
    }

    private static void oef1(){
        System.out.println("\nOef 1.1");

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        System.out.println(names);

        //Vervang bovenstaande code door een kortere versie, mbv lambda expressie

        Collections.sort(names, ((a,b) -> b.compareTo(a)));
        //Collections.sort(names, (Comparator.reverseOrder()));
        System.out.println(names);

        //lambda en stream
        names.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }
    private static void oef2(){
        System.out.println("\nOef1.2");

        JButton testButton = new JButton("Test Button");
        //Vervang volgende code door een kortere versie, mbv lambda expressie

//        testButton.addActionListener(new ActionListener(){
//            @Override public void actionPerformed(ActionEvent ae){
//                System.out.println("Clicked anonymous class definition");
//            }
//        });

        //lambda version
        testButton.addActionListener(ae -> System.out.println("Clicked lambda"));

        JFrame frame = new JFrame("Listener Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(testButton, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
    private static void oef3(){
        System.out.println("\nOef 1.3");

        List<Person> lectorenLijst = new ArrayList<>();
        lectorenLijst.add(new Person("Kelly", "Casal", "kelly.casalmosteiro@ap.be"));
        lectorenLijst.add(new Person("Olga", "Coutrin", "olga.coutrin@ap.be"));
        lectorenLijst.add(new Person("Philippe", "Possemiers", "philippe.possemiers@ap.be"));
        System.out.println("\n=== E-Mail ===");

        //Herschrijf onderstaande code zodat je gebruik maakt van lamda expressie en de foreach functie
        //voorzien bij de collections.
        for (Person l: lectorenLijst) {
            System.out.println(l.getEmail());
        }

        // lambda version
        lectorenLijst.forEach(l -> System.out.println(l.getEmail()));

        // lambda en stream
        lectorenLijst.stream()
                .map(l -> l.getEmail())
                .forEach(System.out::println);

    }
    private static void oef4(){
        System.out.println("\nOef 1.4");

        List<Person> lectorenLijst = new ArrayList<>();
        lectorenLijst.add(new Person("Kelly", "Casal", "kelly.casalmosteiro@ap.be"));
        lectorenLijst.add(new Person("Olga", "Coutrin", "olga.coutrin@ap.be"));
        lectorenLijst.add(new Person("Philippe", "Possemiers", "philippe.possemiers@ap.be"));
        System.out.println("\n=== print Name with stream ===");

        //Pas de volgende code zo aan dat je werkt met streams, filter en lambda
        for (Person lector : lectorenLijst)
        {
            if (lector.getEmail().equals("philippe.possemiers@ap.be"))
            {
                System.out.println(lector.getAchternaam());
                break;
            }
        }

        //stream, filter en lambda
        lectorenLijst.stream()
                .filter(l-> l.getEmail().equals("philippe.possemiers@ap.be"))
                .map(l -> l.getAchternaam())
                .forEach(System.out::println);
    }
    private static void oef5(){
        System.out.println("\nOef 1.5");

        //Pas de code van oef4 zo aan, dat je een nieuwe lijst maakt ahv de filter uit de vorige oefening.

        List<Person> lectorenLijst = new ArrayList<>();
        lectorenLijst.add(new Person("Kelly", "Casal", "kelly.casalmosteiro@ap.be"));
        lectorenLijst.add(new Person("Olga", "Coutrin", "olga.coutrin@ap.be"));
        lectorenLijst.add(new Person("Philippe", "Possemiers", "philippe.possemiers@ap.be"));
        System.out.println("\n=== print Name with stream ===");

        List<String> newLectorenLijst = lectorenLijst.stream()
                .filter(l-> l.getEmail().equals("philippe.possemiers@ap.be"))
                .map(l -> l.getAchternaam())
                .collect(Collectors.toList());

        newLectorenLijst.forEach(System.out::println);

    }
    private static Person oef6(){
        System.out.println("\nOef 1.6");

        List<Person> lectorenLijst = new ArrayList<>();
        lectorenLijst.add(new Person("Kelly", "Casal", "kelly.casalmosteiro@ap.be"));
        lectorenLijst.add(new Person("Olga", "Coutrin", "olga.coutrin@ap.be"));
        lectorenLijst.add(new Person("Philippe", "Possemiers", "philippe.possemiers@ap.be"));
//        for (Person lector : lectorenLijst)
//        {
//            if (lector.getEmail().equals("philippe.possemiers@ap.be") &&
//                    lector.getAchternaam().equals("Philippe Possemiers"))
//            {
//                return lector;
//            }
//        }
//        return null;

        //Pas de code aan zodat je met stream, filter, findany en orElse de code korter en overzichtelijker kan maken.

        return lectorenLijst.stream()
                .filter(l -> l.getEmail().equals("philippe.possemiers@ap.be") && l.getAchternaam().equals("Possemiers"))
                .findAny() // findAny geeft iets van type Optional terug
                .orElse(null); // orElse zorgt ervoor dat er geen optional wordt teruggegeven
    }
    private static void oef7(){
        System.out.println("\nOef 1.7");

        //Lees de file van de 4 letter woorden in. Maak een nieuwe file, dewelke de 4
        //letterwoorden bevat die een a bevatten, schrijf ze in uppercase.

        Stream<String> words;

        try {
            words = new BufferedReader(new FileReader("four-letter-words.txt")).lines();

            Files.write(Paths.get("four-letter-words-with-a.txt"), (Iterable<String>)words
                    .filter(w -> w.contains("A"))
                    ::iterator);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void oef8(){
        System.out.println("\nOef 1.8");

        //Lees de file van de 4 letter woorden in. Maak een nieuwe file, dewelke de 4 letterwoorden bevat die een a bevatten,
        //schrijf ze alfabetisch.

        Stream<String> words;

        try {
            words = new BufferedReader(new FileReader("four-letter-words.txt")).lines();

            Files.write(Paths.get("four-letter-words-with-a-alphabetical.txt"), (Iterable<String>)words
                    .filter(w -> w.contains("A"))
                    .sorted()
                    ::iterator);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void oef9(){
        System.out.println("\nOef 1.9");

        //Lees de file van de 4 letter woorden in. Maak een nieuwe file,met enkel de palindromen. vb: ABBA

        Stream<String> words;

        try {
            words = new BufferedReader(new FileReader("four-letter-words.txt")).lines();

            Files.write(Paths.get("four-letter-words-with-palindrome.txt"), (Iterable<String>)words
                    .filter(w -> w.equals(new StringBuilder(w).reverse().toString()))
                    ::iterator);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void oef10(){
        System.out.println("\nOef 1.10");

        List<City> cities = new ArrayList<>();
        cities.add(new City("New York",8550405, 86443));
        cities.add(new City("Boston",754987, 3864));
        cities.add(new City("LA",5439768, 1043));
        cities.add(new City("Chicago",2720546, 87));
        cities.add(new City("San Diego",1394928, 86443));

        //Zoek de stad/steden met meer dan 4.000.000 inwoners en minder dan 8.000 misdrijven. Druk de naam van de stad af
        //met stream en lambda.

        cities.stream()
                .filter(c -> c.getNrOfPeople() > 4000000 && c.getNrOfCrimes() < 8000)
                .map(c -> c.getName())
                .forEach(System.out::println);
    }
}
