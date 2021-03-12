public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	// copy constructor (shallow copy)
	public Planet(Planet b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Planet b) {
		double dx = b.xxPos - xxPos;
		double dy = b.yyPos - yyPos;
		double r = Math.sqrt(dx * dx + dy * dy);
		return r;
	}

	public double calcForceExertedBy(Planet b) {
		double r = this.calcDistance(b);
		double g = 6.67e-11;
		double f = g * mass * b.mass / (r * r);
		return f;
	}

	public double calcForceExertedByX(Planet b) {
		double f = this.calcForceExertedBy(b);
		double r = this.calcDistance(b);
		double dx = b.xxPos - xxPos;
		double fx = f * dx / r;
		return fx;
	}

	public double calcForceExertedByY(Planet b) {
		double f = this.calcForceExertedBy(b);
		double r = this.calcDistance(b);
		double dy = b.yyPos - yyPos;
		double fy = f * dy / r;
		return fy;
	}

	public double calcNetForceExertedByX(Planet[] allBodys) {
		double netFx = 0;
		for (Planet body : allBodys) {
			if (body != this) {
				netFx += this.calcForceExertedByX(body);
			}
		}
		return netFx;
	}

	public double calcNetForceExertedByY(Planet[] allBodys) {
		double netFy = 0;
		for (Planet body : allBodys) {
			if (body != this) {
				netFy += this.calcForceExertedByY(body);
			}
		}
		return netFy;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel += aX * dt;
		yyVel += aY * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, imgFileName);
	}
}
