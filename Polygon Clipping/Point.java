

import java.util.*;
import java.io.*;
import java.lang.*;
/**
 *
 * @author gkumar
 */
public class Point {
    double x, y;

    public Point( double x, double y){
        this.x = x;
        this.y = y;
    }
    public Point(){

    }

    public void setPoint(double x, double y){
        this.x = x;
        this.y = y;
    }


    public boolean equals( Point p){

        if ( (Math.pow((p.x - x),2.0) + Math.pow(p.y - y, 2.0)) <= 10e-12){
            return true;
        }

        else {
            return false;
        }

    }

}
