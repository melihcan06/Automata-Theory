package mealeymakinesi;
class dugum{
    String durum;
    char ciktilar[];    //komsu için çıktı
    dugum komsular[];    //her harf için 1 komsu indexleri harf olarak dusun
    public dugum(String d,int harf_sayisi,int cikti_sayisi){
        durum=d;        
        komsular=new dugum[harf_sayisi];
        ciktilar=new char[cikti_sayisi];
    }           
}
class tahta{
    dugum dugumler[];  
    String durumlar[];
    char harfler[],ciktilar[];       
    void input(String s,String s1,String s2){
        durumlar=new String[(s.length()+1)/3];
        int j=0;
        for(int i=0;i<durumlar.length;i++){
            durumlar[i]="";
            while(j<s.length()){
                if(s.charAt(j)!=',')
                    durumlar[i]+=s.charAt(j); 
                else{
                    j++;
                    break;
                }
                j++;
            }             
        }        
        
        harfler=new char[(s1.length()+1)/2];
        for(int i=0;i<harfler.length;i++)
            harfler[i]=s1.charAt(i*2);
        
        ciktilar=new char[(s2.length()+1)/2];
        for(int i=0;i<ciktilar.length;i++)
            ciktilar[i]=s2.charAt(i*2);
        
        dugumler=new dugum[durumlar.length];
        for(int i=0;i<dugumler.length;i++)
            dugumler[i]=new dugum(durumlar[i],harfler.length,ciktilar.length);        
    }       
    void gecisleri_ayarla(String gecisler[][]){
        for(int i=0;i<dugumler.length;i++){
            for(int j=0;j<gecisler[i].length;j++){
                for(int m=0;m<harfler.length;m++){
                    if(gecisler[i][j]!=null){
                        for(int n=0;n<gecisler[i][j].length();n+=4){
                            if(gecisler[i][j].charAt(n)==(harfler[m])){
                                dugumler[i].ciktilar[m]=gecisler[i][j].charAt(n+2);
                                dugumler[i].komsular[m]=dugumler[j];
                            }                    
                        }
                    }
                }
            }
        }
    }
    String oynat(String s){
        String ss="<html>";        
        dugum iter=dugumler[0];
        for(int i=0;i<s.length();i++){
            for(int j=0;j<harfler.length;j++){
                if(s.charAt(i)==harfler[j]){                    
                    ss+=iter.durum+"--"+harfler[j]+"-->"+iter.komsular[j].durum+" = "+iter.ciktilar[j]+"<br>";                   
                    iter=iter.komsular[j];                    
                }
            }            
        }
        ss+="<html/>";
        return ss;
    }
}
public class mealeymakinesi {
    /*public static void main(String[] args){
        tahta t1=new tahta();
        t1.input("q0,q1,q2","a,b","0,1");
        String gecisler[][]={{null,"a/0","b/0"},{null,"a/1","b/0"},{null,"a/0","b/1"}};
        t1.gecisleri_ayarla(gecisler);
        t1.oynat("ababbaab");
    }*/
}
