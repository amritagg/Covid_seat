package com.amrit.practice.covidseattracker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DataDialog extends DialogFragment {

    private final Data data;

    public DataDialog(Data mData){
        data = mData;
    }

    @NonNull
    @SuppressLint({"InflateParams", "SetTextI18n", "QueryPermissionsNeeded"})
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.data_dialog, null);
        builder.setView(view);

        TextView age = view.findViewById(R.id.age_data_dia);
        TextView dose1 = view.findViewById(R.id.dose1_data_dia);
        TextView dose2 = view.findViewById(R.id.dose2_data_dia);
        TextView address = view.findViewById(R.id.address_data_dia);
        TextView paid = view.findViewById(R.id.paid_data_dia);
        TextView vaccineName = view.findViewById(R.id.vaccine_data_dia);
        Button book = view.findViewById(R.id.book);

        String age18 = (data.isAge18()) ? "18" : "45";
        String isPaid = (data.isPaid()) ? "Paid" : "Free";

        age.setText(age18);
        dose1.setText(data.getDose1() + "");
        dose2.setText(data.getDose2() + "");
        address.setText(data.getAddress());
        paid.setText(isPaid);
        vaccineName.setText(data.getVaccine_name());

        book.setOnClickListener(view1 -> {
            String urlString = "https://selfregistration.cowin.gov.in/";
            Uri uri = Uri.parse(urlString);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                startActivity(intent);
            }
        });

        return builder.create();
    }

}