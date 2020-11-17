/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package respaldo_BD;

/**
 *
 * @author 4l3
 */
public class reiniciar {
    public void oddeven(int x){
        if(x%2!=0){
            try{
                this.restart();
            }catch(Exception e){
                
            }
        }else{
            System.out.println(x+"is even");
        }
    }
    
    public void restart() throws Exception{
        try{
            throw new Exception("Force restart");
            
        }finally{
            reiniciar.main(new String[0]);
        }
    }
    public static void main(String[] args) {
        reiniciar r=new reiniciar();
        r.oddeven((int)(Math.random()*100));
    }
}
