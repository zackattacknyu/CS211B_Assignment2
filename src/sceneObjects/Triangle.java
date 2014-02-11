package sceneObjects;

public class Triangle extends SceneObject{
	
	private double[] vertexAcoords, vertexBcoords, vertexCcoords;
	
	//the color components for the triangle
	//	must be a number between 0.0 and 1.0
	private double red, green, blue;
	
	//variables for intersection calculation
	private double a,b,c,d,e,f,g,h,i,beta,gamma,t,j,k,l,M;
	private double eihf,gfdi,dheg,akjb,jcal,blkc;
	
	private double nearPlane = 1;
	private double farPlane = 30;
	
	
	public Triangle(double[] vertex1coords, double[] vertex2coords,
			double[] vertex3coords) {
		super();
		this.vertexAcoords = vertex1coords;
		this.vertexBcoords = vertex2coords;
		this.vertexCcoords = vertex3coords;
		
		preComputeIntersectionVariables();
	}

	public double[] getVertex1coords() {
		return vertexAcoords;
	}

	public double[] getVertex2coords() {
		return vertexBcoords;
	}

	public double[] getVertex3coords() {
		return vertexCcoords;
	}
	
	/*
	 * When computing a ray-triangle intersection, the following system is solved
	 * 		[a d g] [beta]   = [j]
	 * 		[b e h] [gamma]    [k] 
	 * 		[c f i] [t]        [l]
	 * 
	 * a = x_a - x_b		d = x_a - x_c
	 * b = y_a - y_b		e = y_a - y_c		 
	 * c = z_a - z_b		f = z_a - z_c
	 * 
	 * (g,h,i) = (x_d, y_d, z_d)
	 * (j,k,l) = (x_a, y_a, z_a)
	 * 
	 * (x_a,y_a,z_a), (x_b,y_b,z_b), (x_c,y_c,z_c) are vertices of triangle
	 * (x_d, y_d, z_d) is vector of ray
	 * t is scaler of ray before it hits the triangle plane
	 * 
	 * To solve it,
	 *  M = a(ei - hf) + b(gf-di) + c(dh-eg)
	 *  
	 *  beta = [ j(ei-hf) + k(gf-di) + l(dh-eg) ]/M
	 *  gamma = [ i(ak-jb) + h(jc-al) + g(bl-kc) ]/M
	 *  t = - [ f(ak-jb) + e(jc-al) + d(bl-kc) ]/M
	 */
	
	private void preComputeIntersectionVariables(){
		
		a = vertexAcoords[0] - vertexBcoords[0];
		b = vertexAcoords[1] - vertexBcoords[1];
		c = vertexAcoords[2] - vertexBcoords[2];
		
		d = vertexAcoords[0] - vertexCcoords[0];
		e = vertexAcoords[1] - vertexCcoords[1];
		f = vertexAcoords[2] - vertexCcoords[2];
		
		j = vertexAcoords[0];
		k = vertexAcoords[1];
		l = vertexAcoords[2];
		
		akjb = a*k - j*b;
		jcal = j*c - a*l;
		blkc = b*l - k*c;
		
		
	}
	
	public boolean doesItIntersect(double rayVectorX, double rayVectorY, double rayVectorZ){
		g = rayVectorX;
		h = rayVectorY;
		i = rayVectorZ;
		
		eihf = e*i - h*f;
		gfdi = g*f - d*i;
		dheg = d*h - e*g;
		
		M = a*eihf + b*gfdi + c*dheg;
		
		t = -1*(f*akjb + e*jcal + d*blkc)/M;
		
		if(t < nearPlane || t > farPlane){
			return false;
		}
		
		gamma = (i*akjb + h*jcal + g*blkc)/M;
		
		if(gamma < 0 || gamma > 1){
			return false;
		}
		
		beta = (j*eihf + k*gfdi + l*dheg)/M;
		
		if(beta < 0 || beta > 1-gamma){
			return false;
		}
		
		return true;
	}
	
	
}
