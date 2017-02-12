/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Phuong
 */
public class Main {
    public static void main(String[] args){
    	Config c4;
    	if(args.length==0)
    	 c4 = new Config();
    	else{
    	c4 = new Config(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        c4.createConfig();
    	}
    }
}
