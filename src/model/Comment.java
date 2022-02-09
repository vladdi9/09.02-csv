package model;

public class Comment {
    private String comment;

    private int user_id;

    public Comment(String comment, int user_id) {
        this.comment = comment;
        this.user_id = user_id;
    }

    public String getComment() {
        return comment;
    }

    public int getUser_id() {
        return user_id;
    }

}
