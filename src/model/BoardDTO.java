package model;

import java.sql.Timestamp;

public class BoardDTO {
    private int boardid;
    private int writeuser;
    private String title;
    private String content;
    private Timestamp boarddate;

    public BoardDTO(int boardid, int writeuser, String title) {
        this.boardid = boardid;
        this.writeuser = writeuser;
        this.title = title;
    }

    public BoardDTO(String content, int boardid, int writeuser) {
        this.content = content;
        this.boardid = boardid;
        this.writeuser = writeuser;
    }

    public BoardDTO(int boardid, String title, String content) {
        this.boardid = boardid;
        this.title = title;
        this.content = content;
    }

    public BoardDTO(int boardid, int writeuser, String title, String content) {
        this.boardid = boardid;
        this.writeuser = writeuser;
        this.title = title;
        this.content = content;
    }

    public BoardDTO(int boardid, String title) {
        this.boardid = boardid;
        this.title = title;
    }

    public BoardDTO(int boardid, int writeuser, String title, String content, Timestamp boarddate) {
        this.boardid = boardid;
        this.writeuser = writeuser;
        this.title = title;
        this.content = content;
        this.boarddate = boarddate;
    }

    public BoardDTO(int boardid, String title, String content, Timestamp boarddate) {
        this.boardid = boardid;
        this.title = title;
        this.content = content;
        this.boarddate = boarddate;
    }

    public BoardDTO(int boardid, String title, Timestamp boarddate) {
        this.boardid = boardid;
        this.title = title;
        this.boarddate = boarddate;
    }

    public int getBoardid() {
        return boardid;
    }

    public void setBoardid(int boardid) {
        this.boardid = boardid;
    }

    public int getWriteuser() {
        return writeuser;
    }

    public void setWriteuser(int writeuser) {
        this.writeuser = writeuser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getBoarddate() {
        return boarddate;
    }

    public void setBoarddate(Timestamp boarddate) {
        this.boarddate = boarddate;
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "boardid=" + boardid +
                ", writeuser=" + writeuser +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", boarddate=" + boarddate +
                '}';
    }
}
