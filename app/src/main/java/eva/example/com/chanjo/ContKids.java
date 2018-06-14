package eva.example.com.chanjo;

/**
 * Created by eva on 10/4/16.
 */
public class ContKids {
    String jina, simu;
    int siku,mwezi,mwaka;
    public void setJina(String jina){
        this.jina = jina;
    }
    public String getJina(){
        return this.jina;
    }
    public void setDOB (int DOB){
        this.siku = DOB;
    }
    public int getDOB(){
        return this.siku;
    }
    public void setMOB (int MOB){
        this.mwezi = MOB;
    }
    public int getMOB(){
        return this.mwezi;
    }
    public void setYOB (int YOB){
        this.mwaka = YOB;
    }
    public int getYOB(){
        return this.mwaka;
    }

    public ContKids(String jina,int siku,int mwezi,int mwaka){
        this.jina=jina;
        this.siku=siku;
        this.mwezi = mwezi;
        this.mwaka = mwaka;
    }

    public ContKids() {
    }
}
