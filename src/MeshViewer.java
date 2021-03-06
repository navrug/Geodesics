import java.util.Random;

import geodesics.ExactAlgorithm;
import processing.core.*;
import Jcg.geometry.*;
import Jcg.polyhedron.*;

/**
 * A simple 3d viewer for visualizing surface meshes (based on Processing)
 * 
 * @author Luca Castelli Aleardi (INF555, 2012)
 *
 */
public class MeshViewer extends PApplet {

	SurfaceMesh mesh; // 3d surface mesh
	MeshSubdivision ms; // subdivision scheme
	ExactAlgorithm geodesics;
	int renderType=0; // choice of type of rendering
	int renderModes=3; // number of rendering modes
	//	Random r = new Random(System.currentTimeMillis());
	Random r = new Random(0);


	//String filename="OFF/plane.off";
	//String filename="OFF/high_genus.off";
	//String filename="OFF/sphere.off";
	//String filename="OFF/cube.off";
	//String filename="OFF/torus_33.off";
	String filename="OFF/tore.off";
	//String filename="OFF/tri_hedra.off";
	//String filename="OFF/letter_a.off";
	//String filename="OFF/star.off";
	//String filename="OFF/tri_triceratops.off";

	public void setup() {
		size(800,600,P3D);
		ArcBall arcball = new ArcBall(this);

		this.mesh=new SurfaceMesh(this, filename);
		geodesics = new ExactAlgorithm();

		ms=new LoopSubdivision(this.mesh.polyhedron3D);
	}

	public void setFirstPoint(Vertex<Point_3> first) {
		geodesics.setFirstPoint(first);
		return;
	}

	public int getFirstId()
	{
		return geodesics.getFirstId();
	}

	public void setSecondPoint(Vertex<Point_3> second) {
		geodesics.setSecondPoint(second);
		return;
	}

	public SurfaceMesh getSurfaceMesh(){
		return mesh;
	}

	public void draw() {
		background(0);
		//this.lights();
		//		directionalLight(101, 204, 255, -1, 0, 0);
		//		directionalLight(51, 102, 126, 0, -1, 0);
		//		directionalLight(51, 102, 126, 0, 0, -1);
		//		directionalLight(102, 50, 126, 1, 0, 0);
		//		directionalLight(51, 50, 102, 0, 1, 0);
		//		directionalLight(51, 50, 102, 0, 0, 1);

		translate(width/2.f,height/2.f,-1*height/2.f);
		this.strokeWeight(1);
		this.stroke(150,150,150);

		this.mesh.draw(renderType);
	}
	
	public void subdivide() {
		this.ms.subdivide();
		this.mesh.updateScaleFactor();
		this.mesh.polyhedron3D.isValid(false);
	}

	public void keyPressed(){
		int id;
		switch(key) {
	    case('s'):case('S'): {
	    	mesh.clean();
	    	this.subdivide();
	    	for (Halfedge<Point_3> h : this.mesh.polyhedron3D.halfedges)
	    		h.computeLength();
	    	break;
	    }
		case('r'):this.renderType=(this.renderType+1)%this.renderModes; break;
		case('a'): {
			id = r.nextInt(mesh.polyhedron3D.vertices.size()-1);
			setFirstPoint(mesh.polyhedron3D.vertices.get(id));
			break;
		}
		case('d'): {
			mesh.clean();
			if (geodesics.first == null) {
				id = r.nextInt(mesh.polyhedron3D.vertices.size()-1);
				setFirstPoint(mesh.polyhedron3D.vertices.get(id));
			}
			geodesics.compute(); 
			break;
		}		
		case('f'): {
			mesh.clean();
			id = r.nextInt(mesh.polyhedron3D.vertices.size()-1);
			setFirstPoint(mesh.polyhedron3D.vertices.get(id));
			geodesics.compute(); 
			break;
		}
		case('g'): {
			mesh.clean();
			if (geodesics.first == null) {
				id = r.nextInt(mesh.polyhedron3D.vertices.size()-1);
				setFirstPoint(mesh.polyhedron3D.vertices.get(id));
			}
			geodesics.computeInit();
			break;
		}		
		case('j'): {
			mesh.clean();
			id = r.nextInt(mesh.polyhedron3D.vertices.size()-1);
			setFirstPoint(mesh.polyhedron3D.vertices.get(id));
			geodesics.computeInit();

			while(geodesics.computeOne())
				draw();
			break;
		}
		case('h'): {
			geodesics.computeOne();
			break;
		}
		case('b'): {
			mesh.cleanPath();
			id = r.nextInt(mesh.polyhedron3D.vertices.size()-1);
			id = id >= getFirstId() ? id+1 : id;
			setSecondPoint(mesh.polyhedron3D.vertices.get(id));
			geodesics.backtrack(); 
			break;
		}
		case('n'): {
			mesh.cleanPath();
			if (geodesics.second == null) {
				id = r.nextInt(mesh.polyhedron3D.vertices.size()-1);
				id = id >= getFirstId() ? id+1 : id;
				setSecondPoint(mesh.polyhedron3D.vertices.get(id));
			}
			geodesics.backtrack(); 
			break;
		}
		case('c'): 
			mesh.checkEdges(1000); 
		break;
		}
	}



	/**
	 * For running the PApplet as Java application
	 */
	public static void main(String args[]) {
		//PApplet pa=new MeshViewer();
		//pa.setSize(400, 400);
		PApplet.main(new String[] { "MeshViewer" });
	}

}
