package bases;

public class Vector2D {
    public float x;
    public float y;

    public Vector2D(){
        this(0,0);
    }

    public Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void print(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void set(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2D clone(){
        return new Vector2D(this.x, this.y);
    }

    public void addUp(float x, float y){
        this.x += x;
        this.y += y;
    }

    public void addUp(Vector2D anotherVector){
        this.x += anotherVector.x;
        this.y += anotherVector.y;
    }

    public Vector2D add(float x, float y){
        return new Vector2D(this.x+x,this.y+y);
    }

    public Vector2D add(Vector2D anotherVector){
        return new Vector2D(this.x+anotherVector.x, this.y+anotherVector.y);
    }

    public void subtractBy(float x, float y){
        this.x -= x;
        this.y -= y;
    }

    public void subtracBy(Vector2D anotherVector){
        this.x -= anotherVector.x;
        this.y -= anotherVector.y;
    }
    public Vector2D subtract(float x, float y){
        return new Vector2D(this.x - x, this.y -y);
    }
    public Vector2D subtract(Vector2D anotherVector){
        return new Vector2D(this.x - anotherVector.x, this.y - anotherVector.y);
    }
    public Vector2D multiply(float number){
        return new Vector2D(this.x*number,this.y*number);
    }
    public float length(){
        return (float)Math.sqrt( (this.x*this.x) + (this.y*this.y) );
    }


}

