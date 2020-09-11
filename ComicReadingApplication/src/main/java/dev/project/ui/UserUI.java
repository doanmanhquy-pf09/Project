package dev.project.ui;

import java.util.List;
import java.util.Scanner;

import dev.project.bl.UserBL;
import dev.project.persistance.User;

public class UserUI {
    public static Scanner scanner = new Scanner(System.in);

    public void welcome() {
        System.out.println();
        String a[] = { "W", "e", "l", "c", "o", "m", "e", " ", "t", "o", " ", "T", "r", "u", "y", "e", "n", " ", "V",
                "u", "i", "\n", "A", "p", "p", "l", "i", "c", "a", "t", "i", "o", "n", " ", "d", "e", "v", "e", "l",
                "o", "p", "e", "d", " ", "b", "y", " ", "P", "F", "0", "9", "_", "G", "R", "O", "U", "P", "_", "2" };
        try {
            for (int i = 0; i < a.length; i++) {
                System.out.print(a[i]);
                Thread.sleep(100);
            }
            System.out.println("\nLoading...99%");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        new UserUI().UI(0);
    }

    public void UI(int ID) {
        System.out.println(" ______________TRUYEN_VUI______________");
        System.out.println("|                                      |");
        System.out.println("|  Login                          <1>  |");
        System.out.println("|  Register                       <2>  |");
        System.out.println("|  View & search comic            <3>  |");
        System.out.println("|  Close                          <0>  |");
        System.out.println("|__________(c) PF09 ~ GROUP 2__________|");
        System.out.print(".Choose: ");
        switch (interger()) {
            case 1:
                insertLogin();
                break;
            case 2:
                insertCreateAccount(ID);
                break;
            case 3:
                new ComicUI().viewAndSearchComic(ID);
                break;
            case 0:
                System.out.println("BYE....!");
                System.exit(0);
                break;
            default:
                System.out.println("(*) The item you selected is not available!");
                UI(ID);
                break;
        }
    }

    public void CaseUI(int ID) {
        System.out.println(" ______________HELLO_BOSS______________");
        System.out.println("|                                      |");
        System.out.println("|  Management interface           <1>  |");
        System.out.println("|  Customer interface             <2>  |");
        System.out.println("|  Logout                         <0>  |");
        System.out.println("|__________(c) PF09 ~ GROUP 2__________|");
        System.out.print(".Choose: ");
        switch (interger()) {
            case 1:
                menuAdmin(ID);
                break;
            case 2:
                menuCustomer(ID);
                break;
            case 0:
                UI(0);
                break;
            default:
                System.out.println("(*) The item you selected is not available!");
                CaseUI(ID);
                break;
        }
    }

    /*-----------------------------           admin          -----------------------------------------*/
    /* */
    /* */
    /* */
    /* */
    public void menuAdmin(int ID) {
        System.out.println(" _______________HOME_PAGE______________");
        System.out.println("|                                      |");
        System.out.println("|  Update Account                 <1>  |");
        System.out.println("|  Manage customer                <2>  |");
        System.out.println("|  Manage comic                   <3>  |");
        System.out.println("|  Logout                         <0>  |");
        System.out.println("|__________(c) PF09 ~ GROUP 2__________|");
        System.out.print(".Choose: ");
        switch (interger()) {
            case 1:
                updateAdmin(ID);
                break;
            case 2:
                manageUser(ID);
                break;
            case 3:
                new ComicUI().manageComic(ID);
                break;
            case 0:
                UI(0);
                break;
            default:
                System.out.println("(*) The item you selected is not available!");
                menuAdmin(ID);
                break;
        }

    }

    public static void manageUser(int ID) {
        System.out.println(" ______________Manage_User_____________");
        System.out.println("|                                      |");
        System.out.println("|  Show all customers             <1>  |");
        System.out.println("|  Search for customer username   <2>  |");
        System.out.println("|  Add a customer                 <3>  |");
        System.out.println("|  Update customer                <4>  |");
        System.out.println("|  Delete customer                <5>  |");
        System.out.println("|  Back                           <0>  |");
        System.out.println("|__________(c) PF09 ~ GROUP 2__________|");
        System.out.print(".Choose: ");
        switch (interger()) {
            case 1:
                showAllUser(ID);
                break;
            case 2:
                showAllCustomerByUserName(ID);
                break;
            case 3:
                insertCreateAccount(ID);
                break;
            case 4:
                inputUpdateID(ID);
                break;
            case 5:
                inputDeleteUserID(ID);
                break;
            case 0:
                new UserUI().menuAdmin(ID);
                break;
            default:
                System.out.println("(*) The item you selected is not available!");
                manageUser(ID);
                break;
        }
    }

    private static User inputUpdateAdmin(int ID) {
        User user = new User();
        System.out.print("Enter a new username: ");
        String name = scanner.nextLine().toLowerCase().trim();
        user.setUserName(blankOrNull(name, 2));
        int c = 1;
        while (c == 1) {
            System.out.print("Enter your new password: ");
            String p1 = scanner.nextLine().trim();
            user.setTemporary(blankOrNull(p1, 1));

            System.out.print("Re-enter your new password: ");
            String p2 = scanner.nextLine().trim();
            user.setTemporary2(blankOrNull(p2, 1));

            if (p1.equals(p2)) {
                user.setPassword(p2);
                c = 2;
            } else {
                System.out.println("\nInvalid password!\n\nPlease re-enter!\n");
            }
        }
        user.setID(ID);
        return user;
    }

    private static User inputUpdateID(int ID) {
        User user = new User();
        System.out.print("Enter customer id: ");
        user.setID(interger());
        List<User> lst = new UserBL().getIdCustomer(user);
        if (lst.isEmpty()) {
            System.err.println("<!> No corresponding user <!>");
            manageUser(ID);
        } else {
            if (user.getID() == 1) {
                System.err.println("(*) Update failed!");
                manageUser(ID);
            } else {
                updateProfiles(user.getID());
            }
        }

        return user;
    }

    public static void updateProfiles(int ID) {
        System.out.println(" ____________UPDATE_PROFILE____________");
        System.out.println("|                                      |");
        System.out.println("|  Update your name               <1>  |");
        System.out.println("|  Update date of birth           <2>  |");
        System.out.println("|  Update gender                  <3>  |");
        System.out.println("|  Update address                 <4>  |");
        System.out.println("|  Update email                   <5>  |");
        System.out.println("|  Update password                <6>  |");
        System.out.println("|  Back                           <0>  |");
        System.out.println("|__________(c) PF09 ~ GROUP 2__________|");
        System.out.print(".Choose: ");
        switch (interger()) {
            case 1:
                System.out.print("Enter new name: ");
                updateUser(ID, 1);
                break;
            case 2:
                System.out.print("Enter date of birth: ");
                updateUser(ID, 2);
                break;
            case 3:
                System.out.print("Enter gender(M/F): ");
                updateUser(ID, 3);
                break;
            case 4:
                System.out.print("Enter address: ");
                updateUser(ID, 4);
                break;
            case 5:
                System.out.print("Enter new email: ");
                updateUser(ID, 5);
                break;
            case 6:
                System.out.print("Enter new password: ");
                updateUser(ID, 6);
                break;
            case 0:
                manageUser(ID);
                break;
            default:
                System.out.println("(*) The item you selected is not available!");
                updateProfiles(ID);
                break;
        }
    }

    private static void updateUser(int ID, int t) {
        UserBL userBL = new UserBL();
        if (userBL.updateAcc(inputUpdateUser(ID, t))) {
            System.out.println("(*) Update successfully!");
            updateProfiles(ID);
        } else {
            System.err.println("(*) Update failed!");
            updateProfiles(ID);
        }
    }

    private static void inputDeleteUserID(int ID) {
        User user = new User();
        UserBL userBL = new UserBL();
        System.out.print("Enter customer id: ");
        user.setID(interger());
        List<User> lst = new UserBL().getIdCustomer(user);
        if (lst.isEmpty()) {
            System.err.println("<!> No corresponding user <!>");
            manageUser(ID);
        } else {
            if (user.getID() == 1) {
                System.err.println("(*) Delete failed!");
                manageUser(ID);
            } else {
                userBL.deleteAcc(user);
                System.out.println("(*) Deleted successfully!");
                manageUser(ID);
            }
        }
    }

    private static void updateAdmin(int ID) {
        UserBL userBL = new UserBL();
        System.out.println("Update administrator");
        if (userBL.updateAdmin(inputUpdateAdmin(ID))) {
            System.out.println("(*) Update successfully!");
            new UserUI().menuAdmin(ID);
        } else {
            System.err.println("(*) Update failed!");
            new UserUI().menuAdmin(ID);
        }
    }

    private static void showAllUser(int ID) {
        UserBL ubl = new UserBL();
        headUserListAD();
        List<User> lst = ubl.getAllUser();
        if (lst.isEmpty()) {
            System.out.println("<!> There is no corresponding customer book <!>");
            showAllUser(ID);
        }
        for (User user : lst) {
            ListAllUser(user);
        }
        System.out.println(
                "|______|___________________________|______________|__________|___________|_____________________|________________|________________|");
        showUpdateAndDelete(ID);
    }

    private static void showAllCustomerByUserName(int ID) {
        User search = new User();
        System.out.print("Enter Username customer: ");
        String userName = scanner.nextLine().toLowerCase().trim();
        search.setUserName(blankOrNull(userName, 2));
        headUserListAD();
        List<User> lst = new UserBL().getCustomerByUserName(search);
        if (lst.isEmpty()) {
            System.out.println("<!> No corresponding comic book <!>");
            showAllCustomerByUserName(ID);
        }
        for (User user : lst) {
            ListAllUser(user);
        }
        System.out.println(
                "|______|___________________________|______________|__________|___________|_____________________|________________|________________|");
        showUpdateAndDelete(ID);

    }

    public static void headUserListAD() {
        System.out.println(
                "                                                            CUSTOMER LIST                               ");
        System.out.println(
                " ________________________________________________________________________________________________________________________________");
        System.out.println(
                "|   ID |                      Name | Date of birth|   Gender |   Address |               Email |       UserName |       Password |");
        System.out.println(
                "|______|___________________________|______________|__________|___________|_____________________|________________|________________|");
    }

    public static void ListAllUser(User user) {
        System.out.format("|%5d |%26s |%13s |%9s |%10s |%20s |%15s |%15s |\n", user.getID(), user.getYourName(),
                user.getDateOfBirth(), user.getGender(), user.getAddress(), user.getEmail(), user.getUserName(),
                user.getPassword());
        System.out.println(
                "|------|---------------------------|--------------|----------|-----------|---------------------|----------------|----------------|");
    }

    public static void showUpdateAndDelete(int ID) {
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "|                                                    Update  <1> | <2>  Delete                                                   |");
        System.out.println(
                "|                                                             <0> BACK                                                           |");
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------------------");
        System.out.print(".Choose: ");
        switch (interger()) {
            case 1:
                inputUpdateID(ID);
                break;
            case 2:
                inputDeleteUserID(ID);
                break;
            case 0:
                manageUser(ID);
                break;
            default:
                System.out.println("(*) The item you selected is not available!");
                showUpdateAndDelete(ID);
                break;
        }
    }

