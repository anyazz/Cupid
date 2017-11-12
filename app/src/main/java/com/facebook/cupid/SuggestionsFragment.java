package com.facebook.cupid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.daprlabs.aaron.swipedeck.SwipeDeck;

import java.util.ArrayList;
import java.util.List;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link SuggestionsFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link SuggestionsFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class SuggestionsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private SwipeDeck cardStack;
    private Context context;
    private SwipeDeckAdapter adapter;
    private ArrayList<String> testData;

    public SuggestionsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suggestions, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardStack = (SwipeDeck) view.findViewById(R.id.swipe_deck);

        testData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testData.add(String.valueOf(i));
        }

        adapter = new SwipeDeckAdapter(testData, context);
        if(cardStack != null){
            cardStack.setAdapter(adapter);
        }
        cardStack.setCallback(new SwipeDeck.SwipeDeckCallback() {
            @Override
            public void cardSwipedLeft(long stableId) {
                Log.i("MainActivity", "card was swiped left, position in adapter: " + stableId);
            }

            @Override
            public void cardSwipedRight(long stableId) {
                Log.i("MainActivity", "card was swiped right, position in adapter: " + stableId);

            }

        });

        cardStack.setLeftImage(R.id.left_image);
        cardStack.setRightImage(R.id.right_image);

//        Button btn = (Button) view.findViewById(R.id.button_left);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cardStack.swipeTopCardLeft(500);
//
//            }
//        });
//        Button btn2 = (Button) view.findViewById(R.id.button_right);
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cardStack.swipeTopCardRight(180);
//            }
//        });
//
//        Button btn3 = (Button) view.findViewById(R.id.button_center);
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                testData.add("a sample string.");
////                adapter.notifyDataSetChanged();
//                cardStack.unSwipeCard();
//            }
//        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public class SwipeDeckAdapter extends BaseAdapter {

        private List<String> data;
        private Context context;

        public SwipeDeckAdapter(List<String> data, Context context) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = convertView;
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                // normally use a viewholder
                v = inflater.inflate(R.layout.item_cardview, parent, false);
            }

            ImageView imageView = (ImageView) v.findViewById(R.id.offer_image);
            Glide.with(context).load(R.drawable.com_facebook_profile_picture_blank_portrait).fitCenter().centerCrop().into(imageView);
            TextView textView = (TextView) v.findViewById(R.id.sample_text);
            String item = (String)getItem(position);
            textView.setText(item);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Layer type: ", Integer.toString(v.getLayerType()));
                    Log.i("Hardware Accel type:", Integer.toString(View.LAYER_TYPE_HARDWARE));
                }
            });
            return v;
        }
    }

}
