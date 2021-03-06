package com.example.hajken.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.hajken.InterfaceMainActivity;
import com.example.hajken.R;
import com.example.hajken.bluetooth.Bluetooth;

public class GatewayFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "GatewayFragment";
    private InterfaceMainActivity mInterfaceMainActivity;
    private Bluetooth mBluetooth;

    private Button collectionRouteButton, designRouteButton, mapsRouteButton;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInterfaceMainActivity = (InterfaceMainActivity) getActivity();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBluetooth = Bluetooth.getInstance(getContext(), mInterfaceMainActivity);
        mBluetooth.startCar("m!");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gateway,container,false);

        //Creates the buttons
        collectionRouteButton = view.findViewById(R.id.collection_button);
        designRouteButton = view.findViewById(R.id.draw_button);
        mapsRouteButton = view.findViewById(R.id.GoogleMapsButton);

        //Enables functions to buttons
        collectionRouteButton.setOnClickListener(this);
        designRouteButton.setOnClickListener(this);
        mapsRouteButton.setOnClickListener(this);

        //Sets the state of buttons upon inflation
        checkStateOfButtons();

        return view;
    }

    public void checkStateOfButtons(){

        collectionRouteButton.setActivated(true);
        designRouteButton.setActivated(true);
        mapsRouteButton.setActivated(true);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            //This is the events that are associated with the buttons
            case R.id.GoogleMapsButton: {
                mBluetooth.startCar("g!");
                mInterfaceMainActivity.inflateFragment(getString(R.string.google_maps_fragment));
                break;
            }

            case R.id.collection_button: {
                mBluetooth.startCar("d!");

                mInterfaceMainActivity.inflateFragment(getString(R.string.collection_fragment));
                break;
            }

            case R.id.draw_button: {
                mBluetooth.startCar("d!");

                mInterfaceMainActivity.inflateFragment(getString(R.string.draw_fragment));
                break;
            }
        }
    }
}
