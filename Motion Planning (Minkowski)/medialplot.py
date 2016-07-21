# -*- coding: utf-8 -*-
"""
Created on Fri Apr 22 11:51:54 2011

@author: gkumar
"""
import pylab as py
def plotline(v1,v2):
    n = 5.
    vx = py.zeros(n+1)
    vy = py.zeros(n+1)
    for i in range (n+1):
        t = i/n
        vx[i] = (1-t)*v1[0] + (t)*v2[0]
        vy[i] = (1-t)*v1[1] + (t)*v2[1]
        #print vx,vy
    py.plot(v1[0],v1[1],'o')
    py.plot(v2[0],v2[1],'o')
    py.plot(vx,vy)
    
#def plotCircle(cx,cy,r):
#    a = py.arange(0,2*3.14,.1)
#    x= cx + r*py.cos(a)
#    y =cy + r*py.sin(a)
#    #print cx, cy, r,py.cos(0)
#    #py.plot(x,y)
#    py.plot(cx,cy,'ko')
    

v1 = py.array([0,0])
v2 = py.array([4,4])
f = open('out.txt', 'r')
py.clf();
py.cla();
#fig = py.figure();
#fig.set_size_inches(10,10)
py.plot(-1.,-2)
#print f
for line in f:
    t = line.split()                                     
    x1 = float(t[0])
    y1 = float(t[1])
    x2 = float(t[2])
    y2 = float(t[3])        
    v1 = [x1,y1]
    v2 = [x2,y2]
    plotline(v1, v2)
#f1 = open('out2.txt', 'r')
#for line in f1:
#    t = line.split()    
#    x = float(t[0])
#    y = float(t[1])
#    r = float(t[2])
#    plotCircle(x,y,r)
    
    
    
    


        