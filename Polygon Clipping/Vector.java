


/**
 *
 * @author gkumar
 */
public class Vector {
    double x, y;



    public Vector ( Point p, Point q){
        x = q.x - p.x;
        y = q.y - p.y;
    }
    public Vector (){

    }

    public double CrossProd (Vector l){

        return ( x*l.y - y*l.x) ;

    }

}
