package lt.code.academy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lt.code.academy.data.Destytojas;
import lt.code.academy.data.Studentas;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ObjectMapper map = new ObjectMapper();
        Faker faker = new Faker();
        Scanner skaneris = new Scanner(System.in);
        DarbasSuFailais Failai =new DarbasSuFailais(map);
        GeneravimasStudDest generavimas = new GeneravimasStudDest(map,faker,Failai);
        Egzaminavimas egzaminavimas = new Egzaminavimas(skaneris, faker,map,Failai);
        Ivertinimas ivertinimas = new Ivertinimas(map, skaneris, faker, Failai, egzaminavimas);
        Pazimys pazimys = new Pazimys(map, skaneris);
        Menu menu = new Menu(skaneris, egzaminavimas, ivertinimas, pazimys);

        Map <String, Studentas> studentai = generavimas.NuskaitytiStudentus();
        Map <String, Destytojas> destytojai = generavimas.NuskaitytiDestytojus();

        menu.VeiksmuMenu(studentai,destytojai);
        //-------------------------------------//
        /*generavimas.GeneruotiStudentus();
        generavimas.GeneruotiDestytojus();
        */

    }
}