    /*-----------------------------           customer          -----------------------------------------*/
    /* */
    /* */
    /* */
    /* */
    public void menuCustomer(int ID) {
        System.out.println(" _______________HOME_PAGE______________");
        System.out.println("|                                      |");
        System.out.println("|  Account                        <1>  |");
        System.out.println("|  View & Search Comic            <2>  |");
        System.out.println("|  Logout                         <0>  |");
        System.out.println("|__________(c) PF09 ~ GROUP 2__________|");
        System.out.print(".Choose: ");
        switch (interger()) {
            case 1:
                showUserInfo(ID);
                break;
            case 2:
                new ComicUI().viewAndSearchComic(ID);
                break;
            case 0:
                UI(0);
                System.out.println("(*) Successfully logged out!");
                break;
            default:
                System.out.println("(*) The item you selected is not available!");
                menuCustomer(ID);
                break;
        }
    }

    public void updateInfo(int ID) {
        System.out.println("----------------------------------------");
        System.out.println("|     Update  <1> | <2> Delete account |");
        System.out.println("|             <0> BACK                 |");
        System.out.println("----------------------------------------");
        System.out.print(".Choose: ");
        switch (interger()) {
            case 1:
                updateProfile(ID);
                break;
            case 2:
                deleteAcc(ID);
                break;
            case 0:
                menuCustomer(ID);
                break;
            default:
                System.out.println("(*) The item you selected is not available!");
                updateInfo(ID);
                break;
        }
    }

