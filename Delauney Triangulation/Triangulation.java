

import java.util.*;
import java.lang.*;

/**
 *
 * @author gkumar
 */
public class Triangulation {

     Face root;
     int numOfT = 0;

     public Triangulation (Face f){
          root = f;
     }

     public Triangulation (HalfEdge a, HalfEdge b, HalfEdge c){
          root = new Face(a,b,c);
     }

     public void AddNode (Face f, Face old){
          old.edgesOut.add(f);

     }

     public void AddEdge(Face from, Face to){
          from.edgesOut.add(to);
     }


     public void AddPoint (Vertex v){
          

          Face f = Search (v, root);
          if (f != null){
               int t = f.InTriange(v);
               if(t == 1){
                    InsidePoint(f,v);
               }
               else {
                    OnPoint(v, f, t);
               }
          }

     }

     public void OnPoint (Vertex o, Face f, int k){
          
          HalfEdge h;
          if (k == 2){
               h = f.m;
          }

          else if (k ==3){
               h = f.n;
          }

          else {
               h = f.l;
          }


          Vertex a = h.v, b = h.next.v, c = h.next.next.v;

          HalfEdge oa = new HalfEdge(a, null, null);
          HalfEdge ab = new HalfEdge(b,null, null);
          HalfEdge bo = new HalfEdge(o, null, null);


          HalfEdge ob = new HalfEdge(b, null, null);
          HalfEdge bc = new HalfEdge (c, null, null);
          HalfEdge co = new HalfEdge(o, null, null);

          ob.SetPair(bo);
          if (h.next.pair != null){
               ab.SetPair(h.next.pair);
          }

          if (h.next.next.pair != null){
               bc.SetPair(h.next.next.pair);
          }

          Face boa = new Face(oa,ab,bo);
          Face cob = new Face(co,ob,bc);

          //graph update
          f.AddEdge(boa);
          f.AddEdge(cob);

          if (h.pair != null){
               HalfEdge H = h.pair;
               Vertex A = H.v, B = H.next.v, C = H.next.next.v;

               HalfEdge OA = new HalfEdge(A, null, null);
               HalfEdge AB = new HalfEdge(B, null, null);
               HalfEdge BO = new HalfEdge(o, null, null);

               HalfEdge OB = new HalfEdge(B, null, null);
               HalfEdge BC = new HalfEdge(C, null, null);
               HalfEdge CO = new HalfEdge(o, null, null);

               OB.SetPair(BO);
               co.SetPair(OA);
               oa.SetPair(CO);

               if (H.next.pair != null){
                    AB.SetPair(H.next.pair);
               }

               if (H.next.next.pair != null){
                    BC.SetPair(H.next.next.pair);
               }

               Face BOA = new Face(OA, AB,BO);
               Face COB = new Face(CO,OB,BC);

               H.face.AddEdge(BOA);
               H.face.AddEdge(COB);

               LegalizeEdge(AB);
               LegalizeEdge(BC);

          }

          LegalizeEdge(ab);
          LegalizeEdge(bc);


     }


     public void InsidePoint( Face f, Vertex v){
          Vertex a = f.l.v, b = f.m.v, c = f.n.v;
          //HalfEdge ca = f.l, ab = f.m, bc = f.n;
          HalfEdge oa = new HalfEdge(a, null, null);
          HalfEdge ao = new HalfEdge(v, null, null);
          oa.SetPair(ao);

          HalfEdge ob = new HalfEdge(b, null, null);
          HalfEdge bo = new HalfEdge(v, null, null);
          ob.SetPair(bo);

          HalfEdge oc = new HalfEdge(c, null, null);
          HalfEdge co = new HalfEdge(v, null, null);
          oc.SetPair(co);

          HalfEdge ab = new HalfEdge (b, null, null);
          HalfEdge bc = new HalfEdge (c, null, null);
          HalfEdge ca = new HalfEdge (a, null, null);

          if(f.m.pair != null) {
               ab.SetPair(f.m.pair);
          }
          if(f.n.pair != null){
               bc.SetPair(f.n.pair);
          }
          if(f.l.pair != null){
               ca.SetPair(f.l.pair);
          }
          Face aob = new Face(oa,ab,bo);
          Face boc = new Face(ob,bc,co);
          Face coa = new Face(oc,ca,ao);

          f.AddEdge(aob);
          f.AddEdge(boc);
          f.AddEdge(coa);

          LegalizeEdge(ab);
          LegalizeEdge(bc);
          LegalizeEdge(ca);
          


     }



     public Face Search (Vertex v, Face f){
          int t = f.InTriange(v);
          if (t  == -1){
               return null;
          }

          if(f.edgesOut == null || f.edgesOut.isEmpty()){
               return f;
           }

          Iterator<Face> itr = f.edgesOut.iterator();
          Face ft;
          while(itr.hasNext()){
               ft = Search(v,itr.next());
               if (ft != null){
                   return ft;
               }
          }

          return null;

     }

