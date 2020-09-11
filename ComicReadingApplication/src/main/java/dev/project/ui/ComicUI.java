package dev.project.ui;

import java.util.List;
import java.util.Scanner;

import dev.project.bl.ComicBL;
import dev.project.bl.CommentBL;
import dev.project.bl.LikeBL;
import dev.project.persistance.Comic;
import dev.project.persistance.Comment;
import dev.project.persistance.Like;

public class ComicUI {
    public static Scanner scanner = new Scanner(System.in);

    /* --------------------------------- admin --------------------- */
    /* */
    /* */
    /* */
    /* */
    public void manageComic(int ID) {
        System.out.println(" ______________Manage_Comic____________");
        System.out.println("|                                      |");
        System.out.println("|  See the full comic             <1>  |");
        System.out.println("|  Search comic                   <2>  |");
        System.out.println("|  Add comics                     <3>  |");
        System.out.println("|  Comic update                   <4>  |");
        System.out.println("|  Delete comics                  <5>  |");
        System.out.println("|  Back                           <0>  |");
        System.out.println("|__________(c) PF09 ~ GROUP 2__________|");
        System.out.print(".Choose: ");
        switch (interger()) {
            case 1:
                showAllComic(ID);
                break;
            case 2:
                showAllComicByName(ID);
                break;
            case 3:
                insertComic(ID);
                break;
            case 4:
                inputUpdateComicID();
                break;
            case 5:
                deleteComic(ID);
                break;
            case 0:
                new UserUI().menuAdmin(ID);
                break;
            default:
                System.out.println("(*) The item you selected is not available!");
                manageComic(ID);
                break;
        }
    }

    public static void updateProfileComic(int ID) {
        System.out.println(" _____________UPDATE_COMIC_____________");
        System.out.println("|                                      |");
        System.out.println("|  Update comic name              <1>  |");
        System.out.println("|  Update comic content           <2>  |");
        System.out.println("|  Update comic images            <3>  |");
        System.out.println("|  Update comic category          <4>  |");
        System.out.println("|  Update comic source            <5>  |");
        System.out.println("|  Update comic status            <6>  |");
        System.out.println("|  Back                           <0>  |");
        System.out.println("|__________(c) PF09 ~ GROUP 2__________|");
        System.out.print(".Choose: ");
        switch (interger()) {
            case 1:
                System.out.print("Enter a new comic name : ");
                updateComic(ID, 1);
                break;
            case 2:
                System.out.print("Enter new comic content: ");
                updateComic(ID, 2);
                break;
            case 3:
                System.out.print("Import a new comic picture: ");
                updateComic(ID, 3);
                break;
            case 4:
                System.out.print("Enter a new category of comics ");
                updateComic(ID, 4);
                break;
            case 5:
                System.out.print("Enter new comic source: ");
                updateComic(ID, 5);
                break;
            case 6:
                System.out.print("Enter a new comic state(slacking/finish): ");
                updateComic(ID, 6);
                break;
            case 0:
                new ComicUI().manageComic(ID);
                break;
            default:
                System.out.println("(*) The item you selected is not available!");
                updateProfileComic(ID);
                break;
        }
    }

    private static Comic inputComic() {
        Comic comic = new Comic();
        System.out.print("Enter the comic name: ");
        String name = scanner.nextLine().trim();
        comic.setNameComic(blankOrNull(name, 1));

        System.out.print("Enter a comic content: ");
        String content = scanner.nextLine().trim();
        comic.setContent(blankOrNull(content, 1));

        System.out.print("Enter a comic avatar: ");
        String image = scanner.nextLine().toUpperCase().trim();
        comic.setImage(blankOrNull(image, 2));

        System.out.print("Enter the category: ");
        String category = scanner.nextLine().trim();
        comic.setCategory(blankOrNull(category, 1));

        System.out.print("Enter comic status(slacking/finish): ");
        String status = scanner.nextLine().toUpperCase().trim();
        comic.setStatus(blankOrNull(status, 2));

        System.out.print("Enter the cource: ");
        String cource = scanner.nextLine().toUpperCase().trim();
        comic.setSource(blankOrNull(cource, 2));
        return comic;
    }

    private static Comic inputDeleteComic() {
        Comic comic = new Comic();
        System.out.print("Enter comic id: ");
        comic.setComicID(interger());
        return comic;
    }

