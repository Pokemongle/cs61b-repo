public class NBody {
    public static double readRadius(String filepath){
        In in = new In(filepath);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filepath){
        In in = new In(filepath);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] p = new Planet[num];

        for(int i = 0;i<num;i++) {
            double xx = in.readDouble();
            double yy = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            p[i] = new Planet(xx, yy, vx, vy, m, img);
        }
        return p;
    }

    public static void main(String[] args){
        /* 1. Collecting all the needed input */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] p = readPlanets(filename);
        /* 2. Drawing the background */
        /*
        String imgToDraw = "./images/starfield.jpg";
        StdDraw.setXscale(-radius,radius);
        StdDraw.setYscale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0,0,imgToDraw);
        StdDraw.show();
        StdDraw.pause(1000);
         */
        /* 3. Draw all the planets */
        /*
        for(int i = 0; i < p.length; i++){
            p[i].draw();
        }
        */
        /* 4. Animation */
        StdDraw.setXscale(-radius,radius);
        StdDraw.setYscale(-radius,radius);
        StdDraw.enableDoubleBuffering();
        double t = 0;
        while(t <= T){
            double[] xForces = new double[p.length];
            double[] yForces = new double[p.length];
            for(int i = 0;i < p.length;i++){
                xForces[i] = p[i].calcNetForceExertedByX(p);
                yForces[i] = p[i].calcNetForceExertedByY(p);
            }
            for(int i = 0;i < p.length;i++){
                p[i].update(t,xForces[i],yForces[i]);
            }
            // draw the background
            String imgToDraw = "./images/starfield.jpg";
            StdDraw.clear();
            StdDraw.picture(0,0,imgToDraw);
            // draw all planets
            for(Planet planet:p){
                planet.draw();
            }
            StdDraw.show();
            int waitTimeMilliseconds = 100;
            StdDraw.pause(waitTimeMilliseconds);
            t += dt;
        }
        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p[i].xxPos, p[i].yyPos, p[i].xxVel,
                    p[i].yyVel, p[i].mass, p[i].imgFileName);
        }

    }
}