    public void updateProfile(int ID) {
        System.out.println(" ____________UPDATE_PROFILE____________");
        System.out.println("|                                      |");
        System.out.println("|  Update your name               <1>  |");
        System.out.println("|  Update date of birth           <2>  |");
        System.out.println("|  Update gender                  <3>  |");
        System.out.println("|  Update address                 <4>  |");
        System.out.println("|  Update email                   <5>  |");
        System.out.println("|  Update password                <6>  |");
        System.out.println("|  Back                           <0>  |");
        System.out.println("|__________(c) PF09 ~ GROUP 2__________|");
        System.out.print(".Choose: ");
        switch (interger()) {
            case 1:
                System.out.print("Enter new name: ");
                insertUpdateUser(ID, 1);
                break;
            case 2:
                insertUpdateUser(ID, 2);
                break;
            case 3:
                System.out.print("Enter gender(M/F): ");
                insertUpdateUser(ID, 3);
                break;
            case 4:
                System.out.print("Enter address: ");
                insertUpdateUser(ID, 4);
                break;
            case 5:
                System.out.print("Enter new email: ");
                insertUpdateUser(ID, 5);
                break;
            case 6:
                System.out.print("Enter new password: ");
                insertUpdateUser(ID, 6);
                break;
            case 0:
                menuCustomer(ID);
                break;
            default:
                System.out.println("(*) The item you selected is not available!");
                updateProfile(ID);
                break;
        }
    }

