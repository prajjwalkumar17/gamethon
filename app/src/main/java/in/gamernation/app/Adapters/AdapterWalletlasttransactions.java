package in.gamernation.app.Adapters;

import android.content.Context;
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

public class AdapterWalletlasttransactions extends RecyclerView.Adapter<AdapterWalletlasttransactions.recycleviews> {
    private JSONObject object;
    private Context thiscontext;
    private int length;

    public AdapterWalletlasttransactions() {
    }

    public AdapterWalletlasttransactions(JSONObject object, Context thiscontext) {
        this.object = object;
        this.thiscontext = thiscontext;
    }

    @NonNull
    @NotNull
    @Override
    public recycleviews onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerwalletlasttransactionitem, parent, false);
        return new recycleviews(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull recycleviews holder, int position) {
        try {
            JSONArray transactionarray = object.getJSONArray("Recent_transactions");
            JSONObject lasttransactions = transactionarray.getJSONObject(position);
            String deposit = lasttransactions.getString("deposit");
            String winning = lasttransactions.getString("winning");
            String bonus = lasttransactions.getString("bonus");
            String transaction_amount = lasttransactions.getString("transaction_amount");
            String balance_amount = lasttransactions.getString("balance_amount");
            String type = lasttransactions.getString("type");
            String cause = lasttransactions.getString("for");
            String date = lasttransactions.getString("created_at");


            holder.recyclerwallettransactiondate.setText(date);
            holder.recyclerwallettransactionamount.setText(transaction_amount);
            holder.recyclerwalletsumarry.setText("Deposit: " + deposit + " ,Winning: " + winning + " ,Bonus: " + bonus);
            holder.recyclerwallettransactionfor.setText(cause);

            if (type.equals("CREDIT")) {

                holder.recyclerwallettransaction.setTextColor(thiscontext.getResources().getColor(R.color.green));
                holder.recyclerwallettransaction.setText(type);

            } else {
                holder.recyclerwallettransaction.setTextColor(thiscontext.getResources().getColor(R.color.red));
                holder.recyclerwallettransaction.setText(type);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        try {
            length = object.getJSONArray("Recent_transactions").length();
            return length;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return length;
    }

    public static class recycleviews extends RecyclerView.ViewHolder {
        private TextView recyclerwallettransactionamount, recyclerwalletsumarry,
                recyclerwallettransactionfor, recyclerwallettransactiondate,
                recyclerwallettransaction;

        public recycleviews(@NonNull @NotNull View itemView) {
            super(itemView);
            recyclerwallettransactionamount = itemView.findViewById(R.id.recyclerwallettransactionamount);
            recyclerwalletsumarry = itemView.findViewById(R.id.recyclerwalletsumarry);
            recyclerwallettransactionfor = itemView.findViewById(R.id.recyclerwallettransactionfor);
            recyclerwallettransactiondate = itemView.findViewById(R.id.recyclerwallettransactiondate);
            recyclerwallettransaction = itemView.findViewById(R.id.recyclerwallettransaction);
        }
    }
}
