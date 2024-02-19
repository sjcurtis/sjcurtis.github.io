package com.example.inventoryproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemRVAdapter extends RecyclerView.Adapter<ItemRVAdapter.ViewHolder>{
    // variable for our array list and context
    private ArrayList<ItemModal> itemModalArrayList;
    private DBHandlerInventory dbHandler;
    private Context context;

    // Constructor
    public ItemRVAdapter(ArrayList<ItemModal> itemModalArrayList, Context context) {
        this.itemModalArrayList = itemModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating layout file for recycler view items
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_item, parent, false);
        return new ViewHolder(view, this.context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Setting data to views of recycler view item
        ItemModal modal = itemModalArrayList.get(position);
        holder.itemId.setText(modal.getId());
        holder.itemName.setText(modal.getItemName());
        holder.quantity.setText(modal.getQuantity());

    }

    @Override
    public int getItemCount() {
        return itemModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName, quantity, itemId;
        private Button delete, update;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            // initializing our text views
            itemId = itemView.findViewById(R.id.textId);
            itemName = itemView.findViewById(R.id.textItemName);
            quantity = itemView.findViewById(R.id.textQuantity);

            delete = itemView.findViewById(R.id.buttonDelete);
            update = itemView.findViewById(R.id.buttonUpdate);

            dbHandler = new DBHandlerInventory(context);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Delete item from inventory
                    dbHandler.deleteItem(itemId.getText().toString());
                    Toast.makeText(context, "Item successfully deleted.", Toast.LENGTH_SHORT).show();

                    // Refresh activity
                    Intent intent = new Intent(context, InventoryActivity.class);

                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String id = itemId.getText().toString();
                    String name = itemName.getText().toString();
                    String itemQuantity = quantity.getText().toString();

                    // Delete item from inventory
                    dbHandler.updateItem(id, itemQuantity);
                    Toast.makeText(context, "Item successfully updated.", Toast.LENGTH_SHORT).show();

                    // Send sms alert if quantity is zero
                    if (itemQuantity.equals("0"))
                        sendSMS(name);

                    // Refresh activity
                    Intent intent = new Intent(context, InventoryActivity.class);
                }
            });
        }


        private void sendSMS(String item) {

            String permissionStr = "android.permission.SEND_SMS";

            // Check if SMS permission is enabled.
            if (ContextCompat.checkSelfPermission(context, permissionStr) != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions((Activity) context, new String[]{permissionStr}, 1);


            // Check if user enabled permission
            if (ContextCompat.checkSelfPermission(context, permissionStr) == PackageManager.PERMISSION_GRANTED) {
                SmsManager smsManager = SmsManager.getDefault();
                String message = item + " has no inventory left.";
                smsManager.sendTextMessage("1234567890",null, message, null, null);

                Toast.makeText(context, "Notification successfully sent.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Notifications disabled.", Toast.LENGTH_SHORT).show();
            }

        }

    }
}
