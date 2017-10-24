package bases;

import java.util.Vector;

public class Vector2D {
    public float x,y;

    public Vector2D(){
        this(0,0);
    }

    public Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D vector2D){
        this.set(vector2D.x,vector2D.y);
    }

    public Vector2D clone(){
        return new Vector2D(this.x,this.y);
    }

    public void addUp(float x, float y){
        this.x += x;
        this.y += y;
    }

    public void addUp(Vector2D other){
        addUp(other.x,other.y);
    }

    public Vector2D add(float x, float y){
        return new Vector2D(this.x + x, this.y + y);
    }

    public Vector2D add(Vector2D other){
        return add(other.x,other.y);
    }

    public void subtractBy(float x, float y){
        this.x -= x;
        this.y -= y;
    }

    public void subtractBy(Vector2D other){
        subtractBy(other.x,other.y);
    }

    public Vector2D subtract(float x, float y){
        return new Vector2D(this.x - x, this.y - y);
    }
    public Vector2D subtract(Vector2D other){
        return subtract(other.x,other.y);
    }

    public Vector2D multiply(float number){return new Vector2D(this.x*x,this.y*y);}

    public float length(){
        return (float) Math.sqrt( (this.x*this.x) + (this.y*this.y) );
    }

    public Vector2D normalize(){
        float length = length();
        return new Vector2D(this.x/length,this.y/length);
    }

    public void print(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
