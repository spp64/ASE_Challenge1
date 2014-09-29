package ase.spp64.shopeasy;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class PantryFragment extends Fragment{

	Database db;
    ArrayList<String> list ;
 
    ArrayAdapter<String> adapter;
    View mainView;
    ListView list2;
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        mainView = inflater.inflate(R.layout.fragment_pantry, container, false);
	         
	        db  = new Database(getActivity().getApplicationContext());
	        list = (ArrayList<String>) db.getPantryList();
	        Button btn = (Button) mainView.findViewById(R.id.btnAdd2);
	        Button btnToGrocery = (Button) mainView.findViewById(R.id.btnToGrocery);
	        Button delBtn = (Button) mainView.findViewById(R.id.btnDel2);
	        
	        
	 
	        adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_multiple_choice, list);
	 
	        list2 = (ListView) mainView.findViewById(R.id.list2);
	        
	        OnClickListener listener = new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                EditText edit = (EditText) mainView.findViewById(R.id.txtItem2);
	                String item = edit.getText().toString();
	                list.add(item);
	                db.storePantryList(item);
	                edit.setText("");
	                adapter.notifyDataSetChanged();
	            }
	        };
	        
	        OnClickListener listener2 = new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					SparseBooleanArray checkedItems = list2.getCheckedItemPositions();
	                int count = list2.getCount();
	 
	                for(int i=count-1; i >= 0; i--){
	                    if(checkedItems.get(i)){
	                        adapter.remove(list.get(i));
	                        list.remove(i);
	                        db.updatePantryList(list);
	                    }
	                }
	                checkedItems.clear();
	                adapter.notifyDataSetChanged();
				}
			};

			OnClickListener listener3 = new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					SparseBooleanArray checkedItems = list2.getCheckedItemPositions();
	                int count = list2.getCount();
	 
	                for(int i=count-1; i >= 0; i--){
	                    if(checkedItems.get(i)){
	                        db.storeGroceryList(list.get(i));
	                    }
	                }
	                

				}
			};
	 
	        btn.setOnClickListener(listener);
	        delBtn.setOnClickListener(listener2);
	        btnToGrocery.setOnClickListener(listener3);
	        list2.setAdapter(adapter);
	        
	        return mainView;
	        
	        
	    }
}
