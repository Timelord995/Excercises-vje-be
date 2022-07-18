package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Glavna {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Profesor> profesori = new ArrayList<>();
        ArrayList<Predmet> predmeti = new ArrayList<>();
        ArrayList<Student> studenti = new ArrayList<>();
        ArrayList<Ispit> ispiti = new ArrayList<>();

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

        for (int i = 0; i < 3; i++){
            System.out.println("Unesite " + (i + 1) + ". predmet");
            System.out.print("Unesite šifru predmeta: ");
            String sifraPredmeta = scanner.nextLine();
            System.out.print("Unesite naziv predmeta: ");
            String nazivPredmeta = scanner.nextLine();
            System.out.print("Unesite broj ECTS bodova za predmet " + "´" + nazivPredmeta + "´" + " : ");
            int brojECTSBodova = scanner.nextInt();
            System.out.println("Odaberite profesora: ");
            for (Profesor profesor: profesori){
                System.out.println(profesor.getSifraProf() + ". " + profesor.getImeProf() + " " + profesor.getPrezimeProf());
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

        for (int i = 0; i < 3; i++){
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

        for (int i = 0; i < 1; i++){
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


    }


}