    private static User inputUpdateUser(int ID, int t) {
        User update = new User();
        if (t == 1) {
            update.setTemporary("name");
            String error = scanner.nextLine().trim();
            update.setTemporary2(blankOrNull(error, 1));
        } else if (t == 2) {
            update.setTemporary("date_of_birth");
            System.out.print("Enter year(1900 ~ 2020): ");
            String year = "" + year() + "";
            System.out.print("Enter month(1 ~ 12): ");
            String month = "" + month() + "";
            System.out.print("Enter day: ");
            String day = "" + day(year, month) + "";
            update.setTemporary2(year + "/" + month + "/" + day);
            System.out.println(update.getTemporary2());
        } else if (t == 3) {
            update.setTemporary("gender");
            String error = scanner.nextLine().toUpperCase().trim();
            while (error == null || error.equals("") || !error.equals("M") && !error.equals("F")) {
                System.out.println("<!> Please enter M or F <!>");
                System.out.print("Retype: ");
                error = scanner.nextLine().toUpperCase().trim();
            }
            update.setTemporary2(error);
        } else if (t == 4) {
            update.setTemporary("address");
            String error = scanner.nextLine().trim();
            update.setTemporary2(blankOrNull(error, 1));
        } else if (t == 5) {
            update.setTemporary("email");
            String error = scanner.nextLine().toLowerCase().trim();
            update.setTemporary2(blankOrNull(error, 2));
        } else if (t == 6) {
            update.setTemporary("password");
            String error = scanner.nextLine().trim();
            update.setTemporary2(error);
        }
        update.setID(ID);
        return update;
    }

    public static String year() {
        int year = 0;
        String yearr = "";
        boolean check = false;
        while (!check) {
            try {
                String enter = scanner.nextLine().trim();
                while (enter == null || enter.equals("")) {
                    System.out.println("<!> Don't leave it blank <!>");
                    System.out.print(".Retype: ");
                    enter = scanner.nextLine().trim();
                }
                year = Integer.parseInt(enter);
                if (1900 < year && year < 2020) {
                    check = true;
                } else {
                    System.out.println("<!> Found the year of birth is not real <!>");
                    System.out.print(".Retype: ");
                }
                yearr = "" + year + "";
            } catch (Exception e) {
                System.out.println("<!> Please enter a number <!>");
                System.out.print(".Retype: ");
            }
        }
        return yearr;
    }

