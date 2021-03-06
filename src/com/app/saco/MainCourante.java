/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app.saco;

import java.util.Locale;

import com.example.android.navigationdrawerexample.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class MainCourante extends Activity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mCategories = getResources().getStringArray(R.array.categories_main_courante);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mCategories));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
        case R.id.action_websearch:
            // create intent to perform web search for this planet
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
//        Fragment fragment = new PlanetFragment();
//        Bundle args = new Bundle();
//        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
//        fragment.setArguments(args);

    	switch(position){
    	case 0:
    		//Toast.makeText(this, "coucou", Toast.LENGTH_SHORT).show();
    		Fragment fragment = new DonneesPrimaires();
    		// Insert the fragment by replacing any existing fragment
    	    FragmentManager fragmentManager = getFragmentManager();
    	    fragmentManager.beginTransaction()
    	                   .replace(R.id.content_frame, fragment)
    	                   .commit();
    	    
    		break;
    	case 1:
    		Fragment fragment1 = new Identification();
    		// Insert the fragment by replacing any existing fragment
    	    FragmentManager fragmentManager1 = getFragmentManager();
    	    fragmentManager1.beginTransaction()
    	                   .replace(R.id.content_frame, fragment1)
    	                   .commit();
//    		Intent main= new Intent(this, MainCourante.class);
//    		startActivity(main);
    		break;
    		
    	case 2:
    		Fragment fragment2 = new Circonstances();
    		// Insert the fragment by replacing any existing fragment
    	    FragmentManager fragmentManager2 = getFragmentManager();
    	    fragmentManager2.beginTransaction()
    	                   .replace(R.id.content_frame, fragment2)
    	                   .commit();
//    		Intent main= new Intent(this, MainCourante.class);
//    		startActivity(main);
    		break;
    		
    	case 4:
    		Fragment fragment4 = new BilanComplementaire();
    		// Insert the fragment by replacing any existing fragment
    	    FragmentManager fragmentManager4 = getFragmentManager();
    	    fragmentManager4.beginTransaction()
    	                   .replace(R.id.content_frame, fragment4)
    	                   .commit();
//    		Intent main= new Intent(this, MainCourante.class);
//    		startActivity(main);
    		break;
    		
    	case 5:
    		Fragment fragment5 = new GestesEffectues();
    		// Insert the fragment by replacing any existing fragment
    	    FragmentManager fragmentManager5 = getFragmentManager();
    	    fragmentManager5.beginTransaction()
    	                   .replace(R.id.content_frame, fragment5)
    	                   .commit();
//    		Intent main= new Intent(this, MainCourante.class);
//    		startActivity(main);
    		break;
    	
    	case 6:
    		Fragment fragment6 = new Evolution();
    		// Insert the fragment by replacing any existing fragment
    	    FragmentManager fragmentManager6 = getFragmentManager();
    	    fragmentManager6.beginTransaction()
    	                   .replace(R.id.content_frame, fragment6)
    	                   .commit();
//    		Intent main= new Intent(this, MainCourante.class);
//    		startActivity(main);
    		break;
    		
    	case 7:
    		Fragment fragment7 = new MoyensPresents();
    		// Insert the fragment by replacing any existing fragment
    	    FragmentManager fragmentManager7 = getFragmentManager();
    	    fragmentManager7.beginTransaction()
    	                   .replace(R.id.content_frame, fragment7)
    	                   .commit();
//    		Intent main= new Intent(this, MainCourante.class);
//    		startActivity(main);
    		break;
    		
    	case 8:
    		Fragment fragment8 = new SuiteDonnee();
    		// Insert the fragment by replacing any existing fragment
    	    FragmentManager fragmentManager8 = getFragmentManager();
    	    fragmentManager8.beginTransaction()
    	                   .replace(R.id.content_frame, fragment8)
    	                   .commit();
//    		Intent main= new Intent(this, MainCourante.class);
//    		startActivity(main);
    		break;
    	}
        

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mCategories[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

   
}