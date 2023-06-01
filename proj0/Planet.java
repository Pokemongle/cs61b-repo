public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet(){}
    public Planet(double xP, double yP, double xV, double yV,
            double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    /* Calculate the distance between Planet p and this planet*/
    public double calcDistance(Planet p){
        double delta_x = this.xxPos - p.xxPos;
        double delta_y = this.yyPos - p.yyPos;
        return Math.sqrt(delta_x*delta_x + delta_y*delta_y);
    }
    /* Calculate the force exerted on this planet by Planet p*/
    public double calcForceExertedBy(Planet p){
        return (Planet.G * this.mass * p.mass) / (this.calcDistance(p) * this.calcDistance(p));
    }
    /* Calculate the force in the axis-x direction*/
    public double calcForceExertedByX(Planet p){
        double f = this.calcForceExertedBy(p);
        double delta_x = p.xxPos - this.xxPos;
        return (delta_x) / this.calcDistance(p) * f;
    }
    /* Calculate the force in the axis-y direction*/
    public double calcForceExertedByY(Planet p){
        double f = this.calcForceExertedBy(p);
        double delta_y = p.yyPos - this.yyPos;
        return (delta_y) / this.calcDistance(p) * f;
    }
    /* Calculate all forces in axis-x direction provided by all other planets*/
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double f_sum = 0;
        for(Planet p:allPlanets){
            if(!p.equals(this)){
                f_sum += this.calcForceExertedByX(p);
            }
        }
        return f_sum;
    }
    /* Calculate all forces in axis-y direction provided by all other planets*/
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double f_sum = 0;
        for(Planet p:allPlanets){
            if(!p.equals(this)){
                f_sum += this.calcForceExertedByY(p);
            }
        }
        return f_sum;
    }
    public void update(double t, double f_x, double f_y){
        double a_x = f_x / this.mass;
        double a_y = f_y / this.mass;
        this.xxVel = this.xxVel + a_x * t;
        this.yyVel = this.yyVel + a_y * t;
        this.xxPos = this.xxPos + this.xxVel * t;
        this.yyPos = this.yyPos + this.yyVel * t;
    }
    public void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,"./images/"+this.imgFileName);
    }
}