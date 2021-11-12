package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;


/**
 * Simulación del movimiento de rotación del sol y otros planetas xd, alomejor usando quaternios
 * @author Alanstartk19
 */
public class Main extends SimpleApplication {
    Node sol_node = new Node("sol_node");
    Node marte_node = new Node("marte_node");
    Node neptuno_node = new Node("neptuno_node");
    Node saturno_node = new Node("saturno_node");
    Node luna_node = new Node("Luna_node");
    Node tierra_node = new Node("Tierra_node");//nodo que se creara en el centro de la tierra pero no se verá
    float tm=0;
    boolean bandera=true;
    Spatial eart=null, moon=null,sol=null, marte=null, saturno=null, neptuno=null;
    //Spatial pivot;
    
    //
   // Quaternion PITCH090 = new Quaternion().fromAngleAxis(FastMath.PI/2, new Vector3f(1,0,0));//quaternion;
    //posiciones del sistema solar
    //sol, mercurio, venus, tierra, marte, jupiter, saturno, urano, neptuno, pluton
    //1     2       3       4       5       6       7           8       9       10

    //bieng el vector es para definir que eje de la matriz se quiere rotar aqui solo se necesitaba mover el z por la vista de la camara
    //astMath.PI/6000 ; divide pi=180° / 6,000 -> .030154 = lo que tarda la eart en rotar dividido en segundos aprox
    Quaternion QSol = new Quaternion().fromAngleAxis(FastMath.PI/20000, new Vector3f(0,1,0));//quaternion;
    
    //la tierra en 365 dias = aprox .030154 seg
    //Marte 0.05675
    //180/3600= .05
    //3600
    Quaternion QMarte = new Quaternion().fromAngleAxis(FastMath.PI/3600, new Vector3f(0,1,0));//quaternion;
    
    //Saturno 30 veces mas lento que la tierra ...
    //.030154/30 = 0.0010051
    //180/ 30,000= aprox .001
    //30000
    Quaternion QSaturno = new Quaternion().fromAngleAxis(FastMath.PI/30000, new Vector3f(0,1,0));//quaternion;
    
  //Neptuno 165 mas lento que la tierra
    //0.0001827
    //180/990000
    
    Quaternion QNeptuno = new Quaternion().fromAngleAxis(FastMath.PI/990000, new Vector3f(0,1,0));//quaternion;
    

    
    
    //Quaternion PITCH090 = new Quaternion().fromAngleAxis(FastMath.PI/2, new Vector3f(1,0,0));//quaternion;
    
    //bieng el vector es para definir que eje de la matriz se quiere rotar aqui solo se necesitaba mover el z por la vista de la camara
    //astMath.PI/6000 ; divide pi=180° / 6,000 -> .030154 = lo que tarda la eart en rotar dividido en segundos aprox
    Quaternion Qeart = new Quaternion().fromAngleAxis(FastMath.PI/6000, new Vector3f(0,1,0));//quaternion;
    
    
    //0.100800   = PI/1800= 0.1// eje z-> lo deje en 18000 porque es muy rapido xd
    Quaternion Qmoon = new Quaternion().fromAngleAxis(FastMath.PI/18000, new Vector3f(5,0,0));//quaternion;
    
    //ahora en el eje Y
    Quaternion QnodeEart = new Quaternion().fromAngleAxis(FastMath.PI/18000, new Vector3f(0,1,0));//quaternion;
      //(0,0.100800f*tpf, 0);//24h a segundos

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
       //codigo normal    
       //sol, mercurio, venus, tierra, marte, jupiter, saturno, urano, neptuno, pluton
        //1     2       3       4       5       6       7           8       9       10
        Vector3f vectierra = new Vector3f(4,0,0);
        Vector3f vecluna = new Vector3f(6,0,0);
        Vector3f vecmarte = new Vector3f(8,0,0);
        Vector3f vecsaturno = new Vector3f(16,0,0);
        Vector3f vecneptuno = new Vector3f(25,0,0);
        
