package dev.project.bl;

import java.util.List;

import dev.project.dal.ComicDAL;
import dev.project.persistance.Comic;

public class ComicBL {
    private ComicDAL comicDAL = new ComicDAL();

    public boolean addComic(Comic comic) {
        return comicDAL.insertComic(comic) > 0;
    }

    public boolean updateComic(Comic comic) {
        return comicDAL.updateComic(comic) > 0;
    }

    public boolean deleteComic(Comic comic) {
        return comicDAL.deleteComic(comic) > 0;
    }

    public List<Comic> getAllComic() {
        return comicDAL.getAll();
    }

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