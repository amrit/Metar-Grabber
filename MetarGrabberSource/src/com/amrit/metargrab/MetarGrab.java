package com.amrit.metargrab;



import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MetarGrab extends Activity implements OnClickListener{
    
	Button buttonRetrieveMetar, buttonReset, buttonRetrieveTaf, buttonResetTaf;
    TextView textDisplayMetar, textDisplayTaf;
    EditText EditTextAirportCode, EditTextTaf;
    String code = "";
    String airportcode ="";
    String airportcodet = "";
    String urlmetar = "http://weather.noaa.gov/pub/data/observations/metar/stations/";
    String urltaf = "http://weather.noaa.gov/pub/data/forecasts/taf/stations/";
    String url = "";

String str ;
InputStream content=null;
    
    
   

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
        
        buttonRetrieveMetar = (Button) findViewById(R.id.buttonRetrieveMetar);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        buttonRetrieveTaf= (Button) findViewById(R.id.buttonRetrieveTaf);
        buttonResetTaf = (Button) findViewById(R.id.buttonRetrieveTaf);
        
        textDisplayTaf = (TextView) findViewById(R.id.textDisplayTaf);
        textDisplayMetar = (TextView) findViewById(R.id.textDisplayMetar);
        EditTextAirportCode = (EditText) findViewById(R.id.EditTextAirportCode); 
        EditTextTaf = (EditText) findViewById(R.id.EditTextTaf);
      
        
       //Start TextViews
        textDisplayMetar.setText ("");
        textDisplayTaf.setText ("");
        textDisplayMetar.setMovementMethod(new ScrollingMovementMethod());
        textDisplayTaf.setMovementMethod(new ScrollingMovementMethod());
       
        
        
        //Button listener
        buttonReset.setOnClickListener(this);
        buttonResetTaf.setOnClickListener(this);
        buttonRetrieveTaf.setOnClickListener(this);
        buttonRetrieveMetar.setOnClickListener(this);
    
    }
        public void onClick(View src) {
        	switch(src.getId()) {
        	case R.id.buttonRetrieveMetar:

        		
        		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        		    imm.hideSoftInputFromWindow(EditTextAirportCode.getWindowToken(), 0);
        		
        		
        		textDisplayMetar.setText ("");
        		
        		
        		airportcode = EditTextAirportCode.getText().toString();
        		url = urlmetar + airportcode + ".TXT";
        		
        		//Added 06-27-11 METAR code
        		textDisplayMetar.setText ("");
        	
        		try {
        			HttpGet httpGet = new HttpGet(url);
        			HttpClient httpclient = new DefaultHttpClient();
        			// Execute HTTP Get Request
        			HttpResponse response = httpclient.execute(httpGet);
        			content = response.getEntity().getContent();
        			BufferedReader r = new BufferedReader(new InputStreamReader(content));
        			StringBuilder total = new StringBuilder();
        			String line;
        			while ((line = r.readLine()) != null) {
        			    total.append(line);
        			} 
        			textDisplayMetar.append("\n" + total.toString().substring(16) + "\n");
                        } catch (Exception e) {
        			//handle the exception !
        		}
                        
                
       break;
        	
        	case R.id.buttonReset:
        		textDisplayMetar.setText("");
        		EditTextAirportCode.setText("");
        		break;
        	
        		
   
        	
        	
        	
        	   
               
               	case R.id.buttonRetrieveTaf:
               		
               		

            		InputMethodManager imn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            		    imn.hideSoftInputFromWindow(EditTextTaf.getWindowToken(), 0);
               		
               		textDisplayTaf.setText ("");
               		
               		
               		airportcodet = EditTextTaf.getText().toString();
               		url = urltaf + airportcodet + ".TXT";
               		
               		
               		textDisplayTaf.setText ("");
               	
               		try {
               			HttpGet httpGet = new HttpGet(url);
               			HttpClient httpclient = new DefaultHttpClient();
               			// Execute HTTP Get Request
               			HttpResponse response = httpclient.execute(httpGet);
               			content = response.getEntity().getContent();
               			BufferedReader r = new BufferedReader(new InputStreamReader(content));
               			StringBuilder total = new StringBuilder();
               			String line;
               			while ((line = r.readLine()) != null) {
               			    total.append(line);
               			} 
               			textDisplayTaf.append("\n" + total.toString().substring(16) + "\n");
                               } catch (Exception e) {
               			//handle the exception !
               		}
                               
                       
              break;
               	
            	case R.id.buttonResetTaf:
            		textDisplayTaf.setText(" ");
            		EditTextTaf.setText("");
            		break;
        	
        	}
        }
		
			
		}


