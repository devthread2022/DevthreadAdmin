package com.devthread.devthreadadmin.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.devthread.devthreadadmin.R;
import com.devthread.devthreadadmin.databinding.FragmentDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dashboard extends Fragment {
    private FragmentDashboardBinding binding;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    public static Fragment activeFragment;
    String address, company, companyAddress, companyWeb, email, empId, mobile, name, profile, uid;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference = FirebaseDatabase.getInstance().getReference();;
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
        databaseReference.child("Admin").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    address = snapshot.child("address").getValue().toString();
                    company = snapshot.child("company").getValue().toString();
                    companyAddress =snapshot.child("companyAddress").getValue().toString();
                    companyWeb = snapshot.child("companyWeb").getValue().toString();
                    email = snapshot.child("email").getValue().toString();
                    empId = snapshot.child("empId").getValue().toString();
                    mobile = snapshot.child("mobile").getValue().toString();
                    name = snapshot.child("name").getValue().toString();
                    profile = snapshot.child("profile").getValue().toString();
                    binding.empName.setText(name);
                    binding.regId.setText(empId);
                    Glide.with(getContext()).load(profile).into(binding.profileImage);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.workUpdate.setOnClickListener(view1 -> {
            Fragment fragment = new WorkUpdate();
            loadFragment(fragment,"WorkUpdate");
        });
        binding.attendance.setOnClickListener(view1 -> {
            Fragment fragment = new Attendance();
            loadFragment(fragment,"Attendance");
        });
        binding.timesheet.setOnClickListener(view1 -> {
            Fragment fragment = new TimeSheet();
            loadFragment(fragment,"TimeSheet");
        });
        binding.leave.setOnClickListener(view1 -> {
            Fragment fragment = new Leave();
            loadFragment(fragment,"Leave");
        });
        binding.calender.setOnClickListener(view1 -> {
            Fragment fragment = new HolidayCalender();
            loadFragment(fragment,"HolidayCalender");
        });
        binding.tickets.setOnClickListener(view1 -> {
            Fragment fragment = new RaisedTickets();
            loadFragment(fragment,"RaisedTickets");
        });
        binding.project.setOnClickListener(view1 -> {
            Fragment fragment = new AvailableProjects();
            loadFragment(fragment,"AvailableProjects");
        });
        binding.chat.setOnClickListener(view1 -> {
            Fragment fragment = new Chat();
            loadFragment(fragment,"Chat");
        });
        binding.demoRequest.setOnClickListener(view1 -> {
            Fragment fragment = new DemoRequest();
            loadFragment(fragment,"DemoRequest");
        });
        binding.ux.setOnClickListener(view1 -> {
            Fragment fragment = new UI();
            loadFragment(fragment,"UI");
        });
        binding.frontend.setOnClickListener(view1 -> {
            Fragment fragment = new FrontEnd();
            loadFragment(fragment,"FrontEnd");
        });
        binding.backend.setOnClickListener(view1 -> {
            Fragment fragment = new BackEnd();
            loadFragment(fragment,"BackEnd");
        });
        binding.testing.setOnClickListener(view1 -> {
            Fragment fragment = new TestingProjects();
            loadFragment(fragment,"TestingProjects");
        });
        binding.deployment.setOnClickListener(view1 -> {
            Fragment fragment = new DeploymentProjects();
            loadFragment(fragment,"DeploymentProjects");
        });

        binding.readymadeOrder.setOnClickListener(view1 -> {
            Fragment fragment = new OrderedItems();
            loadFragment(fragment,"OrderedItems");
        });
        binding.cancelled.setOnClickListener(view1 -> {
            Fragment fragment = new CancelledOrder();
            loadFragment(fragment,"CancelledOrder");
        });
        binding.customerChat.setOnClickListener(view1 -> {
            Fragment fragment = new CustomerChat();
            loadFragment(fragment,"CustomerChat");
        });
        binding.transaction.setOnClickListener(view1 -> {
            Fragment fragment = new Transactions();
            loadFragment(fragment,"Transactions");
        });
        binding.testimonials.setOnClickListener(view1 -> {
            Fragment fragment = new Testimonial();
            loadFragment(fragment,"Testimonial");
        });
        binding.users.setOnClickListener(view1 -> {
            Fragment fragment = new Users();
            loadFragment(fragment,"Users");
        });
        binding.queries.setOnClickListener(view1 -> {
            Fragment fragment = new Queries();
            loadFragment(fragment,"Queries");
        });
        binding.coupon.setOnClickListener(view1 -> {
            Fragment fragment = new Coupons();
            loadFragment(fragment,"Coupons");
        });
        binding.demo.setOnClickListener(view1 -> {
            Fragment fragment = new Demos();
            loadFragment(fragment,"Demos");
        });
        binding.trackingData.setOnClickListener(view1 -> {
            Fragment fragment = new Tracking();
            loadFragment(fragment,"Tracking");
        });
        binding.mobile.setOnClickListener(view1 -> {
            Fragment fragment = new MobileItems();
            loadFragment(fragment,"MobileItems");
        });
        binding.web.setOnClickListener(view1 -> {
            Fragment fragment = new WebItems();
            loadFragment(fragment,"WebItems");
        });

        binding.pDetail.setOnClickListener(view1 -> {
            Fragment fragment = new UserProfile();
            loadFragment(fragment,"UserProfile");
        });
        binding.profile.setOnClickListener(view1 -> {
            Fragment fragment = new UserProfile();
            loadFragment(fragment,"UserProfile");
        });
        binding.notification.setOnClickListener(view1 -> {
            Fragment fragment = new Notification();
            loadFragment(fragment,"Notification");
        });


        return view;
    }
    private void loadFragment(Fragment fragment, String tag) {
        executorService.execute(()->{
            if (fragment != null) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, fragment, tag).addToBackStack(tag).commit();

            }
            handler.post(()->{
                activeFragment =getActivity().getSupportFragmentManager().findFragmentById(R.id.frame_container);
            });
        });

    }
}