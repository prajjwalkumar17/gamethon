package in.gamernation.app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import in.gamernation.app.R;

public class AdapterWalletpaymenthistory extends RecyclerView.Adapter<AdapterWalletpaymenthistory.viewrecycler> {
    int length;
    private JSONObject jsonObject;

    public AdapterWalletpaymenthistory() {
    }

    public AdapterWalletpaymenthistory(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @NonNull
    @NotNull
    @Override
    public viewrecycler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerwalletpaymenthistoryitem, parent, false);
        return new viewrecycler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewrecycler holder, int position) {

        JSONArray listarray = null;
        try {
            listarray = jsonObject.getJSONArray("Payment_history");

            JSONObject list = listarray.getJSONObject(position);
            String type = list.getString("type");
            String _id = list.getString("_id");
            String order = list.getString("order");
            String status = list.getString("status");

//            String amount = list.getString("amount");
//            if (!amount.isEmpty()) {
//                holder.recyclerwallethistoryamount.setText(amount);
//            }


            holder.recyclerwallethistoryorderid.setText(_id);
            holder.recyclerwallethistorystatus.setText(status);
            holder.recyclerwallethistorytype.setText(type);
            holder.recyclerwallethistoryorder.setText(order);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        try {
            length = jsonObject.getJSONArray("Payment_history").length();
            return length;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return length;
    }

    public class viewrecycler extends RecyclerView.ViewHolder {
        private TextView recyclerwallethistoryamount, recyclerwallethistorystatus,
                recyclerwallethistoryorder, recyclerwallethistorytype, recyclerwallethistoryorderid;

        public viewrecycler(@NonNull @NotNull View itemView) {
            super(itemView);
            recyclerwallethistoryamount = itemView.findViewById(R.id.recyclerwallethistoryamount);
            recyclerwallethistorystatus = itemView.findViewById(R.id.recyclerwallethistorystatus);
            recyclerwallethistoryorder = itemView.findViewById(R.id.recyclerwallethistoryorder);
            recyclerwallethistorytype = itemView.findViewById(R.id.recyclerwallethistorytype);
            recyclerwallethistoryorderid = itemView.findViewById(R.id.recyclerwallethistoryorderid);
        }
    }
}
