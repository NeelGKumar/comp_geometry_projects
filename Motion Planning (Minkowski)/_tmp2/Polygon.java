package minkowski;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gkumar
 */
public class Polygon {
     Vertex head, tail;
     int num = 0;
     Vertex ymin= new Vertex(100000.0,100000.0,0);
     ArrayList<Polygon> pList = new ArrayList<Polygon>();
     PrintStream myOut;
     intersection t = new intersection();

     public Polygon(Vertex v, PrintStream out){
          num = num +1;
          head = v;
          tail = v;
          head.next = tail;
          head.prev = tail;
          tail.next = head;
          tail.prev = head;
          if (v.y < ymin.y){
              ymin = v;
          }
          myOut = out;

     }

         public Polygon flipPolygon(){
          ArrayList<Vertex> l = new ArrayList<Vertex>();
          Vertex index = head;
          //System.out.println(" num  " + num );
          for (int i = 0; i < num; i ++){
               index.x = -index.x;
               index.y = -index.y;
               l.add(index);
               index = index.next;
          }
           Polygon q = new Polygon(l.get(0), myOut);
           for (int i = 1; i <= num-1; i++){
                q.add(l.get(i));
           }
           q.angle(false);
           q.pointMin();
           //System.out.println(" flip q  " + q.num + " , " + num );
           //q.head.print();
           //q.print();
           return q;
     }

     public void intersection (Vector v){
         Vertex h = head;
         for (int i = 0; i < num; i++){
             v.lineIntersection(h,t);
             h = h.next;
         }
         t.pointInter(v);
     }
      public void add( Vertex v){
          num = num +1;
           tail.next = v;
           v.prev = tail;
           tail = v;
           tail.next = head;
           head.prev = tail;
           if (v.y < ymin.y){
              ymin = v;

           }
           if (v.y  == ymin.y){
                if (v.y < ymin.x){
                     ymin = v;
                }
          }

      }

      public void pointMin(){
          Vertex min = head;
          for(int i = 0; i < num-1; i++, head = head.prev){
              if (head.prev.ang < min.ang){
                  min = head.prev;
              }
          }
          head = min;
      }

       public Polygon Minkoswski (Polygon A){
          head = ymin;
          tail = ymin.prev;
          this.angle(false);
          A.angle(false);
          Polygon At, Bt;
          int n = A.num+this.num;
           int j = 1,ai = 0, bi =0;
          Vertex a,b;
          if (A.head.ang < this.head.ang){
               a = A.head;
               At = A;
               b = this.head;
               Bt = this;
          }
          else {
               a = this.head;
               At = this;
               b = A.head;
               Bt = A;
          }
          //a.print();
          //b.print();
          Vertex v = a.plus(b);
          //v.print();
          Polygon p = new Polygon (v,myOut);

          a = a.next;
          ai = ai+1;
          boolean flag = true;
         
          while(j < n){
              //a.print();
              //b.print();
              v = a.plus(b);
              //v.print();
               p.add(v);
               if(b.ang < a.ang){
                   if(bi < Bt.num){
                       b = b.next;
                       bi = bi+1;
                       //b.print();
                       //System.out.println("bi  " + bi + "  " + b.n);
                   }
                   else{
                       a = a.next;
                       ai = ai+1;
                       //a.print();
                       //System.out.println("ai else " + ai  + "  " + a.n);
                   }
                    j = j+1;
               }
               else {
                   if(ai < At.num){
                       a = a.next;
                       ai = ai+1;
                       //a.print();
                       //System.out.println("ai " + ai  + "  " + a.n);
                   }
                   else{
                       b = b.next;
                       bi = bi+1;
                       //b.print();
                       //System.out.println("bi else " + bi  + "  " + b.n);
                   }
                    j = j+1;
                    
               }
          }
          return p;
      }
      public void getConvex(){
          divide (this);
      }

      public void divide(Polygon p){
          
           //System.out.println("Divide ");
           Vertex a = isConvex(p);
           //p.print();
           if( a==null){
               //System.out.println("Adding ");
               p.angle(false);
               p.pointMin();
               //p.head.print();
               
                pList.add(p);
                //System.out.println("Adding ");
           }
           else{
               ArrayList<Vertex> nP = new ArrayList<Vertex>();
               //System.out.println("is Reflex ");
               nP.add(new Vertex (a));
               //int n = 2;
               System.out.println("adding a " + a.n + " - " + a.x + " ," + a.y );
               nP.add(a.next);
               Vertex b = a.next.next;
//               try {
//                    int sd = System.in.read();
//               } catch (IOException ex) {
//                    Logger.getLogger(Polygon.class.getName()).log(Level.SEVERE, null, ex);
//               }
               while(!(a.isLeft(b) ==1&& this.interTest(a, b)) ){
                    System.out.println(" adding b " + b.n + "-- "+ b.x + " ," + b.y );
                    System.out.println(" i ");
                    nP.add(b);
                    b = b.next;
                    //n = n+1;
               }
               nP.add(b);

               Vertex c = p.Uniform(a,b,nP);
               //System.out.println(" adding c " + c.x + " ," + c.y );
               nP.add(new Vertex(c));
               p.head = a;
               a.next = c;
               c.prev = a;
               int n = nP.size();
               p.num = p.num - n+2;
              // System.out.println(" p size   " + p.num + " q size   " + n );
               Polygon q  = newPolygon(nP);
               System.out.println(" P  " );
               p.print(); 
               System.out.println(" Q  " );
               q.print();
//               try {
//                    int k = System.in.read();
//               } catch (IOException ex) {
//                    Logger.getLogger(Polygon.class.getName()).log(Level.SEVERE, null, ex);
//               }
               divide(q);
               divide(p);
               

           }
      }

