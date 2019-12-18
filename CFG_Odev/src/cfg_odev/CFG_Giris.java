package cfg_odev;

import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

class dugum{
    static String[] Alfabe;
    static String[] S;
    static String[] X;
    int numara;
    String ifade;
    dugum[] cocuklar;
    dugum(String s){
        ifade=s;
    }
    dugum(String s,String[] Alfabe,String[] S,String[] X){
        ifade=s;
        this.Alfabe=Alfabe.clone();
        this.S=S.clone();
        this.X=X.clone();
        cocuklar=new dugum[S.length];
        for(int i=0;i<cocuklar.length;i++){
            cocuklar[i]=new dugum(S[i]);
        }
    }
    void baslangic(String[] S){
        numara=0;
        cocuklar=new dugum[S.length];
        for(int i=0;i<cocuklar.length;i++){
            cocuklar[i]=new dugum(S[i]);
            cocuklar[i].numara=i+1;
        }
    }
    void cocuklari_bas(){        
        for(int i=0;i<cocuklar.length;i++){
            System.out.print(cocuklar[i].ifade+" ");
        }
        System.out.println("");
    }
    int kac_tane_var(String ust,char alt){
        int x=0;
        for(int i=0;i<ust.length();i++){
            if(ust.charAt(i)==alt)
                ++x;
        }
        return x;
    }
    String yerine_koy(String ana,int index,String koy){
        String yeni=ana.substring(0, index);
        yeni+=koy;
        yeni+=ana.substring(index+1,ana.length());
        return yeni;
    }
    dugum ifade_olustur(dugum d){        
        int m=0;
        for(int i=0;i<d.ifade.length();i++){
            if(d.ifade.charAt(i)=='X'){
                for(int j=0;j<X.length;j++){                    
                    d.cocuklar[m]=new dugum(yerine_koy(d.ifade,i,X[j]));
                    d.cocuklar[m].numara=d.numara*10+m;
                    ++m;
                }                
            }
            else if(d.ifade.charAt(i)=='S'){//S görürse kendini çağırsın
                for(int j=0;j<S.length;j++){                    
                    d.cocuklar[m]=new dugum(yerine_koy(d.ifade,i,S[j]));
                    d.cocuklar[m].numara=d.numara*10+m;
                    ++m;
                }
            }
        }
        return d;        
    }   
    int basamak_sayisi(int sayi){
        int i=1;
        while(true){
            if(sayi/10==0)
                break;
            sayi/=10;
            ++i;
        }
        return i;
    }
    dugum agac_olustur(dugum bas,int sinir){                
        for(int i=0;i<bas.cocuklar.length;i++){            
            if(bas.cocuklar[i].ifade.indexOf('X')!=-1 || bas.cocuklar[i].ifade.indexOf('S')!=-1 ){
                if(bas.cocuklar!=null && basamak_sayisi(bas.cocuklar[i].numara)==sinir-1)                   
                    bas.cocuklar[i].cocuklar=null;
                else{
                    bas.cocuklar[i].cocuklar=new dugum[(kac_tane_var(bas.cocuklar[i].ifade,'X')*X.length)+(kac_tane_var(bas.cocuklar[i].ifade,'S')*S.length)];
                    bas.cocuklar[i]=ifade_olustur(bas.cocuklar[i]);                                                            
                    bas.cocuklar[i]=agac_olustur(bas.cocuklar[i],sinir);
                }
            }
            else{
                bas.cocuklar[i].cocuklar=null;
            }
        }
        return bas;
    }
    dugum agac_olustur(dugum bas){        
        for(int i=0;i<bas.cocuklar.length;i++){
            if(bas.cocuklar[i].ifade.indexOf('X')!=-1 || bas.cocuklar[i].ifade.indexOf('S')!=-1 ){
                bas.cocuklar[i].cocuklar=new dugum[(kac_tane_var(bas.cocuklar[i].ifade,'X')*X.length)+(kac_tane_var(bas.cocuklar[i].ifade,'S')*S.length)];
                bas.cocuklar[i]=ifade_olustur(bas.cocuklar[i]);                                
                bas.cocuklar[i]=agac_olustur(bas.cocuklar[i]);
            }
            else{
                bas.cocuklar[i].cocuklar=null;
            }
        }
        return bas;
    }
    void bastir(dugum d){
        for(int i=0;i<d.cocuklar.length;i++){
            System.out.print(d.cocuklar[i].numara+":"+d.cocuklar[i].ifade+"    ");
            if(d.cocuklar[i].cocuklar!=null){
                bastir(d.cocuklar[i]);
            }            
        }
        System.out.println("");        
    }
    