     public void LegalizeEdge (  HalfEdge l ){
          
          if (l.pair == null){
               return;
          }
          if (CheckEdge(l)){
               HalfEdge h1 = new HalfEdge(l.next.v, null, null);
               HalfEdge h4 = new HalfEdge(l.pair.next.v, null, null);
               h1.SetPair(h4);

               HalfEdge h2 = new HalfEdge( l.next.next.v,l.next.next, null);
               HalfEdge h3 = new HalfEdge( l.pair.next.v, l.pair.next, null);

               if (l.next.next.pair!= null){
                    h2.SetPair(l.next.next.pair);
               }
               if (l.pair.next.pair != null){
                    h3.SetPair(l.pair.next.pair);
               }

               HalfEdge h5 = new HalfEdge( l.pair.next.next.v, l.pair.next.next, null);
               HalfEdge h6 = new HalfEdge( l.next.v, l.next, null);

               if (l.pair.next.next.pair != null){
                    h5.SetPair(l.pair.next.next.pair);
               }

               if (l.next.pair != null){
                    h6.SetPair(l.next.pair);
               }


               Face f1 = new Face(h1, h2, h3);
               Face f2 = new Face(h4, h5, h6);



          
               //graph update
               l.face.AddEdge(f1);
               l.face.AddEdge(f2);
               l.pair.face.AddEdge(f1);
               l.pair.face.AddEdge(f2);

               //Legalize

              LegalizeEdge(h3);
              LegalizeEdge(h5);


          }

     }

     public boolean CheckEdge (HalfEdge l){

          Face f1 = l.face;
          Face f2 = l.pair.face;
           if (f1.SameSide(l.next.v, l.pair.next.v, l.v, l.pair.v) != -1){
                return false;
           }


          boolean con1 = false, con2 = false;
          if (l.v.n ==0 || l.pair.v.n == 0){
               con1 = true;
          }
          if (l.next.v.n ==0 || l.pair.next.v.n ==0){
               con2 = true;
          }
          if (con1 && !con2){
               return true;
          }
          if (con1 && con2){
               return false;
          }
          if (!con1 && con2){
               return false;
          }

      
          double m1 = f1.Angles(f1.l.v, f1.m.v, f1.n.v);
          double m2 = f2.Angles(f2.l.v, f2.m.v, f2.n.v);
          double min1, min2;
          min1 = Math.max(m1, m2);
          double m3 = f1.Angles(l.next.v, l.pair.next.v, l.v);
          double m4 = f1.Angles(l.next.v, l.pair.next.v, l.pair.v);
          min2 = Math.max(m3, m4);
          if (min2 < min1){
               return true;
          }
          return false;

     }

     public ArrayList<Edges> DelauneyFace (){
          ArrayList<Face> faces = new ArrayList<Face>();
          ArrayList<Edges> e = new ArrayList<Edges>();
          FACES(root, faces);
           Iterator<Face> itr = faces.iterator();
           int i = 0;
           while (itr.hasNext()){
               i = i +1;
                Face f = itr.next();
                HalfEdge a = f.l, b = f.m, c = f.n;
                 if (!a.visited){
                    if (a.pair != null){
                        if (a.pair.face.edgesOut.isEmpty() || a.pair.face.edgesOut == null){
                            AddVisited(a,e);
                        }
                    }
                    else{
                        AddVisited(a,e);
                    }
                     a.MarkVisited();
                }
                if (!b.visited){
                    if (b.pair != null){
                        if (b.pair.face.edgesOut.isEmpty() || b.pair.face.edgesOut == null){
                            AddVisited(b,e);
                        }
                    }
                    else{
                        AddVisited(b,e);
                    }
                     b.MarkVisited();
                }

                if (!c.visited){
                    if (c.pair != null){
                        if (c.pair.face.edgesOut.isEmpty() || c.pair.face.edgesOut == null){
                            AddVisited(c,e);
                        }
                    }
                    else{
                        AddVisited(c,e);
                    }
                     c.MarkVisited();
                }
           }

          return e;

     }



     public void FACES(Face f, ArrayList<Face> faces){
          if (f.edgesOut == null || f.edgesOut.isEmpty()){
               faces.add(f);
          }
          else{
               Iterator<Face> itr = f.edgesOut.iterator();
               while (itr.hasNext()){
                    FACES(itr.next(), faces);
               }
          }
     }


      public void AddVisited(HalfEdge h,ArrayList<Edges> e ){

          int a = h.v.n;
          int b = h.next.next.v.n;

          if (a>b){
               e.add(new Edges(b,a));
          }
          else{
               e.add(new Edges(a,b));
          }
     }

     public Face DFace(Face f){
          if (f.edgesOut == null || f.edgesOut.isEmpty()){
               return f;
          }
          return DFace(f.edgesOut.get(0));

     }



}