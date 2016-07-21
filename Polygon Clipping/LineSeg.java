
public class LineSeg {

    Point a, b;
    Vector vL;

    public LineSeg ( Point o, Point s){
        a = o;
        b = s;
        vL = new Vector(a, b); //vector from a to b
    }

    public LineSeg ( ){

    }

    public Point lineSegHalfPlaneIntersection ( HalfPlanes hp){

        Vector xy = hp.lineSeg.vL;
        Vector ax = new Vector ( hp.lineSeg.a, a);
        Vector ab = new Vector ( );
        ab = vL;
        Vector bx = new Vector (hp.lineSeg.a, b);
        double area1 = xy.CrossProd(ax);
        double area2 = xy.CrossProd(bx);
        double area  = ab.CrossProd(xy);

        if ( (area1>0 && area2 > 0) || (area1 < 0 && area2 < 0)) {
            return null;
        }

        if (area < 10E-6 && area > -10E-6){
            return b;
        }

        double s = (xy.CrossProd(ax))/ab.CrossProd(xy);   // -ve since ax = a-x
        Point p = new Point(0.0, 0.0);
        p.x = a.x + s*ab.x;
        p.y = a.y + s*ab.y;
        return p;
    }

   

}