    //LinkedList<dugum> dugumler=new LinkedList<dugum>();    
    LinkedList<String> ifadeler=new LinkedList<String>();    
    LinkedList<String> numaralar=new LinkedList<String>();
    String[] ifadeler_dizi;
    int[] numaralar_dizii;
    String[] numaralar_dizi;
    void hazirlik(dugum d){
        for(int i=0;i<d.cocuklar.length;i++){            
            //dugumler.add(d.cocuklar[i]);            
            if(d.cocuklar[i].cocuklar!=null){
                hazirlik(d.cocuklar[i]);
            }            
            ifadeler.add(d.cocuklar[i].ifade);
            numaralar.add(String.valueOf(d.cocuklar[i].numara));
        }           
    }  
    void s_bastir(String []s){
        for(int i=0;i<s.length;i++){
            System.out.print(s[i]+" ");
        }
        System.out.println("");
    }
    void agaci_dondurmek_icin_hazirla(dugum d){
        hazirlik(d);
        ifadeler_dizi=new String[ifadeler.size()];
        numaralar_dizii=new int[numaralar.size()];
        numaralar_dizi=new String[numaralar.size()];
        for(int i=0;i<ifadeler_dizi.length;i++){
            ifadeler_dizi[i]=ifadeler.get(i);
            numaralar_dizii[i]=Integer.valueOf(numaralar.get(i));
        }        
        int g;
        String gcc;
        for(int i=0;i<numaralar_dizii.length-1;i++){
            for(int j=i;j<numaralar_dizii.length;j++){
                if(numaralar_dizii[i]>numaralar_dizii[j]){
                    g=numaralar_dizii[j];
                    numaralar_dizii[j]=numaralar_dizii[i];
                    numaralar_dizii[i]=g;
                    gcc=ifadeler_dizi[j];
                    ifadeler_dizi[j]=ifadeler_dizi[i];
                    ifadeler_dizi[i]=gcc;
                }
            }
        }
        for(int i=0;i<numaralar_dizi.length;i++){
            numaralar_dizi[i]=String.valueOf(numaralar_dizii[i]);
        }
    }
}
public class CFG_Giris extends javax.swing.JFrame {
    public CFG_Giris() {
        initComponents();
    }
    
    DefaultTreeModel aModeli;
    void agaci_baslat(String[] usak){
        DefaultMutableTreeNode bas=(DefaultMutableTreeNode)jTree1.getModel().getRoot();        
        DefaultMutableTreeNode root_cocuklari[]=new DefaultMutableTreeNode[usak.length];
        aModeli=(DefaultTreeModel) jTree1.getModel();
        for(int i=0;i<usak.length;i++){
            root_cocuklari[i]=new DefaultMutableTreeNode(usak[i]+" "+String.valueOf(i+1));            
            aModeli.insertNodeInto(root_cocuklari[i], bas,bas.getChildCount());
        }                            
    }
    void agaca_ekle(DefaultMutableTreeNode ata,DefaultMutableTreeNode usak){
        aModeli.insertNodeInto(usak, ata,ata.getChildCount());
    }    
    boolean altinda_mi(String x,String y){//y x in altında dır
        if(y.length()>x.length()){
            for(int i=0;i<x.length();i++)
                if(x.charAt(i)!=y.charAt(i))
                    return false;
        }
        else return false;
        return true;
    }   
    boolean yerini_bul(DefaultMutableTreeNode iter,String ifade,String numara){        
        String gcc;
        for(int i=0;i<iter.getChildCount();i++){
            gcc=iter.getChildAt(i).toString().split(" ")[1];
            if(altinda_mi(gcc, numara)){
                return yerini_bul((DefaultMutableTreeNode)iter.getChildAt(i),ifade,numara);             
            }                                        
        }
        agaca_ekle(iter,new DefaultMutableTreeNode(ifade+" "+numara));
        return true;
    }
    void agaci_olustur(String[] ifadeler_dizi,String[] numaralar_dizi){        
        DefaultMutableTreeNode iter=(DefaultMutableTreeNode)jTree1.getModel().getRoot();  
        for(int i=0;i<ifadeler_dizi.length;i++){
            for(int j=0;j<iter.getChildCount();j++){
                if(altinda_mi(iter.getChildAt(j).toString().split(" ")[1], numaralar_dizi[i])){
                    yerini_bul((DefaultMutableTreeNode)iter.getChildAt(j),ifadeler_dizi[i],numaralar_dizi[i]);
                }
            }
        }
    } 
    String[] butun_sozcukler(String[] ifadeler_dizi){
        //hash tablosu şeklinde yap her seferinde bütün listede aratma!
        String[] yeni=null;
        LinkedList<String> sozcukler=new LinkedList<String>();
        boolean al=true;
        for(int i=0;i<ifadeler_dizi.length;i++){
            al=true;
            for(int j=0;j<sozcukler.size();j++){
                if(ifadeler_dizi[i].equals(sozcukler.get(j))){
                    al=false;
                    break;
                }
            }
            if(al){
                sozcukler.add(ifadeler_dizi[i]);
            }
        }
        yeni=new String[sozcukler.size()];
        for(int j=0;j<sozcukler.size();j++){
            yeni[j]=sozcukler.get(j);
        }        
        return yeni;
    }
    DefaultListModel dlm=null;
    String[] uzunluga_gore_sirala(String[] eski){        
        String g;
        for(int i=0;i<eski.length-1;i++){
            for(int j=i;j<eski.length;j++){
                if(eski[i].length()>eski[j].length()){
                    g=eski[i];
                    eski[i]=eski[j];
                    eski[j]=g;
                }
            }
        }
        return eski;
    }
    void jListe_ekle(String[] ifadeler_dizi){
        String[] yeni=butun_sozcukler(ifadeler_dizi);        
        dlm=new DefaultListModel(); 
        yeni=uzunluga_gore_sirala(yeni);
        for(int i=0;i<yeni.length;i++)
            dlm.addElement(yeni[i]);
        jList1.setModel(dlm); 
        jLabel5.setText("Sayı: "+yeni.length);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(400, 150, 600, 600));

