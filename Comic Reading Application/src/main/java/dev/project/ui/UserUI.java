package main.java.dev.project.ui;

import java.util.List;
import java.util.Scanner;

import main.java.dev.project.bl.ComicBL;
import main.java.dev.project.bl.CommentBL;
import main.java.dev.project.bl.LikeBL;
import main.java.dev.project.bl.UserBL;
import main.java.dev.project.persistance.Comic;
import main.java.dev.project.persistance.Comment;
import main.java.dev.project.persistance.Like;
import main.java.dev.project.persistance.User;

public class UserUI {
    public static Scanner scanner = new Scanner(System.in);

    public void loginUI(int ID) {
        System.out.println(" ______________TRUYEN_VUI______________");
        System.out.println("|                                      |");
        System.out.println("|  Login                          <1>  |");
        System.out.println("|  Register                       <2>  |");
        System.out.println("|  View & search comic            <3>  |");
        System.out.println("|  Close                          <0>  |");
        System.out.println("|__________(c) PF09 ~ GROUP 2__________|");
        System.out.print(".Choose: ");
        String choose = scanner.nextLine().trim();
        while (choose == null || choose.equals("")) {
            System.out.println("<!> Don't leave it blank <!>");
            System.out.print(".Choose: ");
            choose = scanner.nextLine().trim();
        }
        int choosee = Integer.parseInt(choose);
        switch (choosee) {
            case 1:
                insertLogin();
                break;
            case 2:
                insertCreateAccount();
                break;
            case 3:
                viewAndSearchComic(ID);
                break;
            case 0:
                System.exit(0);
                System.out.println("BYE....!");
                break;
            default:
                System.out.println("(*) This item " + choosee + " is not available!");
                loginUI(ID);
                break;
        }
    }

    public void loginAdmin(int ID) {
        System.out.println(" ______________HELLO_BOSS______________");
        System.out.println("|                                      |");
        System.out.println("|  Management interface           <1>  |");
        System.out.println("|  Customer interface             <2>  |");
        System.out.println("|  Logout                         <0>  |");
        System.out.println("|__________(c) PF09 ~ GROUP 2__________|");
        System.out.print(".Choose: ");
        String choose = scanner.nextLine().trim();
        while (choose == null || choose.equals("")) {
            System.out.println("<!> Don't leave it blank <!>");
            System.out.print(".Choose: ");
            choose = scanner.nextLine().trim();
        }
        int choosee = Integer.parseInt(choose);
        switch (choosee) {
            case 1:
                break;
            case 2:
                menuCustomer(ID);
                break;
            case 3:
                break;
            case 0:
                loginUI(0);
                break;
            default:
                System.out.println("(*) This item " + choosee + " is not available!");
                loginAdmin(ID);
                break;
        }
    }

    public void menuCustomer(int ID) {
        System.out.println(" _______________HOME_PAGE______________");
        System.out.println("|                                      |");
        System.out.println("|  Account                        <1>  |");
        System.out.println("|  View & Search Comic            <2>  |");
        System.out.println("|  Logout                         <0>  |");
        System.out.println("|__________(c) PF09 ~ GROUP 2__________|");
        System.out.print(".Choose: ");
        String choose = scanner.nextLine().trim();
        while (choose == null || choose.equals("")) {
            System.out.println("<!> Don't leave it blank <!>");
            System.out.print(".Choose: ");
            choose = scanner.nextLine().trim();
        }
        int choosee = Integer.parseInt(choose);
        switch (choosee) {
            case 1:
                showUserInfo(ID);
                break;
            case 2:
                viewAndSearchComic(ID);
                break;
            case 0:
                loginUI(0);
                System.out.println("(*) Successfully logged out!");
                break;
            default:
                System.out.println("(*) This item " + choosee + " is not available!");
                menuCustomer(ID);
                break;
        }
    }

