package main.java.dev.project.ui;

import java.util.List;
import java.util.Scanner;

import main.java.dev.project.bl.ComicBL;
import main.java.dev.project.bl.UserBL;
import main.java.dev.project.persistance.Comic;
import main.java.dev.project.persistance.User;

public class UserUI {
    public static Scanner scanner = new Scanner(System.in);

    // Giao dien ban dau
    public void loginUI() {
        System.out.println("1. Login");
        System.out.println("2. Create a new account");
        System.out.println("3. View & Search Comic");
        System.out.println("0. Exit ");
        System.out.print("Choice: ");
        String choice = scanner.nextLine().trim();
        while (choice == null || choice.equals("")) {
            System.out.println("Bạn vừa để trống, mời nhập lại");
            System.out.print("Choice: ");
            choice = scanner.nextLine().trim();
        }
        int choicee = Integer.parseInt(choice);
        switch (choicee) {
            case 1:
                insertLogin();
                break;
            case 2:
                insertCreateAccount();
                break;
            case 3:
                viewAndSearchComic();
                break;
            case 0:
                System.exit(0);
                System.out.println("hẹn gặp lại");
                break;
            default:
                break;
        }
    }

    // Giao dien admin login
    public void loginAdmin() {
        System.out.println("1. Log in under the management interface");
        System.out.println("2. Login according to the client interface");
        System.out.println("0. Exit ");
        System.out.print("Choice: ");
        String choice = scanner.nextLine().trim();
        while (choice == null || choice.equals("")) {
            System.out.println("Bạn vừa để trống, mời nhập lại");
            System.out.print("Chọn: ");
            choice = scanner.nextLine().trim();
        }
        int choicee = Integer.parseInt(choice);
        switch (choicee) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 0:
                System.exit(0);
                System.out.println("hẹn gặp lại");
                break;
            default:
                break;
        }
    }

    public void menuCustomer() {
        System.out.println("1. Account");
        System.out.println("2. View & Search Comic");
        System.out.println("3. Logout ");
        System.out.print("Choice: ");
        String choice = scanner.nextLine().trim();
        while (choice == null || choice.equals("")) {
            System.out.println("Bạn vừa để trống, mời nhập lại");
            System.out.print("Chọn: ");
            choice = scanner.nextLine().trim();
        }
        int choicee = Integer.parseInt(choice);
        switch (choicee) {
            case 1:
                
                break;
            case 2:
                viewAndSearchComic();
                break;
            case 3:
                loginUI();
                break;
            case 0:
                System.exit(0);
                System.out.println("hẹn gặp lại");
                break;
            default:
                break;
        }
    }

    // Nhap de login
    private static User inputLogin() {
        User login = new User();
        System.out.print("Enter username: ");
        login.setUserNameLogin(scanner.nextLine().toLowerCase().trim());
        System.out.print("Enter password: ");
        login.setPasswordLogin(scanner.nextLine());
        return login;
    }

    // Check login
    private static void insertLogin() {
        UserBL loginBL = new UserBL();
        System.out.println("--- LOGIN ---");
        int check = loginBL.addlogin(inputLogin());
        if (check == 1) {
            System.out.println("HELLO BOSS");
            new UserUI().loginAdmin();
        } else if (check > 1) {
            System.out.println("Logged in successfully");
            new UserUI().menuCustomer();
        } else {
            System.out.println("Can not find the corresponding account");
            insertLogin();
        }
    }

    // Nhap de tao tai khoan
    private static User inputCreateAccount() {
        User cre = new User();
        System.out.print("Enter email: ");
        cre.setEmailCre(scanner.nextLine().toLowerCase().trim());
        System.out.print("Enter username: ");
        cre.setUserNameCre(scanner.nextLine().toLowerCase().trim());
        int check = 1;
        while (check == 1) {
            System.out.print("Enter password: ");
            String p1 = scanner.nextLine();
            System.out.print("Confirm password: ");
            String p2 = scanner.nextLine();
            if (p1.equals(p2)) {
                cre.setPasswordCre(p2);
                check = 2;
            } else {
                System.out.println("\nInvalid password!\n\nPlease re-enter!\n");
            }
        }
        System.out.print("Enter YourName: ");
        cre.setYourNameCre(scanner.nextLine());
        return cre;
    }

    // Check tao tai khoan
    private static void insertCreateAccount() {
        UserBL creBL = new UserBL();
        System.out.println("\n--- Create a new account ---");
        if (creBL.creAcc(inputCreateAccount())) {
            System.out.println("Account successfully created!");
            new UserUI().loginUI();
        } else {
            System.err.println("Email or username is already taken!");
            System.err.println("Account creation failed!");
        }
    }

    // menu xem va tim kiem
    public void viewAndSearchComic() {
        System.out.println("1. Search by comic name");
        System.out.println("2. Category");
        System.out.println("3. Status ");
        System.out.println("4. Rank ");
        System.out.println("0. Exit ");
        System.out.print("Choice: ");
        String choice = scanner.nextLine().trim();
        while (choice == null || choice.equals("")) {
            System.out.println("Bạn vừa để trống, mời nhập lại");
            System.out.print("Chọn: ");
            choice = scanner.nextLine().trim();
        }
        int choicee = Integer.parseInt(choice);
        switch (choicee) {
            case 1:
                showComicByName();
                break;
            case 2:
                showComicByCategory();
                break;
            case 3:
                viewStatus();
                break;
            case 4:
                showComicByRank();
                break;
            case 0:
                System.exit(0);
                System.out.println("hẹn gặp lại");
                break;
            default:
                break;
        }
    }

    private static void showComicByStatus(Comic status) {
        System.out.println("\nComic List:");
        List<Comic> lst = new ComicBL().getComicByStatus(status);
        for (Comic comic : lst) {
            lst.get(1);
            System.out.println(comic);
        }
    }

    private static void showComicByRank() {
        System.out.println("\nComic List:");
        List<Comic> lst = new ComicBL().getComicByRank();
        for (Comic comic : lst) {
            lst.get(1);
            System.out.println(comic);
        }
    }

    public static void viewStatus() {
        Comic status = new Comic();
        System.out.println("1. Slacking");
        System.out.println("2. Finish");
        System.out.println("0. Exit ");
        System.out.print("Choice: ");
        String choice = scanner.nextLine().trim();
        while (choice == null || choice.equals("")) {
            System.out.println("Bạn vừa để trống, mời nhập lại");
            System.out.print("Chọn: ");
            choice = scanner.nextLine().trim();
        }
        int choicee = Integer.parseInt(choice);
        switch (choicee) {
            case 1:
                status.setStatus("slacking");
                showComicByStatus(status);
                break;
            case 2:
                status.setStatus("finish");
                showComicByStatus(status);
                break;
            case 0:
                System.exit(0);
                System.out.println("hẹn gặp lại");
                break;
            default:
                break;
        }
    }

    private static void showComicByCategory() {
        Comic category = new Comic();
        System.out.print("Enter category name: ");
        String cate = scanner.nextLine();
        category.setCategory(cate);
        System.out.println("\nComic List:");
        List<Comic> lst = new ComicBL().getComicByCategory(category);
        for (Comic comic : lst) {
            System.out.println(comic);
        }
    }

    private static void showComicByName() {
        Comic search = new Comic();
        System.out.print("Enter comic name: ");
        String name = scanner.nextLine();
        search.setNameComic(name);
        System.out.println("\nComic List:");
        List<Comic> lst = new ComicBL().getComicByName(search);
        for (Comic comic : lst) {
            System.out.println(comic);
        }
    }

}