        jLabel1.setText("ALFABE:");

        jLabel2.setText("S - - >");

        jLabel3.setText("X - - >");

        jTextField1.setText("a,b");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setText("SXS|b");

        jTextField3.setText("ba|b");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton1.setText("HESAPLA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("S");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(jTree1);

        jButton2.setText("SIFIRLA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Alfabe Kontrol");

        jScrollPane2.setViewportView(jList1);

        jLabel5.setText("Sayı: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1)))
                .addGap(127, 127, 127)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(93, 93, 93))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
    void bastir(String []s){
        for(int i=0;i<s.length;i++){
            System.out.print(s[i]+" ");
        }
        System.out.println("");
    }
    boolean alfabe_kontrol(String[] Alfabee,String[] S,String[] X){
        String[] Alfabe=new String[Alfabee.length+2];
        for(int i=0;i<Alfabee.length;i++)
            Alfabe[i]=Alfabee[i];
        Alfabe[Alfabee.length]="S";
        Alfabe[Alfabee.length+1]="X";
        boolean kontrol=false;
        for(int i=0;i<S.length;i++){            
            for(int j=0;j<S[i].length();j++){
                kontrol=false;
                for(int m=0;m<Alfabe.length;m++){
                    if(Alfabe[m].equals(S[i].charAt(j)+"")){
                        kontrol=true;
                        break;              
                    }
                }
                if(kontrol==false)
                    return false;
            }            
        }        
        for(int i=0;i<X.length;i++){            
            for(int j=0;j<X[i].length();j++){
                kontrol=false;
                for(int m=0;m<Alfabe.length;m++){
                    if(Alfabe[m].equals(X[i].charAt(j)+"")){
                        kontrol=true;
                        break;              
                    }
                }
                if(kontrol==false)
                    return false;
            }           
        }        
        return true;
    }
    boolean sonsuz_mu(String[] S){
        for(int i=0;i<S.length;i++){
            for(int j=0;j<S[i].length();j++)
                if(S[i].charAt(j)=='S')
                    return true;
        }
        return false;
    }
    dugum bas;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String[] Alfabe,S,X;        
        String sinir="-1";
        Alfabe=jTextField1.getText().split(",");
        S=jTextField2.getText().split("\\|");
        X=jTextField3.getText().split("\\|");            
        if(alfabe_kontrol(Alfabe,S,X)){        
            jLabel4.setText("Alfabeye uygun");
            if(sonsuz_mu(S)){                
                sinir=JOptionPane.showInputDialog("Sonsuz cocuk var!   Sınır giriniz:");
            }
            bas=new dugum("S",Alfabe,S,X);        
            bas.baslangic(S);        
            if(sinir.equals("-1"))
                bas=bas.agac_olustur(bas);        
            else
                bas=bas.agac_olustur(bas,Integer.valueOf(sinir)+1);        
            
            bas.agaci_dondurmek_icin_hazirla(bas);
            agaci_baslat(S);  
            agaci_olustur(bas.ifadeler_dizi,bas.numaralar_dizi); 
        }
        else{
            jLabel4.setText("Alfabeye uygun değil!");
        }
        jListe_ekle(bas.ifadeler_dizi);        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MutableTreeNode kok=(MutableTreeNode) jTree1.getModel().getRoot();
        MutableTreeNode sil;
        int a=kok.getChildCount();        
        for(int i=a-1;i>=0;i--){
            sil=(MutableTreeNode) kok.getChildAt(i);            
            aModeli.removeNodeFromParent(sil);
            a=kok.getChildCount();
        }
        dlm.clear();
        jList1.setModel(dlm);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CFG_Giris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CFG_Giris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CFG_Giris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CFG_Giris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CFG_Giris().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
