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
import android.widget.TextView;

import com.example.hajken.bluetooth.BluetoothConnection;
import com.example.hajken.InterfaceMainActivity;
import com.example.hajken.R;

public class GatewayFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "GatewayFragment";
    private InterfaceMainActivity interfaceMainActivity;

    private Button collectionRouteButton, designRouteButton, mapsRouteButton;
    private TextView textView;

    //occurs after onAttach
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    //occurs after onCreate
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gateway,container,false);

        //Creates the buttons, listOfXCoordinates and image of the collFragment
        collectionRouteButton = view.findViewById(R.id.collection_button);
        designRouteButton = view.findViewById(R.id.draw_button);
        mapsRouteButton = view.findViewById(R.id.GoogleMapsButton);

        textView = view.findViewById(R.id.device_gateway_fragment);

        if (BluetoothConnection.getInstance(getContext()).getIsConnected()){
            textView.setText("Connected Device:"+BluetoothConnection.getInstance(getContext()).getDeviceName());
        } else {
            textView.setText("Connected Device: None");
        }

        //Enables functions to buttons
        collectionRouteButton.setOnClickListener(this);
        designRouteButton.setOnClickListener(this);
        mapsRouteButton.setOnClickListener(this);
        collectionRouteButton.setActivated(true);
        designRouteButton.setActivated(true);
        mapsRouteButton.setActivated(true);

        return view;
    }

    //calls before onCreate, used to instantiate the interface
    //part of the collFragment to activity communication
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interfaceMainActivity = (InterfaceMainActivity) getActivity();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            //This is the events that are associated with the buttons

            case R.id.GoogleMapsButton: {
                interfaceMainActivity.inflateFragment(getString(R.string.googlemaps_fragment));
                break;
            }

            case R.id.collection_button: {
                interfaceMainActivity.inflateFragment(getString(R.string.collection_fragment));
                break;
            }

            case R.id.draw_button: {
                interfaceMainActivity.inflateFragment(getString(R.string.draw_fragment));
                break;
            }



        }

    }
}
