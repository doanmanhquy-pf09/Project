package dev.project.bl;

import dev.project.dal.LikeDAL;
import dev.project.persistance.Like;

public class LikeBL {

    private LikeDAL likeDAL = new LikeDAL();

    public boolean checkLike(Like checkLike) {
        return likeDAL.checkLike(checkLike) > 0;
    }
    public int unlike(Like unlike) {
        return likeDAL.unlike(unlike);
    }
    public int addlike(Like addlike) {
        return likeDAL.addlike(addlike);
    }
}