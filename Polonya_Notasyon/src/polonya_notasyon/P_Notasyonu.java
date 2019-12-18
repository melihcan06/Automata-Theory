/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polonya_notasyon;

/**
 *
 * @author melih
 */
public class P_Notasyonu extends javax.swing.JFrame {
    static boolean operator_mu(String dizi){
        if(dizi.equals('+'+"") || dizi.equals('-'+"") || dizi.equals('*'+"") || dizi.equals('/'+""))
            return true;
        return false;
    }    
    static String islem(String operator,String xx,String yy){
        int x=Integer.valueOf(xx),y=Integer.valueOf(yy);                
        if(operator.equals('+'+"")){
            return String.valueOf(x+y);
        }
        else if(operator.equals('-'+"")){
            return String.valueOf(x-y);
        }
        else if(operator.equals('*'+"")){
            return String.valueOf(x*y);
        }
        else if(operator.equals('/'+"")){
            return String.valueOf(x/y);
        }
        return null;
    }
    public P_Notasyonu() {
        initComponents();
    }
class sira{
    String ad;
    sira sonraki=null;
    sira(String s){
        ad=s;
    }
}
class stack{
    sira baslangic=null;    
    void it(String s){
        if(baslangic==null){
            baslangic=new sira(s);            
            return;
        }
        sira iter=baslangic;
        while(iter.sonraki!=null)
            iter=iter.sonraki;
        iter.sonraki=new sira(s);        
    }
    String cikart(){
        if(baslangic==null){
            return null;
        }
        else if(baslangic.sonraki==null){            
            String iter=baslangic.ad;
            baslangic=null;
            return iter;
        }
        sira iter=baslangic;
        while(iter.sonraki.sonraki!=null)
            iter=iter.sonraki;
        String gcc=iter.sonraki.ad;
        iter.sonraki=null;
        return gcc;
    }
    void bastir(sira iter){
        if(iter==null)
            return;
        if(iter.sonraki!=null)
            bastir(iter.sonraki);
        System.out.println(iter.ad);
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("GİRDİ:");

        jLabel2.setText("SONUÇ:");

        jLabel3.setText("SONUÇ");

        jTextField1.setText("* + * + 1 2 + 3 4 5 6");

        jButton1.setText("HESAPLA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jButton1)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(220, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//* + * + 1 2 + 3 4 5 6
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String girdi=jTextField1.getText();
        String[] dizi=girdi.split(" ");         
        stack stc=new stack();
        //+ 15 * - 2 3 4        
        String elde1,elde2;
        for(int i=dizi.length-1;i>=0;i--){
            if(operator_mu(dizi[i])){
                elde1=stc.cikart();
                elde2=stc.cikart();
                if(!operator_mu(elde1) && !operator_mu(elde2)){
                    stc.it(islem(dizi[i],elde1,elde2));
                }
                else{
                    stc.it(elde2);
                    stc.it(elde1);
                    stc.it(dizi[i]); 
                }
            }
            else{
                stc.it(dizi[i]);
            }
        }
        jLabel3.setText(stc.baslangic.ad);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(P_Notasyonu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P_Notasyonu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P_Notasyonu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P_Notasyonu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new P_Notasyonu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