    private static Comic inputUpdateComicID() {
        Comic comic = new Comic();
        System.out.print("Enter comic id: ");
        comic.setComicID(interger());
        updateProfileComic(comic.getComicID());
        return comic;
    }

    private static Comic inputUpdateComic(int ID, int t) {
        Comic update = new Comic();
        if (t == 1) {
            update.setTemporary("name_comic");
            String error = scanner.nextLine().trim();
            update.setTemporary2(blankOrNull(error, 1));
        } else if (t == 2) {
            update.setTemporary("content");
            String error = scanner.nextLine().trim();
            update.setTemporary2(blankOrNull(error, 1));
        } else if (t == 3) {
            update.setTemporary("image");
            String error = scanner.nextLine().toLowerCase().trim();
            update.setTemporary2(blankOrNull(error, 2));
        } else if (t == 4) {
            update.setTemporary("category");
            String error = scanner.nextLine().trim();
            update.setTemporary2(blankOrNull(error, 1));
        } else if (t == 5) {
            update.setTemporary("source");
            String error = scanner.nextLine().trim();
            update.setTemporary2(blankOrNull(error, 1));
        } else if (t == 6) {
            update.setTemporary("status");
            String error = scanner.nextLine().toLowerCase().trim();
            while (error == null || error.equals("") || !error.equals("slacking") && !error.equals("finish")) {
                System.out.println("<!> Don't leave it blank <!>");
                System.out.print("Retype: ");
                error = scanner.nextLine().toLowerCase().trim();
            }
            update.setTemporary2(error);
        }
        update.setComicID(ID);
        return update;
    }

    private static void insertComic(int ID) {
        ComicBL comicBL = new ComicBL();
        System.out.println("Insert New Comic");
        if (comicBL.addComic(inputComic())) {
            System.out.println("Insert Comic complete!");
            new ComicUI().manageComic(ID);
        } else {
            System.err.println("Insert comic failed!");
            new ComicUI().manageComic(ID);
        }
    }

    private static void updateComic(int ID, int t) {
        ComicBL comicBL = new ComicBL();
        if (comicBL.updateComic(inputUpdateComic(ID, t))) {
            System.out.println("<!> Successful comic update!");
            updateProfileComic(ID);
        } else {
            System.out.println("<!> Failed!");
            updateProfileComic(ID);
        }
    }

    private static void deleteComic(int ID) {
        ComicBL comicBL = new ComicBL();
        System.out.println("Delete comic:");
        if (comicBL.deleteComic(inputDeleteComic())) {
            System.out.println("<!> Deleted successfully!!");
            new ComicUI().manageComic(ID);
        } else {
            System.out.println("<!> Failed!");
            new ComicUI().manageComic(ID);
        }
    }

    public static void headComicListAD() {
        System.out.println(
                "                                                             COMIC LIST                                                             ");
        System.out.println(
                " __________________________________________________________________________________________________________________________________ ");
        System.out.println(
                "|   ID |                      Name |             Content |    Image |  Category |  Source |  Status |  Like |                 Date |");
        System.out.println(
                "|______|___________________________|_____________________|__________|___________|_________|_________|_______|______________________|");
    }

    private static void showAllComic(int ID) {
        ComicBL cbl = new ComicBL();
        headComicListAD();
        List<Comic> lst = cbl.getAllComic();
        if (lst.isEmpty()) {
            System.out.println("<!> No corresponding comic book <!>");
            showAllComic(ID);
        }
        for (Comic comic : lst) {
            ListAllComic(comic);
        }
        System.out.println(
                "|______|___________________________|_____________________|__________|___________|_________|_________|_______|______________________|");
        showUpdateAndDelete(ID);
    }

    private static void showAllComicByName(int ID) {
        Comic search = new Comic();
        System.out.print("Enter comic name: ");
        String comicname = scanner.nextLine().trim();
        search.setNameComic(blankOrNull(comicname, 1));
        headComicListAD();
        List<Comic> lst = new ComicBL().getComicByName(search);
        if (lst.isEmpty()) {
            System.out.println("<!> No corresponding comic book <!>");
            showAllComicByName(ID);
        }
        for (Comic comic : lst) {
            ListAllComic(comic);
        }
        System.out.println(
                "|______|___________________________|_____________________|__________|___________|_________|_________|_______|______________________|");
        showUpdateAndDelete(ID);
    }

