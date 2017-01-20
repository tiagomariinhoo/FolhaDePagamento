/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folhapagamento;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

/**
 *
 * @author tiago
 */
public class Data {
    public static void main(String[] args) throws ParseException {
        
        //mostrar
        Date data = new Date();
        System.out.println(DateFormat.getDateTimeInstance().format(data));
        
        //Receber
        String dh = JOptionPane.showInputDialog("digite a data");
        Date data1 = DateFormat.getDateTimeInstance().parse(dh);
        
        //long hr = data1.getTime() - data.getTime();
        
        //System.out.println(TimeUnit.MILLISECONDS.toHours(hr)); 
        
        System.out.println(new GregorianCalendar().get(Calendar.DAY_OF_WEEK));
        
    }
    

    
}
