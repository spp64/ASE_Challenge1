package ase.spp64.shopeasy;

import java.util.ArrayList;
import java.util.List;

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

public class GroceryFragment extends Fragment {

	 

	
	ArrayList<String> list;

	ArrayAdapter<String> adapter;
	View mainView;
	ListView list2;
	List<String> groceryList;
	Database db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mainView = inflater.inflate(R.layout.fragament_grocery, container,
				false);
		db =  new Database(getActivity().getApplicationContext());

		list = (ArrayList<String>) db.getGroceryList();

		Button btn = (Button) mainView.findViewById(R.id.btnAdd);
		Button delBtn = (Button) mainView.findViewById(R.id.btnDel);
		Button btnToPantry = (Button) mainView.findViewById(R.id.btnToPantry);

		adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
				android.R.layout.simple_list_item_multiple_choice, list);

		list2 = (ListView) mainView.findViewById(R.id.list);

		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText edit = (EditText) mainView.findViewById(R.id.txtItem);
				String item = edit.getText().toString();
				list.add(item);
				db.storeGroceryList(item);
				edit.setText("");
				adapter.notifyDataSetChanged();
			}
		};

		OnClickListener listener2 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SparseBooleanArray checkedGrocery = list2.getCheckedItemPositions();
                int count = list2.getCount();
 
                for(int i=count-1; i >= 0; i--){
                    if(checkedGrocery.get(i)){
                        adapter.remove(list.get(i));
                        list.remove(i);
                        db.updateGroceryList(list);
                    }
                }
                checkedGrocery.clear();
                adapter.notifyDataSetChanged();
			}
		};

		OnClickListener listener3 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SparseBooleanArray checkedGrocery = list2.getCheckedItemPositions();
                int count = list2.getCount();
 
                for(int i=count-1; i >= 0; i--){
                    if(checkedGrocery.get(i)){
                        db.storePantryList(list.get(i));
                    }
                }
                
			}
		};

		btn.setOnClickListener(listener);
		delBtn.setOnClickListener(listener2);
		btnToPantry.setOnClickListener(listener3);

		list2.setAdapter(adapter);

		return mainView;
	}
}
