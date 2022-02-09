package model;

public class Buy {
    private String date;
    private int count;
    private int user_id;
    private int product_id;

    public Buy(String date, int count, int user_id, int product_id) {
        this.date = date;
        this.count = count;
        this.user_id = user_id;
        this.product_id = product_id;
    }

    public String getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getProduct_id() {
        return product_id;
    }
}