    public static String month() {
        int month = 0;
        String monthh = "";
        boolean check = false;
        while (!check) {
            try {
                String enter = scanner.nextLine().trim();
                while (enter == null || enter.equals("")) {
                    System.out.println("<!> Don't leave it blank <!>");
                    System.out.print(".Retype: ");
                    enter = scanner.nextLine().trim();
                }
                month = Integer.parseInt(enter);
                if (0 < month && month < 13) {
                    check = true;
                } else {
                    System.out.println("<!> Found the month of birth is not real <!>");
                    System.out.print(".Retype: ");
                }
                if (month >= 10) {
                    monthh = "" + month + "";
                } else {
                    monthh = "0" + month + "";
                }
                
            } catch (Exception e) {
                System.out.println("<!> Please enter a number <!>");
                System.out.print(".Retype: ");
            }
        }
        return monthh;
    }

    public static String day(String y, String m) {
        int day, x = 0;
        String dayy = "";
        boolean check = false;
        int year = Integer.parseInt(y);
        int month = Integer.parseInt(m);
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    x = 1;
                } else {
                    x = 2;
                }
            } else {
                x = 1;
            }
        } else {
            x = 2;
        }
        while (!check) {
            try {
                String enter = scanner.nextLine().trim();
                while (enter == null || enter.equals("")) {
                    System.out.println("<!> Don't leave it blank <!>");
                    System.out.print(".Retype: ");
                    enter = scanner.nextLine().trim();
                }
                day = Integer.parseInt(enter);
                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    if (0 < day && day < 32) {
                        check = true;
                    } else {
                        System.out.println("<!> This date does not exist for the month <!>");
                        System.out.print(".Retype: ");
                    }
                }
                if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if (0 < day && day < 31) {
                        check = true;
                    } else {
                        System.out.println("<!> This date does not exist for the month <!>");
                        System.out.print(".Retype: ");
                    }
                }
                if (month == 2) {
                    if (x == 1) {
                        if (0 < day && day < 30) {
                            check = true;
                        } else {
                            System.out.println("<!> This date does not exist for the month <!>");
                            System.out.print(".Retype: ");
                        }
                    }
                    if (x == 2) {
                        if (0 < day && day < 39) {
                            check = true;
                        } else {
                            System.out.println("<!> This date does not exist for the month <!>");
                            System.out.print(".Retype: ");
                        }
                    }
                }
                if (day >= 10) {
                    dayy = "" + day + "";
                } else {
                    dayy = "0" + day + "";
                }
            } catch (Exception e) {
                System.out.println("<!> Please enter a number <!>");
                System.out.print(".Retype: ");
            }
        }
        return dayy;
    }

    private static void insertUpdateUser(int ID, int t) {
        UserBL updateBL = new UserBL();
        if (t == 6) {
            if (updateBL.updatePass(inputUpdateUser(ID, t))) {
                System.out.println("(*) Update successfully!");
                new UserUI().updateProfile(ID);
            } else {
                System.err.println("(*) Update failed!");
                new UserUI().updateProfile(ID);
            }
        } else {
            if (updateBL.updateAcc(inputUpdateUser(ID, t))) {
                System.out.println("(*) Update successfully!");
                new UserUI().updateProfile(ID);
            } else {
                System.err.println("(*) Update failed!");
                new UserUI().updateProfile(ID);
            }
        }
    }

    private static void deleteAcc(int ID) {
        User user = new User();
        user.setID(ID);
        UserBL userBL = new UserBL();
        if (ID == 1) {
            System.err.println("(*) Delete failed!");
            new UserUI().updateInfo(ID);
        } else {
            if (userBL.deleteAcc(user)) {
                System.out.println("(*) Deleted successfully!");
                new UserUI().UI(0);
            } else {
                System.err.println("(*) Delete failed!");
                new UserUI().UI(0);
            }
        }
    }

    private static void showUserInfo(int ID) {
        User user = new User();
        user.setID(ID);
        List<User> lst = new UserBL().getInfoUser(user);
        for (User user2 : lst) {
            showProfile(user2);
        }
        new UserUI().updateInfo(ID);
    }

    public static void showProfile(User user2) {
        System.out.println(" _______________PROFILE________________ ");
        System.out.print("|                                      |");
        System.out.format("\n|  User:%29s  |", user2.getUserName());
        System.out.format("\n|  Email:%28s  |", user2.getEmail());
        System.out.format("\n|  Name:%29s  |", user2.getYourName());
        System.out.format("\n|  Address:%26s  |", user2.getAddress());
        System.out.format("\n|  Date Of Birth:%20s  |", user2.getDateOfBirth());
        System.out.format("\n|  Gender:%27s  |", user2.getGender());
        System.out.println("\n|______________________________________|");

    }

    /* ---------------------- Login and Create Account ---------------------- */
    /* */
    /* */
    /* */
    /* */
    private static User inputLogin(String username, String password) {
        User login = new User();
        System.out.print("Enter username: ");
        username = scanner.nextLine().toLowerCase().trim();
        login.setUserNameLogin(blankOrNull(username, 2));
        System.out.print("Enter password: ");
        password = scanner.nextLine().trim();
        login.setPasswordLogin(blankOrNull(password, 1));
        return login;
    }

    private static void insertLogin() {
        UserBL loginBL = new UserBL();
        System.out.println("                -------                ");
        System.out.println("               | LOGIN |               ");
        System.out.println("                -------                ");
        int ID = loginBL.addlogin(inputLogin("", ""));
        if (ID == 1) {
            new UserUI().CaseUI(ID);
        } else if (ID > 1) {
            System.out.println("(*) Logged in successfully!");
            new UserUI().menuCustomer(ID);
        } else {
            System.out.println("(*) Login failed!");
            new UserUI().UI(ID);
        }
    }

    private static User inputCreateAccount() {
        User cre = new User();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().toLowerCase().trim();
        cre.setEmail(blankOrNull(email, 2));
        System.out.print("Enter username: ");
        String username = scanner.nextLine().toLowerCase().trim();
        cre.setUserName(blankOrNull(username, 2));
        int check = 1;
        while (check == 1) {
            System.out.print("Enter password: ");
            String p1 = scanner.nextLine().trim();
            cre.setTemporary(blankOrNull(p1, 1));
            System.out.print("Confirm password: ");
            String p2 = scanner.nextLine().trim();
            cre.setTemporary2(blankOrNull(p2, 1));
            if (p1.equals(p2)) {
                cre.setPassword(p2);
                check = 2;
            } else {
                System.out.println("\nInvalid password!\n\nPlease re-enter!\n");
            }
        }
        System.out.print("Enter Your Name: ");
        String youName = scanner.nextLine().trim();
        cre.setYourName(blankOrNull(youName, 1));
        return cre;
    }

    private static void insertCreateAccount(int ID) {
        UserBL creBL = new UserBL();
        System.out.println("              ----------              ");
        System.out.println("             | REGISTER |             ");
        System.out.println("              ----------              ");
        if (ID == 1) {
            if (creBL.creAcc(inputCreateAccount())) {
                System.out.println("(*) Account successfully created!");
                manageUser(ID);
            } else {
                System.err.println("(*) Account creation failed!");
                System.err.println("(*) Email or username is already taken!");
                manageUser(ID);
            }
        } else {
            if (creBL.creAcc(inputCreateAccount())) {
                System.out.println("(*) Account successfully created!");
                new UserUI().UI(0);
            } else {
                System.err.println("(*) Account creation failed!");
                System.err.println("(*) Email or username is already taken!");
                new UserUI().UI(0);
            }
        }

    }

    /*-----------------Check----------------- */
    /*--*/
    /*--*/
    /*--*/
    /*--*/
    public static int interger() {
        int c = 0;
        boolean check = false;
        while (!check) {
            try {
                String choose = scanner.nextLine().trim();
                while (choose == null || choose.equals("")) {
                    System.out.println("<!> Don't leave it blank <!>");
                    System.out.print(".Retype: ");
                    choose = scanner.nextLine().trim();
                }
                c = Integer.parseInt(choose);
                check = true;
            } catch (Exception e) {
                System.out.println("<!> Please enter a number <!>");
                System.out.print(".Retype: ");
            }
        }
        return c;
    }

    public static String blankOrNull(String error, int check) {
        if (check == 1) {
            while (error == null || error.equals("")) {
                System.out.println("<!> Don't leave it blank <!>");
                System.out.print(".Retype: ");
                error = scanner.nextLine().trim();
            }
        } else if (check == 2) {
            while (error == null || error.equals("")) {
                System.out.println("<!> Don't leave it blank <!>");
                System.out.print(".Retype: ");
                error = scanner.nextLine().toLowerCase().trim();
            }
        }
        return error;
    }

}
