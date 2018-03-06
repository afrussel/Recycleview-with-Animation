package com.w3engineers.com.project1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.AdapterListAnimation;
import data.DataGenerator;
import model.People;
import utils.ItemAnimation;

/*
*  ****************************************************************************
*  * Created by : Ahmed Mohmmad Ullah (Azim) on 1/16/2018 at 11:12 AM.
*  * Email : azim@w3engineers.com
*  *
*  * Last edited by : Ahmed Mohmmad Ullah (Azim) on 1/16/2018.
*  *
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
*  ****************************************************************************
*/
public class CardDesignActivity extends AppCompatActivity {

    private View parent_view;
    private FloatingActionButton fab;
    private ImageView imageHidden, imageBottom;
    private RecyclerView recyclerView;
    private AdapterListAnimation mAdapter;
    private List<People> items = new ArrayList<>();
    private int animation_type = ItemAnimation.BOTTOM_UP;
    private int previousIndex ;
    private LinearLayoutManager linearLayoutManager;



    private RelativeLayout layoutMain;
    private RelativeLayout layoutButtons;
    private RelativeLayout layoutContent;
    private boolean isOpen = false;
    private View btnGreen;

    private RelativeLayout bgViewGroup;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_design);
        parent_view = findViewById(android.R.id.content);

        initToolbar();
        initComponent();
        animation_type = ItemAnimation.BOTTOM_UP;
        setAdapter();
        initView();

    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Assignment 02");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        items = DataGenerator.getPeopleData(this);




        //showSingleChoiceDialog();
    }

    private void initView(){

        imageBottom = (ImageView) findViewById(R.id.image_bottom);
        imageHidden = (ImageView) findViewById(R.id.image_hidden);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        Animation animation = AnimationUtils.loadAnimation(CardDesignActivity.this, R.anim.grow_effect);
        fab.startAnimation(animation);

        layoutMain = (RelativeLayout) findViewById(R.id.layoutMain);
        layoutButtons = (RelativeLayout) findViewById(R.id.layoutButtons);
        layoutContent = (RelativeLayout) findViewById(R.id.layoutContent);

        bgViewGroup = (RelativeLayout) findViewById(R.id.reveal_root);

        btnGreen = (View) findViewById(R.id.square_green);
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //revealGreen();
            }
        });



        /*alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);*/




        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                /*super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    // Scrolling up
                    //Toast.makeText(CardDesignActivity.this,"UP",Toast.LENGTH_SHORT).show();
                } else {
                    // Scrolling down
                    Toast.makeText(CardDesignActivity.this,"Doen",Toast.LENGTH_SHORT).show();
                }*/


                int total = linearLayoutManager.getItemCount();
                int firstVisibleItemCount = linearLayoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemCount = linearLayoutManager.findLastVisibleItemPosition();


                Log.d("F_firstItem", firstVisibleItemCount + "");
                Log.d("L_lastItem", lastVisibleItemCount + "");

                Log.d("total", total + "");

                Log.d("plus", firstVisibleItemCount + lastVisibleItemCount + "");
                Log.d("minus", firstVisibleItemCount - lastVisibleItemCount + "");



                if (firstVisibleItemCount == 0 && firstVisibleItemCount != previousIndex){

                    Animation animation = AnimationUtils.loadAnimation(CardDesignActivity.this, R.anim.move_out);
                    fab.startAnimation(animation);

                }else if(firstVisibleItemCount == 1 && firstVisibleItemCount != previousIndex && dy > 0){

                    Animation animation = AnimationUtils.loadAnimation(CardDesignActivity.this, R.anim.move);
                    fab.startAnimation(animation);

                } else if (firstVisibleItemCount == 5 && firstVisibleItemCount != previousIndex && dy > 0){

                    /*Animation animation = AnimationUtils.loadAnimation(CardDesignActivity.this, R.anim.zoom_in);
                    imageHidden.startAnimation(animation);*/
                    //imageHidden.setVisibility(View.VISIBLE);
                    //imageBottom.setVisibility(View.VISIBLE);

                    /*imageBottom.setVisibility(View.VISIBLE);
                    Animation animationSlide = AnimationUtils.loadAnimation(CardDesignActivity.this, R.anim.slide_up);
                    imageBottom.startAnimation(animationSlide);*/

                    /*int cx = imageBottom.getWidth();
                    int cy = imageBottom.getHeight();

                    // get the final radius for the clipping circle

                    int finalRadius = (int) Math.hypot(cx, cy);

                    // create the animator for this view (the start radius is zero)
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(imageBottom, cx, cy, 0, finalRadius);

                    // make the view visible and start the animation
                    imageBottom.setVisibility(View.VISIBLE);
                    imageHidden.setVisibility(View.GONE);
                    anim.setDuration(1000);
                    anim.start();*/

                    //viewMenu();
                    //revealGreen();
                    //revealRed();

                    Animation animation = AnimationUtils.loadAnimation(CardDesignActivity.this, R.anim.anim_in);
                    imageHidden.startAnimation(animation);

                }else if (firstVisibleItemCount == 4 && firstVisibleItemCount != previousIndex && dy < 0){

                    /*Animation animation = AnimationUtils.loadAnimation(CardDesignActivity.this, R.anim.zoom_out);
                    imageHidden.startAnimation(animation);*//*
                    imageHidden.setVisibility(View.VISIBLE);

                    Animation animationSlide = AnimationUtils.loadAnimation(CardDesignActivity.this, R.anim.slide_down);
                    imageBottom.startAnimation(animationSlide);
                    imageBottom.setVisibility(View.GONE);*/

                    /*Animation animation = AnimationUtils.loadAnimation(CardDesignActivity.this, R.anim.zoom_in);
                    imageHidden.startAnimation(animation);*/
                    //imageHidden.setVisibility(View.VISIBLE);
                    //imageBottom.setVisibility(View.GONE);

                    /*imageBottom.setVisibility(View.VISIBLE);
                    Animation animationSlide = AnimationUtils.loadAnimation(CardDesignActivity.this, R.anim.slide_up);
                    imageBottom.startAnimation(animationSlide);*/

                    /*int cx = imageBottom.getWidth();
                    int cy = imageBottom.getHeight();

                    // get the initial radius for the clipping circle
                    float initialRadius = (float) Math.hypot(cx, cy);

                    // create the animation (the final radius is zero)
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(imageBottom, cx, cy, initialRadius, 0);

                    // make the view invisible when the animation is done
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            imageBottom.setVisibility(View.GONE);
                            imageHidden.setVisibility(View.VISIBLE);
                        }
                    });

                    // start the animation

                    anim.setDuration(1000);
                    anim.start();*/

                    //closeMenu();

                    Animation animation = AnimationUtils.loadAnimation(CardDesignActivity.this, R.anim.anim_out);
                    imageHidden.startAnimation(animation);

                    // out


                }else {

                    // Toast.makeText(CardDesignActivity.this,"Doen",Toast.LENGTH_SHORT).show();
                }

                previousIndex = firstVisibleItemCount;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

        });

    }


    private void revealRed() {
        final ViewGroup.LayoutParams originalParams = btnGreen.getLayoutParams();
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                animateRevealColor(bgViewGroup, R.color.light_blue_400);
                btnGreen.setLayoutParams(originalParams);
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        TransitionManager.beginDelayedTransition(bgViewGroup, transition);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        btnGreen.setLayoutParams(layoutParams);
    }

    private void revealGreen() {
        animateRevealColor(bgViewGroup, R.color.light_blue_400);
    }


    private void animateRevealColor(ViewGroup viewRoot, @ColorRes int color) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        animateRevealColorFromCoordinates(viewRoot, color, cx, cy);
    }

    private Animator animateRevealColorFromCoordinates(ViewGroup viewRoot, @ColorRes int color, int x, int y) {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
        viewRoot.setBackgroundColor(ContextCompat.getColor(this, color));
        anim.setDuration(800);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
        return anim;
    }


    private void viewMenu() {

        int x = layoutContent.getLeft();
        int y = layoutContent.getBottom();

        int startRadius = 0;
        int endRadius = (int) Math.hypot(layoutMain.getWidth(), layoutMain.getHeight());

        //fab.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(getResources(), android.R.color.white, null)));
        //fab.setImageResource(R.drawable.ic_refresh);

        Animator anim = ViewAnimationUtils.createCircularReveal(layoutButtons, x, y, startRadius, endRadius);

        layoutButtons.setVisibility(View.VISIBLE);
        anim.setDuration(1000);
        anim.start();


        isOpen = true;

    }

    private void closeMenu(){

        int x = layoutButtons.getLeft();
        int y = layoutButtons.getBottom();

        int startRadius = Math.max(layoutContent.getWidth(), layoutContent.getHeight());
        int endRadius = 0;

        //fab.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null)));
        //fab.setImageResource(R.drawable.ic_menu);

        Animator anim = ViewAnimationUtils.createCircularReveal(layoutButtons, x, y, startRadius, endRadius);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                layoutButtons.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        anim.setDuration(1000);
        anim.start();
    }


    private void setAdapter() {
        //set data and list adapter
        mAdapter = new AdapterListAnimation(this, items, animation_type);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterListAnimation.OnItemClickListener() {
            @Override
            public void onItemClick(View view, People obj, int position) {
                //Snackbar.make(parent_view, "Item " + obj.name + " clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_animation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_refresh:
                setAdapter();
                break;
            case R.id.action_mode:
                showSingleChoiceDialog();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private static final String[] ANIMATION_TYPE = new String[]{
            "Bottom Up", "Fade In", "Left to Right", "Right to Left"
    };

    private void showSingleChoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Animation Type");
        builder.setCancelable(false);
        builder.setSingleChoiceItems(ANIMATION_TYPE, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String selected = ANIMATION_TYPE[i];
                if (selected.equalsIgnoreCase("Bottom Up")) {
                    animation_type = ItemAnimation.BOTTOM_UP;
                } else if (selected.equalsIgnoreCase("Fade In")) {
                    animation_type = ItemAnimation.FADE_IN;
                } else if (selected.equalsIgnoreCase("Left to Right")) {
                    animation_type = ItemAnimation.LEFT_RIGHT;
                } else if (selected.equalsIgnoreCase("Right to Left")) {
                    animation_type = ItemAnimation.RIGHT_LEFT;
                }
                getSupportActionBar().setTitle(selected);
                setAdapter();
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}
