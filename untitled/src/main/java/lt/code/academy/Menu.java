package lt.code.academy;

import lt.code.academy.data.Destytojas;
import lt.code.academy.data.Studentas;

import java.util.Map;
import java.util.Scanner;

public class Menu {

    Scanner scanner;
    Egzaminavimas egzaminavimas;
    Ivertinimas ivertinimas;
    Pazimys pazimys;

    public Menu(Scanner scanner, Egzaminavimas egzaminavimas, Ivertinimas ivertinimas, Pazimys pazimys) {
        this.scanner = scanner;
        this.egzaminavimas = egzaminavimas;
        this.ivertinimas = ivertinimas;
        this.pazimys = pazimys;
    }

    void VeiksmuMenu(Map <String, Studentas> studentai, Map <String, Destytojas> destytojai)
    {
        String pasirinkimas;
        do {
            System.out.println("""
                    |1| -> Destytojo prisijungimas
                    |2| -> Studento prisijungimas
                    |0| -> Baigti darba
                    """);
            pasirinkimas = scanner.nextLine();
            VeiksmuMenuPasirinkimas(pasirinkimas,studentai,destytojai);
        }while (!pasirinkimas.equals(0));
    }

    void VeiksmuMenuPasirinkimas(String pasirinkimas, Map <String, Studentas> studentai, Map <String, Destytojas> destytojai)
    {
        switch (pasirinkimas){
            case "1" -> DestytojoPrisijungimas(destytojai, pasirinkimas);
            case "2" -> StudentoPrisijungimas(studentai, pasirinkimas);
            case "0" -> System.out.println("Darbo pabaiga");
                default -> System.out.println("Blogas pasirinkimas");
        }
    }
    void DestytojoPrisijungimas(Map <String, Destytojas> destytojai, String pasirinkimas)
    {
        System.out.println("Iveskite savo id");
        String id = scanner.nextLine();
        Destytojas destytojas = destytojai.get(id);
        if (destytojas == null) {
            System.out.println("Tokio destytojo id neegzistuoja!");
            return;
        }
        System.out.println("Iveskite slaptazodi");
        String slaptazodis = scanner.nextLine();
        if(!destytojas.getSlaptazodis().equals(slaptazodis)){
            System.out.println("Neteisingas slaptazodis");
            return;
        }
        DestytojoMenu(destytojas);
    }
        void DestytojoMenu(Destytojas destytojas)
        {
            String pasirinkimas;
            do{
                System.out.println("""
                        |1| -> Sukurti egzamina
                        |2| -> Ivertinti egzamina
                        |3| -> Pazziureti studentu rezultatus
                        |0| -> Baigti darba
                        """);
                pasirinkimas = scanner.nextLine();
                DestytojoMeniuPasirinkimas(pasirinkimas, destytojas);
            }while (!pasirinkimas.equals("0"));
        }
        void DestytojoMeniuPasirinkimas(String pasirinkimas, Destytojas destytojas)
        {
            switch (pasirinkimas){
                case "1" -> egzaminavimas.SukurtiEgzamina(destytojas);
                case "2" -> ivertinimas.IvertinimoSistema(scanner);
                case "3" -> pazimys.rodytiIvertinimusDestytojui();
                case "0" -> System.out.println("Darbas baigtas");
                default -> System.out.println("Blogas pasirinkimas");
            }
        }
    void StudentoPrisijungimas(Map <String, Studentas>  studentai, String pasirinkimas)
    {
        System.out.println("Iveskite savo id");
        String id = scanner.nextLine();
        Studentas studentas = studentai.get(id);
        if(studentas == null){
            System.out.println("Tokio studento id neegzistuoja!");
            return;
        }
        System.out.println("Iveskite slaptazodi");
        String slaptazodis = scanner.nextLine();
        if(!studentas.getSlaptazodis().equals(slaptazodis)){
            System.out.println("Blogas slaptazodis");
            return;

        }
        StudentoMenu(studentas);
    }
    void StudentoMenu(Studentas studentas)
    {
        String pasirinkimas;
        do {
           System.out.println("""
                   |1| -> Laikyti egzammina
                   |2| -> Suzinoti rezultata
                   |0| -> Baigti darba
                   """);
            pasirinkimas = scanner.nextLine();
            StudentuMenuPasirinkimas(pasirinkimas, studentas);
        }while (!pasirinkimas.equals(0));
    }
    void StudentuMenuPasirinkimas(String pasirinkimas, Studentas studentas)
    {
        switch (pasirinkimas){
            case "1" -> egzaminavimas.LaikytiEgzamina(studentas);
            case "2" -> pazimys.rodytiIvertinimaStudentui(studentas);
            case "0" -> System.out.println("Darbas baigtas");
            default -> System.out.println("Blogas pasirinkimas");
        }
    }
}
