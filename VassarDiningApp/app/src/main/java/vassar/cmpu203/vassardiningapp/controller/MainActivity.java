package vassar.cmpu203.vassardiningapp.controller;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Objects;

import vassar.cmpu203.vassardiningapp.R;
import vassar.cmpu203.vassardiningapp.model.Data;
import vassar.cmpu203.vassardiningapp.model.MealtimeItem;
import vassar.cmpu203.vassardiningapp.model.MealtimeMenu;
import vassar.cmpu203.vassardiningapp.model.User;
import vassar.cmpu203.vassardiningapp.view.FragFactory;
import vassar.cmpu203.vassardiningapp.view.IFavoriteView;
import vassar.cmpu203.vassardiningapp.view.IMainView;
import vassar.cmpu203.vassardiningapp.view.IMenuSelectView;
import vassar.cmpu203.vassardiningapp.view.MainView;
import vassar.cmpu203.vassardiningapp.view.ManageFavoritesFragment;
import vassar.cmpu203.vassardiningapp.view.MenuSelectFragment;

public class MainActivity extends AppCompatActivity implements
        IMenuSelectView.Listener, NavigationView.OnNavigationItemSelectedListener {

    private List<MealtimeMenu> currentMenu;
    private IMainView mainView;
    private final User user = new User();
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().setFragmentFactory(new FragFactory(this));
        super.onCreate(savedInstanceState);
        mainView = new MainView(this);
        setContentView(mainView.getRootView());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle = mainView.setupActionBar();

        Data.populateMenus();
        currentMenu = Data.findMenus("deece", "today");

        mainView.displayFragment(new MenuSelectFragment(this), false);
    }

    @Override
    public void onMenuFieldSelected(String cafe, String date, IMenuSelectView view) {
        currentMenu = Data.findMenus(cafe, date);
        updateVisibleMenu(view);
        view.refreshMenu();
    }

    @Override
    public void onFavoriteClicked(MealtimeItem item, IFavoriteView favoriteView) {
        user.switchFavoriteStatus(item);
        favoriteView.updateFavoriteDisplay();
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void updateVisibleMenu(IMenuSelectView view) {
        view.updateMenuItems(user.filterMenus(currentMenu));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.navigation_menu) {
            mainView.displayFragment(new MenuSelectFragment(this), true);
        } else if (id == R.id.navigation_favorites) {
            mainView.displayFragment(new ManageFavoritesFragment(this), true);
        } else if (id == R.id.navigation_restrictions) {
            System.out.println("restrictions click");
        }
        mainView.closeDrawer();
        return true;
    }
}
