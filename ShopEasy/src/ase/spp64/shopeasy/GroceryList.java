package ase.spp64.shopeasy;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import ase.spp64.tabswipe.adapter.TabsPagerAdapter;


public class GroceryList extends FragmentActivity implements ActionBar.TabListener {

	private ViewPager pager;
    private TabsPagerAdapter adapter;
    private ActionBar action;
    private String[] viewTabs = { "Grocery List", "Pantry", "Scan" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grocery_list);
		
        pager = (ViewPager) findViewById(R.id.pager);
        action = getActionBar();
        adapter = new TabsPagerAdapter(getSupportFragmentManager());
 
        pager.setAdapter(adapter);
        action.setHomeButtonEnabled(false);
        action.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
 
        for (String tab_name : viewTabs) {
            action.addTab(action.newTab().setText(tab_name)
                    .setTabListener(this));
        }
    	
    	/**
    	 * on swiping the viewpager make respective tab selected
    	 * */
    	pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    	 
    	    @Override
    	    public void onPageSelected(int position) {
    	        action.setSelectedNavigationItem(position);
    	    }
    	 
    	    @Override
    	    public void onPageScrolled(int arg0, float arg1, int arg2) {
    	    }
    	 
    	    @Override
    	    public void onPageScrollStateChanged(int arg0) {
    	    }
    	});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.grocery_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		pager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	
	
}
