package im.tox.toktok.app.MainActivity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.{FloatingActionButton, TabLayout}
import android.support.v4.app.Fragment
import android.support.v4.view.{GravityCompat, ViewPager}
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.{ActionBarDrawerToggle, AppCompatActivity}
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View.{OnClickListener}
import android.view._
import im.tox.toktok.R
import im.tox.toktok.app.NewMessageActivity.NewMessageActivity

class MainActivityFragment extends Fragment {

  var mViewPaper: ViewPager = null
  var mMenu: Menu = null
  var mToolbar: Toolbar = null
  var mTabs: TabLayout = null
  var mFab: FloatingActionButton = null
  var mPagerAdapter: MainTabsAdapter = null
  var mDrawer: DrawerLayout = null


  override def onCreate(savedState: Bundle): Unit = {
    super.onCreate(savedState)
    setHasOptionsMenu(true);
  }

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedState: Bundle): View = {

    super.onCreate(savedState)
    val view: View = inflater.inflate(R.layout.activity_main_fragment, container, false)

    initViewPaper(view)
    initToolbar(view)
    initFAB(view)
    mDrawer = getActivity.findViewById(R.id.home_layout).asInstanceOf[DrawerLayout]
    return view

  }


  override def onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) = {
    mMenu = menu
    inflater.inflate(R.menu.menu_main, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }


  def initViewPaper(view: View): Unit = {

    mViewPaper = view.findViewById(R.id.home_tab_holder).asInstanceOf[ViewPager]
    mPagerAdapter = new MainTabsAdapter(getChildFragmentManager);
    mViewPaper.setAdapter(mPagerAdapter)

    mTabs = view.findViewById(R.id.home_tabs).asInstanceOf[TabLayout]
    mTabs.setupWithViewPager(mViewPaper)

    mViewPaper.setCurrentItem(1)

  }

  def initToolbar(view: View): Unit = {

    mToolbar = view.findViewById(R.id.home_toolbar).asInstanceOf[Toolbar]
    mToolbar.setTitle("TokTok")

    mToolbar.setNavigationOnClickListener(new OnClickListener {
      override def onClick(v: View): Unit = {
        mDrawer.openDrawer(GravityCompat.START);
      }
    })

    getActivity.asInstanceOf[AppCompatActivity].setSupportActionBar(mToolbar)
    getActivity.asInstanceOf[AppCompatActivity].getSupportActionBar.setTitle("TokTok")
    getActivity.asInstanceOf[AppCompatActivity].getSupportActionBar.setHomeAsUpIndicator(R.drawable.ic_navigation_menu)
    getActivity.asInstanceOf[AppCompatActivity].getSupportActionBar.setDisplayHomeAsUpEnabled(true);

  }

  def initFAB(view: View): Unit = {

    mFab = view.findViewById(R.id.home_fab).asInstanceOf[FloatingActionButton]
    mFab.setOnClickListener(new OnClickListener {
      override def onClick(view: View): Unit = {
        startActivity(new Intent(getActivity, classOf[NewMessageActivity]))
      }
    })

  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {

    item.getItemId match {

      case android.R.id.home => {
        mDrawer.openDrawer(GravityCompat.START); // OPEN DRAWER
        return true;
      }
    }

    return super.onOptionsItemSelected(item)

  }

}
