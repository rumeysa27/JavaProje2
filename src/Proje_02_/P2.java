package Proje_02_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P2 {
    public static void main(String[] args) {

        // Scanner Class tanimlayin...
        Scanner scan = new Scanner(System.in);

        // Banka Islemleri Listesi tanimlayin..."Para_Yatir", "Para_Cek", "Transfer", "Cikis"...
        List<String> actions = new ArrayList<>(Arrays.asList("Para_Yatir", "Para_Cek", "Transfer", "Cikis"));

        // Banka musterileri kullanici adi listesi tanimlayin..."User1", "User2", "User3"...
        List<String> users = new ArrayList<>(Arrays.asList("User1", "User2", "User3"));

        // Banka musterileri sifreleri listesi tanimlayin..."password1", "password2", "password3"...
        List<String> passwords = new ArrayList<>(Arrays.asList("password1", "password2", "password3"));

        // Banka musterilerine ait banka hesap numaralari listesi tanimlayin...Her musteri 2 adet hesaba sahip olsun...
        List<List<String>> accountNumbers = new ArrayList<>();
        List<String> user1Accounts = new ArrayList<>(Arrays.asList("1234", "4321"));
        List<String> user2Accounts = new ArrayList<>(Arrays.asList("5678", "8765"));
        List<String> user3Accounts = new ArrayList<>(Arrays.asList("9999", "1111"));
        accountNumbers.add(user1Accounts);
        accountNumbers.add(user2Accounts);
        accountNumbers.add(user3Accounts);

        // Banka musterilerine ait banka hesaplarinda bulunan para miktarlarini tanimlayin..
        List<List<Integer>> currentFunds = new ArrayList<>();
        List<Integer> user1Money = new ArrayList<>(Arrays.asList(100 , 200));
        List<Integer> user2Money = new ArrayList<>(Arrays.asList(1000 , 2000));
        List<Integer> user3Money = new ArrayList<>(Arrays.asList(500, 400));
        currentFunds.add(user1Money);
        currentFunds.add(user2Money);
        currentFunds.add(user3Money);

        int currentUserIndex ;

        while (true){
            System.out.println("Please enter your username: ");
            String username = scan.nextLine();
            System.out.println("Please enter your password: ");
            String password = scan.nextLine();

            currentUserIndex = confirmUsernameAndPassword(users, passwords, username, password);
            if (currentUserIndex != -1){
                System.out.println("Basarili bir sekilde giris yaptiniz");
                break;
            }
            System.out.println("Sistemde kayitli boyle bir kullanici bulunamadi.. Tekrar deneyin");
        }

        // Actions starts here
        while (true){
            System.out.println("Yapmak istediginiz islemi seciniz...");
            for (int i = 0; i < actions.size(); i++) {
                System.out.println(actions.get(i)+ " - " + (i+1));
            }
            int userChoice = scan.nextInt();

            switch (userChoice){

                case 1:{
                    System.out.println("Bu bankamatik gecici olarak para yatirma islemine kapalidir...Daha sonra tekrar deneyin...");
                    break;
                }

                case 2: {

                    while (true){

                        System.out.println("Lutfen para cekmek istediginiz hesap numarasini giriniz...: ");
                        System.out.println(accountNumbers.get(currentUserIndex));
                        String chosenAccount = scan.next();
                        int chosenAccountIndex = confirmAccountNumber(accountNumbers.get(currentUserIndex), chosenAccount);
                        if (chosenAccountIndex == -1) {
                            System.out.println("Hatali hesap numarasi girdiniz...");
                            continue;
                        }
                        System.out.println("Lutfen para miktarini giriniz...: ");
                        int amountToWithdraw = scan.nextInt();
                        if (!withdraw(currentFunds.get(currentUserIndex), amountToWithdraw, chosenAccountIndex)){
                            break;
                        }
                    }
                    break;
                }

                case 3:{
                    System.out.println("Bu islemi su an gerceklestiremiyoruz...");
                    break;
                }

                case 4: System.exit(1);

                default:{
                    System.out.println("Basarili bir secim yapmadiniz...");
                }

            }
        }
    }

    private static boolean withdraw(List<Integer> funds, int amountToWithdraw, int index ) {

        if (funds.get(index) < amountToWithdraw){
            System.out.println("Hesabinizda yeterli bakiye bulunmamaktadir...");
            return true;
        }
        funds.set(index, funds.get(index) - amountToWithdraw);
        System.out.println("Lutfen paranizi aliniz, hesabinizda toplam "+funds.get(index)+" euro kalmistir...");
        return false;
    }

    public static int confirmAccountNumber(List<String> accounts, String chosenAccount){

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).equals(chosenAccount)) return i;
        }
        return -1;
    }

    private static int confirmUsernameAndPassword(List<String> users, List<String> passwords, String username, String password) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(username) && passwords.get(i).equals(password)){
                return i;
            }
        }
        return -1;
    }
}
