package Jcg.geometry;

public class Vector_3 extends Vector_2 {
  public Double z;

  public Vector_3() {}
  
  public Vector_3(Number x,Number y,Number z) { 
  	this.x=x.doubleValue(); 
  	this.y=y.doubleValue(); 
  	this.z=z.doubleValue(); 
  }

  public Vector_3(Point_3 a, Point_3 b) { 
  	this.x=b.getX().doubleValue()-a.getX().doubleValue(); 
  	this.y=b.getY().doubleValue()-a.getY().doubleValue(); 
  	this.z=b.getZ().doubleValue()-a.getZ().doubleValue(); 
  }
  
  public Number getX() {return x; }
  public Number getY() {return y; }
  public Number getZ() {return z; }
  
  public void setX(Number x) {this.x=x.doubleValue(); }
  public void setY(Number y) {this.y=y.doubleValue(); }
  public void setZ(Number z) {this.z=z.doubleValue(); }
  
  public boolean equals(Vector_ v) { 
  	return(this.x==v.getCartesian(0).doubleValue() 
  		&& this.y==v.getCartesian(1).doubleValue() 
  		&& this.z==v.getCartesian(2).doubleValue()); 
  }

  public String toString() {return "["+x+","+y+","+z+"]"; }
  public int dimension() { return 3;}
  
  public Number getCartesian(int i) {
  	if(i==0) return x;
  	else if(i==1) return y;
  	else return z;
  } 
  
  public void setCartesian(int i, Number x) {
  	if(i==0) this.x=x.doubleValue();
  	else if(i==1) this.y=x.doubleValue();
  	else this.z=x.doubleValue();
  }
    
  public Vector_3 sum(Vector_ v) {
  	return new Vector_3(this.x+v.getCartesian(0).doubleValue(),
  						this.y+v.getCartesian(1).doubleValue(), 
  						this.z+v.getCartesian(2).doubleValue());  	
  }
  
  public Vector_3 difference(Vector_ v) {
  	return new Vector_3(v.getCartesian(0).doubleValue()-x,
  						v.getCartesian(1).doubleValue()-y, 
  						v.getCartesian(2).doubleValue()-z);  	
  }
  
  public Vector_3 opposite() {
  	return new Vector_3(-x,-y,-z);  	
  }
  
  public Number innerProduct(Vector_ v) {
  	return this.x*v.getCartesian(0).doubleValue()
  		  +this.y*v.getCartesian(1).doubleValue()
  		  +this.z*v.getCartesian(2).doubleValue();  	
  }

  public Vector_3 divisionByScalar(Number s) {
  	if(s.doubleValue()==0.) throw new Error("error: division by zero");
  	return new Vector_3(x/s.doubleValue(),y/s.doubleValue(),z/s.doubleValue());  	
  }
  
  public Vector_3 multiplyByScalar(Number s) {
  	return 
  		new Vector_3(x*s.doubleValue(),y*s.doubleValue(),z*s.doubleValue());  	
  }
  
  public Number squaredLength() {
  	return innerProduct(this);  	
  }
  
  public Vector_3 crossProduct(Vector_ b) {
  	return 
  		new Vector_3(y*b.getCartesian(2).doubleValue()-z*b.getCartesian(1).doubleValue(),
  					 z*b.getCartesian(0).doubleValue()-x*b.getCartesian(2).doubleValue(),
  					 x*b.getCartesian(1).doubleValue()-y*b.getCartesian(0).doubleValue());
  }

  
}