        //El sol
       Sphere sol = new Sphere(20,20,3);
        Geometry sol_geom = new Geometry("sol", sol);
        Material sol_mat = new Material (assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        sol_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/sol.jpg"));
        sol_geom.setMaterial(sol_mat);
        sol_geom.rotate(FastMath.DEG_TO_RAD*-65, 0, 0);
        
  
    //la Marte
        Sphere marte = new Sphere(20,20,1);
        Geometry marte_geom = new Geometry("marte", marte);
        Material marte_mat = new Material (assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        marte_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/Marte.jpg"));
        marte_geom.setMaterial(marte_mat);
        marte_geom.rotate(FastMath.DEG_TO_RAD*-65, 0, 0);
        marte_geom.move(vecmarte);
        
//Saturno
        Sphere saturno = new Sphere(20,20,2);
        Geometry saturno_geom = new Geometry("saturno", saturno);
        Material saturno_mat = new Material (assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        saturno_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/Saturno.jpg"));
        saturno_geom.setMaterial(saturno_mat);
        saturno_geom.rotate(FastMath.DEG_TO_RAD*-65, 0, 0);
        saturno_geom.move(vecsaturno);
       
       
    //Neptuno
        Sphere neptuno = new Sphere(20,20,.75f);
        Geometry neptuno_geom = new Geometry("neptuno", neptuno);
        Material neptuno_mat = new Material (assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        neptuno_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/Neptuno.jpg"));
        neptuno_geom.setMaterial(neptuno_mat);
        neptuno_geom.rotate(FastMath.DEG_TO_RAD*-65, 0, 0);
       neptuno_geom.move(vecneptuno);
       
       
        
      
        
    //la tierra xd
       Sphere tierra = new Sphere(20,20,1);
        Geometry tierra_geom = new Geometry("tierra", tierra);
        Material tierra_mat = new Material (assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        tierra_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/earthmap1k.jpg"));
        tierra_geom.setMaterial(tierra_mat);
        tierra_geom.rotate(FastMath.DEG_TO_RAD*-65, 0, 0);
        tierra_geom.move(vectierra);
  
    //la luna xd
        Sphere luna = new Sphere(20,20,0.5f);
        Geometry luna_geom = new Geometry("luna", luna);
        Material luna_mat = new Material (assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        luna_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/moonmap1k.jpg"));
        luna_geom.setMaterial(luna_mat);
        luna_geom.move(vecluna);
   

  //haciendo visibles los objetos

         
        rootNode.attachChild(sol_geom);
        rootNode.attachChild(marte_geom);
        rootNode.attachChild(saturno_geom);
        rootNode.attachChild(neptuno_geom);
        
        rootNode.attachChild(tierra_geom);//agregando la eart
        //rootNode.attachChild(tierra_node);//se agregara al centro de la tierra ya que es el centro de la escena
       // rootNode.attachChild(luna_geom);//agregando la moon al nodo de la tierra que no se ve   
    
        
        
        rootNode.attachChild(sol_node);
        rootNode.attachChild(marte_node);
        rootNode.attachChild(saturno_node);
        rootNode.attachChild(neptuno_node);
        rootNode.attachChild(tierra_node);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
        
        if(sol != null){
           
  //se añade la configuración del quaternion para su rotacion sobre su eje
        sol.rotate(Qeart);
        marte.rotate(Qeart);
        saturno.rotate(Qeart);
        neptuno.rotate(Qeart);
        eart.rotate(Qeart);
        
        
        marte_node.attachChild(marte);
        sol_node.attachChild(eart);
        saturno_node.attachChild(saturno);
        neptuno_node.attachChild(neptuno);
        
        marte_node.rotate(QMarte);
        sol_node.rotate(Qeart);
        saturno_node.rotate(QSaturno);
        neptuno_node.rotate(QNeptuno);
        
        
        
         /*eart.rotate(Qeart);
            //eart.rotate(0, 0, tpf*.030154f);//esta a quaternion
            
            moon.rotate(Qmoon);
            tierra_node.attachChild(moon);
            //moon.rotate(0,0,0.100800f*tpf);
            
            tierra_node.rotate(QnodeEart);
            //tierra_node.rotate(0,0.100800f*tpf, 0);//24h a segundos
            */
       
        } else{
            
           
            sol = rootNode.getChild("sol");//buscando al hijo del nodo llamado tierra y guardandolo en un spatial
            saturno = rootNode.getChild("saturno");//buscando al hijo del nodo llamado saturno y guardandolo en un spatial
            marte = rootNode.getChild("marte");//buscando al hijo del nodo llamado marte y guardandolo en un spatial
            neptuno = rootNode.getChild("neptuno");//buscando al hijo del nodo llamado neptuno y guardandolo en un spatial
           
            eart = rootNode.getChild("tierra");//buscando al hijo del nodo llamado tierra y guardandolo en un spatial
            moon = rootNode.getChild("luna");//buscando al hijo del nodo llamado luna y guardandolo en un spatial
        }
        
            
    }
    
    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}