    public void updateInfo(int ID) {
        System.out.println("----------------------------------------");
        System.out.println(" Update profile <1> | <2> Delete account");
        System.out.println("                <0> BACK                ");
        System.out.print(".Choose: ");
        String choose = scanner.nextLine().trim();
        while (choose == null || choose.equals("")) {
            System.out.println("<!> Don't leave it blank <!>");
            System.out.print(".Choose: ");
            choose = scanner.nextLine().trim();
        }
        int choosee = Integer.parseInt(choose);
        switch (choosee) {
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
                System.out.println("(*) This item " + choosee + " is not available!");
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
        String choose = scanner.nextLine().trim();
        while (choose == null || choose.equals("")) {
            System.out.println("<!> Don't leave it blank <!>");
            System.out.print(".Choose: ");
            choose = scanner.nextLine().trim();
        }
        int choosee = Integer.parseInt(choose);
        switch (choosee) {
            case 1:
                System.out.print("Enter new name: ");
                insertUpdateUser(ID, 1);
                break;
            case 2:
                System.out.print("Enter date of birth: ");
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
                System.out.println("(*) This item " + choosee + " is not available!");
                updateProfile(ID);
                break;
        }
    }

    private static User inputUpdateUser(int ID, int t) {
        User update = new User();
        if (t == 1) {
            update.setTemporary("name");
            String error = scanner.nextLine().trim();
            while (error == null || error.equals("")) {
                System.out.println("<!> Don't leave it blank <!>");
                System.out.print("Retype: ");
                error = scanner.nextLine().trim();
            }
            update.setTemporary2(error);
        } else if (t == 2) {
            update.setTemporary("date_of_birth");
            String error = scanner.nextLine().trim();
            while (error == null || error.equals("")) {
                System.out.println("<!> Don't leave it blank <!>");
                System.out.print("Retype: ");
                error = scanner.nextLine().trim();
            }
            update.setTemporary2(error);
        } else if (t == 3) {
            update.setTemporary("gender");
            String error = scanner.nextLine().toUpperCase().trim();
            while (error == null || error.equals("") || !error.equals("M") || !error.equals("F")) {
                System.out.println("<!> Don't leave it blank <!>");
                System.out.print("Retype: ");
                error = scanner.nextLine().toUpperCase().trim();
            }
            update.setTemporary2(error);
        } else if (t == 4) {
            update.setTemporary("address");
            String error = scanner.nextLine().trim();
            while (error == null || error.equals("")) {
                System.out.println("<!> Don't leave it blank <!>");
                System.out.print("Retype: ");
                error = scanner.nextLine().trim();
            }
            update.setTemporary2(error);
        } else if (t == 5) {
            update.setTemporary("email");
            String error = scanner.nextLine().toLowerCase().trim();
            while (error == null || error.equals("")) {
                System.out.println("<!> Don't leave it blank <!>");
                System.out.print("Retype: ");
                error = scanner.nextLine().toLowerCase().trim();
            }
            update.setTemporary2(error);
        } else if (t == 6) {
            update.setTemporary("password");
            String error = scanner.nextLine().trim();
            while (error == null || error.equals("")) {
                System.out.println("<!> Don't leave it blank <!>");
                System.out.print("Retype: ");
                error = scanner.nextLine().trim();
            }
            update.setTemporary2(error);
        }
        update.setID(ID);
        return update;
    }

    private static void insertUpdateUser(int ID, int t) {
        UserBL updateBL = new UserBL();
        if (updateBL.updateAcc(inputUpdateUser(ID, t))) {
            System.out.println("(*) Update successfully!");
            new UserUI().updateProfile(ID);
        } else {
            System.err.println("(*) Update failed!");
            new UserUI().updateProfile(ID);
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
                new UserUI().loginUI(0);
            } else {
                System.err.println("(*) Delete failed!");
                new UserUI().updateInfo(ID);
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

    private static User inputLogin() {
        User login = new User();
        System.out.print("Enter username: ");
        String username = scanner.nextLine().toLowerCase().trim();
        while (username == null || username.equals("")) {
            System.out.println("<!> Don't leave it blank <!>");
            System.out.print("Retype: ");
            username = scanner.nextLine().toLowerCase().trim();
        }
        login.setUserNameLogin(username);
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        while (password == null || password.equals("")) {
            System.out.println("<!> Don't leave it blank <!>");
            System.out.print("Retype: ");
            password = scanner.nextLine().trim();
        }
        login.setPasswordLogin(password);
        return login;
    }

    private static void insertLogin() {
        UserBL loginBL = new UserBL();
        System.out.println("                -------                ");
        System.out.println("               | LOGIN |               ");
        System.out.println("                -------                ");
        int ID = loginBL.addlogin(inputLogin());
        if (ID == 1) {
            new UserUI().loginAdmin(ID);
        } else if (ID > 1) {
            System.out.println("(*) Logged in successfully!");
            new UserUI().menuCustomer(ID);
        } else {
            System.out.println("(*) Login failed!");
            insertLogin();
        }
    }

    private static User inputCreateAccount() {
        User cre = new User();

        System.out.print("Enter email: ");
        String email = scanner.nextLine().toLowerCase().trim();
        while (email == null || email.equals("")) {
            System.out.println("<!> Don't leave it blank <!>");
            System.out.print("Retype: ");
            email = scanner.nextLine().toLowerCase().trim();
        }
        cre.setEmailCre(email);

        System.out.print("Enter username: ");
        String username = scanner.nextLine().toLowerCase().trim();
        while (username == null || username.equals("")) {
            System.out.println("<!> Don't leave it blank <!>");
            System.out.print("Retype: ");
            username = scanner.nextLine().toLowerCase().trim();
        }
        cre.setEmailCre(username);

        int check = 1;
        while (check == 1) {

            System.out.print("Enter password: ");
            String p1 = scanner.nextLine().trim();
            while (p1 == null || p1.equals("")) {
                System.out.println("<!> Don't leave it blank <!>");
                System.out.print("Retype: ");
                p1 = scanner.nextLine().trim();
            }

            System.out.print("Confirm password: ");
            String p2 = scanner.nextLine().trim();
            while (p2 == null || p2.equals("")) {
                System.out.println("<!> Don't leave it blank <!>");
                System.out.print("Retype: ");
                p2 = scanner.nextLine().trim();
            }

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

    private static void insertCreateAccount() {
        UserBL creBL = new UserBL();
        System.out.println("              ----------              ");
        System.out.println("             | REGISTER |             ");
        System.out.println("              ----------              ");
        if (creBL.creAcc(inputCreateAccount())) {
            System.out.println("(*) Account successfully created!");
            new UserUI().loginUI(0);
        } else {
            System.err.println("(*) Account creation failed!");
            System.err.println("(*) Email or username is already taken!");
            new UserUI().loginUI(0);
        }
    }

    public void viewAndSearchComic(int ID) {

        System.out.println(" ______________VIEW_&_SEARCH___________");
        System.out.println("|                                      |");
        System.out.println("|  Search comic                   <1>  |");
        System.out.println("|  Category                       <2>  |");
        System.out.println("|  Status                         <3>  |");
        System.out.println("|  Rank                           <4>  |");
        System.out.println("|  Back                           <0>  |");
        System.out.println("|__________(c) PF09 ~ GROUP 2__________|");
        System.out.print(".Choice: ");
        String choose = scanner.nextLine().trim();
        while (choose == null || choose.equals("")) {
            System.out.println("<!> Don't leave it blank <!>");
            System.out.print(".Choice: ");
            choose = scanner.nextLine().trim();
        }
        int choosee = Integer.parseInt(choose);
        if (ID == 0) {
            switch (choosee) {
                case 1:
                    showComicByName(ID);
                    break;
                case 2:
                    showComicByCategory(ID);
                    break;
                case 3:
                    viewStatus(ID);
                    break;
                case 4:
                    showComicByRank(ID);
                    break;
                case 0:
                    loginUI(0);
                    break;
                default:
                    System.out.println("(*) This item " + choosee + " is not available!");
                    viewAndSearchComic(ID);
                    break;
            }
        } else {
            switch (choosee) {
                case 1:
                    showComicByName(ID);
                    break;
                case 2:
                    showComicByCategory(ID);
                    break;
                case 3:
                    viewStatus(ID);
                    break;
                case 4:
                    showComicByRank(ID);
                    break;
                case 0:
                    menuCustomer(ID);
                    break;
                default:
                    System.out.println("(*) This item " + choosee + " is not available!");
                    viewAndSearchComic(ID);
                    break;
            }
        }
    }

    private static void showComicByStatus(Comic status, int ID) {
        headComicList();
        List<Comic> lst = new ComicBL().getComicByStatus(status);
        for (Comic comic : lst) {
            showComic(comic);
        }
        System.out.println("|______|___________________________|______________|__________|________|");
        new UserUI().back(ID);
    }

    private static void showComicByRank(int ID) {
        headComicList();
        List<Comic> lst = new ComicBL().getComicByRank();
        for (Comic comic : lst) {
            showComic(comic);
        }
        System.out.println("|______|___________________________|______________|__________|________|");
        new UserUI().back(ID);
    }

    public static void viewStatus(int ID) {
        Comic status = new Comic();
        System.out.println("----------------------------------------");
        System.out.println("|     Slacking  <1> | <2>  Finish      |");
        System.out.println("----------------------------------------");
        System.out.print(".Choice: ");
        String choose = scanner.nextLine().trim();
        while (choose == null || choose.equals("")) {
            System.out.println("<!> Don't leave it blank <!>");
            System.out.print(".Choice: ");
            choose = scanner.nextLine().trim();
        }
        int choosee = Integer.parseInt(choose);
        switch (choosee) {
            case 1:
                status.setStatus("slacking");
                showComicByStatus(status, ID);
                break;
            case 2:
                status.setStatus("finish");
                showComicByStatus(status, ID);
                break;
            default:
                System.out.println("(*) This item " + choosee + " is not available!");
                viewStatus(ID);
                break;
        }
        new UserUI().back(ID);
    }

    private static void showComicByCategory(int ID) {
        Comic category = new Comic();
        System.out.print("Enter category name: ");
        String cate = scanner.nextLine();
        category.setCategory(cate);
        headComicList();
        List<Comic> lst = new ComicBL().getComicByCategory(category);
        if (lst.isEmpty()) {
            System.out.println("<!> No corresponding comic book <!>");
            showComicByCategory(ID);
        }
        for (Comic comic : lst) {
            showComic(comic);
        }
        System.out.println("|______|___________________________|______________|__________|________|");
        new UserUI().back(ID);
    }

    private static void showComicByName(int ID) {
        Comic search = new Comic();
        System.out.print("Enter comic name: ");
        String comicname = scanner.nextLine().trim();
        while (comicname == null || comicname.equals("")) {
            System.out.println("<!> Don't leave it blank <!>");
            System.out.print("Retype: ");
            comicname = scanner.nextLine().trim();
        }
        search.setNameComic(comicname);
        headComicList();
        List<Comic> lst = new ComicBL().getComicByName(search);
        if (lst.isEmpty()) {
            System.out.println("<!> No corresponding comic book <!>");
            showComicByName(ID);
        }
        for (Comic comic : lst) {
            showComic(comic);
        }
        System.out.println("|______|___________________________|______________|__________|________|");
        new UserUI().back(ID);
    }

    public void back(int ID) {
        if (ID == 0) {
            System.out.println("---------------");
            System.out.println("|  <0>  BACK  |");
            System.out.println("---------------");
            System.out.print(".Choice: ");
            String choose = scanner.nextLine().trim();
            while (choose == null || choose.equals("")) {
                System.out.println("<!> Don't leave it blank <!>");
                System.out.print(".Choice: ");
                choose = scanner.nextLine().trim();
            }
            int choosee = Integer.parseInt(choose);
            switch (choosee) {
                case 0:
                    viewAndSearchComic(ID);
                    break;
                default:
                    System.out.println("(*) This item " + choosee + " is not available!");
                    back(ID);
                    break;
            }
        } else {
            System.out.println("----------------------");
            System.out.println("|  <1>  Read comics  |");
            System.out.println("|  <0>  BACK         |");
            System.out.println("----------------------");
            System.out.print(".Choice: ");
            String choose = scanner.nextLine().trim();
            while (choose == null || choose.equals("")) {
                System.out.println("<!> Don't leave it blank <!>");
                System.out.print(".Choice: ");
                choose = scanner.nextLine().trim();
            }
            int choosee = Integer.parseInt(choose);
            switch (choosee) {
                case 1:
                    insertGetComicDetails(ID);
                    break;
                case 0:
                    viewAndSearchComic(ID);
                    break;
                default:
                    System.out.println("(*) This item " + choosee + " is not available!");
                    back(ID);
                    break;
            }
        }
    }

    private static void insertGetComicDetails(int ID) {
        Comic details = new Comic();
        Comment comm = new Comment();
        System.out.print("Enter comic ID: ");
        int id = 0, like = 0;
        boolean check = false;
        while (!check) {
            try {
                String comicId = scanner.nextLine().trim();
                while (comicId == null || comicId.equals("")) {
                    System.out.println("<!> Don't leave it blank <!>");
                    System.out.print("Retype: ");
                    comicId = scanner.nextLine().trim();
                }
                id = Integer.parseInt(comicId);
                check = true;
            } catch (Exception e) {
                System.out.print("<!> Please enter a number <!>");
            }
        }
        details.setComicID(id);
        List<Comic> lst = new ComicBL().getComicDetails(details);
        if (lst.isEmpty()) {
            System.out.println("<!> No corresponding comic book <!>");
            insertGetComicDetails(ID);
        }
        for (Comic comic : lst) {
            showComicDetails(comic);
            like = comic.getLikes();
        }
        comm.setComicID(id);
        List<Comment> lstt = new CommentBL().getComicDetails(comm);
        System.out.println("<Comment>");
        for (Comment comment : lstt) {
            showComment(comment);
        }
        showInputComment(id, ID, like);
    }

    public static void showInputComment(int id, int ID, int like) {
        System.out.println("----------------------------------------");
        System.out.println("|       Comment  <1> | <2>  Like       |");
        System.out.println("|                <0> BACK              |");
        System.out.println("----------------------------------------");
        System.out.print(".Choice: ");
        String choose = scanner.nextLine().trim();
        while (choose == null || choose.equals("")) {
            System.out.println("<!> Don't leave it blank <!>");
            System.out.print(".Choice: ");
            choose = scanner.nextLine().trim();
        }
        int choosee = Integer.parseInt(choose);
        switch (choosee) {
            case 1:
                insertComment(id, ID);
                break;
            case 2:
                insertLike(id, ID, like);
                break;
            case 0:
                new UserUI().back(ID);
                break;
            default:
                System.out.println("(*) This item " + choosee + " is not available!");
                showInputComment(id, ID, like);
                break;
        }
    }

    private static void insertLike(int id, int ID, int like) {
        Like likee = new Like();
        LikeBL likeBL = new LikeBL();
        Comic comic = new Comic();
        ComicBL comicBL = new ComicBL();
        comic.setComicID(id);
        likee.setComicID(id);
        likee.setCustomerID(ID);
        if (likeBL.checkLike(likee)) {
            likeBL.unlike(likee);
            comic.setLikes(like - 1);
            comicBL.updatelikeComic(comic);
            System.out.println("<!> Unliked successfully <!>");
            showInputComment(id, ID,like);
        } else {
            likeBL.addlike(likee);
            comic.setLikes(like + 1);
            comicBL.updatelikeComic(comic);
            System.out.println("<!> Liked successfully <!>");
            showInputComment(id, ID,like);
        }
    }

    public static Comment inputComment(int id, int ID) {
        Comment comment = new Comment();
        System.out.print("Enter content: ");
        String content = scanner.nextLine().trim();
        while (content == null || content.equals("")) {
            System.out.println("<!> Don't leave it blank <!>");
            System.out.print("Retype: ");
            content = scanner.nextLine().trim();
        }
        comment.setContent(content);
        comment.setComicID(id);
        comment.setCustomerID(ID);
        return comment;
    }

    private static void insertComment(int id, int ID) {
        CommentBL commentBL = new CommentBL();

        if (commentBL.addComment(inputComment(id, ID))) {
            System.out.println("<!> Reload to show comments");
            new UserUI().back(ID);
        } else {
            System.out.println("<!> Failed!");
            insertComment(id, ID);
        }
    }

    public static void showComicDetails(Comic comic) {
        System.out.println(" ______________________________________ ");
        System.out.print("|                                      |");
        System.out.format("\n|  Name:%29s  |", comic.getNameComic());
        System.out.format("\n|  Category:%25s  |", comic.getCategory());
        System.out.format("\n|  Status:%27s  |", comic.getStatus());
        System.out.format("\n|  Content:%26s  |", comic.getContent());
        System.out.format("\n|  Image:%28s  |", comic.getContent());
        System.out.format("\n|  Source:%27s  |", comic.getSource());
        System.out.format("\n|  Posting date:%21s  |", comic.getPosting_date());
        System.out.print("\n|_____________                _________|");
        System.out.format("\n|  Like:%4d  |              |*CODE:%3d|", comic.getLikes(), comic.getComicID());
        System.out.println("\n|_____________|______________|_________|");
    }

    public static void showComment(Comment comment) {
        System.out.println("---------");
        System.out.format(comment.getNameUser() + ":\n");
        System.out.format(comment.getContent() + "\n");
        System.out.println("---------");
    }

    public static void headComicList() {
        System.out.println("                              COMIC LIST                               ");
        System.out.println(" _____________________________________________________________________ ");
        System.out.println("|   ID |                      Name |     Category |   Status |  Likes |");
        System.out.println("|______|___________________________|______________|__________|________|");
    }

    public static void showComic(Comic comic) {
        System.out.format("|%5d |%26s |%13s |%9s |%7d |\n", comic.getComicID(), comic.getNameComic(),
                comic.getCategory(), comic.getStatus(), comic.getLikes());
        System.out.println("|------|---------------------------|--------------|----------|--------|");
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

}