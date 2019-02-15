package com.mdgiitr.karthik.cognizance19.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class ForgotPasswordFragment extends Fragment {

    Button nextButton;
    private CircleImageView smallImageView;
    private PreferenceHelper preferenceHelper;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        smallImageView = view.findViewById(R.id.small_profile_image);

        preferenceHelper = new PreferenceHelper(getActivity());

        nextButton = view.findViewById(R.id.next_action);
        nextButton.setOnClickListener((View v) -> {
            navController.navigate(R.id.action_forgotPasswordFragment_to_changePasswordFragment);
        });

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.com_facebook_profile_picture_blank_square)
                .error(R.drawable.com_facebook_profile_picture_blank_square);
        Glide.with(this)
                .load(preferenceHelper.getProfilePicURL())
                .apply(options)
                .into(smallImageView);
        return view;
    }

}
