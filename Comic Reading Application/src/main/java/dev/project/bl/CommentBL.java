package main.java.dev.project.bl;

import java.util.List;

import main.java.dev.project.dal.CommentDAL;
import main.java.dev.project.persistance.Comment;

public class CommentBL {
    private CommentDAL commentDAL = new CommentDAL();

    public List<Comment> getComicDetails(Comment comment) {
        return commentDAL.getCommentCID(comment);
    }

    public boolean addComment(Comment addC) {
        return commentDAL.addComment(addC) > 0;
    }
}