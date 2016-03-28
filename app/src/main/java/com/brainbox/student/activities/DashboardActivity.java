package com.brainbox.student.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.brainbox.student.R;
import com.brainbox.student.dashboard_fragments.AbsentNoteFragment;
import com.brainbox.student.dashboard_fragments.CalenderFragment;
import com.brainbox.student.dashboard_fragments.DashboardFragment;
import com.brainbox.student.dashboard_fragments.EventsFragment;
import com.brainbox.student.dashboard_fragments.FeedbackFragment;
import com.brainbox.student.dashboard_fragments.GroupsFragment;
import com.brainbox.student.dashboard_fragments.LostNFoundFragment;
import com.brainbox.student.dashboard_fragments.MapsFragment;
import com.brainbox.student.dashboard_fragments.PayFeeFragment;
import com.brainbox.student.dashboard_fragments.SettingsFragment;
import com.brainbox.student.global.BrainBox;
import com.brainbox.student.ui.CustomTitle;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {

    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer)
    DrawerLayout drawerLayout;
    private MenuItem previousMenuItem;
	private View header;
    private boolean isPaused = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        overridePendingTransition(0, 0);

       /* gaussianBlur = new GaussianBlur();
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        AndroidImage androidImage = new AndroidImage(icon);
        androidImage = gaussianBlur.process(androidImage);

        View headerView = navigationView.getHeaderView(0);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), androidImage.getImage());

        headerView.setBackgroundDrawable(bitmapDrawable);
        ((ImageView)headerView.findViewById(R.id.profile_image)).setImageBitmap(icon);
*/

		header = navigationView.getHeaderView(0);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				header.setPadding(0,150,0,0);
		}

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (previousMenuItem != null)
                    previousMenuItem.setChecked(false);

                menuItem.setCheckable(true);
                menuItem.setChecked(true);

                previousMenuItem = menuItem;

                drawerLayout.closeDrawers();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.dashboard:
                        Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();
                        return true;

                    case R.id.myProfile:
                        Intent intent2 = new Intent(DashboardActivity.this, ProfileActivity.class);
                        startActivity(intent2);
                        return true;

                    case R.id.policies:
                        Intent intent1 = new Intent(DashboardActivity.this, PoliciesActivity.class);
                        startActivity(intent1);
                        return true;

                    case R.id.calender:
                        CalenderFragment calenderFragment = new CalenderFragment();
                        fragmentTransaction.replace(R.id.frame, calenderFragment);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(CustomTitle.getTitle(DashboardActivity.this, getString(R.string.calender)));
                        return true;

                    case R.id.absentNote:
                        AbsentNoteFragment absentNoteFragment = new AbsentNoteFragment();
                        fragmentTransaction.replace(R.id.frame, absentNoteFragment);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(CustomTitle.getTitle(DashboardActivity.this, getString(R.string.absent_note)));
                        return true;

                    case R.id.feedback:
                        FeedbackFragment feedbackFragment = new FeedbackFragment();
                        fragmentTransaction.replace(R.id.frame, feedbackFragment);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(CustomTitle.getTitle(DashboardActivity.this, getString(R.string.feedback)));
                        return true;

                    case R.id.maps:
                        if(BrainBox.currentFragment != null && !(BrainBox.currentFragment instanceof MapsFragment))
                        {
                            MapsFragment mapFragment = new MapsFragment();
                            fragmentTransaction.replace(R.id.frame, mapFragment);
                            fragmentTransaction.commit();
                            getSupportActionBar().setTitle(CustomTitle.getTitle(DashboardActivity.this, getString(R.string.maps)));
                        }
                       return true;

                    case R.id.feesPayment:
                        PayFeeFragment payFeeFragment = new PayFeeFragment();
                        fragmentTransaction.replace(R.id.frame, payFeeFragment);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(CustomTitle.getTitle(DashboardActivity.this, getString(R.string.fees_payment)));
                        return true;

                    case R.id.lost_n_found:
                        LostNFoundFragment lostNFoundFragment = new LostNFoundFragment();
                        fragmentTransaction.replace(R.id.frame, lostNFoundFragment);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(CustomTitle.getTitle(DashboardActivity.this, getString(R.string.lost_n_found)));
                        return true;

                    case R.id.events:
                        EventsFragment eventsFragment = new EventsFragment();
                        fragmentTransaction.replace(R.id.frame, eventsFragment);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(CustomTitle.getTitle(DashboardActivity.this, getString(R.string.events)));
                        return true;

                    case R.id.groups:
                        GroupsFragment groupsFragment = new GroupsFragment();
                        fragmentTransaction.replace(R.id.frame, groupsFragment);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(CustomTitle.getTitle(DashboardActivity.this, getString(R.string.groups)));
                        return true;

                    case R.id.settings:
                        SettingsFragment settingsFragment = new SettingsFragment();
                        fragmentTransaction.replace(R.id.frame, settingsFragment);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(CustomTitle.getTitle(DashboardActivity.this, getString(R.string.action_settings)));
                        return true;


                    /*case R.id.starred:
                        Toast.makeText(getApplicationContext(), "Stared Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.sent_mail:
                        Toast.makeText(getApplicationContext(), "Send Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.drafts:
                        Toast.makeText(getApplicationContext(), "Drafts Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.allmail:
                        Toast.makeText(getApplicationContext(), "All Mail Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.trash:
                        Toast.makeText(getApplicationContext(), "Trash Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.spam:
                        Toast.makeText(getApplicationContext(), "Spam Selected", Toast.LENGTH_SHORT).show();
                        return true;*/
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });


        navigationView.getMenu().getItem(0).setChecked(true);
        DashboardFragment fragment = new DashboardFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_notifications:
                Intent intent = new Intent(this, NotificationsActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed()
    {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.frame);

        if(f instanceof DashboardFragment){
            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawers();
            }else
                super.onBackPressed();
        }

        else{
            Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        /*if(isPaused){
            isPaused = false;
            // recreate();
            Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();

        }
*/    }

    @Override
    protected void onPause()
    {
        isPaused = true;
        super.onPause();
    }
}
