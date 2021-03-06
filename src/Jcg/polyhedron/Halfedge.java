package Jcg.polyhedron;

import Jcg.polyhedron.HalfedgePair;
import geodesics.Window;

import java.util.ArrayList;
import java.util.HashSet;

import Jcg.geometry.*;


/**
 * Class for representing half-edges
 *
 * @author Code by Luca Castelli Aleardi (INF555, 2012)
 */
public class Halfedge<X extends Point_>{

	public Halfedge<X> next;
	public Halfedge<X> opposite;
	public Vertex<X> vertex;

	public Halfedge<X> prev;
	public Face<X> face;
	public HalfedgePair pair;
	public int tag;
	private static int counter = 0;
	public int index = 0;

	public double length;

	public void setNext(Halfedge<X> e) { this.next=e; }
	public void setOpposite(Halfedge<X> e) { this.opposite=e; }
	public void setPrev(Halfedge<X> e) { this.prev=e; }
	public void setVertex(Vertex<X> v) { this.vertex=v; }
	public void setFace(Face<X> f) { this.face=f; }
	public void computeLength()
	{
		length = (double) ((Point_3) vertex.getPoint()).distanceFrom((Point_3) prev.vertex.getPoint());
	}
	public void initialize()
	{
		computeLength();
	}

	public Halfedge<X> getNext() { return this.next; }
	public Halfedge<X> getOpposite() { return this.opposite; }
	public Halfedge<X> getPrev() { return this.prev; }
	public Vertex<X> getVertex() { return this.vertex; }
	public Face<X> getFace() { return this.face; }

	public Halfedge() {
		index = counter;
		counter++;
	}

	public String toString(){
		return "("+opposite.getVertex().getPoint()+" - "+vertex.getPoint()+")";
	}

	
	@SuppressWarnings("unchecked")
	public void setFirst(HalfedgePair p)
	{
		p.one = (Halfedge<Point_3>) this;
		pair = p;
	}
	
	@SuppressWarnings("unchecked")
	public void setSecond(HalfedgePair p)
	{
		p.two = (Halfedge<Point_3>) this;
		pair = p;
	}
	

}


