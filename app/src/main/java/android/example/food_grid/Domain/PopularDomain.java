package android.example.food_grid.Domain;

import java.io.Serializable;

public class PopularDomain implements Serializable {
    private String tittle;
    private String pic;


    public int getNumberICart() {
        return numberICart;
    }

    public void setNumberICart(int numberICart) {
        this.numberICart = numberICart;
    }

    public PopularDomain(String tittle, String pic, String description, Double fee, int star, int time, int calories) {
        this.tittle = tittle;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.star = star;
        this.time = time;
        this.calories = calories;
    }

    private int numberICart;
    private String description;
    private Double fee;
    private int star;
    private int time;
    private int calories;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
