package ase.spp64.shopeasy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarcodeFragment extends Fragment{

	View mainView;
	private Button scanBtn;
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        mainView = inflater.inflate(R.layout.fragment_barcode, container, false);
	        
	        scanBtn = (Button)mainView.findViewById(R.id.scanBtn);
	        
	        OnClickListener listener = new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(v.getId()==R.id.scanBtn){
						//scan
						IntentIntegrator scanIntegrator=new IntentIntegrator(getActivity());
						scanIntegrator.initiateScan();
						}
				}
			};
	        scanBtn.setOnClickListener(listener);
	        return mainView;
	 }
	 
	 public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//retrieve scan result
		 IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		 if (scanningResult != null) {
			//we have a result
			 String Content = scanningResult.getContents();
			 String Format = scanningResult.getFormatName();
			}else{
			    Toast toast = Toast.makeText(getActivity().getApplicationContext(), 
			            "Scan Data not recieved!", Toast.LENGTH_LONG);
			        toast.show();
			    }
		}
	        	
}
