package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Glavna {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesite broj obrazovnih ustanova: ");
        Integer brojUstanova = scanner.nextInt();
        for (int i = 0; i < brojUstanova; i++){
            ArrayList<Profesor> profesori = new ArrayList<>();
            profesori = unosProfesora(scanner, profesori);

            ArrayList<Predmet> predmeti = new ArrayList<>();
            predmeti = unosPredmet(scanner, predmeti, profesori);

            ArrayList<Student> studenti = new ArrayList<>();
            studenti = unosStudenta(scanner, studenti);

            ArrayList<Ispit> ispiti = new ArrayList<>();
            ispiti = unosIspita(scanner, ispiti, studenti, predmeti);

            unosObrazovneUstanove(scanner, ispiti, studenti, predmeti, profesori);
        }

    }

    public static ArrayList<Profesor> unosProfesora(Scanner scanner, ArrayList<Profesor> profesori){

        for (int i = 0; i < 2; i++){
            System.out.println("Unesite " + (i + 1) + ". profesora");
            System.out.print("Unesite šifru profesora: ");
            String sifraProfesora = scanner.nextLine();
            System.out.print("Unesite ime profesora: ");
            String imeProfesora = scanner.nextLine();
            System.out.print("Unesite prezime profesora: ");
            String prezimeProfesora = scanner.nextLine();
            System.out.print("Unesite titulu profesora: ");
            String titula = scanner.nextLine();
            profesori.add(new Profesor(sifraProfesora, imeProfesora, prezimeProfesora, titula));
        }
        return profesori;
    }

    public static ArrayList<Predmet> unosPredmet(Scanner scanner, ArrayList<Predmet> predmeti, ArrayList<Profesor> profesori){


        for (int i = 0; i < 2; i++){
            System.out.println("Unesite " + (i + 1) + ". predmet");
            System.out.print("Unesite šifru predmeta: ");
            String sifraPredmeta = scanner.nextLine();
            System.out.print("Unesite naziv predmeta: ");
            String nazivPredmeta = scanner.nextLine();
            System.out.print("Unesite broj ECTS bodova za predmet " + "´" + nazivPredmeta + "´" + " : ");
            int brojECTSBodova = scanner.nextInt();
            System.out.println("Odaberite profesora: ");
            for (Profesor profesor: profesori){
                System.out.println(profesor.getSifraProf() + ". " + profesor.getIme() + " " + profesor.getPrezime());
            }
            System.out.print("Odabir >> ");
            // treba odabrat jednog od dva profesora
            Integer odobraniProfesor = scanner.nextInt();
            System.out.print("Unesite broj studenata za predmet " + "´" + nazivPredmeta + "´" + " : ");
            Integer brojStudenata = scanner.nextInt();
            scanner.nextLine();
            Student[] students = new Student[brojStudenata];
            predmeti.add(new Predmet(sifraPredmeta, nazivPredmeta, brojECTSBodova, profesori.get(odobraniProfesor - 1), students));
        }
        return predmeti;
    }

    public static ArrayList<Student> unosStudenta(Scanner scanner, ArrayList<Student> studenti){

        for (int i = 0; i < 2; i++){
            System.out.println("Unesite " + (i + 1) + ". studenta: ");
            System.out.print("Unesite ime studenta: ");
            String ime = scanner.nextLine();
            System.out.print("Unesite prezime studenta: ");
            String prezime = scanner.nextLine();
            System.out.print("Unesite datum rođenja studenta" + prezime + " " + ime + " u formatu (dd.MM.yyyy.):");
            String datumRodjenjaString = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
            LocalDate datumRodjenja = LocalDate.parse(datumRodjenjaString, formatter);
            System.out.print("Unesite JMBAG studenta: " + prezime + " " + ime + ": ");
            String jmbag = scanner.nextLine();
            studenti.add(new Student(ime, prezime, jmbag, datumRodjenja));
        }
        return studenti;
    }

    public static ArrayList<Ispit> unosIspita(Scanner scanner, ArrayList<Ispit> ispiti, ArrayList<Student> studenti, ArrayList<Predmet> predmeti){
        for (int i = 0; i < 2; i++){
            System.out.println("Odaberite predmet: ");
            for (Predmet predmet: predmeti){
                System.out.println(predmet.getSifra() + ". " + predmet.getNaziv());
            }
            System.out.print("Odabir >> ");
            Integer odabirPredmeta = scanner.nextInt();
            System.out.print("Odabir studenta: ");
            for (int j = 0; j < studenti.size(); j++){
                System.out.println((j + 1) + ". " + studenti.get(j).getIme() + " " + studenti.get(j).getPrezime());
            }
            System.out.print("Odabir >> ");
            Integer odabirStudenta = scanner.nextInt();
            System.out.print("Unesite ocjenu na ispitu (1-5) ");
            Integer ocjenaIspita = scanner.nextInt();
            scanner.nextLine();
            String ocjenaUText = null;

            System.out.println("Unesite datum i vrijeme ispita u formatu (dd.MM.yyyy.THH:mm): ");
            String datumIVrijemeIspita = scanner.nextLine();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
            LocalDateTime date = LocalDateTime.parse(datumIVrijemeIspita, dtf);
            ispiti.add(new Ispit(predmeti.get(odabirPredmeta - 1), studenti.get(odabirStudenta - 1),ocjenaIspita, date));

            if (ocjenaIspita.equals(1)){
                ocjenaUText = "nedovovoljan";
            }else if (ocjenaIspita.equals(2)){
                ocjenaUText = "dovoljan";
            } else if (ocjenaIspita.equals(3)){
                ocjenaUText = "dobar";
            } else if (ocjenaIspita.equals(4)){
                ocjenaUText = "vrlo dobar";
            } else if (ocjenaIspita.equals(5)){
                ocjenaUText = "izvrstan";
            }

            for (int j = 0; j < ispiti.size(); j++){
                System.out.println("Student " + ispiti.get(j).getStudent().getIme() + " " + ispiti.get(j).getStudent().getPrezime() + " je ostvario ocjenu " + ocjenaUText + " ´na predmetu ´ " + ispiti.get(j).getPredmet().getNaziv() + " ´");
            }

        }
        return ispiti;
    }

    public static void unosObrazovneUstanove(Scanner scanner, ArrayList<Ispit> ispiti, ArrayList<Student> studenti, ArrayList<Predmet> predmeti, ArrayList<Profesor> profesori){
        ObrazovnaUstanova ustanova = null;

        String nazivUstanove;
        Integer ocjenaZavrsnogRada;
        Integer ocjenaObranaZavrsnogRada;

        System.out.println("Odaberite obrazovnu ustanovu za navedene podatke koju želite unijeti (1 - Veleučilište Jave, 2 - Fakultet računarstva): ");
        Integer odabirUstanove = scanner.nextInt();
        scanner.nextLine();

        if (odabirUstanove == 1){
            ustanova = new VeleucilisteJave(nazivUstanove,predmeti,profesori,studenti,ispiti);
        }

        if (odabirUstanove == 2){
            ustanova = new FakultetRacunarstva(nazivUstanove,predmeti,profesori,studenti,ispiti);
        }
        System.out.println("Unesite naziv obrazovne ustanove: ");
        nazivUstanove = scanner.nextLine();

        for (int i = 0; i < studenti.size(); i++){
            System.out.println("Unesite ocjenu završnog rada za studenta " + studenti.get(i).getIme() + " " + studenti.get(i).getPrezime() + ": ");
            ocjenaZavrsnogRada = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Unesite ocjenu obrane završnog rada za studenta: " + studenti.get(i).getIme() + " " + studenti.get(i).getPrezime() + ": ");
            ocjenaObranaZavrsnogRada = scanner.nextInt();
            scanner.nextLine();

            if(ustanova instanceof VeleucilisteJave){
                System.out.println("Konačna ocjena studija studenta " + studenti.get(i).getIme() + " " + studenti.get(i).getPrezime() + " " +((VeleucilisteJave) ustanova).izracunajKonacnuOcjenuStudijaZaStudenta( ((VeleucilisteJave) ustanova).filtrirajIspitePoStudentu(ispiti,studenti.get(i)), ocjenaZavrsnogRada, ocjenaObranaZavrsnogRada));

            }

            if (ustanova instanceof FakultetRacunarstva){
                System.out.println("Konačna ocjena studija studenta " + studenti.get(i).getIme() +  " " +studenti.get(i).getPrezime() + " " +((FakultetRacunarstva) ustanova).izracunajKonacnuOcjenuStudijaZaStudenta(((FakultetRacunarstva) ustanova).filtrirajIspitePoStudentu(ispiti,studenti.get(i)), ocjenaZavrsnogRada, ocjenaObranaZavrsnogRada));
            }

        }

        if (ustanova instanceof VeleucilisteJave){
            Student najuspjesnijiStudent = ((VeleucilisteJave) ustanova).odrediNajuspjesnijegStudentaNaGodini(2018);
            System.out.println("Najbolji student 2018. godine je " + najuspjesnijiStudent.getIme() + " " + najuspjesnijiStudent.getPrezime() + " JMBAG: " + najuspjesnijiStudent.getJmbag());
        }

        if (ustanova instanceof FakultetRacunarstva){
            Student rektorovaNagrada = ((FakultetRacunarstva) ustanova).odrediStudentaZaRektorovuNagradu();
            Student najuspjesnijiStudent = ((FakultetRacunarstva) ustanova).odrediNajuspjesnijegStudentaNaGodini(2018);
            System.out.println("Najbolji student 2018. godine je " + najuspjesnijiStudent.getIme() + " " + najuspjesnijiStudent.getPrezime() + " JMBAG: " + najuspjesnijiStudent.getJmbag());
            System.out.println("Student koji je osvojio rektorovu nagradu je: " + rektorovaNagrada.getIme() + " " + rektorovaNagrada.getPrezime() + " JMBAG: " + rektorovaNagrada.getJmbag());
        }

    }


}
