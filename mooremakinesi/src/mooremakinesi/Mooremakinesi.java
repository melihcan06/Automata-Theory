package mooremakinesi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class dugum{
    String durum;
    char cikti;
    dugum komsular[];
    public dugum(String d,char c,int harf_sayisi){
        durum=d;
        cikti=c;
        komsular=new dugum[harf_sayisi];
    }       
}

public class Mooremakinesi{
    public static void main(String[] args) throws FileNotFoundException, IOException{
        Scanner sc=new Scanner(System.in);
        
        dugum dugumler[];
        String s,durumlar[];       
        int harf_sayisi;
        char harfler[],cikis_alfabe[],ciktilar[];
        
        File dosya;
        FileReader fr;
        BufferedReader br;
        
        dosya=new File("INPUT.txt");
        fr = new FileReader(dosya);
        br = new BufferedReader(fr);        
        
        s=br.readLine();                
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
        
        s=br.readLine();
        harf_sayisi=(s.length()+1)/2;
        harfler=new char[harf_sayisi];
        for(int i=0;i<harf_sayisi;i++)
            harfler[i]=s.charAt(i*2);
        
        s=br.readLine();
        cikis_alfabe=new char[(s.length()+1)/2];
        for(int i=0;i<cikis_alfabe.length;i++)
            cikis_alfabe[i]=s.charAt(i*2);
                                
        dosya=new File("OUTPUT.txt");
        fr = new FileReader(dosya);
        br = new BufferedReader(fr);
        s=br.readLine();
        ciktilar=new char[durumlar.length];
        String ss[]=s.split("\t");          
        for(int i=0;i<durumlar.length;i++)
            ciktilar[i]=ss[i].charAt(0); 
        
        dugumler=new dugum[durumlar.length];
        for(int i=0;i<durumlar.length;i++)            
            dugumler[i]=new dugum(durumlar[i],ciktilar[i],harf_sayisi);
        
        dosya=new File("GECISTABLOSU.txt");
        fr = new FileReader(dosya);
        br = new BufferedReader(fr);
        br.readLine();
        for(int i=0;i<durumlar.length;i++){
            s=br.readLine();
            ss=s.split("\t");            
            for(j=0;j<harf_sayisi;j++){
                for(int m=0;m<dugumler.length;m++)
                    if(ss[j+1].equals(dugumler[m].durum)){
                        dugumler[i].komsular[j]=dugumler[m];
                        break;
                    }                
            }                
        }                            
        fr.close();
        br.close();  
                
        dugum gc=dugumler[0];
        System.out.println("Giriş sözcüğünü giriniz: ");
        s=sc.next();
        System.out.print(dugumler[0].cikti+" ");
        for(int i=0;i<s.length();i++){            
            for(j=0;j<harfler.length;j++){
                if(harfler[j]==s.charAt(i))                    
                    break;                               
            }
            gc=gc.komsular[j];
            System.out.print(gc.cikti+" ");
        }
    }        
}