package main.java.dev.project.bl;

import java.util.List;

import main.java.dev.project.dal.ComicDAL;
import main.java.dev.project.persistance.Comic;

public class ComicBL {
    private ComicDAL comicDAL = new ComicDAL();

    public List<Comic> getComicByName(Comic search) {
        return comicDAL.getCBName(search);
    }
    public List<Comic> getComicByCategory(Comic category) {
        return comicDAL.getCBCategory(category);
    }
    public List<Comic> getComicByStatus(Comic status) {
        return comicDAL.getCBStatus(status);
    }
    public List<Comic> getComicByRank() {
        return comicDAL.getCBRank();
    }
    public List<Comic> getComicDetails(Comic details) {
        return comicDAL.getComicID(details);
    }
    public int updatelikeComic(Comic updateLike) {
        return comicDAL.updatelikeComic(updateLike);
    }
}