      public boolean interTest(Vertex a, Vertex b){
           intersection t1 = new intersection();
           Vertex index = b.next;
           Vector v = new Vector(a,b);
           while( index.next != a){
                v.lineIntersection(index, t1);
                if(t1.tmin != null){
                      System.out.println( "intersection " + index.n + " - " + index.next.n + " tmin " + t1.tmin);
                     return false;

                }
                index = index.next;
           }
           System.out.println( " no  intersection " + index.n + " - " + index.next.n+ " tmin " + t1.tmin);
           return true;
      }

      public Vertex Uniform(Vertex b, Vertex c, ArrayList<Vertex> P){
           System.out.println(" Unifrom   1: ");// + ang1 + "  2:  " + ang2 );
           b.print();
           c.print();
           if (c.isReflex()){
                P.remove(P.size()-1);
                return c;
           }
           Vertex d = c;
           int add = 0;
           double min1 = Math.abs(b.angleG(c,b.prev) -b.angleG(b.next,c));
            //System.out.println(" Minimum   1: " + min1  );
           c = c.next;
          // c.print();
           double min2 = Math.abs(b.angleG(c,b.prev) -b.angleG(b.next,c));
           //System.out.println(" Minimum   2: "  + min2 );
          while (min2 < min1){
               if (c.isReflex()){
                    return c;
               }
               P.add(c);
               add = add+1;
                c = c.next;
                min1 = min2;
                min2 = Math.abs(b.angleG(c,b.prev) -b.angleG(b.next,c));
            }
           if(this.interTest(b, c.prev)){
                for (int i = 1; i <= add; i++){
                      P.remove(P.size()-i);
                }
                P.remove(P.size()-1);
                return d;
           }
           P.remove(P.size()-1);
           //c.prev.print();
           return c.prev;
      }
      public Polygon newPolygon(ArrayList<Vertex> l){
           Polygon q = new Polygon(l.get(0), myOut);
           for (int i = 1; i < l.size(); i++){
                q.add(l.get(i));
           }
           //System.out.println(" q  " );
           //q.print();
           
           return q;
           
      }

      public Vertex isConvex(Polygon p){
           if(p.num == 3){
                return null;
           }
           Vertex v = p.head;
           for (int i = 0; i <p.num; i++ ){
                if(v.isReflex()){
                     System.out.println("Reflex " + v.x + " ," + v.y );
                     return v;
                }
                v = v.next;
           }
           return null;         

      }


      public void angle(boolean f){
          double xm = ymin.x, ym = ymin.y;
          for (int i = 0; i < num; i++, head = head.next){
               //System.out.println("ymin " + xm + " ," + ym );
               //System.out.println("b4 " + head.x + " ," + head.y );
               head.x = head.x - xm;
               head.y = head.y - ym;
               //head.sub(ymin);
               //System.out.println("aftr " + head.x + " ," + head.y );
           }
          for (int i = 0; i < num; i++, head = head.next){
             head.angle();
           }
          
          if (!f){
              for (int i = 0; i < num; i++, head = head.next){
               //System.out.println("ymin " + xm + " ," + ym );
               //System.out.println("b4 " + head.x + " ," + head.y );
               head.x = head.x + xm;
               head.y = head.y + ym;
               //head.sub(ymin);
               //System.out.println("aftr " + head.x + " ," + head.y );
           }
         
          }
      }

     

      public void print(){
         Vertex index = head;
        for (int i = 0; i < num; i++){
            if(index.ang != null){
                System.out.println(index.n + " " + index.x + " " + index.y + " " +index.ang*180.0/Math.PI );
            }
            else{
                System.out.println(index.n + " " +index.x + " " + index.y + " " +index.ang);
            }
             myOut.println(index.x + " " + index.y + " " + index.next.x + " " + index.next.y);
             index = index.next;
        }
      }

      public void printInter(){
          if(t.tmin != null && t.tmax !=null){
            System.out.println("inter  "+t.v1.x + " " + t.v1.y + " " + t.v2.x + " " + t.v2.y );
            myOut.println(t.v1.x + " " + t.v1.y + " " + t.v2.x + " " + t.v2.y );
          }
      }

 }

