import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
 
public class RelojProyecto extends JFrame{

    public static void main(String[] args) {
        RelojProyecto app = new RelojProyecto();
app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 Reloj Cara;
    
public RelojProyecto() {

super( "Reloj Proyecto" );
setVisible( true );
setResizable( false );
Container content=this.getContentPane();
content.setLayout(new BorderLayout());
Cara=new Reloj();
content.add(Cara, BorderLayout.CENTER);
this.pack();
Cara.start();
    }
}
 
class Reloj extends JPanel {

private int horas;
private int minutos;
private int segundos;

private static final int espacio=9;
private static final float dosPi=(float)(2.0 * Math.PI);
private static final float tresPi=(float)(3.0 * Math.PI);
private static final float rad=(float)(Math.PI / 30.0);
 
private int tamano;      
private int xCentro;   
private int yCentro;    
private BufferedImage muestra;
private javax.swing.Timer t;
  
public Reloj() {
this.setPreferredSize(new Dimension(300,300)); 
t=new javax.swing.Timer(1000,
new ActionListener() {
public void actionPerformed(ActionEvent e) {
update();
                  }
              });
    }
 
public void update() {
        this.repaint();
    }
 

public void start() {
        t.start(); 
    }
public void stop() {
        t.stop();
    }
   
public void paintComponent(Graphics g) {
   super.paintComponent(g);  
   Graphics2D g2=Graphics2D)g;
        
g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
 int ancho=getWidth();
 int alto=getHeight();
 tamano=((ancho < alto) ? ancho : alto) - 2*espacio;
  xCentro=tamano/2 + espacio;
  yCentro=tamano/2 + espacio;
 
 if (muestra == null || muestra.getWidth() != ancho || muestra.getHeight() != alto) 

{
 
muestra=(BufferedImage)(this.createImage(ancho, alto));
Graphics2D gc=muestra.createGraphics();
gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
RenderingHints.VALUE_ANTIALIAS_ON);
caraReloj(gc);
gc.setColor(Color.black);
        }
 
 Calendar now=Calendar.getInstance();
  horas=now.get(Calendar.HOUR);
  minutos=now.get(Calendar.MINUTE);
  segundos=now.get(Calendar.SECOND); 
 
g2.drawImage(muestra, null, 0, 0);
g2.setColor(Color.orange);
    Manecillas(g);
    }

private void caraReloj(Graphics g) {
 g.setColor(new Color(209, 234, 255));
 g.fillOval(espacio, espacio, tamano, tamano);
 g.setColor(Color.black);
 g.drawOval(espacio, espacio, tamano, tamano);
 
 for (int seg = 0; seg<60; seg++) {
   int inicio;
if (seg%5 == 0) {
  
inicio = tamano/2-10;
 } 
else 
{
inicio = tamano/2-5;
  }
diseno(g, xCentro, yCentro, rad*seg, inicio , tamano/2);
        }
    }

private void Manecillas(Graphics g) {
  int radioSegundero = tamano/3;
  int radioMinutero = radioSegundero * 3/4;
  int radioHora   = radioSegundero/3;
 
 float fsegundos = segundos;
 float anguloSegundero = tresPi - (rad * fsegundos);
 diseno(g, xCentro, yCentro, anguloSegundero, 0, radioSegundero);
 
 float fminutos = (float)(minutos + fsegundos/60.0);
 float anguloMinutero = tresPi - (rad * fminutos);
 diseno(g, xCentro, yCentro, anguloMinutero, 0, radioMinutero);
 
 float fhours = (float)(horas + fminutos/60.0);
 float anguloHora = tresPi - (5 * rad * fhours);
 diseno(g, xCentro, yCentro, anguloHora, 0, radioHora);

Font font = new Font("Arial", Font.BOLD, 14);    
g.setFont(font);
g.drawString( "12", 140, 40 );
g.drawString( "1", 205, 55 );
g.drawString( "2", 245, 100 );
g.drawString( "3", 265, 155 );
g.drawString( "4", 245, 210 );
g.drawString( "5", 205, 255 );
g.drawString( "6", 145, 270 );
g.drawString( "7", 90, 255 );
g.drawString( "8", 45, 210 );
g.drawString( "9", 25, 155 );
g.drawString( "10", 45, 100 );
g.drawString( "11", 80, 55 );
Font font1 = new Font("Arial", Font.BOLD, 10);
g.setFont(font1);

g.drawString( "***CLOCK MEXICAN***", 130, 80 );
g.drawString( "CREATE FOR WM.", 100, 220 );
    }

private void diseno(Graphics g, int x, int y, double angulo, int minRadius, int maxRadius) {

float sine=(float)Math.sin(angulo);
float cosine=(float)Math.cos(angulo);
 
int dxmin=(int)(minRadius * sine);
int dymin=(int)(minRadius * cosine);
int dxmax=(int)(maxRadius * sine);
int dymax = (int)(maxRadius * cosine);
g.drawLine( x+dxmin, y+dymin, x+dxmax, y+dymax);
    }
}