    public static void ListAllComic(Comic comic) {
        System.out.format("|%5d |%26s |%20s |%9s |%10s |%8s |%8s |%6d |%21s |\n", comic.getComicID(),
                comic.getNameComic(), comic.getContent(), comic.getImage(), comic.getCategory(), comic.getSource(),
                comic.getStatus(), comic.getLikes(), comic.getPosting_date());
        System.out.println(
                "|------|---------------------------|---------------------|----------|-----------|---------|---------|-------|----------------------|");
    }

    public static void showUpdateAndDelete(int ID) {
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "|                                                    Update  <1> | <2>  Delete                                                     |");
        System.out.println(
                "|                                                             <0> BACK                                                             |");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print(".Choose: ");
        switch (interger()) {
            case 1:
                inputUpdateComicID();
                break;
            case 2:
                deleteComic(ID);
                break;
            case 0:
                new ComicUI().manageComic(ID);
                break;
            default:
                System.out.println("(*) The item you selected is not available!");
                showUpdateAndDelete(ID);
                break;

        }
    }

    /* ----------------------------------- customer ---------------------- */
    /* */
    /* */
    /* */
    /* */
    public void viewAndSearchComic(int ID) {
        System.out.println(" ______________VIEW_&_SEARCH___________");
        System.out.println("|                                      |");
        System.out.println("|  Search comic                   <1>  |");
        System.out.println("|  Category                       <2>  |");
        System.out.println("|  Status                         <3>  |");
        System.out.println("|  Rank                           <4>  |");
        System.out.println("|  Back                           <0>  |");
        System.out.println("|__________(c) PF09 ~ GROUP 2__________|");
        System.out.print(".Choose: ");
        switch (interger()) {
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
                if (ID == 0) { 
                    new UserUI().UI(0);
                } else {
                    new UserUI().menuCustomer(ID);
                }
                break;
            default:
                System.out.println("(*) The item you selected is not available!");
                viewAndSearchComic(ID);
                break;
        }
    }

    public static Comment inputComment(int id, int ID) {
        Comment comment = new Comment();
        System.out.print("Enter content: ");
        String content = scanner.nextLine().trim();
        comment.setContent(blankOrNull(content, 1));
        comment.setComicID(id);
        comment.setCustomerID(ID);
        return comment;
    }

    private static void insertComment(int id, int ID, int like) {
        CommentBL commentBL = new CommentBL();
        if (commentBL.addComment(inputComment(id, ID))) {
            System.out.println("<!> Please find this comic again to refresh <!>");
            showInputComment(id, ID, like);
        } else {
            System.out.println("<!> Failed!");
            showInputComment(id, ID, like);
        }
    }

    private static void insertGetComicDetails(int ID) {
        Comic details = new Comic();
        Comment comm = new Comment();
        System.out.print("Enter comic ID: ");
        int like = 0;
        details.setComicID(interger());
        List<Comic> lst = new ComicBL().getComicDetails(details);
        if (lst.isEmpty()) {
            System.out.println("<!> No corresponding comic book <!>");
            insertGetComicDetails(ID);
        }
        for (Comic comic : lst) {
            showComicDetails(comic);
            like = comic.getLikes();
        }
        comm.setComicID(details.getComicID());
        List<Comment> lstt = new CommentBL().getComicDetails(comm);
        System.out.println("<Comment>");
        for (Comment comment : lstt) {
            showComment(comment);
        }
        showInputComment(details.getComicID(), ID, like);
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
            System.out.println("<!> Please find this comic again to refresh <!>");
            showInputComment(id, ID, like);
        } else {
            likeBL.addlike(likee);
            comic.setLikes(like + 1);
            comicBL.updatelikeComic(comic);
            System.out.println("<!> Liked successfully <!>");
            System.out.println("<!> Please find this comic again to refresh <!>");
            showInputComment(id, ID, like);
        }
    }

    private static void showComicByCategory(int ID) {
        Comic category = new Comic();
        System.out.print("Enter category name: ");
        String cate = scanner.nextLine();
        category.setCategory(blankOrNull(cate, 1));
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
        new ComicUI().back(ID);
    }

    private static void showComicByName(int ID) {
        Comic search = new Comic();
        System.out.print("Enter comic name: ");
        String comicname = scanner.nextLine().trim();
        search.setNameComic(blankOrNull(comicname, 1));
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
        new ComicUI().back(ID);
    }

    private static void showComicByStatus(Comic status, int ID) {
        headComicList();
        List<Comic> lst = new ComicBL().getComicByStatus(status);
        for (Comic comic : lst) {
            showComic(comic);
        }
        System.out.println("|______|___________________________|______________|__________|________|");
        new ComicUI().back(ID);
    }

    private static void showComicByRank(int ID) {
        headComicList();
        List<Comic> lst = new ComicBL().getComicByRank();
        for (Comic comic : lst) {
            showComic(comic);
        }
        System.out.println("|______|___________________________|______________|__________|________|");
        new ComicUI().back(ID);
    }

    public static void viewStatus(int ID) {
        Comic status = new Comic();
        System.out.println("----------------------------------------");
        System.out.println("|     Slacking  <1> | <2>  Finish      |");
        System.out.println("----------------------------------------");
        System.out.print(".Choose: ");
        switch (interger()) {
            case 1:
                status.setStatus("slacking");
                showComicByStatus(status, ID);
                break;
            case 2:
                status.setStatus("finish");
                showComicByStatus(status, ID);
                break;
            default:
                System.out.println("(*) The item you selected is not available!");
                viewStatus(ID);
                break;
        }
        new ComicUI().back(ID);
    }

    public static void headComicList() {
        System.out.println("                              COMIC LIST                               ");
        System.out.println(" _____________________________________________________________________ ");
        System.out.println("|   ID |                      Name |     Category |   Status |  Likes |");
        System.out.println("|______|___________________________|______________|__________|________|");
    }

    public static void showInputComment(int id, int ID, int like) {
        System.out.println("----------------------------------------");
        System.out.println("|       Comment  <1> | <2>  Like       |");
        System.out.println("|                <0> BACK              |");
        System.out.println("----------------------------------------");
        System.out.print(".Choose: ");
        switch (interger()) {
            case 1:
                insertComment(id, ID, like);
                break;
            case 2:
                insertLike(id, ID, like);
                break;
            case 0:
                new ComicUI().back(ID);
                break;
            default:
                System.out.println("(*) The item you selected is not available!");
                showInputComment(id, ID, like);
                break;
        }
    }

    public static void showComicDetails(Comic comic) {
        System.out.println(" ______________________________________ ");
        System.out.print("|                                      |");
        System.out.format("\n|  Name:%29s  |", comic.getNameComic());
        System.out.format("\n|  Category:%25s  |", comic.getCategory());
        System.out.format("\n|  Status:%27s  |", comic.getStatus());
        System.out.format("\n|  Content:%26s  |", comic.getContent());
        System.out.format("\n|  Image:%28s  |", comic.getImage());
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

    public static void showComic(Comic comic) {
        System.out.format("|%5d |%26s |%13s |%9s |%7d |\n", comic.getComicID(), comic.getNameComic(),
                comic.getCategory(), comic.getStatus(), comic.getLikes());
        System.out.println("|------|---------------------------|--------------|----------|--------|");
    }

    public void back(int ID) {
        if (ID == 0) {
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("|                              <0> BACK                               |");
            System.out.println("-----------------------------------------------------------------------");
            System.out.print(".Chosse: ");
            switch (interger()) {
                case 0:
                    viewAndSearchComic(ID);
                    break;
                default:
                    System.out.println("(*) The item you selected is not available!");
                    back(ID);
                    break;
            }
        } else {
            System.out.println("----------------------------------------");
            System.out.println("|     Read comics <1> | <0> BACK       |");
            System.out.println("----------------------------------------");
            System.out.print(".Choose: ");
            switch (interger()) {
                case 1:
                    insertGetComicDetails(ID);
                    break;
                case 0:
                    viewAndSearchComic(ID);
                    break;
                default:
                    System.out.println("(*) The item you selected is not available!");
                    back(ID);
                    break;
            }
        }
    }

    /*-----------------Check---------------------- */
    /* */
    /* */
    /* */
    /* */
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